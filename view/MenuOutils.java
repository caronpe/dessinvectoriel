package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
// INTERNE
import model.*;
import controler.*;

/**
 * Menu de gauche contenant tous les outils de couleur,
 * de type et d'objet pour les formes déssinées.
 * Il contient pour le moment : quelques couleurs fonctionnelles,
 * 3 formes dont seule la droite fonctionne.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.1
 */
public class MenuOutils extends JPanel{
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
		
	/**
	 * @param model Modèle du MVC
	 */
	public MenuOutils(Model model){
		this.model = model;
		initialiser();
		
		// Boutons : Outils
		crayon = new JButton(new ActionTrait(model));
		crayon.setPreferredSize(new Dimension(22,22));
		rectangle = new JButton(new ActionRectangle(model));
		rectangle.setPreferredSize(new Dimension(22,22));
		cercle = new JButton(new ActionCercle(model));
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
	
	/**
	 * Initialise la taille des boutons à 45x45
	 * @category init
	 */
	public void initialiser() {
		this.setPreferredSize(new Dimension(45,45));
		this.setMinimumSize(new Dimension(45,45));
		this.setMaximumSize(new Dimension(45,45));
	}
}
