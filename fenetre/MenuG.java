package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class MenuG extends JPanel{
	BoxLayout box1;
	JColorChooser bacacouleur;
	// JButton
		JButton crayon;
		JButton	rectangle;
		JButton	cercle;
		JButton rouge;
		JButton bleu;
		JButton vert;
	// ImageIcon
	ImageIcon imgrectangle;
	ImageIcon imgcercle;
	ImageIcon imgcrayon;
		
	public MenuG(){
		initialisation();
		
		// Définition de l'adresse de chaque icône
		imgrectangle = new ImageIcon("src_projet/ressources/carre.jpg");
		imgcercle = new ImageIcon("src_projet/ressources/cercle.png");
		imgcrayon = new ImageIcon("src_projet/ressources/crayon.jpg");
		
		// Outils
		crayon = new JButton(imgcrayon);
		crayon.setPreferredSize(new Dimension(22,22));
		rectangle = new JButton(imgrectangle);
		rectangle.setPreferredSize(new Dimension(22,22));
		cercle = new JButton(imgcercle);
		cercle.setPreferredSize(new Dimension(22,22));
		
		// Couleurs
		rouge= new JButton();
		rouge.setBackground(Color.RED);
		rouge.setPreferredSize(new Dimension(22,22));
		bleu= new JButton();
		bleu.setBackground(Color.BLUE);
		bleu.setPreferredSize(new Dimension(22,22));
		vert= new JButton();
		vert.setBackground(Color.GREEN);
		vert.setPreferredSize(new Dimension(22,22));
		
		// Ajouts outils
		add(crayon);
		add(rectangle);
		add(cercle);
		// Ajours couleurs
		add(rouge);
		add(bleu);
		add(vert);
	}
	
	public void initialisation() {
		this.setPreferredSize(new Dimension(45,45));
		this.setMinimumSize(new Dimension(45,45));
		this.setMaximumSize(new Dimension(45,45));
	}
}

