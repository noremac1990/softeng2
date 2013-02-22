package com.cameron.checkers.game;

public class Board {
	private static final Board INSTANCE = new Board();
	
	private static final int BOARD_WIDTH = 8;
	private static final int BOARD_HEIGHT = 8;
	
	private BoardSpace[][] spaces;
	
	public BoardSpace[][] getSpaces() {
		return spaces;
	}

	private Board() {
		newGame();
	}
	
	public void newGame() {
		spaces = new BoardSpace[BOARD_HEIGHT][];
		int col = 0;
		
		for(int y = 0; y < BOARD_HEIGHT; y++)
		{
			spaces[y] = new BoardSpace[BOARD_WIDTH];
			
			for(int x = 0; x < BOARD_WIDTH; x++) {
				if(col == 0)
					spaces[y][x] = new BoardSpace(BoardSpace.SPACE_BLACK);
				else
					spaces[y][x] = new BoardSpace(BoardSpace.SPACE_WHITE);
				
				col = 1 - col;
			}
			col = 1 - col;
		}
		
		col = 1;
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < BOARD_WIDTH; x++) {
				if(col == 0)
					spaces[x][y].setChecker(new Checker(Checker.CHECKER_BLACK));
				
				col = 1 - col;
			}
			col = 1 - col;
		}
		
		col = 0;
		for(int y = BOARD_HEIGHT - 1; y > 4; y--) {
			for(int x = 0; x < BOARD_WIDTH; x++) {
				if(col == 0)
					spaces[x][y].setChecker(new Checker(Checker.CHECKER_WHITE));
				
				col = 1 - col;
			}
			col = 1 - col;
		}
		
		
	}

	public static Board getInstance() {
		return INSTANCE;
	}
	
}
