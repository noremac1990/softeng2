package com.cameron.checkers.game.pieces;

public class BlackKingChecker extends Checker {

	protected BlackKingChecker(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verticalDirectionCheck(int y) {
		return true;
	}

}
