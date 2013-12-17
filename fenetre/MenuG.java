package fenetre;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuG extends JPanel{

	
	JButton	stylo;
	JButton	rectangle;
	JButton	cercle;
	JButton	ligne;
	JButton crayon;
	
	
	
	public MenuG(){
		ImageIcon imgrectangle = new ImageIcon("carre.jpg");
		ImageIcon imgcercle = new ImageIcon("cercle.jpg");
		ImageIcon imgcrayon = new ImageIcon("crayon.jpg");

		crayon=new JButton(imgcrayon);
		crayon.setPreferredSize(new Dimension(29,29));
	
		rectangle=new JButton(imgrectangle);
		rectangle.setPreferredSize(new Dimension(29,29));

		cercle=new JButton(imgcercle);
		cercle.setPreferredSize(new Dimension(29,29));


		GridLayout grid = new GridLayout(2,3);
		setLayout(grid);
		add(crayon,grid);
		add(rectangle,grid);
		add(cercle,grid);
		
		//this.setMaximumSize(new Dimension(29*2,5*29));
	}

	/* MAIN D'ALEX */
	public static void  main (String args[]){
		JFrame fram = new JFrame();
		JPanel menuG = new MenuG();
		fram.add(BorderLayout.WEST,menuG);
		
		fram.pack();
		fram.setVisible(true);
	}
}

