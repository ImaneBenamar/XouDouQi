package com.game.strategie;

import java.util.List;
import java.util.Random;

import com.game.Point;

public class RandomMoves {

	
	public static Point pickRandomPoint(List<Point> possibleMoves) {
		
		
		Random r = new Random();
		Point p = possibleMoves.get(r.nextInt(possibleMoves.size()));
		return p;
		
	}
}
