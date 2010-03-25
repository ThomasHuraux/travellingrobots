package simulation;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.*;
import model.Environment;

public class Test {

	public static void main(String[] args){
		
		Environment env = new Environment();
		RandomMover rm = new RandomMover(10000000,env);
		
		Simple view = new Simple(env);
		Count count = new Count(env);
		
		JPanel all = new JPanel();
		all.add(view);
		all.add(count);
		
		view.setPreferredSize(new Dimension(40*env.getGrid().getSize(),40*env.getGrid().getSize()));
		count.setPreferredSize(new Dimension(42*env.getGrid().getSize(),40*env.getGrid().getSize()));
		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(all);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();

		rm.start(view,count);
	}
	
}
