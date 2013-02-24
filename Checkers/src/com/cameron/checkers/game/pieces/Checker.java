package com.cameron.checkers.game.pieces;

import com.cameron.checkers.game.Board;
import com.cameron.checkers.game.spaces.Space;

public abstract class Checker {

	protected int positionX;
	protected int positionY;
	
	protected Checker(int x, int y) {
		positionX = x;
		positionY = y;
	}
	
	public int getPositionX() {
		return positionX;
	}


	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}


	public int getPositionY() {
		return positionY;
	}


	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public boolean canMove(Board board, Space from, Space to) {
		
		// check bounds
		if(to.getX() < 0 || to.getX() >= board.getBoardWidth())
			return false;
		
		if(to.getY() < 0 || to.getY() >= board.getBoardHeight())
			return false;
		
		// make sure we can move horizontally
		if(!verticalDirectionCheck(to.getY()))
			return false;
		
		// make sure no checker in way
		if(to.hasChecker())
			return false;
		
		
		return true;
	}
	
	public abstract boolean verticalDirectionCheck(int y);
	
}
