package com.cameron.checkers.game;

import com.cameron.checkers.game.move.BlackMove;
import com.cameron.checkers.game.move.Move;
import com.cameron.checkers.game.move.RedMove;
import com.cameron.checkers.game.pieces.BlackKingChecker;
import com.cameron.checkers.game.pieces.Checker;
import com.cameron.checkers.game.pieces.RedChecker;
import com.cameron.checkers.game.pieces.BlackChecker;
import com.cameron.checkers.game.pieces.RedKingChecker;
import com.cameron.checkers.game.spaces.Space;

public class GameDirector {
	Board board;
	
	Move move;
	
	public GameDirector() {
		board = new Board();
		move = new BlackMove();
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Move getMove() {
		return move;
	}

	
	public void doMove(Space from, Space to)
	{
		Checker c = from.getChecker();
		
		Space jumped = isJumpable(from, to);
		
		if(jumped != null)
			jumped.setChecker(null);
		
		from.setChecker(null);
		
		if(to.getY() == board.getBoardHeight() - 1 && c instanceof RedChecker) {
			c = new RedKingChecker(to.getX(), to.getY());
		} else if(to.getY() == 0
				&& c instanceof BlackChecker) {
			c = new BlackKingChecker(to.getX(), to.getY());
		} else {
			c.setPositionX(to.getX());
			c.setPositionY(to.getY());
		}
		
		to.setChecker(c);
		
		
		if(move.getClass().equals(BlackMove.class))
			move = new RedMove();
		else
			move = new BlackMove();
		
	}
	
	public boolean movePossible(Space from, Space to) {

		if(!from.getChecker().canMove(board, from, to))
			return false;
		
		
		int xDifference = Math.abs(from.getX() - to.getX());
		
		int yDifference = Math.abs(from.getY() - to.getY());
		
		if(yDifference == 2 && xDifference == 2) {
			
			Space jumped = isJumpable(from, to);
			
			if(jumped == null)
				return false;
			
			
			return true;
		} else if(yDifference == 1 && xDifference == 1) {
			if(hasJumps())
				return false;
			
			return true;
		}
		
		
		
		return false;
	}
	
	private Space isJumpable(Space from, Space to) {
		
		Space middle = board.getSpaces()[(to.getX() + from.getX()) / 2][(to.getY() + from.getY()) / 2];
		
		if(!middle.hasChecker())
			return null;
		
		if(middle.getChecker() instanceof BlackChecker && from.getChecker() instanceof BlackChecker)
			return null;
		
		if(middle.getChecker() instanceof RedChecker && from.getChecker() instanceof RedChecker)
			return null;
		
		
		return middle;
	}
	
	private boolean hasJumps() {
		
		for(int y = 0; y < board.getBoardHeight(); y++) {
			for(int x = 0; x < board.getBoardWidth(); x++) {
				
				Space position = board.getSpaces()[x][y];
				Checker c = position.getChecker();
				
				if(c == null)
					continue;
				else if(c instanceof BlackChecker && move instanceof RedMove)
					continue;
				else if(c instanceof RedChecker && move instanceof BlackMove)
					continue;
				
				if (x >= 2 && y >= 2) {
					Space to = board.getSpaces()[x - 2][y - 2];
					if (c.canMove(board, position, to)
							&& isJumpable(position, to) != null)
						return true;
				}
								
				if (x >= 2 && y < board.getBoardHeight() - 2) {
					Space to = board.getSpaces()[x - 2][y + 2];
					if (c.canMove(board, position, to)
							&& isJumpable(position, to) != null)
						return true;
				}
								
				if (x < board.getBoardWidth() - 2
						&& y < board.getBoardHeight() - 2) {
					Space to = board.getSpaces()[x + 2][y + 2];
					if (c.canMove(board, position, to)
							&& isJumpable(position, to) != null)
						return true;
				}

				
				if (x < board.getBoardWidth() - 2 && y >= 2) {
					Space to = board.getSpaces()[x + 2][y - 2];

					if (c.canMove(board, position, to)
							&& isJumpable(position, to) != null)
						return true;
				}
				
							
			}
		}

		return false;
	}
}
