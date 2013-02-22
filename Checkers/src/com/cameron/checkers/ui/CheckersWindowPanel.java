package com.cameron.checkers.ui;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cameron.checkers.game.Board;

public class CheckersWindowPanel extends JPanel {

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
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		boardDrawer.draw(g, Board.getInstance());
		
	}
}
