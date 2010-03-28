package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.Environment;
import model.Ghost;
import model.Position;
import model.Robot;

public class Simple extends JPanel{

	private static final long serialVersionUID = 1L;
	private final static String IMGPATH = "../RicochetRobots/img/";

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
	}
	
	public void update(Environment env){
		this.env = env;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int x = 0; x<size; x++)
        	for(int y = 0; y<size; y++)
        		g.drawImage(cells[x][y],x*40,y*40,null);
        for(Position p : env.getPositions()){
        	Robot r = env.getGrid().getCell(p).getRobot();
        	if(r!=null){
        		Color color = r.getColor();
        		g.setColor(color);
        		g.fillOval(p.getX()*40+10, p.getY()*40+10, 20, 20);
        	} else {
        		g.setColor(Color.LIGHT_GRAY);
        		g.fillOval(p.getX()*40+10, p.getY()*40+10, 20, 20);
        	}
        }
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTabSize() {
		return size;
	}
	
}
