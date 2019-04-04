package com.animals.interfaces;

import java.util.List;

import com.game.Board;
import com.game.Player;
import com.game.Point;

public abstract class Animal {

	public Board board;
	private Point position;
	public Player player;

	// different moves
	public static int JUMP = 0;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;

	public Animal(Board b, Point position, Player player) {
		this.board = b;
		this.position = position;
		this.player = player;
	}

	public Animal() {
	}
	
	/**
	 * 
	 * @return power of each animal
	 */

	public abstract int getPower();

	/**
	 * @param Point p
	 *           
	 * this method check if the move is possible logically. It's allowed
	 *  by rule's game. It's different from animal to another
	 *           
	 * @return TRUE/FALSE 
	 * 
	 */
	public abstract boolean isPossibleMove(Point p);
	
	/**
	 * 
	 * @return list of possible moves of each animal
	 */

	public abstract List<Point> getPossibleMoves();
	
	/**
	 * 
	 * @return this method for now returns a random move
	 */
	public abstract Point playBestMove();
	
	/**
	 * 
	 * @return the current position of the animal
	 */
	public Point getPosition() {

		return this.position;

	}

	public void moves(Point p) {
		this.board.pieceMove(this, p, this.player);
	}

	/**
	 * 
	 * @return the format that represents the animal in board
	 * 
	 */
	public abstract String getFormat();

	
	/**
	 * 
	 * 
	 * @param i : represents the direction that the animal wants to jump to 
	 * @return the new position of the animal
	 */
	public Point jumpTo(int i) {

		Point p = new Point();

		if (player.getColor() == 1) {
			if (i == Animal.UP) {
				p.setI(this.getPosition().getI() + 4);
				p.setJ(this.getPosition().getJ());
			} else if (i == Animal.DOWN) {
				p.setI(this.getPosition().getI() - 4);
				p.setJ(this.getPosition().getJ());
			} else if (i == Animal.LEFT) {
				p.setI(this.getPosition().getI());
				p.setJ(this.getPosition().getJ() + 3);
				Animal.JUMP = Animal.RIGHT;
			} else if (i == Animal.RIGHT) {
				p.setI(this.getPosition().getI());
				p.setJ(this.getPosition().getJ() - 3);
				Animal.JUMP = Animal.LEFT;
			}
		}

		if (player.getColor() == 2) {
			if (i == Animal.UP) {
				p.setI(this.getPosition().getI() - 4);
				p.setJ(this.getPosition().getJ());
			} else if (i == Animal.DOWN) {
				p.setI(this.getPosition().getI() + 4);
				p.setJ(this.getPosition().getJ());
			} else if (i == Animal.LEFT) {
				p.setI(this.getPosition().getI());
				p.setJ(this.getPosition().getJ() - 3);
				Animal.JUMP = Animal.LEFT;
			} else if (i == Animal.RIGHT) {
				p.setI(this.getPosition().getI());
				p.setJ(this.getPosition().getJ() + 3);
				Animal.JUMP = Animal.RIGHT;
			}
		}

		return p;
	}

}
