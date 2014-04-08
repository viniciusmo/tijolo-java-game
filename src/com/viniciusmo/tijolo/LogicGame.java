package com.viniciusmo.tijolo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class LogicGame extends MouseAdapter {
	private JPanel panel;
	private Integer[][] mGame;
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public LogicGame(Integer[][] mGame, JPanel panel) {
		this.mGame = mGame;
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int i = e.getY() / 70;
		int j = e.getX() / 70;
		if (i <= 7 && j <= 7) {

			switch (mGame[i][j]) {
			case Game.TIJOLO:
				mGame[i][j] = Game.OK;
				score++;
				break;
			case Game.TIJOLO3:
				mGame[i][j] = Game.OK;
				score += 3;
				break;
			case Game.CACHORRO:
				mGame[i][j] = Game.ERROR;
				score -= 3;
				break;

			}

		}

		panel.repaint();
	}

}
