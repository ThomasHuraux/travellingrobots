package simulation;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.SolutionView;

import model.Environment;
import model.Position;
import model.Robot;

public class TestReachTheTarget {

	static Sequence steps = new Sequence();
	static Environment current = new Environment();
	static JPanel all = new JPanel();
	
	public static void main(String[] args) {
		AStar algo = new AStar(ReachTheTarget.DEFAULT);
		
		//steps = algo.search(current);
		TestHeuristic tests = new TestHeuristic(25,Heuristic.ReachTheTargetID,60,10,0.0);
		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(new SolutionView(current,steps));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

}
