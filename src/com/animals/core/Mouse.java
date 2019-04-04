package com.animals.core;

import java.util.ArrayList;
import java.util.List;

import com.animals.interfaces.Animal;
import com.game.Board;
import com.game.Player;
import com.game.Point;
import com.game.strategie.RandomMoves;

public class Mouse extends Animal {

	private static final int MOUSE_POWER = 1;

	public String PieceFormat = "M" + player.getColor();

	public Mouse(Board board, Point position, Player player) {
		super(board, position, player);
	}

	public Mouse() {
		System.out.println("New Empty Mouse was created");
	}

	@Override
	public int getPower() {
		
		if (board.isTrap(this.getPosition())) {
			return 0;
		}
		
		return MOUSE_POWER;
	}

	@Override
	public boolean isPossibleMove(Point p) {
		
		if (board.isOutOfBoard(p)) {
			return false;
		}

		if (!board.isEmpty(p) && (board.getAnimalAt(p).player.getColor() == this.player.getColor()
				|| board.getAnimalAt(p).getPower() > this.getPower())) {
			if (board.getAnimalAt(p).getPower() == 8 && !board.isRiver(this.getPosition())) {
				return true;
			}
			return false;
		}

		return true;
	}

	public List<Point> getPossibleMoves() {
		
		List<Point> moves = new ArrayList<Point>();

		Point positionToReach1 = new Point();
		Point positionToReach2 = new Point();
		Point positionToReach3 = new Point();
		Point positionToReach4 = new Point();

		positionToReach1.setI(this.getPosition().getI());
		positionToReach1.setJ(this.getPosition().getJ() + 1);

		if (isPossibleMove(positionToReach1))
			moves.add(positionToReach1);

		positionToReach2.setI(this.getPosition().getI() + 1);
		positionToReach2.setJ(this.getPosition().getJ());

		if (isPossibleMove(positionToReach2))
			moves.add(positionToReach2);

		positionToReach3.setI(this.getPosition().getI());
		positionToReach3.setJ(this.getPosition().getJ() - 1);

		if (isPossibleMove(positionToReach3))
			moves.add(positionToReach3);

		positionToReach4.setI(this.getPosition().getI() - 1);
		positionToReach4.setJ(this.getPosition().getJ());

		if (isPossibleMove(positionToReach4))
			moves.add(positionToReach4);

		return moves;

	}

	public Point playBestMove() {
		Point moveToTak = RandomMoves.pickRandomPoint(getPossibleMoves());
		moves(moveToTak);
		return moveToTak;
	}
	
	public String toString() {
		return "Mouse: Power" + this.getPower() + " " + this.player.toString();
	}

	public String getFormat() {
		return this.PieceFormat;
	}

}
