package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class MenuG extends JPanel{

	JButton crayon;
	JButton	rectangle;
	JButton	cercle;
	JButton rouge;
	JButton bleu;
	JButton vert;
	BoxLayout box1;
	ImageIcon imgrectangle;
	ImageIcon imgcercle;
	ImageIcon imgcrayon;
	JColorChooser bacacouleur;
	
	public MenuG(){
		imgrectangle = new ImageIcon("carre.jpg");
		imgcercle = new ImageIcon("cercle.png");
		imgcrayon = new ImageIcon("crayon.jpg");
		 
		this.setPreferredSize(new Dimension(45,45));
		this.setMinimumSize(new Dimension(45,45));
		this.setMaximumSize(new Dimension(45,45));
		crayon = new JButton(imgcrayon);
		crayon.setPreferredSize(new Dimension(22,22));
		rectangle = new JButton(imgrectangle);
		rectangle.setPreferredSize(new Dimension(22,22));
		cercle = new JButton(imgcercle);
		cercle.setPreferredSize(new Dimension(22,22));
		rouge= new JButton();
		rouge.setBackground(Color.RED);
		rouge.setPreferredSize(new Dimension(22,22));
		bleu= new JButton();
		bleu.setBackground(Color.BLUE);
		bleu.setPreferredSize(new Dimension(22,22));
		vert= new JButton();
		vert.setBackground(Color.GREEN);
		vert.setPreferredSize(new Dimension(22,22));
		
		add(crayon);
		add(rectangle);
		add(cercle);
		add(crayon);
		add(rectangle);
		add(cercle);
	}
}

