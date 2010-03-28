package view;

import java.awt.Color;
import java.awt.Graphics;

import model.Environment;

public class Count extends Simple{

	private static final long serialVersionUID = 1L;
	private int[][] number;
		
	public Count(Environment env, int[][] number) {
		super(env);
		this.number = number;
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		int size = number[0].length;
		g.setColor(Color.BLUE);
		for(int x = 0;x<size;x++)	
			for(int y = 0;y<size;y++)
				if(number[x][y] != Integer.MAX_VALUE)
					g.drawString(number[x][y]+"", x*40+15, y*40+22);
	}
}