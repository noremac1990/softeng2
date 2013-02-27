package com.cameron.checkers.game.move;

import com.cameron.checkers.game.pieces.Checker;

public abstract class Move {
	public abstract boolean canMove(Checker c);
}
