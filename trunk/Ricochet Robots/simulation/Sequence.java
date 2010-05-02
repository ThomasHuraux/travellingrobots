package simulation;

import java.util.ArrayList;

public class Sequence extends ArrayList<Object>{

	private static final long serialVersionUID = 1L;

	public Sequence(Node[] tab){
		for(Node n : tab){
			add(n);
		}
	}
	
	public Sequence() {
		// TODO Auto-generated constructor stub
	}

	public Sequence organize() {

		Node current = (Node)get(size()-1);
		Node[] s = new Node[current.getCost()+1];
		
		do{
			s[current.getCost()] = current;
			current = current.getPrevious();
		}while(current != null);
		
		return new Sequence(s);
	}

}
