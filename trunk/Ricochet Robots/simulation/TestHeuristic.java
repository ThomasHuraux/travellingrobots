package simulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Environment;

public class TestHeuristic {
	
	Sequence steps = new Sequence();
	Environment current = new Environment();
	Heuristic h;
	AStar algo;
	
	public TestHeuristic(int NbTest, int HeuristicID, String export){
		
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
            File f = new File(export+".txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            fw.write("TYPE\tMAXTIME\tSTEPS\tTIME\n");
    		System.out.println("== START TESTS ==");
    		for(int i=0; i<NbTest; i++){
    			current = new Environment();
    			steps = algo.search(current);
    			float timeH = algo.getTimelength();
    			if(steps == null)
    				fw.write(HeuristicID+"\t"+AStar.MAXTIME+"\t-1\t"+AStar.MAXTIME+"\n");
    			else
    				fw.write(HeuristicID+"\t"+AStar.MAXTIME+"\t"+algo.current.getCost()+"\t"+timeH+"\n");
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
