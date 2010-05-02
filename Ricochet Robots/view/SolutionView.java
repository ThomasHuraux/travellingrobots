package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Environment;

import simulation.Node;
import simulation.Sequence;

public class SolutionView extends JPanel{

	private static final long serialVersionUID = 1L;
	
	Environment current;
	int currentI;
	Simple a;
	Sequence steps;

	public SolutionView(Environment e, Sequence s){
		
		current = e;
		currentI = 0;
		a = new Simple(current);
		steps = s;
		
		setLayout(new BorderLayout());
		add(a,BorderLayout.CENTER);
		
		JPanel buttons = new JPanel();
		
		final JButton initialN = new JButton("INITIAL");
		final JButton previous = new JButton("PREVIOUS");
		final JButton next = new JButton("NEXT");
		final JButton finalN = new JButton("FINAL");
		
		previous.setEnabled(false);
		
		initialN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				previous(true);
				previous.setEnabled(false);
				next.setEnabled(true);
			}
		});

		previous.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if( ! previous(false))
					previous.setEnabled(false);
				else 
					next.setEnabled(true);
			}
		});
		
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if( ! next(false))
					next.setEnabled(false);
				else
					previous.setEnabled(true);
			}
		});
		
		finalN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				next(true);
				next.setEnabled(false);
				previous.setEnabled(true);
			}
		});
		
		buttons.add(initialN);
		buttons.add(previous);
		buttons.add(next);
		buttons.add(finalN);
		
		add(buttons,BorderLayout.SOUTH);
		
	}
	
	public boolean next(boolean wantFinal){
		if(steps!=null && !steps.isEmpty() && currentI<steps.size()-1){
			if(! wantFinal)
				currentI++;
			else
				currentI = steps.size()-1;
			current = ((Node)steps.get(currentI)).getEnvironment();
			refresh();
			return true;
		}else return false; 
	}
	
	public boolean previous(boolean wantFinal){
		if(steps!=null && !steps.isEmpty() && currentI>0){
			if(! wantFinal)
				currentI--;
			else
				currentI = 0;
			current = ((Node)steps.get(currentI)).getEnvironment();
			refresh();
			return true;
		}else return false;
	}
	
	public void refresh(){
		remove(a);
		a = new Simple(current);
		add(a,BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}
