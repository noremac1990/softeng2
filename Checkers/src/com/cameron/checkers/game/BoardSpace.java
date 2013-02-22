package com.cameron.checkers.game;

public class BoardSpace {
	public static final int SPACE_WHITE = 0;
	public static final int SPACE_BLACK = 1;
	
	private Checker checker = null;
	
	private int color;
	
	public BoardSpace(int color)
	{
		this.setColor(color);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Checker getChecker() {
		return checker;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}
	
}
