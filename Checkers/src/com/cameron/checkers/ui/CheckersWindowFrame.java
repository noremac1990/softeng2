package com.cameron.checkers.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CheckersWindowFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CheckersWindowPanel mainPanel;

	public CheckersWindowFrame() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationByPlatform(true);
		setSize(800, 600);
		setTitle("Checkers");
		setVisible(true);
		
		mainPanel = new CheckersWindowPanel();
		
		add(mainPanel);
		
		//pack();
		setupMenu();
	}
	
	private class MenuExitAction implements ActionListener {

		CheckersWindowFrame parent;
		
		MenuExitAction(CheckersWindowFrame parent) {
			this.parent = parent;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			parent.close();
		}
		
	}
	
	private class MenuAboutAction implements ActionListener {
		CheckersWindowFrame parent;
		
		MenuAboutAction(CheckersWindowFrame parent) {
			this.parent = parent;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog aboutDialog = new JDialog(parent, true);
			
			aboutDialog.setSize(300, 200);
			aboutDialog.setResizable(false);
			aboutDialog.setVisible(true);

		}
		
	}

	private void setupMenu() {
		
		JMenuBar menu = new JMenuBar();
		
		JMenu game = new JMenu("Game");
		menu.add(game);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new MenuExitAction(this));
		game.add(exit);
		
		JMenu help = new JMenu("Help");
		menu.add(help);
		
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new MenuAboutAction(this));
		help.add(about);
		
		setJMenuBar(menu);
	}
	
	protected void close() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
