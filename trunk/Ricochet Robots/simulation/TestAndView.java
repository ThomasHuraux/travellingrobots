package simulation;

import javax.swing.JFrame;

import view.SolutionView;

public class TestAndView {
	
	public static void main(String[] args){
		
		TestHeuristic test = new TestHeuristic(100,Heuristic.CorridorHeuristicID,30,15,0.0);
	
		JFrame solved = new JFrame("-Solved-");		
		solved.setContentPane(new SolutionView(((Node)test.getSolved().get(0)).getEnvironment(),test.getSolved()));
		solved.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		solved.setVisible(true);
		solved.pack();
		
		JFrame unsolved = new JFrame("-Unsolved-");		
		unsolved.setContentPane(new SolutionView(((Node)test.getUnsolved().get(0)).getEnvironment(),test.getUnsolved()));
		unsolved.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		unsolved.setVisible(true);
		unsolved.pack();
	}

}
