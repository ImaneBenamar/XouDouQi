package com.game.types;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.animals.interfaces.Animal;
import com.game.Board;
import com.game.Player;
import com.game.Point;

public class PvsP {

	public Board board;

	public PvsP(Board pboard) {
		this.board = pboard;
	}
	
	public static void PlayerVsPlayer(Board pboard) {
			PvsP pvsp = new PvsP(pboard);
			pvsp.board.getBoard();
			Player playerToPlay;
			Scanner sc = new Scanner(System.in);	
			
			while(!pvsp.board.isGameOver()) {
				playerToPlay = pvsp.board.checkPlayer();
				Animal.JUMP = 0;
				String commande = sc.nextLine();
				
				// Véifier que la commande a été correctement saisie
				Matcher m = Pattern.compile("^([E,L,T,P,D,W,C,M]) (u|d|l|r)$").matcher(commande);
				if (m.matches()) {
					String animalString = m.group(1);
					String directionString = m.group(2);
					Point direction = new Point();					
					Animal a = pvsp.board.getAnimalByPieceFormat(animalString, playerToPlay);
					
					if(!pvsp.board.isAnimalExist(a)) {
						System.out.println("L'animal n'existe plus !");
						System.out.println("ressayer s'il vous plait : ");
					}
					else {
						if ("u".equals(directionString)) {
							if(playerToPlay.getColor()==2) {
								direction.setI(a.getPosition().getI()-1);
								direction.setJ(a.getPosition().getJ());
							}
							else {
								direction.setI(a.getPosition().getI()+1);
								direction.setJ(a.getPosition().getJ());
							}
							if(pvsp.board.isRiver(direction) && ("L".equals(animalString) || "T".equals(animalString))) {
								direction = a.jumpTo(Animal.UP);
							}
							
						} else if ("d".equals(directionString)) {
							if(playerToPlay.getColor()==2) {
								direction.setI(a.getPosition().getI()+1);
								direction.setJ(a.getPosition().getJ());
							}
							else {
								direction.setI(a.getPosition().getI()-1);
								direction.setJ(a.getPosition().getJ());	
							}
							if(pvsp.board.isRiver(direction) && ("L".equals(animalString) || "T".equals(animalString))) {
								direction = a.jumpTo(Animal.DOWN);
							}
		
						} else if ("l".equals(directionString)) {
							
							if(playerToPlay.getColor()==2) {
								direction.setI(a.getPosition().getI());
								direction.setJ(a.getPosition().getJ()-1);
							}else {
								direction.setI(a.getPosition().getI());
								direction.setJ(a.getPosition().getJ()+1);
							}
							if(pvsp.board.isRiver(direction) && ("L".equals(animalString) || "T".equals(animalString))) {
								direction = a.jumpTo(Animal.LEFT);
							}
		
						} else if ("r".equals(directionString)) {
							if(playerToPlay.getColor()==2) {
								direction.setI(a.getPosition().getI());
								direction.setJ(a.getPosition().getJ()+1);
							}
							else {
								direction.setI(a.getPosition().getI());
								direction.setJ(a.getPosition().getJ()-1);
							}
							if(pvsp.board.isRiver(direction) && ("L".equals(animalString) || "T".equals(animalString))) {
								direction = a.jumpTo(Animal.RIGHT);
							}
						}
						
						if(a.isPossibleMove(direction)) {
							pvsp.board.checkIfIsInTarget(direction);
							a.moves(direction);
							
							pvsp.board.getBoard();
							pvsp.board.setTour(-pvsp.board.getTour());
							

						}
						else {
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
				
			}
		}
}
