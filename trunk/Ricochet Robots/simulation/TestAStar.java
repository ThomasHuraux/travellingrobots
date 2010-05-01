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
	static Simple a = new Simple(current);
	static JPanel all = new JPanel();

	public static void main(String[] args){
		
		Heuristic h =  CorridorHeuristic.DEFAULT;
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
		
		all.setLayout(new BorderLayout());
		all.add(a,BorderLayout.CENTER);
		
		JPanel buttons = new JPanel();
		
		final JButton next = new JButton("NEXT");
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if( ! next(false))
					next.setEnabled(false);
			}
		});
		
		final JButton finalN = new JButton("FINAL");
		finalN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				next(true);
				next.setEnabled(false);
			}
		});
		
		buttons.add(next);
		buttons.add(finalN);
		
		all.add(buttons,BorderLayout.SOUTH);
		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(all);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
	}
	
	public static boolean next(boolean wantFinal){
		if(steps!=null && ! steps.isEmpty()){
			//System.out.println(AStar.getStringRepresentation(current));
			if(! wantFinal)
				current = ((Node)steps.remove(0)).getEnvironment();
			else
				current = ((Node)steps.get(steps.size()-1)).getEnvironment();
			all.remove(a);
			a = new Simple(current);
			all.add(a,BorderLayout.CENTER);
			all.revalidate();
			all.repaint();
			return true;
		}else return false; 
	}
	
}
