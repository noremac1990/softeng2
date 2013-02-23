package com.cameron.checkers.game.pieces;


public class BlackChecker extends Checker {

	public BlackChecker(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean verticalDirectionCheck(int y) {
		// black checkers can only move down
		if(y > positionY)
			return true;
		
		return false;
	}
	
	

}
