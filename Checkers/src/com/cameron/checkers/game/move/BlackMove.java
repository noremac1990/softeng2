package com.cameron.checkers.game.move;

import com.cameron.checkers.game.pieces.BlackChecker;
import com.cameron.checkers.game.pieces.Checker;

public class BlackMove extends Move {

	@Override
	public boolean canMove(Checker c) {
		if(c instanceof BlackChecker)
			return true;
		
		return false;
	}
	
}
