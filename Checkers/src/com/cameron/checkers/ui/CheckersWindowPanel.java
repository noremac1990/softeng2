package com.cameron.checkers.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cameron.checkers.game.GameDirector;
import com.cameron.checkers.game.move.BlackMove;
import com.cameron.checkers.game.spaces.Space;

public class CheckersWindowPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BoardDrawer boardDrawer;
	private GameDirector gameDirector;
	
	public Space getSelectedSpace() {
		return selectedSpace;
	}

	private Space selectedSpace;

	public CheckersWindowPanel() {
		super();
		try {
			boardDrawer = new BoardDrawer(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to load board images", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		setPreferredSize(new Dimension(800, 512));
		
		gameDirector = new GameDirector();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getX() >= boardDrawer.getSpaceWidth() * gameDirector.getBoard().getBoardWidth())
					return;
				
				if(e.getY() >= boardDrawer.getSpaceHeight() * gameDirector.getBoard().getBoardHeight())
					return;
				
				
				if(selectedSpace == null) {
					selectSpace(e.getX(), e.getY());
				} else {
					tryMove(e.getX(), e.getY());
					selectedSpace = null;
				}
				
				repaint();
				super.mousePressed(e);
			}
		});
		
		repaint();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		g.clearRect(0, 0, getWidth(), getHeight());
		
		boardDrawer.draw(g, gameDirector.getBoard());
		
		if(gameDirector.getMove() instanceof BlackMove)
			g.drawString("Black's move", 600, 20);
		else
			g.drawString("Red's move", 600, 20);
		
	}

	public void newGame() {
		gameDirector = new GameDirector();
		selectedSpace = null;
		repaint();
	}
	

	private void tryMove(int x, int y) {
		
		Space to = gameDirector.getBoard().getSpaces()[x / 64][y / 64];
		if(!gameDirector.movePossible(selectedSpace, to))
		{
			JOptionPane.showMessageDialog(this, "Not possible");
			return;
		}
		
		gameDirector.doMove(selectedSpace, to);
		
		selectedSpace = null;
		
		repaint();
		
	}

	private void selectSpace(int x, int y) {
		
		Space space = gameDirector.getBoard().getSpaces()[x / 64][y / 64];
		
		if(!space.hasChecker())
			return;
		
		if(!gameDirector.getMove().canMove(space.getChecker()))
			return;
		
		selectedSpace = space;
		
		
	}

}
