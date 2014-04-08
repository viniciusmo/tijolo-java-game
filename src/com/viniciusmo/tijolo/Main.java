package com.viniciusmo.tijolo;

import java.awt.Color;

import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private Game game;

	public Main() {
		game = new Game();
		add(game);
		setBackground(new Color(238, 238, 238));
		setSize(570, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setTitle("by viniciusmo");
	}

	public static void main(String[] args) {
		new Main();
	}
}
