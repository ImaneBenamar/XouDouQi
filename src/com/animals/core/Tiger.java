package com.animals.core;

import java.util.ArrayList;
import java.util.List;

import com.animals.interfaces.Animal;
import com.game.Board;
import com.game.Player;
import com.game.Point;
import com.game.strategie.RandomMoves;

public class Tiger extends Animal {

	public static final int TIGER_POWER = 6;
	public String PieceFormat = "T" + player.getColor();

	public Tiger(Board b, Point position, Player player) {
		super(b, position, player);
	}

	public Tiger() {
		System.out.println("New Empty Mouse has been created");
	}

	@Override
	public int getPower() {
		if (board.isTrap(this.getPosition())) {
			return 0;
		}
		return TIGER_POWER;
	}

	@Override
	public boolean isPossibleMove(Point p) {

		// Check if the point is out of the board
		if (board.isOutOfBoard(p)) {
			return false;
		}


		if (!board.isEmpty(p) && (board.getAnimalAt(p).player.getColor() == this.player.getColor()
				|| (board.getAnimalAt(p).getPower() > this.getPower()
						&& board.getAnimalAt(p).getFormat().charAt(0) != 'E'))) {
			return false;
		}
		
		if(!board.checkIfLakeIsEmpty(this.getPosition())) {
			return false;
		}

		return true;

	}

	@Override
	public List<Point> getPossibleMoves() {

		List<Point> moves = new ArrayList<Point>();

		Point positionToReach1 = new Point();
		Point positionToReach2 = new Point();
		Point positionToReach3 = new Point();
		Point positionToReach4 = new Point();

		positionToReach1.setI(this.getPosition().getI());
		positionToReach1.setJ(this.getPosition().getJ() + 1);

		if (board.isRiver(positionToReach1)) {
			if (board.checkIfLakeIsEmpty(this.getPosition())) {
				positionToReach1.setI(this.getPosition().getI());
				positionToReach1.setJ(this.getPosition().getJ() + 3);

				if (isPossibleMove(positionToReach1))
					moves.add(positionToReach1);
			}
		} else {
			if (isPossibleMove(positionToReach1))
				moves.add(positionToReach1);
		}

		positionToReach2.setI(this.getPosition().getI() + 1);
		positionToReach2.setJ(this.getPosition().getJ());

		if (board.isRiver(positionToReach2)) {
			if (board.checkIfLakeIsEmpty(this.getPosition())) {
				positionToReach2.setI(this.getPosition().getI() + 4);
				positionToReach2.setJ(this.getPosition().getJ());

				if (isPossibleMove(positionToReach2))
					moves.add(positionToReach2);
			}
		} else {
			if (isPossibleMove(positionToReach2))
				moves.add(positionToReach2);
		}

		positionToReach3.setI(this.getPosition().getI());
		positionToReach3.setJ(this.getPosition().getJ() - 1);

		if (board.isRiver(positionToReach3)) {
			if (board.checkIfLakeIsEmpty(this.getPosition())) {
				positionToReach3.setI(this.getPosition().getI() - 3);
				positionToReach3.setJ(this.getPosition().getJ());

				if (isPossibleMove(positionToReach3))
					moves.add(positionToReach3);
			}
		} else {
			if (isPossibleMove(positionToReach3))
				moves.add(positionToReach3);
		}

		positionToReach4.setI(this.getPosition().getI() - 1);
		positionToReach4.setJ(this.getPosition().getJ());

		if (board.isRiver(positionToReach4)) {
			if (board.checkIfLakeIsEmpty(this.getPosition())) {
				positionToReach4.setI(this.getPosition().getI() - 4);
				positionToReach4.setJ(this.getPosition().getJ());

				if (isPossibleMove(positionToReach4))
					moves.add(positionToReach4);
			}
		} else {
			if (isPossibleMove(positionToReach4))
				moves.add(positionToReach4);
		}

		return moves;
	}

	@Override
	public Point playBestMove() {
		Point moveToTak = RandomMoves.pickRandomPoint(getPossibleMoves());
		moves(moveToTak);
		return moveToTak;
	}

	public String toString() {
		return "Tiger: Power" + this.getPower() + " " + this.player.toString();
	}

	public String getFormat() {
		return this.PieceFormat;
	}

}
