package com.cameron.checkers.game.pieces;


public class RedCheckerPiece extends RedChecker {

	public RedCheckerPiece(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verticalDirectionCheck(int y) {
		
		// red checkers can only move up
		if(y > positionY)
			return true;
		
		return false;
	}

}
