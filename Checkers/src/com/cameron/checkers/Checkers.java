package com.cameron.checkers;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.cameron.checkers.ui.CheckersWindowFrame;

public class Checkers {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
