package simulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Environment;

public class TestHeuristic {
	
	private static final String PATH = "tests/";
	
	
	Sequence unsolved = new Sequence();
	Sequence solved = new Sequence();
	
	Sequence steps = new Sequence();
	Environment current = new Environment();
	Heuristic h;
	AStar algo;
	
	public TestHeuristic(int NbTest, int HeuristicID, int maxtime, int precalcDepth, double costImportance){
		
		AStar.MAXTIME = maxtime;
		CorridorHeuristic.COSTIMPORTANCE = costImportance;
		CorridorHeuristic.PRECALCDEPTH = precalcDepth;
		ReachTheTarget.PRECALCDEPTH = precalcDepth;
		

		
		switch(HeuristicID){
			case Heuristic.CorridorHeuristicID :
				h = CorridorHeuristic.DEFAULT;
				break;
			case Heuristic.ReachTheTargetID :
				h = ReachTheTarget.DEFAULT;
				break;
			default:
				h = BruteForce.DEFAULT;
				break;
		}
		
		algo = new AStar(h);
		
		
		try {
            File f = new File(PATH+HeuristicID+"_"+NbTest+"_"+AStar.MAXTIME+"_"+precalcDepth+"_"+costImportance+".txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
    		System.out.println("== START TESTS ==");
    		fw.write("NbTests="+NbTest+" HeuristicID="+HeuristicID+" MaxTime="+AStar.MAXTIME+" PrecalcDepth="+precalcDepth+" CostImportance="+costImportance+"\n");
    	    fw.write("STEPS\tTIME\tR0\tR1\tR2\tR3\tTARGET\n");
    		for(int i=0; i<NbTest; i++){
    			System.out.println(i+".\t");
    			current = new Environment();
    			steps = algo.search(current);
    			float timeH = algo.getTimelength();
    			if(steps == null){
    				unsolved.add(algo.close.get(0));
    				steps = new Sequence();
    				steps.add(algo.close.get(0));
    				fw.write("-1\t"+AStar.MAXTIME);
    			}else{
    				solved.add(steps.get(0));
    				fw.write(algo.current.getCost()+"\t"+timeH);
    			}
    			fw.write("\t"+((Node)steps.get(0)).getEnvironment().getStates().get(0).getPosition().toString()+"\t");
    			fw.write(((Node)steps.get(0)).getEnvironment().getStates().get(1).getPosition().toString()+"\t");
    			fw.write(((Node)steps.get(0)).getEnvironment().getStates().get(2).getPosition().toString()+"\t");
    			fw.write(((Node)steps.get(0)).getEnvironment().getStates().get(3).getPosition().toString()+"\t");
    			fw.write(current.getTarget().toString()+"\n");
    		}

    		System.out.println("\n== END TESTS ("+unsolved.size()+" unsolved) ==");
            fw.flush();
            fw.close();
        } 
		catch (IOException ex) {
            ex.printStackTrace();
        }
	}

	public Sequence getUnsolved() {
		return unsolved;
	}

	public Sequence getSolved() {
		return solved;
	}

}
