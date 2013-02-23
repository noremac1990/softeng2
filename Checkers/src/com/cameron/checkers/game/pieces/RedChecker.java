package com.cameron.checkers.game.pieces;


public class RedChecker extends Checker {

	public RedChecker(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verticalDirectionCheck(int y) {
		
		// red checkers can only move up
		if(y < positionY)
			return true;
		
		return false;
	}

}
