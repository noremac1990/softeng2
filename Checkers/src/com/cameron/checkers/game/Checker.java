package com.cameron.checkers.game;

public class Checker {

	public static int CHECKER_BLACK = 0;
	public static int CHECKER_WHITE = 1;
	
	private int color;
	
	public Checker(int color) {
		this.setColor(color);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
}
