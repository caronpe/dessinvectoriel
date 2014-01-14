package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Model;
import controler.ActionOutilCouleurs;
import controler.ActionOutilEllipse;
import controler.ActionOutilRectangle;
import controler.ActionOutilSelection;
import controler.StrokeListener;
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
		JLabel outilsStroke, labelStroke;
	// JTextField
		JTextField strokeField;
		StrokeListener actionStroke;
	/**
	 * Les boutons sont non focusable pour les keylistener fonctionnent
	 * 
	 * @param model Modèle du MVC
	 */
	public MenuOutils(Model model, StrokeListener actionStroke){
		this.model = model;
		this.actionStroke = actionStroke;
		initialiserPanel();
		initialiserBoutons();
	}
	
	private void initialiserBoutons() {
		Font f = new Font("Serif", Font.PLAIN, 10);
		
		// Outils
		outilsStroke= new JLabel("Outils");
		outilsStroke.setHorizontalAlignment(JLabel.CENTER);
		outilsStroke.setFont(f);
		outilsStroke.setPreferredSize(new Dimension(45,10));
		
		
		crayon = new JButton();
		ActionOutilTrait actionTrait = new ActionOutilTrait(model, crayon);
		crayon.setAction(actionTrait);
		crayon.setPreferredSize(new Dimension(35,22));
		crayon.setFocusable(false);
		
		rectangle = new JButton();
		ActionOutilRectangle rectangleAction = new ActionOutilRectangle(model, rectangle);
		rectangle.setAction(rectangleAction);
		rectangle.setPreferredSize(new Dimension(35,22));
		rectangle.setFocusable(false);
		
		ellipse = new JButton();
		ActionOutilEllipse ellipseAction = new ActionOutilEllipse(model, ellipse);
		ellipse.setAction(ellipseAction);
		ellipse.setPreferredSize(new Dimension(35,22));
		ellipse.setFocusable(false);
		
		selection = new JButton();
		ActionOutilSelection selectionAction = new ActionOutilSelection(model, selection);
		selection.setAction(selectionAction);
		selection.setPreferredSize(new Dimension(35,22));
		selection.setFocusable(false);
		
		// Boutons : Couleurs
		selectionCouleur = new JButton(new ActionOutilCouleurs(model));
		selectionCouleur.setBackground(model.getColor());
		selectionCouleur.setPreferredSize(new Dimension(35,22));
		selectionCouleur.setFocusable(false);

		// TextField
		labelStroke= new JLabel("Épaisseur");
		labelStroke.setHorizontalAlignment(JLabel.CENTER);
		labelStroke.setFont(f);
		labelStroke.setPreferredSize(new Dimension(45,10));
		
		strokeField=new JTextField(Float.toString(model.getStrokeFloat()));
		strokeField.setPreferredSize(new Dimension(35,22));
		strokeField.setMaximumSize(new Dimension(35,22));
		strokeField.setMinimumSize(new Dimension(35,22));
		actionStroke.setStrokeField(strokeField);
		strokeField.addKeyListener(actionStroke);
		strokeField.addMouseListener(actionStroke);
		strokeField.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Outils
		this.add(outilsStroke);
		this.add(selection);
		this.add(crayon);
		this.add(rectangle);
		this.add(ellipse);
		this.add(selectionCouleur);
		
		// Stroke
		this.add(labelStroke);
		this.add(strokeField);
	}
	
	/**
	 * Initialise la taille du panel à 45x45
	 * @category init
	 */
	public void initialiserPanel() {
		int width = 60, height = 45;
		this.setPreferredSize(new Dimension(width,height));
        this.setMinimumSize(new Dimension(width,height));
        this.setMaximumSize(new Dimension(width,height));
	}
	
	public JTextField getStrokeField() {
		return this.strokeField;
	}
}


