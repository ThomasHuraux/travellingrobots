package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.*;


public class Simple extends JPanel{

	private static final long serialVersionUID = 1L;
	protected final static String IMGPATH = "../RicochetRobots/img/";
	protected final static int CELLSIZE = 40;
	

	private Image[][] cells;
	private Environment env;
	private int size;
	
	public Simple(Environment env){
		this.env = env;
		setSize(env. getGrid().getSize());
		cells = new Image[size][size];	
		for(int y = 0; y<size; y++)
        	for(int x = 0; x<size; x++)
        		cells[x][y] = Toolkit.getDefaultToolkit().getImage(IMGPATH+env.getGrid().getCell(new Position(x,y)).getType()+".jpg");
        repaint();
        
        setPreferredSize(new Dimension(CELLSIZE*env.getGrid().getSize(),CELLSIZE*env.getGrid().getSize()));
	}
	
	public void update(Environment env){
		this.env = env;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int x = 0; x<size; x++)
        	for(int y = 0; y<size; y++)
        		g.drawImage(cells[x][y],x*CELLSIZE,y*CELLSIZE,null);
//        boolean firstGhost = true;
        for(State s : env.getStates()){
        	Robot r = s.getRobot();
        	if(!(r instanceof Ghost)){
	        	Position p = s.getPosition();
	        	Color color = r.getColor();
	        	g.setColor(color);
	        	g.fillOval(p.getX()*CELLSIZE+10, p.getY()*CELLSIZE+10, 20, 20);
        	}
        }
        g.setColor(Color.DARK_GRAY);
        g.fillRect(env.getTarget().getX()*CELLSIZE+5, env.getTarget().getY()*CELLSIZE+15, 25, 5);
        g.fillRect(env.getTarget().getX()*CELLSIZE+15, env.getTarget().getY()*CELLSIZE+5, 5, 25);
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTabSize() {
		return size;
	}
	
}
