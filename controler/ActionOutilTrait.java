package controler;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;

// INTERNE
import model.Model;

/**
 * Listener pour le bouton trait.
 *  
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionOutilTrait extends AbstractAction {
	private Model model;
	private JButton bouton;
	
	/**
	 * Ne comporte pas de nom, autrement
	 * l'affichage ne s'effectuerait pas correctement
	 * @param model Modèle du MVC
	 */
	public ActionOutilTrait(Model model, JButton bouton) {
		this.model = model;
		this.bouton = bouton;
		
		// Values
		this.putValue(SHORT_DESCRIPTION, "Sélectionne l'outil trait");
		this.putValue(SMALL_ICON, new ImageIcon("dessinvectoriel/ressources/crayon.jpg"));
	}

	/**
	 * Sélectionne l'outil trait dans le modèle.
	 */
	public void actionPerformed(ActionEvent e) {
		model.setObjetCourant("trait");
		model.deselectionnerToutesLesFormes();
		bouton.setBackground(Color.GRAY);
	}
}