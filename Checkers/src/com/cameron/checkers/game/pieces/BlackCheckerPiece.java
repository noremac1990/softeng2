package com.cameron.checkers.game.pieces;


public class BlackCheckerPiece extends BlackChecker {

	public BlackCheckerPiece(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean verticalDirectionCheck(int y) {
		if(y > positionY)
			return true;
		
		return false;
	}
	
	

}
