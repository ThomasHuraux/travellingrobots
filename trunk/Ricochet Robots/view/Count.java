package view;

import java.awt.Color;

import java.awt.Graphics;


import javax.swing.JPanel;

import model.Environment;
import model.Position;


public class Count extends JPanel{

	private static final long serialVersionUID = 1L;
	private int MAX;
	private int[][] cells;
	private Color[][] cellsColor;
	private int size;

	
	public Count(Environment env){
		size = env.getGrid().getSize();
		cells = new int[size][size];
		cellsColor = new Color[size][size];
		 for(int x = 0; x<size; x++)
	        	for(int y = 0; y<size; y++)
	        		cellsColor[x][y] = new Color(0,0,0);
		MAX = 1;
		repaint();
	}
	
	public void increase(Position pos){
		cells[pos.getX()][pos.getY()]++;
		if(cells[pos.getX()][pos.getY()]>MAX)
			MAX = cells[pos.getX()][pos.getY()];
		if( cellsColor[pos.getX()][pos.getY()].getBlue() < 255)
			cellsColor[pos.getX()][pos.getY()] = new Color(0,0,cellsColor[pos.getX()][pos.getY()].getBlue()+1);
		else if( cellsColor[pos.getX()][pos.getY()].getGreen() < 255)
			cellsColor[pos.getX()][pos.getY()] = new Color(0,cellsColor[pos.getX()][pos.getY()].getGreen()+1,255);
		else if( cellsColor[pos.getX()][pos.getY()].getRed() < 255)
			cellsColor[pos.getX()][pos.getY()] = new Color(cellsColor[pos.getX()][pos.getY()].getRed()+1,255,255);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int x = 0; x<size; x++)
        	for(int y = 0; y<size; y++){
        		g.setColor(cellsColor[x][y]);
        		g.fillRect((x*38),y*38,38,38);
        	}
        // Legende :
        g.setColor(Color.BLACK);
        g.drawString("Legende (du moins visite au plus visite) :", 40,39*size+2);
        for(int i=0;i<765;i+=2){
        	if(i<255) g.setColor(new Color(0,0,i));
        	else if(i<510) g.setColor(new Color(0,i-255,255));
        	else g.setColor(new Color(i-510,255,255));
	        g.fillRect(38+(i/2), 39*size+7, 1, 5);
        }
        g.setColor(Color.BLACK);
        g.drawRect(37, 39*size+6, 384, 6);
	}

	public String toStringCells() {
		String ret = new String();
		for(int x = 0; x<size; x++){
        	for(int y = 0; y<size; y++){
        		ret += cells[x][y]+"\t";
        	}
        	ret += "\n";
		}
		return ret;
	}
}
