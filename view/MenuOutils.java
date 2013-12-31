package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
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
 * @version 0.2
 */
public class MenuOutils extends JPanel {
	BoxLayout box1;
	JColorChooser bacacouleur;	
	Model model;
	// JButton
		JButton selection, crayon, rectangle, cercle;
		JButton	noir, rouge, bleu, vert, autres;
		
	/**
	 * Les boutons sont non focusable pour les keylistener fonctionnent
	 * 
	 * @param model Modèle du MVC
	 */
	public MenuOutils(Model model){
		this.model = model;
		initialiser();
		
		// Boutons : Outils
		crayon = new JButton(new ActionOutilTrait(model));
		crayon.setPreferredSize(new Dimension(22,22));
		crayon.setFocusable(false);
		
		rectangle = new JButton(new ActionOutilRectangle(model));
		rectangle.setPreferredSize(new Dimension(22,22));
		rectangle.setFocusable(false);
		
		cercle = new JButton(new ActionOutilEllipse(model));
		cercle.setPreferredSize(new Dimension(22,22));
		cercle.setFocusable(false);
		
		selection = new JButton(new ActionOutilSelection(model));
		selection.setPreferredSize(new Dimension(22,22));
		selection.setFocusable(false);
		
		// Boutons : Couleurs
		noir = new JButton(new ActionOutilCouleur(model, Color.BLACK));
		noir.setBackground(Color.BLACK);
		noir.setPreferredSize(new Dimension(22,22));
		noir.setFocusable(false);
		
		rouge= new JButton(new ActionOutilCouleur(model, Color.RED));
		rouge.setBackground(Color.RED);
		rouge.setPreferredSize(new Dimension(22,22));
		rouge.setFocusable(false);
		
		bleu = new JButton(new ActionOutilCouleur(model, Color.BLUE));
		bleu.setBackground(Color.BLUE);
		bleu.setPreferredSize(new Dimension(22,22));
		bleu.setFocusable(false);
		
		vert = new JButton(new ActionOutilCouleur(model, Color.GREEN));
		vert.setBackground(Color.GREEN);
		vert.setPreferredSize(new Dimension(22,22));
		vert.setFocusable(false);
		
		autres = new JButton("autres");
		//autres.setAction(new ActionAutresCouleur();
		autres.setPreferredSize(new Dimension(22,22));
		autres.setFocusable(false);
		
		
		
		
		
		
		// Ajouts boutons outils
		this.add(selection);
		this.add(crayon);
		this.add(rectangle);
		this.add(cercle);
		// Ajouts boutons couleurs
		this.add(noir);
		this.add(rouge);
		this.add(bleu);
		this.add(vert);
	}
	
	/**
	 * Initialise la taille des boutons à 45x45
	 * @category init
	 */
	public void initialiser() {
		int taille = 45;
		this.setPreferredSize(new Dimension(taille,taille));
        this.setMinimumSize(new Dimension(taille,taille));
        this.setMaximumSize(new Dimension(taille,taille));
	}
}

