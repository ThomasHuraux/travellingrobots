package simulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Environment;

public class TestHeuristic {
	
	private static final String PATH = "tests/";
	
	Sequence steps = new Sequence();
	Environment current = new Environment();
	Heuristic h;
	AStar algo;
	
	public TestHeuristic(int NbTest, int HeuristicID, int maxtime, int precalcDepth, double costImportance){
		
		AStar.MAXTIME = maxtime;
		CorridorHeuristic.COSTIMPORTANCE = costImportance;
		CorridorHeuristic.PRECALCDEPTH = precalcDepth;
		
		switch(HeuristicID){
			case Heuristic.CorridorHeuristicID :
				h = CorridorHeuristic.DEFAULT;
				break;
			default:
				h = BruteForce.DEFAULT;
				break;
		}
		
		algo = new AStar(h);
		
		
		try {
            File f = new File(PATH+HeuristicID+"_"+NbTest+"_"+HeuristicID+"_"+AStar.MAXTIME+"_"+precalcDepth+"_"+costImportance+".txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
    		System.out.println("== START TESTS ==");
    		fw.write("NbTests="+NbTest+" HeuristicID="+HeuristicID+" MaxTime="+AStar.MAXTIME+" PrecalcDepth="+precalcDepth+" CostImportance="+costImportance+"\n");
    	    fw.write("STEPS\tTIME\n");
    		for(int i=0; i<NbTest; i++){
    			System.out.println(i+".");
    			current = new Environment();
    			steps = algo.search(current);
    			float timeH = algo.getTimelength();
    			if(steps == null)
    				fw.write("-1\t"+AStar.MAXTIME+"\n");
    			else
    				fw.write(algo.current.getCost()+"\t"+timeH+"\n");
    		}
    		System.out.println("== END TESTS ==");
            fw.flush();
            fw.close();
        } 
		catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}
