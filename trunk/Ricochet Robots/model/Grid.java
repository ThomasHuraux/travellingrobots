package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Grid {
	
	public final static boolean DEBUG = false;
	
	private int size;
	private Cell[][] tab;
	
	public Grid(int size){
		this.size = size;
		tab = new Cell[size][size];
	}
	
	public Grid(String filename){
		tab = importGrid(filename);
	}
	
	private Cell[][] importGrid(String filename){
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String current = br.readLine();
			size = Integer.parseInt(current); 
			Cell[][] ret = new Cell[size][size];
			int y = 0;
			current = br.readLine();
			do{
					String[] tabChar = current.split("\t");
	    			for(int x=0;x<size;x++){
	    				Cell c = new Cell(true);
						switch(Integer.parseInt(String.valueOf(tabChar[x]))){
							case 0: c = new Cell(true);
								break;
							case 1: c = new Cell(false,true,true,true);
								break;
							case 2: c = new Cell(true,false,true,true);
								break;
							case 3: c = new Cell(true,true,false,true);
								break;
							case 4: c = new Cell(true,true,true,false);
								break;
							case 5: c = new Cell(false,true,true,false);
								break;
							case 6: c = new Cell(false,false,true,true);								
								break;
							case 7: c = new Cell(true,false,false,true);
								break;
							case 8: c = new Cell(true,true,false,false);
								break;
							case 9: c = new Cell(false,true,false,true);
								break;
							case 10: c = new Cell(true,false,true,false);
								break;
							case 11: c = new Cell(false);
								break;
						}
						c.setType(Integer.parseInt(String.valueOf(tabChar[x])));
						if(DEBUG) System.out.print(c.getType()+"\t");
						ret[x][y] = c;
	    			}
	    			current = br.readLine();
	    			y++;
	    			if(DEBUG)System.out.println();
			 }while(current != null);
			 br.close();
			 
			 /////// 
			 System.out.println("Import <<"+filename+">> (Size="+getSize()+")");
			 
			 return ret;
        } 
		catch (IOException ex) {
            ex.printStackTrace();
        }
		return null;
	}
	
	public Cell getCell(Position pos){
		if(pos.x >= 0 && pos.x<size && pos.y >= 0 && pos.y<size)
			return tab[pos.x][pos.y];
		return null;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Cell[][] getTab() {
		return tab;
	}

	public void setTab(Cell[][] tab) {
		this.tab = tab;
	}

}
