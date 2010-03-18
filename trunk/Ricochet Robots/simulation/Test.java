package simulation;

import java.awt.Dimension;

import javax.swing.JFrame;

import view.Simple;
import model.Environment;

public class Test {

	public static void main(String[] args){
		
		Environment env = new Environment();
		//RandomMover rm = new RandomMover(1000,env);
		Simple view = new Simple(env);
		view.setPreferredSize(new Dimension(40*view.getTabSize(),40*view.getTabSize()));
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(view);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
		//rm.start();
	}
	
}
