package simulation;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.*;
import view.*;


public class TestAStar {

	public static void main(String[] args){
		
		Environment env = new Environment();
		
		//***********************************************
		
		
		AStar algo = new AStar();
		algo.init(env);
		//algo.preCalc(env);
		
		int[][] distToTarget = algo.getHeuristic();
		int[][] dirToTarget = algo.getDirToTarget();
		
		
		//***********************************************
		
		Count c = new Count(env,distToTarget);
		Arrow a = new Arrow(env,dirToTarget);
		
		JPanel all = new JPanel();
		all.add(c);
		all.add(a);
		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(all);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
	}
	
}
