package com.cameron.checkers.game.pieces;

public class RedKingChecker extends RedChecker {

	public RedKingChecker(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean verticalDirectionCheck(int y) {
		return true;
	}

}
