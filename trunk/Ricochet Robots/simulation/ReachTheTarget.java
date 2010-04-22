package simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import model.CountBot;
import model.Environment;
import model.Robot;
import model.State;

public class ReachTheTarget extends MotionPlanner {

	/**
	 * Une carte contient des cases.
	 */
	private class Map {
		
		/**
		 * Une case contient des doublons <<Robot,poids>>
		 */
		private class Case {
			private Hashtable<Robot,Integer> robots;

			public Case(Collection<Robot> c) { 
				for (Robot robot : c) 
					this.robots.put(robot, 0);
			}
			
			public int getWeight(Robot robot) {
				return this.robots.get(robot);
			}
			
			public void setCase(Robot robot, int weight) {
				this.robots.put(robot, weight);
			}
		}
		
		private Case[][] map;
		
		public Map(int dim, Collection<Robot> c) {
			this.map = new Case[dim][dim];
			
			for (int i = 0; i < dim; i++)
				for (int j = 0; j < dim; j++)
					this.map[i][j] = new Case(c);
		}
		
		public Case[][] getMap() {
			return this.map;
		}

		public void setCase(int i, int j, Robot robot, int weight) {
			this.map[i][j].setCase(robot, weight);
		}
		
		public void setMap(int[][] map, Robot robot) {
			for (int i = 0; i < map.length; i++)
				for (int j = 0; j < map[0].length; j++)
					this.setCase(i, j, robot, map[i][j]);
		}
	}

	
	private Hashtable<Robot,Sequence> sequences;
	private Environment env;
	private static ReachTheTarget instance = null;
	private Map map;
	
	public ReachTheTarget() {}
	
	private ReachTheTarget(Environment env) {
		this.sequences = new Hashtable<Robot, Sequence>();
		this.env = env;
		this.map = new Map(env.getGrid().getSize(), getRobotsFromEnvironment());
	}

	private ArrayList<Robot> getRobotsFromEnvironment() {
		ArrayList<Robot> list = new ArrayList<Robot>();
		for (State s : env.getStates()) {
			list.add(s.getRobot());
		}
		return list;
	}
	
	/**
	 * Recherche de la cible a partir de chacun des robots.
	 * La recherche s'effectue en avant, en laissant l'environnement
	 * dans l'etat initial.
	 * A la fin, le plateau est pondere avec le nombre de coups
	 * minimum qu'un robot doit effectue pour l'atteindre.
	 */
	private void reachTheTarget() {
		CountBot bot;
		int[][] tab;
		
		for (State s : env.getStates()) {
			bot = new CountBot(env, s.getPosition());
			tab = bot.getProximity();
			this.map.setMap(tab, s.getRobot());
		}
	}

	/**
	 * A partir de la carte engendree par reachTheTarget(),
	 * il faut chercher quelle combinaison de coups
	 * permet d'atteindre la cible de facon optimale.
	 */
	private void captureTheRoad() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Sequence search(Environment env) {
		if (instance == null)
			instance = new ReachTheTarget(env);
		
		instance.reachTheTarget();
		instance.captureTheRoad();
		
		return null;
	}
	
}
