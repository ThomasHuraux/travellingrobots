package simulation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import model.CountBot;
import model.Environment;
import model.Position;

public class ReachTheTarget implements Heuristic {

	static final ReachTheTarget DEFAULT = new ReachTheTarget();
	public static int PRECALCDEPTH = 10;

	private int reachTheTarget(Environment open, int[][] initProx, Set<Position> initStops) {
		
		int min = Integer.MAX_VALUE;
		int tmp; int[][] prox = null;
		Set<Position> stops = new HashSet<Position>();
		CountBot botmp;

		for (int i = 1; i < 4; i++) {
			stops.addAll((botmp = new CountBot(open, open.getStates().get(1).getPosition(), prox)).getStopListe());
			prox = botmp.getProximity();
		}
		
		stops.retainAll(initStops);
		
		for (Position pos : stops) {
			tmp = initProx[pos.getX()][pos.getY()]
			      + prox[pos.getX()][pos.getY()];

			if (tmp < min)
				min = tmp;
		}

		return min;
	}

	@Override
	public Node best(Node current, ArrayList<Node> open) {
		System.gc();
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
	public void preCalc(Node n) {
		CountBot.setMAXGENERATION(ReachTheTarget.PRECALCDEPTH);
	}

	@Override
	public void setHeuristic(Node n) {
		CountBot bot = new CountBot(n.getEnvironment(),n.getEnvironment().getState(n.getEnvironment().getTagged()).getPosition(), null);

		int min = bot.getProximity()[n.getEnvironment().getTarget().getX()][n.getEnvironment().getTarget().getY()];
		if (min == Integer.MAX_VALUE)
			min = reachTheTarget(n.getEnvironment(), bot.getProximity(), bot.getStopListe());

		n.setHeuristic(min);
	}

}
