package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import model.Environment;
import model.Movement;

public class Arrow extends Simple {
	private static final long serialVersionUID = 1L;
	private int[][] number;
		
	public Arrow(Environment env, int[][] number) {
		super(env);
		this.number = number;
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		int size = number[0].length;
		g.setColor(Color.BLUE);
		String symbol = new String();
		for(int x = 0;x<size;x++)	
			for(int y = 0;y<size;y++){
				switch(number[x][y]){
					case Movement.NORTH :
						symbol = "up";
						break;
					case Movement.EAST :
						symbol = "right";
						break;
					case Movement.SOUTH :
						symbol = "down";
						break;
					case Movement.WEST :
						symbol = "left";
						break;
					default :
						symbol = "";
				}
				g.drawImage(Toolkit.getDefaultToolkit().getImage(IMGPATH+symbol+".png"), x*CELLSIZE+10, y*CELLSIZE+10,null);
			}
	}
}
