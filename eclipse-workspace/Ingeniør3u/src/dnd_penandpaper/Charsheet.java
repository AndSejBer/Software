package dnd_penandpaper;

import java.awt.*;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class Charsheet extends JPanel implements Runnable {

	private ADM parent;
	private int current;
	protected int maxHP, currentHP, att, charisma, intelligence;

	public Charsheet(ADM p, int num) {
		current = num;
		parent = p;
	}

	public void run() {
		drawStuff();
		parent.sheetP.add(new JButton(parent.player.get(current).getName()));
	}
	
	public void paintComponent(Graphics g) {
		run();
	}

	public void drawStuff() {
		Graphics g = getGraphics();
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawString("Characther sheet for player: " + parent.players.get(current) + "\r\n", getWidth()/100*10, getHeight()/100*10);
		g.drawString("" + maxHP + " / " + currentHP, getWidth()/100*10, getHeight()/100*10+15);
	}
}
