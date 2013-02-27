package com.cameron.checkers;

import javax.swing.SwingUtilities;

import com.cameron.checkers.ui.CheckersWindowFrame;

public class Checkers {

	public static void main(String[] args) {
		
		// TODO sad
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				CheckersWindowFrame window = new CheckersWindowFrame();
				
				window.setVisible(true);
			}
		});
		return;
	}

}
