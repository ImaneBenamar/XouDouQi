package com.game;


public class Point {
	private int i;
	private int j;
	
	public String toString() {
		return String.valueOf(getI()) + " " + String.valueOf(getJ());
	}
	
	public Point() {
		
	}

	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	public boolean isEqual(Point p) {
		return (p.getI() == this.getI() && p.getJ() == this.getJ());
	}
	

}
