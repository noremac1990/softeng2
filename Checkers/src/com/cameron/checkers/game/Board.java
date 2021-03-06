package com.cameron.checkers.game;

import com.cameron.checkers.game.pieces.BlackCheckerPiece;
import com.cameron.checkers.game.pieces.RedCheckerPiece;
import com.cameron.checkers.game.spaces.BlackSpace;
import com.cameron.checkers.game.spaces.Space;
import com.cameron.checkers.game.spaces.WhiteSpace;

public class Board {
	
	private static final int DEFAULT_BOARD_WIDTH = 8;
	private static final int DEFAULT_BOARD_HEIGHT = 8;
	
	private int width;
	private int height;
	
	private Space[][] spaces;
	
	public Space[][] getSpaces() {
		return spaces;
	}

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		
		newGame();
	}
	
	public Board() {
		this(DEFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
	}
	
	public int getBoardWidth() {
		return width;
	}

	public int getBoardHeight() {
		return height;
	}

	public void newGame() {
		spaces = new Space[height][];
		int col = 0;
		
		for(int y = 0; y < height; y++)
		{
			spaces[y] = new Space[width];
			
			for(int x = 0; x < width; x++) {
				if(col == 0)
					spaces[y][x] = new BlackSpace(y, x);
				else
					spaces[y][x] = new WhiteSpace(y, x);
				
				col = 1 - col;
			}
			col = 1 - col;
		}
		
		col = 1;
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < width; x++) {
				if(col == 0)
					spaces[x][y].setChecker(new RedCheckerPiece(x, y));
				
				col = 1 - col;
			}
			col = 1 - col;
		}
		
		col = 0;
		for(int y = height - 1; y > 4; y--) {
			for(int x = 0; x < width; x++) {
				if(col == 0)
					spaces[x][y].setChecker(new BlackCheckerPiece(x, y));
				
				col = 1 - col;
			}
			col = 1 - col;
		}
		
		
	}
	
}
