package simulation;

import java.util.ArrayList;
import java.util.List;
import model.CountBot;
import model.Environment;
import model.Position;

public class ReachTheTarget implements Heuristic {
	
	static final ReachTheTarget DEFAULT = new ReachTheTarget();

	private int reachTheTarget(Node open) {
		Environment env = open.getEnvironment();
		Position init = open.getEnvironment().getStates().get(0).getPosition();
		CountBot bot = new CountBot(env ,env.getStates().get(0).getPosition());
		
		int min = Integer.MAX_VALUE;
		int tmp;
		
		List<Position> stops = new ArrayList<Position>();
		CountBot botmp;
		
		for (int i = 1; i < 3; i++) {
			stops.addAll((botmp = new CountBot(env, env.getStates().get(i).getPosition())).getStopListe());
			stops = captureTheRoad(bot.getStopListe(), stops);
			
			for (Position pos : stops) {
				tmp = bot.getProximity()[init.getX()][init.getY()]
				      + botmp.getProximity()[pos.getX()][pos.getY()];
				
				if (tmp < min)
					min = tmp;
			}
		}
		
		return min;
	}

	/**
	 * A partir de la carte engendree par reachTheTarget(),
	 * il faut chercher quelle combinaison de coups
	 * permet d'atteindre la cible de facon optimale.
	 */
	private List<Position> captureTheRoad(List<Position> l1, List<Position> l2) {
		List<Position> liste = new ArrayList<Position>();
		
		for (Position p1 : l1)
			for (Position p2 : l2)
				if (p1.compare(p2))
					liste.add(p1);
		
		return liste;
	}
	
	@Override
	public Node best(Node current, ArrayList<Node> open) {

		int min = Integer.MAX_VALUE;
		int tmp;
		int minId = 0;

		for (int i=0;i<open.size();i++) {
			if (min > (tmp = open.get(i).getHeuristic())){
				min = tmp;
				minId = i;
			}
		}
		
		return open.get(minId);
	}

	@Override
	public int getNbITER() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void preCalc(Node n) {}

	@Override
	public void setHeuristic(Node n) {
		CountBot bot = new CountBot(n.getEnvironment(),n.getEnvironment().getState(n.getEnvironment().getTagged()).getPosition());
		int min = bot.getProximity()[n.getEnvironment().getTarget().getX()][n.getEnvironment().getTarget().getY()];
		if (min == Integer.MAX_VALUE)
			min = reachTheTarget(n);
		n.setHeuristic(min);
	}
	
}
