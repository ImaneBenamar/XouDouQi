package com.game;

import java.util.ArrayList;
import java.util.List;


public class Player {
	
	private int color ;
	private String name;
	private int score;
	private boolean winner;
	
	
	
	/**
	 * The log is for the player's history moves 
	 */
	List<String> movesHistory = new ArrayList<String>();
	
	@Override
	public String toString() {
		return "Player [color=" + color + ", name=" + name + ", score=" + score + "]";
	}
	
	
	public Player(int color, String name) {
		super();
		this.color = color;
		this.name = name;
		this.score = 0;
		this.winner=false;
	}

	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}


	/**
	 * storeMove for storing new move into the log's move
	 * @param moveDescription
	 * We can customize our moveDescription like in chess: D32 [...]
	 * or we simply create a small description
	 */
	public void storeMove(String moveDescription) {
		this.movesHistory.add(moveDescription);
	}
	
	/**
	 * 
	 * @return the log's history
	 */
	public List<String> getAllMovesList() {
		return this.movesHistory;
	}
	
	/**
	 * Displaying all the moves made by this player
	 */
	public void displayHistoryMoves() {
		System.out.println(this.movesHistory.size() + " moves, player: " + this.name + ": ");
		for (int i = 0; i < this.movesHistory.size(); i++) {
			System.out.println("Move " + (int)(i+1) + " : " + this.movesHistory.get(i));
		}
	}
	
}
	
	