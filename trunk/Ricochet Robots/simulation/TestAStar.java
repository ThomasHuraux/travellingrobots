package simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		
		
		
		//***********************************************
		
		
		AStar algo = new AStar();
		algo.init(current);
		//algo.preCalc(current);
		steps = algo.search(current);
		
		int[][] distToTarget = algo.getHeuristic();
		int[][] dirToTarget = algo.getDirToTarget();
		
		
		//***********************************************
		
		//Count c = new Count(current,distToTarget);
		//Arrow a = new Arrow(env,dirToTarget);
		
		//Environment clone = env.clone();
		//clone.getStates().get(0).getRobot().moveSouth(clone);
		
		//Simple c = new Simple(env);
		
		
		all.setLayout(new BorderLayout());
		all.add(a,BorderLayout.CENTER);
		//all.add(c);
		
		
		
		final JButton next = new JButton("NEXT");
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if( ! next())
					next.setEnabled(false);
			}
		});
		
		all.add(next,BorderLayout.SOUTH);
		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(all);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
	}
	
	public static boolean next(){
		if(steps!=null && ! steps.isEmpty()){
			//System.out.println(AStar.getStringRepresentation(current));
			current = (Environment)steps.remove(0);
			all.remove(a);
			a = new Simple(current);
			all.add(a,BorderLayout.CENTER);
			all.revalidate();
			all.repaint();
			return true;
		}else return false; 
	}
	
}
