package simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import model.CountBot;
import model.Environment;
import model.Position;
import model.Robot;


public class ReachTheTarget extends MotionPlanner {

	private Hashtable<Robot,Sequence> sequences; 
	private Position target;
	private Robot tagged;
	private Environment env;
	private static ReachTheTarget instance = null;
	
	public ReachTheTarget() {}
	
	private ReachTheTarget(Environment env) {
		this.sequences = new Hashtable<Robot, Sequence>();
		this.env = env;
		this.target = env.getTarget();
		this.tagged = env.getTaggedRobot();
	}

	/**
	 * Recherche sequentielle de la cible a partir de tous les robots
	 * pour ponderer le plateau.
	 */
	private void reachTheTarget() {
		ArrayList<CountBot> bots = new ArrayList<CountBot>();

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
