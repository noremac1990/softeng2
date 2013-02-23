package com.cameron.checkers.game;

import com.cameron.checkers.game.pieces.Checker;
import com.cameron.checkers.game.spaces.Space;

public class GameDirector {
	Board board;
	
	public GameDirector() {
		board = new Board();
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void doMove(Space from, Space to)
	{
		Checker c = from.getChecker();
		from.setChecker(null);
		
		to.setChecker(c);
		
		c.setPositionX(to.getX());
		c.setPositionY(to.getY());
		
	}
	
	public boolean movePossible(Space from, Space to) {

		if(!from.getChecker().canMove(board, from, to))
			return false;
		
		System.err.printf("%d %d %d %d %n", from.getX(), from.getY(), to.getX(), to.getY());
		
		
		int xDifference = Math.abs(from.getX() - to.getX());
		
		
		
		int yDifference = Math.abs(from.getY() - to.getY());
		
		if(yDifference == 2 && xDifference == 2) // jump
		{
			Space middle = board.getSpaces()[(to.getX() + from.getX()) / 2][(to.getY() + from.getY()) / 2];
			
			if(!middle.hasChecker())
				return false;
			
			//if(middle.getChecker().getClass().getName()(from.getChecker().getClass()))
			//	return false;
			
			middle.setChecker(null);
			
			return true;
		}
		else if(yDifference == 1 && xDifference == 1)
		{
			return true;
		}
		
		
		
		return false;
	}

	private boolean checkForcedMoves() {

		return false;
	}
}
