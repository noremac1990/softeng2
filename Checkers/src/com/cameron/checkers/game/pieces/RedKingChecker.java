package com.cameron.checkers.game.pieces;

public class RedKingChecker extends Checker {

	public RedKingChecker(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verticalDirectionCheck(int y) {
		return true;
	}

}
