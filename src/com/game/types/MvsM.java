package com.game.types;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.animals.interfaces.Animal;
import com.game.Board;
import com.game.Player;
import com.game.Point;

public class MvsM {
	
	public Board board;

	public MvsM(Board pboard) {
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
				int REDIX = 10;// redix 10 is for decimal number, for hexa use redix 16
				int playerColor = player.getColor();
				char charPlayersColor = Character.forDigit(playerColor, REDIX);
				if (animalFormat.charAt(animalFormat.length() - 1) == charPlayersColor) {
					listOfPossibleAnimalsToPlayFirst.add(this.board.board[i][j]);
				}
			}
		}

		System.out.println(listOfPossibleAnimalsToPlayFirst);

		// Now we're play randomely

		Random r = new Random();
		Animal animalToReturn = listOfPossibleAnimalsToPlayFirst
				.get(r.nextInt(listOfPossibleAnimalsToPlayFirst.size()));

		return animalToReturn;
	}
	
	
	public static void MachineVsMachine(Board pboard) throws InterruptedException {
		MvsM mvsm = new MvsM(pboard);
		Animal animalChosenToPlay;
		mvsm.board.getBoard();
		Player playerToPlay;
		
		while (!mvsm.board.isGameOver()) {
			playerToPlay = mvsm.board.checkPlayer();
			animalChosenToPlay = mvsm.getAnimalToPlay(playerToPlay);
			while (animalChosenToPlay.getPossibleMoves().size() == 0)
				animalChosenToPlay = mvsm.getAnimalToPlay(playerToPlay);
			
			Point direction = animalChosenToPlay.playBestMove();
			mvsm.board.getBoard();
			
			mvsm.board.checkIfIsInTarget(direction);
			
			Thread.sleep(1000);

			mvsm.board.setTour(-mvsm.board.getTour());
	
			
			System.out.println("Number of pieces for player1: " + pboard.numberOfPiecesForPlayer(pboard.player1));
			System.out.println("Number of pieces for player2: " + pboard.numberOfPiecesForPlayer(pboard.player2));

		}

		System.out.println("Player1" + pboard.player1.getAllMovesList().toString());
		System.out.println("Player2" + pboard.player2.getAllMovesList().toString());
	}

}
