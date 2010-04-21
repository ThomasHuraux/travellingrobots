package simulation;

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
		algo.search(env);
		
		int[][] distToTarget = algo.getHeuristic();
		int[][] dirToTarget = algo.getDirToTarget();
		
		
		//***********************************************
		
		//Count c = new Count(env,distToTarget);
		//Arrow a = new Arrow(env,dirToTarget);
		
		//Environment clone = env.clone();
		//clone.getStates().get(0).getRobot().moveSouth(clone);
		
		//Simple c = new Simple(env);
		Simple a = new Simple(env);
		
		JPanel all = new JPanel();
		all.add(a);
		//all.add(a);
		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(all);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
	}
	
}
