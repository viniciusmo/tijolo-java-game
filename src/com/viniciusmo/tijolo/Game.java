package com.viniciusmo.tijolo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	private Integer[][] mGame;
	public static final int FND_BRANCO = 0;
	public static final int TIJOLO = 1;
	public static final int OK = 3;
	public static final int TIJOLO3 = 4;
	public static final int CACHORRO = 5;
	public static final int ERROR = 6;
	private LogicGame logic;
	private static final int QUANTIDADE_TIJOLO = 50;
	private int time = 60;

	public Game() {
		setSize(560, 650);
		JOptionPane.showMessageDialog(Game.this,
				"Clique em OK para iniciar o jogo!", "OK",
				JOptionPane.INFORMATION_MESSAGE);
		mGame = new Integer[8][8];
		initGame();
		logic = new LogicGame(mGame, this);
		addMouseListener(logic);
		Gerador gerador = new Gerador();
		Thread th = new Thread(gerador);
		th.start();

	}

	private void initGame() {
		customCursor();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				mGame[i][j] = FND_BRANCO;
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0));

		for (int j = 0; j < 8; j++) {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(
					"/images/" + 2 + ".png"));
			g2.drawImage(ii.getImage(), j * 70, 560, this);
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ImageIcon ii = new ImageIcon(this.getClass().getResource(
						"/images/" + mGame[i][j] + ".png"));
				g2.setFont(new Font("ARIAL", Font.BOLD, 60));
				if (mGame[i][j] == OK) {
					g2.drawString(" " + logic.getScore(), j * 70, i * 70);
				}
				if (mGame[i][j] == ERROR) {
					g2.drawString("-3", j * 70, i * 70);
				}
				g2.drawImage(ii.getImage(), j * 70, i * 70, this);
				g2.setFont(new Font("ARIAL", Font.BOLD, 20));
				g2.setColor(Color.DARK_GRAY);

				g2.drawString("Seus Tijolos : " + logic.getScore(), 10, 600);
				g2.drawString("Quantidade : " + QUANTIDADE_TIJOLO, 200,
						600);
				g2.drawString("Tempo : " + time, 450, 600);
			}
		}
	}

	private class Gerador implements Runnable {

		@Override
		public void run() {
			while (time > 0) {
				Random random = new Random();
				int x = random.nextInt(8);
				int y = random.nextInt(8);
				try {

					if (x == 3 || x <= 5) {
						x = random.nextInt(8);
						y = random.nextInt(8);
						mGame[x][y] = TIJOLO;
					} else if (x <= 1 || y <= 1) {
						x = random.nextInt(8);
						y = random.nextInt(8);
						mGame[x][y] = CACHORRO;
					} else {
						mGame[x][y] = TIJOLO3;
					}
					repaint();
					Thread.sleep(1200);
					mGame[x][y] = FND_BRANCO;
					time--;
					if (logic.getScore() >= QUANTIDADE_TIJOLO) {
						break;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			repaint();
			if (logic.getScore() < QUANTIDADE_TIJOLO) {

				JOptionPane.showMessageDialog(Game.this, "Você Perdeu!", "OK",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(Game.this,
						"Parabéns!! Você ganhou!", "OK",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	private void customCursor() {
		try {
			Toolkit tk = Toolkit.getDefaultToolkit();
			setCursor(tk.createCustomCursor(
					ImageIO.read(this.getClass().getResourceAsStream(
							"/images/glove.png")), new Point(15, 15),
					"MyCursor"));
		} catch (HeadlessException e2) {
			e2.printStackTrace();
		} catch (IndexOutOfBoundsException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

}
