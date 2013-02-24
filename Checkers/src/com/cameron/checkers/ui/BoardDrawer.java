package com.cameron.checkers.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cameron.checkers.game.Board;
import com.cameron.checkers.game.pieces.BlackCheckerPiece;
import com.cameron.checkers.game.pieces.BlackKingChecker;
import com.cameron.checkers.game.pieces.Checker;
import com.cameron.checkers.game.pieces.RedCheckerPiece;
import com.cameron.checkers.game.pieces.RedKingChecker;
import com.cameron.checkers.game.spaces.BlackSpace;
import com.cameron.checkers.game.spaces.Space;

public class BoardDrawer {
	
	private int spaceWidth = 64;
	private int spaceHeight = 64;

	private BufferedImage checkerRed;
	private BufferedImage checkerBlack;
	private BufferedImage checkerKingRed;
	private BufferedImage checkerKingBlack;
	private BufferedImage selectedSquare;
	
	CheckersWindowPanel panel;
	
	public BoardDrawer(CheckersWindowPanel panel) throws IOException {

		this(panel, 64, 64);
	}
	
	public BoardDrawer(CheckersWindowPanel panel, int spaceWidth, int spaceHeight) throws IOException {
		
		this.spaceWidth = spaceWidth;
		this.spaceHeight = spaceHeight;
		
		checkerRed = ImageIO.read(new File("res/checkerRed.png"));
		checkerBlack = ImageIO.read(new File("res/checkerBlack.png"));
		selectedSquare = ImageIO.read(new File("res/selectedSquare.png"));
		checkerKingRed = ImageIO.read(new File("res/checkerKingRed.png"));
		checkerKingBlack = ImageIO.read(new File("res/checkerKingBlack.png"));
		
		this.panel = panel;
	}
	
	public void draw(Graphics g, Board board) {
		
		Space[][] spaces = board.getSpaces();
		
		drawSpaces(g, spaces);
		
		drawCheckers(g, spaces);
		
		drawSelection(g, panel.getSelectedSpace());

	}

	private void drawSelection(Graphics g, Space selectedSpace) {
		
		if(selectedSpace == null)
			return;
		
		g.drawImage(selectedSquare, selectedSpace.getX() * spaceWidth, selectedSpace.getY() * spaceHeight, null);
		
	}

	public int getSpaceWidth() {
		return spaceWidth;
	}

	public int getSpaceHeight() {
		return spaceHeight;
	}

	private void drawCheckers(Graphics g, Space[][] spaces) {
		
		
		for (int y = 0; y < spaces.length; y++) {
			for (int x = 0; x < spaces.length; x++) {

				Checker checker = spaces[x][y].getChecker();

				if (checker == null)
					continue;

				if (checker instanceof BlackCheckerPiece)
					g.drawImage(checkerBlack, x * spaceWidth, y * spaceWidth,
							null);
				else if (checker instanceof RedCheckerPiece)
					g.drawImage(checkerRed, x * spaceWidth, y * spaceWidth,
							null);
				else if (checker instanceof BlackKingChecker)
					g.drawImage(checkerKingBlack, x * spaceWidth, y
							* spaceWidth, null);
				else if (checker instanceof RedKingChecker)
					g.drawImage(checkerKingRed, x * spaceWidth, y
							* spaceWidth, null);
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
		
				g.fillRect(x * spaceWidth, y * spaceWidth, spaceWidth, spaceHeight);
			}
		}
		
		return;
	}
}
