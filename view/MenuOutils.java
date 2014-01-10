package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Model;
import controler.ActionOutilCouleurs;
import controler.ActionOutilEllipse;
import controler.ActionOutilRectangle;
import controler.ActionOutilSelection;
import controler.ActionOutilStroke;
import controler.ActionOutilTrait;
// INTERNE

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
	Model model;
	// JButton
		JButton selection, crayon, rectangle, ellipse, selectionCouleur;
	//JLabel
		JLabel labelStroke;
	// JTextField
		JTextField textStroke;
	/**
	 * Les boutons sont non focusable pour les keylistener fonctionnent
	 * 
	 * @param model Modèle du MVC
	 */
	public MenuOutils(Model model){
		this.model = model;
		initialiserBoutons();
		initialiserPanel();
	}
	
	private void initialiserBoutons() {
		crayon = new JButton();
		ActionOutilTrait actionTrait = new ActionOutilTrait(model, crayon);
		crayon.setAction(actionTrait);
		crayon.setPreferredSize(new Dimension(22,22));
		crayon.setFocusable(false);
		
		rectangle = new JButton();
		ActionOutilRectangle rectangleAction = new ActionOutilRectangle(model, rectangle);
		rectangle.setAction(rectangleAction);
		rectangle.setPreferredSize(new Dimension(22,22));
		rectangle.setFocusable(false);
		
		ellipse = new JButton();
		ActionOutilEllipse ellipseAction = new ActionOutilEllipse(model, ellipse);
		ellipse.setAction(ellipseAction);
		ellipse.setPreferredSize(new Dimension(22,22));
		ellipse.setFocusable(false);
		
		selection = new JButton();
		ActionOutilSelection selectionAction = new ActionOutilSelection(model, selection);
		selection.setAction(selectionAction);
		selection.setPreferredSize(new Dimension(22,22));
		selection.setFocusable(false);
		
		// Boutons : Couleurs
		selectionCouleur = new JButton(new ActionOutilCouleurs(model));
		selectionCouleur.setBackground(model.getColor());
		selectionCouleur.setPreferredSize(new Dimension(22,22));
		selectionCouleur.setFocusable(false);
		
		//Ajout LabelStroke
		
		labelStroke= new JLabel("Ep");
		labelStroke.setPreferredSize(new Dimension(22,22));

		//Ajout Texte Field
		textStroke=new JTextField();
		textStroke.addKeyListener(new ActionOutilStroke(model,textStroke));
		
		// Ajouts boutons outils
		this.add(selection);
		this.add(crayon);
		this.add(rectangle);
		this.add(ellipse);
		
		// Ajouts boutons couleurs
		this.add(selectionCouleur);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,1));
		
		// Ajout JLabel
		panel.add(labelStroke);
		
		// Ajout JTextField
		panel.add(textStroke);
	
		this.add(panel);
	}
	
	/**
	 * Initialise la taille du panel à 45x45
	 * @category init
	 */
	public void initialiserPanel() {
		int taille = 45;
		this.setPreferredSize(new Dimension(taille,taille));
        this.setMinimumSize(new Dimension(taille,taille));
        this.setMaximumSize(new Dimension(taille,taille));
	}
}


