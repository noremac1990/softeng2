package com.cameron.checkers.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cameron.checkers.game.Board;

public class CheckersWindowPanel extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BoardDrawer boardDrawer;

	public CheckersWindowPanel() {
		try {
			boardDrawer = new BoardDrawer();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to load board images", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		addMouseListener(this);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		boardDrawer.draw(g, Board.getInstance());
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
