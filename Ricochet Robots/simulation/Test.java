package simulation;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.*;
import model.*;

public class Test {

	public static void main(String[] args){
		
		Environment env = new Environment();
		Robot red, blue, yellow, green;
		env.addRobotArbitrarly(green = new Robot(Color.GREEN));
		env.addRobotArbitrarly(blue = new Robot(Color.BLUE));
		env.addRobotArbitrarly(yellow = new Robot(Color.YELLOW));
		env.addRobotArbitrarly(red = new Robot(Color.RED));
		env.setTagged(red);
		
		Position target = new Position(9,2);
		env.addTarget(red, target);
		
		Set<Position> stops = new HashSet<Position>();
		int[][] tab = null;
		CountBot bot1 = new CountBot(env,env.getState(env.getTagged()).getPosition(), tab);
		stops.addAll(bot1.getStopListe());
		tab = bot1.getProximity();
		System.out.println("stopList " + stops.size());
		
		bot1 = new CountBot(env, env.getState(green).getPosition(), tab);
		stops.addAll(bot1.getStopListe());
		System.out.println("stopList " + stops.size());
		tab =bot1.getProximity();
		
		bot1 = new CountBot(env, env.getState(yellow).getPosition(), tab);
		stops.addAll(bot1.getStopListe());
		System.out.println("stopList " + stops.size());
		tab =bot1.getProximity();
		
		bot1 = new CountBot(env, env.getState(blue).getPosition(), tab);
		stops.addAll(bot1.getStopListe());
		System.out.println("stopList " + stops.size());
		tab =bot1.getProximity();
		
		Count count1 = new Count(env,tab );
		
		JPanel all = new JPanel();
		all.add(count1);
		
		JFrame frame = new JFrame("-Test-");		
		frame.setContentPane(all);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
}
