package controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
// INTERNE
import ressources.URLIcons;
import model.Model;

/**
 * Listener pour le bouton rectangle
 *  
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class ActionOutilRectangle extends AbstractAction implements Observer {
	private Model model;
	private JButton bouton;
	private boolean plein;
	private ImageIcon rectangleVide, rectanglePlein;
	
	/**
	 * Ne comporte pas de nom, autrement
	 * l'affichage ne s'effectuerait pas correctement.
	 * 
	 * @param model Modèle du MVC
	 */
	public ActionOutilRectangle(Model model, JButton bouton) {
		this.model = model;
		this.model.addObserver(this);
		this.bouton = bouton;
		this.plein = false;
		
		// ImageIcon
		this.rectangleVide = new ImageIcon(URLIcons.RECTANGLEVIDE);
		this.rectanglePlein = new ImageIcon(URLIcons.RECTANGLEPLEIN);
		
		// Values
		this.putValue(SHORT_DESCRIPTION, "Sélectionne l'outil rectangle");
		this.putValue(SMALL_ICON, rectangleVide);
	}

	/**
	 * Sélectionne l'outil rectangle dans le modèle
	 */
	public void actionPerformed(ActionEvent e) {
		if (model.getObjetCourant().equals("rectangle")) {
			this.plein = !this.plein;
		}
		
		model.setObjetCourant("rectangle");
		model.setTypeCourant(this.plein);
		model.deselectionnerToutesLesFormes();
 	}
	
	/**
	 * Modifie les bordures du bouton selon la forme sélectionnée dans le modèle.
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// Définition du bouton rectangle comme outil courant
		if (model.getObjetCourant().equals("rectangle")) {
			bouton.setBackground(new Color(220, 220, 220));
			bouton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
		} else {
			bouton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
			bouton.setBackground(Color.WHITE);
		}
		
		// Modification de l'icône en fonction du booléen "plein"
		if (model.getObjetCourant().equals("rectangle") && model.getPleinCourant()) {
			bouton.setIcon(rectanglePlein);
		} else if (model.getObjetCourant().equals("rectangle") && !model.getPleinCourant()) {
			bouton.setIcon(rectangleVide);
		}
	}
}