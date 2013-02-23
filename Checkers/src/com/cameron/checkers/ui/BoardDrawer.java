package com.cameron.checkers.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cameron.checkers.game.Board;
import com.cameron.checkers.game.pieces.BlackChecker;
import com.cameron.checkers.game.spaces.BlackSpace;
import com.cameron.checkers.game.spaces.Space;

public class BoardDrawer {
	
	private static final int SPACE_WIDTH = 64;
	private static final int SPACE_HEIGHT = 64;

	private BufferedImage checkerRed;
	private BufferedImage checkerBlack;
	
	public BoardDrawer() throws IOException {
		checkerRed = ImageIO.read(new File("res/checkerRed.png"));
		checkerBlack = ImageIO.read(new File("res/checkerBlack.png"));
	}
	
	public void draw(Graphics g, Board board) {
		
		Space[][] spaces = board.getSpaces();
		
		drawSpaces(g, spaces);
		
		drawCheckers(g, spaces);

	}

	private void drawCheckers(Graphics g, Space[][] spaces) {
		for (int y = 0; y < spaces.length; y++) {
			for (int x = 0; x < spaces.length; x++) {
				
				if (spaces[x][y].getChecker() != null) {
					if (spaces[x][y].getChecker() instanceof BlackChecker)
						g.drawImage(checkerBlack, x * SPACE_WIDTH, y * SPACE_WIDTH, null);
					else
						g.drawImage(checkerRed, x * SPACE_WIDTH, y * SPACE_WIDTH, null);
				}
			}

		}
	}

	private void drawSpaces(Graphics g, Space[][] spaces) {
		for(int y = 0; y < spaces.length; y++)
		{
			for(int x = 0; x < spaces.length; x++)
			{
				if(spaces[x][y] instanceof BlackSpace)
					g.setColor(Color.BLACK);
				else
					g.setColor(Color.WHITE);
		
				g.fillRect(x * SPACE_WIDTH, y * SPACE_WIDTH, SPACE_WIDTH, SPACE_HEIGHT);
			}
		}
		
		return;
	}
}
