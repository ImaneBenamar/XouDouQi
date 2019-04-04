package com.gui;

import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.game.Board;
import com.game.types.MvsM;
import com.game.types.PvsM;
import com.game.types.PvsP;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Logger logger = Logger.getLogger("GAMELOG");
		FileHandler fh;

		try {
			fh = new FileHandler("LOG.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			logger.info("This is a test info");
		} catch (Exception e) {
			// TODO: handle exception
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Vous voulez jouer : ");
		System.out.println(" 1 - Machine entre Machine ?");
		System.out.println(" 2 - Joueur entre Joueur ?");
		System.out.println(" 3 - Joueur entre Machine ?");
		System.out.println("Entrer votre choix : ");

		int choice = sc.nextInt();

		Board board = new Board();
		board.initializeBoard();

		switch (choice) {
		case 1:
			MvsM.MachineVsMachine(board);
			break;
		case 2:
			PvsP.PlayerVsPlayer(board);
		case 3:
			PvsM.PlayerVsMachine(board);
		default:
			System.out.println("Veuillez entrer un choix valide");
		}

	}

}
