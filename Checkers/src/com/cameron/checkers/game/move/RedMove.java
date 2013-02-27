package com.cameron.checkers.game.move;

import com.cameron.checkers.game.pieces.Checker;
import com.cameron.checkers.game.pieces.RedChecker;

public class RedMove extends Move {

	@Override
	public boolean canMove(Checker c) {
		if(c instanceof RedChecker)
			return true;

		return false;
	}

}
