package com.cameron.checkers.ui;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class CheckersWindowFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5032226972259567886L;
	

	CheckersWindowPanel mainPanel;

	public CheckersWindowFrame() {
		super("Checkers");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationByPlatform(true);
		

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				CheckersWindowFrame.this.setVisible(false);
				CheckersWindowFrame.this.dispose();
			}
		});

		setupMenu();

		mainPanel = new CheckersWindowPanel();
		
		setContentPane(mainPanel);
		
		pack();
		
		repaint();
	}

	private void setupMenu() {

		JMenuBar menu = new JMenuBar();

		JMenu game = new JMenu("Game");
		menu.add(game);

		JMenuItem newGame = new JMenuItem(new AbstractAction("New Game") {
			private static final long serialVersionUID = 5378747084407111579L;

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckersWindowFrame.this.mainPanel.newGame();
			}
		});
		game.add(newGame);

		JMenuItem exit = new JMenuItem(new AbstractAction("Exit") {

			/**
			 * 
			 */
			private static final long serialVersionUID = -5900715805110715093L;

			@Override
			public void actionPerformed(ActionEvent e) {
				dispatchEvent(new WindowEvent(CheckersWindowFrame.this,
						WindowEvent.WINDOW_CLOSING));
			}
		});
		game.add(exit);

		JMenu help = new JMenu("Help");
		menu.add(help);

		JMenuItem about = new JMenuItem("About");
		help.add(about);

		setJMenuBar(menu);
	}

}
