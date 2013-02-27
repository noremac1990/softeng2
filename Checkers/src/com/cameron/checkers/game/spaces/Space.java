package com.cameron.checkers.game.spaces;

import com.cameron.checkers.game.pieces.Checker;

public abstract class Space {
	
	private Checker checker = null;

	protected int x;
	protected int y;
	
	protected Space(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Checker getChecker() {
		return checker;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}
	
	public boolean hasChecker() {
		if(checker != null)
			return true;
		
		return false;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
