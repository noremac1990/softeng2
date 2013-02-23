package com.cameron.checkers.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cameron.checkers.game.GameDirector;
import com.cameron.checkers.game.spaces.Space;

public class CheckersWindowPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BoardDrawer boardDrawer;
	private GameDirector gameDirector;
	private Space selectedSpace;

	public CheckersWindowPanel() {
		try {
			boardDrawer = new BoardDrawer();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to load board images", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		gameDirector = new GameDirector();
		
		addMouseListener(this);		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		boardDrawer.draw(g, gameDirector.getBoard());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(selectedSpace == null) {
			selectSpace(e.getX(), e.getY());
		} else {
			tryMove(e.getX(), e.getY());
			selectedSpace = null;
		}
		
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
		
		selectedSpace = space;
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
