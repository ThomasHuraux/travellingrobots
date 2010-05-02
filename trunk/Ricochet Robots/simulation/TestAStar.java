package simulation;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.*;
import view.*;


public class TestAStar {
	
	static Sequence steps = new Sequence();
	static Environment current = new Environment();

	static JPanel all = new JPanel();

	public static void main(String[] args){
		
		CorridorHeuristic h =  CorridorHeuristic.DEFAULT;
		AStar algo = new AStar(h);
		//AStar brutF = new AStar(BruteForce.DEFAULT);
		
		float timeH = 0;
		float timeBrut = 0;
		
//		for(int i=0; i<25; i++){
//			current = new Environment();
//			steps = algo.search(current);
//			timeH += algo.getTimelength();
//			
///*			current = new Environment();
//			steps = brutF.search(current);
//			timeBrut += brutF.getTimelength();*/
//		}
//		System.out.print("\n ==========\nBrutForce:"+timeBrut+"\nWith Heuristic:"+timeH);

		steps = algo.search(current);

		//algo.init(current);
		int[][] tabH = h.getHeuristic();

		//Count c = new Count(current,tabH);
		
		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(new SolutionView(current,steps));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
	}
	
}
