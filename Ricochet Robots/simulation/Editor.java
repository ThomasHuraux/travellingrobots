package simulation;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.stream.*;
import javax.imageio.*;


public class Editor extends JPanel implements MouseListener{
	
	private final static int HDEFAULT = 16;
	private final static String NAMEDEFAULT = "UNNAMED";
	private final static String IMGPATH = "../RicochetRobots/img/";

	private static final long serialVersionUID = 1L;
	protected static int h;
	protected static String mapName;
	protected static int[][] table;	
	protected static JButton[][] buttons;
	

	
	public Editor(){
		h = HDEFAULT;
		mapName = NAMEDEFAULT;
		
		table = new int[h][h];
		
		table[0][0] = 5;
		table[h-1][0] = 8;
		table[0][h-1] = 6;
		table[h-1][h-1] = 7;
		
		for(int l=1; l<h-1; l++){
			table[l][0] = 4;
			table[0][l] = 1;
			table[h-1][l] = 3;
			table[l][h-1] = 2;
		}
		
		table[h/2][h/2] =
		table[h/2-1][h/2-1] = 
		table[h/2][h/2-1] =
		table[h/2-1][h/2] = 11;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}	
	
	public static void refresh(){
		for(int i=0; i<h; i++){
			for(int j=0; j<h; j++){
				buttons[i][j].setIcon(new ImageIcon(IMGPATH+table[i][j]+".jpg"));
			}
		}	
	} 
	
	public static void main(String[] args){
	
		final Editor ed = new Editor();
		final JFrame frame = new JFrame("-Editor-");
		JPanel principal = new JPanel();
		
		principal.setLayout(new BorderLayout());
		
		JMenuBar barre = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		JMenu information = new JMenu("?");
		
		ed.setLayout(new GridLayout(getH(),getH()));
		buttons = new JButton[getH()][getH()];
		for(int i=0; i<h; i++){
			for(int j=0; j<h; j++){
				final int X = i;
				final int Y = j;
				JButton button = new JButton();

				button.addMouseListener(new MouseListener(){
					public void mouseClicked(MouseEvent e) {
						if(e.getButton()== 1)table[X][Y]++;
						if(e.getButton()== 3)table[X][Y]--;
						if(table[X][Y] == 12 || e.getButton()==2)
							table[X][Y] = 0;
						if(table[X][Y] == -1)
							table[X][Y] = 11;
						buttons[X][Y].setIcon(new ImageIcon(IMGPATH+table[X][Y]+".jpg"));
						ed.repaint();
					}
					public void mouseEntered(MouseEvent arg0) {}
					public void mouseExited(MouseEvent arg0) {}
					public void mousePressed(MouseEvent arg0) {}
					public void mouseReleased(MouseEvent arg0) {}
				});	
				buttons[X][Y] = button;
				ed.add(button);
			}
		}
		
		refresh();
		
		JMenuItem  nouv = new JMenuItem("Nouveau");
		nouv.addActionListener(new ActionListener(){
			public void actionPerformed( ActionEvent e){
				table = new int[h][h];
				for(int i=0; i<h; i++)
					for(int j=0; j<h; j++)
						table[i][j] = 0;
				refresh();
				frame.repaint();
			}
		});	
		JMenuItem  save = new JMenuItem("Sauvegarder");
		save.addActionListener(new ActionListener(){
			public void actionPerformed( ActionEvent e){
				JFileChooser chooser = new JFileChooser();
				String fileName = new String();
				chooser.setApproveButtonText("Sauvegarder"); 
				chooser.showOpenDialog(null); 
				fileName = chooser.getSelectedFile().getAbsolutePath(); 
				if(fileName != ""){
					saveMap(fileName);
					BufferedImage tamponSauvegarde = new BufferedImage(ed.getSize().width,ed.getSize().height, BufferedImage.TYPE_3BYTE_BGR); 
					Graphics g = tamponSauvegarde.getGraphics();
					ed.paint(g); 
					
					try{
						ImageIO.write(tamponSauvegarde, "JPG", new FileImageOutputStream(new File(chooser.getSelectedFile().getAbsolutePath()+".jpeg")));
					}catch(Exception ex){}
				}
			}
		});	
		
		JMenuItem  info = new JMenuItem("A propos de ...");
		info.addActionListener(new ActionListener(){
			public void actionPerformed( ActionEvent e){
				JOptionPane.showMessageDialog(
				    frame,
				    "Editeur pour Ricochet Robots\nM1 Informatique\nAuteurs : \n         HURAUX Thomas\n         LABEDAN Christophe"
				);
			}
		});	
		
		fichier.add(nouv);
		fichier.add(save);

		
		information.add(info);
		
		barre.add(fichier);
		barre.add(information);
		
		principal.add(barre,BorderLayout.NORTH);
		principal.add(ed,BorderLayout.CENTER);
		
		principal.add(new JLabel("   Clic gauche = TYPE CASE +  ;  Clic droit = TYPE CASE -  ;  Clic central = CASE VIDE "),BorderLayout.SOUTH);
		principal.add(new JLabel("  "),BorderLayout.EAST);
		principal.add(new JLabel("  "),BorderLayout.WEST);
		
		ed.setPreferredSize(new Dimension(40*getH(),40*getH()));
		frame.setContentPane(principal);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
	
	
	public static void saveMap(String nameFile){
		try {
            File f = new File(nameFile+".txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            fw.write(h+"\n");
            for(int x=0;x<h;x++){
    			for(int y=0;y<h;y++){
    				fw.write(table[x][y]+"\t");
    			}
    			fw.write("\n");
    		}
            fw.flush();
            fw.close();
        } 
		catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	public static void displayTable(){
		System.out.println("\nMap <"+mapName+">\n");
		for(int i=0; i<h; i++){
			for(int j=0; j<h; j++){
				System.out.print(table[j][i]);
			}
			System.out.println();
		}	
	}
	
	public static int getH(){
		return h;
	}

	public static String getMapName(){
		return mapName;
	}

	public static int[][] getTable(){
		return table;
	}
	
	public static void setH(int a){
		h = a;
	}

	public static void setMapName(String a){
		mapName = a;
	}
	
	public void mouseClicked(MouseEvent e) {}
	//Invoked when the mouse has been clicked on a component.

	public void mousePressed(MouseEvent e) {}
	//Invoked when a mouse button has been pressed on a component.

	public void mouseReleased(MouseEvent e) {}
	//Invoked when a mouse button has been released on a component.

	public void mouseEntered(MouseEvent e) {}
	//Invoked when the mouse enters a component.

	public void mouseExited(MouseEvent e) {}
	//Invoked when the mouse exits a component
		
}
