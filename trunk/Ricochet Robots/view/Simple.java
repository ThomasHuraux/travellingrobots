package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.Environment;
import model.Position;

public class Simple extends JPanel{

	private static final long serialVersionUID = 1L;
	private final static String IMGPATH = "../RicochetRobots/img/";

	private Image[][] cells;
	private int size;
	
	public Simple(Environment env){
		setSize(env. getGrid().getSize());
		cells = new Image[size][size];
		
		for(int x = 0; x<size; x++)
        	for(int y = 0; y<size; y++)
        		cells[x][y] = Toolkit.getDefaultToolkit().getImage(IMGPATH+env.getGrid().getCell(new Position(x,y)).getType()+".jpg");
	
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        for(int x = 0; x<size; x++)
        	for(int y = 0; y<size; y++)
        		g.drawImage(cells[x][y],x*40,y*40,null);
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTabSize() {
		return size;
	}
	
}
