package com.game;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.animals.core.Cat;
import com.animals.core.Dog;
import com.animals.core.Elephant;
import com.animals.core.Panthere;
import com.animals.core.Lion;
import com.animals.core.Mouse;
import com.animals.core.Tiger;
import com.animals.core.Wolf;
import com.animals.interfaces.Animal;
import com.game.Player;
import com.game.factories.AnimalFactory;

public class Board {

	// For Logging the game
	public static Date date = new Date();
	public static String strDateFormat = "ddMMhhmm";
	public static DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	public static String formattedDate = dateFormat.format(date);
	public static String FILENAME = "LOGTRACE" + formattedDate;

	public static Logger logger = Logger.getLogger("GAMELOG");
	public static FileHandler fh;
	public static BufferedWriter bw;

	public void initializeLogTrace(String formattedDate) throws IOException {
		fh = new FileHandler(formattedDate + ".log");
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}

	public Animal[][] board;

	/**
	 * Final board conception: There will be a matrix with Animal as a type of
	 * definition Animal[][] board = new Animal[9][7]
	 */
	public Player player1 = new Player(1, "BOCA");
	public Player player2 = new Player(2, "RIVER");

	/**
	 * How is the initialization was made? First, let's define the pieces that we're
	 * going to put in our board! E: Elephant | Ln: Lion | T: Tiger | Lp: Leopard |
	 * D: Dog | W: Wolf | C: Cat | M: Mouse. Now let's talk about the other Special
	 * cases. We have traps, lakes and targets to look for. TRAP LAKE TARGET As in
	 * chess, white pieces start first, so in our case player1 pieces are going to
	 * be indexed first
	 */
	public Board() {
		board = new Animal[9][7];
		try {
			initializeLogTrace(FILENAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initializeBoard() {
		// First Player
		this.board[2][6] = new Elephant(this, new Point(2, 6), player1);
		this.board[0][0] = new Lion(this, new Point(0, 0), player1);
		this.board[0][6] = new Tiger(this, new Point(0, 6), player1);
		this.board[2][2] = new Panthere(this, new Point(2, 2), player1);
		this.board[1][1] = new Dog(this, new Point(1, 1), player1);
		this.board[2][4] = new Wolf(this, new Point(2, 4), player1);
		this.board[1][5] = new Cat(this, new Point(1, 5), player1);
		this.board[2][0] = new Mouse(this, new Point(2, 0), player1);
		// Second Player
		this.board[6][0] = new Elephant(this, new Point(6, 0), player2);
		this.board[8][6] = new Lion(this, new Point(8, 6), player2);
		this.board[8][0] = new Tiger(this, new Point(8, 0), player2);
		this.board[6][4] = new Panthere(this, new Point(6, 4), player2);
		this.board[7][5] = new Dog(this, new Point(7, 5), player2);
		this.board[6][2] = new Wolf(this, new Point(6, 2), player2);
		this.board[7][1] = new Cat(this, new Point(7, 1), player2);
		this.board[6][6] = new Mouse(this, new Point(6, 6), player2);
	}

	public void getBoard() {
		Point p = new Point();
		for (int i = 0; i < 9; i++) {
			p.setI(i);
			System.out.println("------------------------------------------------------------------------");
			for (int j = 0; j < 7; j++) {
				p.setJ(j);
				if (board[i][j] != null)
					System.out.print("|" + String.format("%8s", board[i][j].getFormat()) + "|");
				else if (isTrap(p)) {
					System.out.print("|" + String.format("%8s", "TRAP") + "|");
				} else if (isRiver(p)) {
					System.out.print("|" + String.format("%8s", "LAKE") + "|");
				} else if (isTarget1(p)) {
					System.out.print("|" + String.format("%8s", "TARGET1") + "|");
				} else if (isTarget2(p)) {
					System.out.print("|" + String.format("%8s", "TARGET2") + "|");
				} else
					System.out.print("|" + String.format("%8s", "OO") + "|");
			}
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------------");
	}
	
	/**
	 * 
	 * @param p
	 * @return check if p is in Trap
	 */

	public boolean isTrap(Point p) {
		if (p.isEqual(new Point(0, 2)))
			return true;
		else if (p.isEqual(new Point(0, 4)))
			return true;
		else if (p.isEqual(new Point(1, 3)))
			return true;
		else if (p.isEqual(new Point(8, 2)))
			return true;
		else if (p.isEqual(new Point(8, 4)))
			return true;
		else if (p.isEqual(new Point(7, 3)))
			return true;

		return false;
	}
	
	/**
	 * 
	 * @param p
	 * @return check if p is in River
	 */

	public boolean isRiver(Point p) {
		if (p.isEqual(new Point(3, 1)))
			return true;
		else if (p.isEqual(new Point(3, 2)))
			return true;
		else if (p.isEqual(new Point(4, 1)))
			return true;
		else if (p.isEqual(new Point(4, 2)))
			return true;
		else if (p.isEqual(new Point(5, 1)))
			return true;
		else if (p.isEqual(new Point(5, 2)))
			return true;
		else if (p.isEqual(new Point(3, 4)))
			return true;
		else if (p.isEqual(new Point(3, 5)))
			return true;
		else if (p.isEqual(new Point(4, 4)))
			return true;
		else if (p.isEqual(new Point(4, 5)))
			return true;
		else if (p.isEqual(new Point(5, 4)))
			return true;
		else if (p.isEqual(new Point(5, 5)))
			return true;

		return false;
	}
	
	/**
	 * 
	 * @param p
	 * 
	 * @return TRUE if  p is the sanctuary of the first player
	 */
	public boolean isTarget1(Point p) {
		if (p.isEqual(new Point(0, 3)))
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param p
	 * 
	 * @return TRUE if  p is the sanctuary of the second player
	 */
	public boolean isTarget2(Point p) {
		if (p.isEqual(new Point(8, 3)))
			return true;
		return false;
	}

	public boolean isEmpty(Point p) {
		if (board[p.getI()][p.getJ()] == null)
			return true;

		return false;
	}

	/**
	 * @param p : the current position of the animal
	 * We will check if the two lakes are empty or not. If they are, we could jump
	 * across the river. If not, it's not allowed.
	 * 
	 * @return
	 */
	public boolean checkIfLakeIsEmpty(Point p) {

		// we check if the Animal will jump horizontally (first condition) or
		// vertically
		// (second condition)

		if (p.getI() > 2 && p.getI() < 6) {
			if ((p.getJ() == 0 && Animal.JUMP == Animal.RIGHT) || (p.getJ() == 3 && Animal.JUMP == Animal.LEFT)) {

				for (int j = 1; j < 3; j++) {
					// we test if there is a mouse in the two positions in front
					// of the current
					// position
					if (board[p.getI()][j] != null && ("M1".equals(this.board[p.getI()][j].getFormat())
							|| "M2".equals(this.board[p.getI()][j].getFormat()))) {
						return false;
					}
				}
			} else if ((p.getJ() == 3 && Animal.JUMP == Animal.RIGHT)
					|| (p.getJ() == 6 && Animal.JUMP == Animal.LEFT)) {

				for (int j = 4; j < 6; j++) {
					// we test if there is a mouse in the two positions before
					// the current
					// position
					if (board[p.getI()][j] != null && ("M1".equals(this.board[p.getI()][j].getFormat())
							|| "M2".equals(this.board[p.getI()][j].getFormat()))) {
						return false;
					}
				}
			}
		}

		if (p.getJ() != 0 && p.getJ() != 3 && p.getJ() != 6) {

			if (p.getI() == 2 || p.getI() == 6) {

				for (int i = 3; i < 6; i++) {
					// we test if there is a mouse in the three positions below
					// the current position
					if (board[i][p.getJ()] != null && ("M1".equals(this.board[i][p.getJ()].getFormat())
							|| "M2".equals(this.board[i][p.getJ()].getFormat()))) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public Animal getAnimalAt(Point p) {
		return board[p.getI()][p.getJ()];
	}

	public boolean isOutOfBoard(Point p) {
		if (p.getI() < 0 || p.getI() > 8 || p.getJ() < 0 || p.getJ() > 6) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * this method allows an animal of a given player to move to the
	 * new position by creating the given animal with the new position
	 * using the animal factory and refer the old position to null
	 *  
	 * @param a : animal to move
	 * @param lookForward : the new position 
	 * @param player
	 */
	public void pieceMove(Animal a, Point lookForward, Player player) {

		AnimalFactory animalFactory = new AnimalFactory();
		Point current = a.getPosition();
		board[current.getI()][current.getJ()] = null;
		board[lookForward.getI()][lookForward.getJ()] = animalFactory.getAnimal(a.getClass().getSimpleName(), this,
				lookForward, player);

		logger.info("MOVE: " + a.getFormat() + " FROM " + current.toString() + " TO " + lookForward.toString());
		String moveDescription = player.toString() + a.toString() + " from " + current.toString() + " to"
				+ lookForward.toString();
		player.storeMove(moveDescription);
	}
	
	/**
	 * this method try to give the player's number of pieces in the current game
	 * 
	 * @param player
	 * @return
	 */
	public int numberOfPiecesForPlayer(Player player) {

		int color = player.getColor();
		int piecesCounter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != null && color == board[i][j].player.getColor()) {
					piecesCounter++;
				}
			}
		}

		return piecesCounter;
	}
	
	/**
	 * this method return a player's animal by its given format
	 * @param PF
	 * @param player
	 * @return
	 */
	public Animal getAnimalByPieceFormat(String PF, Player player) {

		PF = PF + String.valueOf(player.getColor());

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 7; j++) {
				if (board[i][j] != null) {
					if (board[i][j].getFormat().equals(PF)) {
						return board[i][j];
					}
				}
			}
		}

		return null;
	}
	
	/**
	 * this method check if the given animal still exist in the board
	 * 
	 * @param a
	 * @return
	 */
	public boolean isAnimalExist(Animal a) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 7; j++) {
				if (board[i][j] != null) {
					if (board[i][j] == a) {
						return true;
					}
				}
			}
		}

		return false;
	}
	
	
	// Winning system
	


	private int tour = 1;

	public int getTour() {
		return tour;
	}

	public void setTour(int t) {
		tour = t;
	}

	/**
	 * this method returns the current player
	 *
	 * @return
	 */
	public Player checkPlayer() {

		if (this.getTour() == -1) {
			return player2;
		}

		return player1;
	}

	/**
	 * this method check if a player has successfully arrived to the opponent's sanctuary
	 * 
	 * @param p
	 */
	public void checkIfIsInTarget(Point p) {
		
		if (this.isTarget1(p) && this.checkPlayer().getColor() == 2)
			player2.setWinner(true);
		if (this.isTarget2(p) && this.checkPlayer().getColor() == 1)
			player1.setWinner(true);
		
	}

	/**
	 * this method check if the game is over 
	 * 
	 * @return
	 */
	public boolean isGameOver() {
		
		player1.setScore(8-this.numberOfPiecesForPlayer(player2));
		player2.setScore(8-this.numberOfPiecesForPlayer(player1));
		
		if (this.numberOfPiecesForPlayer(player2) == 0 || player1.isWinner()) {
			System.out.println("WINNER: " + player1.toString());
			return true;
		}

		else if (this.numberOfPiecesForPlayer(player1) == 0 || player2.isWinner()) {
			System.out.println("WINNER: " + player2.toString());
			return true;
		}

		return false;
	}  

}