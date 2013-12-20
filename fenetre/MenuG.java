package fenetre;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controler.ActionCouleur;
// INTERNE
import model.*;

public class MenuG extends JPanel{
	BoxLayout box1;
	JColorChooser bacacouleur;
	Model model;
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
		
	public MenuG(Model model){
		this.model = model;
		initialiser();
		
		// Images
		imgrectangle = new ImageIcon("src_projet/ressources/carre.jpg");
		imgcercle = new ImageIcon("src_projet/ressources/cercle.png");
		imgcrayon = new ImageIcon("src_projet/ressources/crayon.jpg");
		
		// Boutons : Outils
		crayon = new JButton(imgcrayon);
		crayon.setPreferredSize(new Dimension(22,22));
		rectangle = new JButton(imgrectangle);
		rectangle.setPreferredSize(new Dimension(22,22));
		cercle = new JButton(imgcercle);
		cercle.setPreferredSize(new Dimension(22,22));
		
		// Boutons : Couleurs
		rouge= new JButton(new ActionCouleur(model, "rouge"));
		rouge.setBackground(Color.RED);
		rouge.setPreferredSize(new Dimension(22,22));
		bleu= new JButton(new ActionCouleur(model, "bleu"));
		bleu.setBackground(Color.BLUE);
		bleu.setPreferredSize(new Dimension(22,22));
		vert= new JButton(new ActionCouleur(model, "vert"));
		vert.setBackground(Color.GREEN);
		vert.setPreferredSize(new Dimension(22,22));
		
		// Ajouts boutons outils
		this.add(crayon);
		this.add(rectangle);
		this.add(cercle);
		// Ajouts boutons couleurs
		this.add(rouge);
		this.add(bleu);
		this.add(vert);
	}
	
	public void initialiser() {
		this.setPreferredSize(new Dimension(45,45));
		this.setMinimumSize(new Dimension(45,45));
		this.setMaximumSize(new Dimension(45,45));
	}
}

