package com.game.types;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.animals.interfaces.Animal;
import com.game.Board;
import com.game.Player;
import com.game.Point;

public class PvsM {

	public Board board;

	public PvsM(Board pboard) {
		this.board = pboard;
	}

	public Animal getAnimalToPlay(Player player) {
		List<Animal> listOfPossibleAnimalsToPlayFirst = new LinkedList<Animal>();

		String animalFormat = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 7; j++) {
				if (this.board.board[i][j] == null)
					continue;
				animalFormat = this.board.board[i][j].getFormat();
				int REDIX = 10;// redix 10 is for decimal number, for hexa use
								// redix 16
				int playerColor = player.getColor();
				char charPlayersColor = Character.forDigit(playerColor, REDIX);
				if (animalFormat.charAt(animalFormat.length() - 1) == charPlayersColor) {
					listOfPossibleAnimalsToPlayFirst.add(this.board.board[i][j]);
				}
			}
		}

		System.out.println(listOfPossibleAnimalsToPlayFirst);

		// Now we play randomely

		Random r = new Random();
		Animal animalToReturn = listOfPossibleAnimalsToPlayFirst
				.get(r.nextInt(listOfPossibleAnimalsToPlayFirst.size()));

		return animalToReturn;
	}

	public static void PlayerVsMachine(Board pboard) {

		PvsM pvsm = new PvsM(pboard);
		Player playerToPlay = pvsm.board.player2;
		pvsm.board.getBoard();
		Scanner s = new Scanner(System.in);

		while (!pvsm.board.isGameOver()) {

			Animal.JUMP = 0;
			boolean play = false;
			// Lire la commande au clavier
			String commande = s.nextLine();

			Matcher m = Pattern.compile("^([E,L,T,P,D,W,C,M]) (u|d|l|r)$").matcher(commande);

			if (m.matches()) {
				String animalString = m.group(1);
				String directionString = m.group(2);
				Point direction = new Point();
				Animal a = pvsm.board.getAnimalByPieceFormat(animalString, playerToPlay);

				if (!pvsm.board.isAnimalExist(a)) {
					System.out.println("L'animal n'existe plus !");
					System.out.println("ressayer s'il vous plait : ");
				} else {
					if ("u".equals(directionString)) {
						if (playerToPlay.getColor() == 2) {
							direction.setI(a.getPosition().getI() - 1);
							direction.setJ(a.getPosition().getJ());
						} else {
							direction.setI(a.getPosition().getI() + 1);
							direction.setJ(a.getPosition().getJ());
						}
						if (pvsm.board.isRiver(direction) && ("L".equals(animalString) || "T".equals(animalString))) {
							direction = a.jumpTo(Animal.UP);
						}

					} else if ("d".equals(directionString)) {
						if (playerToPlay.getColor() == 2) {
							direction.setI(a.getPosition().getI() + 1);
							direction.setJ(a.getPosition().getJ());
						} else {
							direction.setI(a.getPosition().getI() - 1);
							direction.setJ(a.getPosition().getJ());
						}
						if (pvsm.board.isRiver(direction) && ("L".equals(animalString) || "T".equals(animalString))) {
							direction = a.jumpTo(Animal.DOWN);
						}

					} else if ("l".equals(directionString)) {

						if (playerToPlay.getColor() == 2) {
							direction.setI(a.getPosition().getI());
							direction.setJ(a.getPosition().getJ() - 1);
						} else {
							direction.setI(a.getPosition().getI());
							direction.setJ(a.getPosition().getJ() + 1);
						}
						if (pvsm.board.isRiver(direction) && ("L".equals(animalString) || "T".equals(animalString))) {
							direction = a.jumpTo(Animal.LEFT);
						}

					} else if ("r".equals(directionString)) {
						if (playerToPlay.getColor() == 2) {
							direction.setI(a.getPosition().getI());
							direction.setJ(a.getPosition().getJ() + 1);
						} else {
							direction.setI(a.getPosition().getI());
							direction.setJ(a.getPosition().getJ() - 1);
						}
						if (pvsm.board.isRiver(direction) && ("L".equals(animalString) || "T".equals(animalString))) {
							direction = a.jumpTo(Animal.RIGHT);
						}
					}

					if (a.isPossibleMove(direction)) {
						pvsm.board.checkIfIsInTarget(direction);
						a.moves(direction);
						pvsm.board.getBoard();

						play = true;
					} else {
						System.out.println("impossible move !!");
						System.out.println("ressayer s'il vous plait : ");
					}
				}

				System.out.println("Number of pieces for player1: " + pboard.numberOfPiecesForPlayer(pboard.player1));
				System.out.println("Number of pieces for player2: " + pboard.numberOfPiecesForPlayer(pboard.player2));
			}

			else if ("exit".equals(commande)) {
				System.exit(0);
			}

			else {
				System.out.println("commande incorrecte");
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (play) {
				Animal animalChosenToPlay = pvsm.getAnimalToPlay(pvsm.board.player1);
				while (animalChosenToPlay.getPossibleMoves().size() == 0)
					animalChosenToPlay = pvsm.getAnimalToPlay(pvsm.board.player1);

				Point d = animalChosenToPlay.playBestMove();
				pvsm.board.getBoard();

				pvsm.board.checkIfIsInTarget(d);

			}

		}

	}

}
