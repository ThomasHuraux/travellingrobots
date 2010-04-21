package simulation;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.*;
import model.*;

public class Test {

	public static void main(String[] args){
		
		Environment env = new Environment();
		//RandomMover rm = new RandomMover(100000,env);
		
		Position target = new Position(9,2);
		env.addTarget(null, target);
		
		CountBot bot1 = new CountBot(env, target);
		int[][] tab = bot1.getProximity();
		Count count1 = new Count(env,tab );
		
		//CountBot bot2 = new CountBot(env, env.getPositions().get(0));
		//Count count2 = new Count(env, bot2.getProximity());
		/**
		 * Pour retrouver la matrice avec les distances a la cible
		 * bot.getProximity()
		 */
		
		//Simple view = new Simple(env);
		
		
		//FrequenceColor fq = new FrequenceColor(env);
		
		
		JPanel all = new JPanel();
		//all.add(view);
		all.add(count1);
		//all.add(count2);
		//all.add(fq);
		
		//view.setPreferredSize(new Dimension(40*env.getGrid().getSize(),40*env.getGrid().getSize()));
		//fq.setPreferredSize(new Dimension(40*env.getGrid().getSize(),40*env.getGrid().getSize()));
		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(all);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();

		//rm.start(view,fq);
	}
	
}
