package controler;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
// INTERNE
import model.*;

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
	
	/**
	 * Ne comporte pas de nom, autrement
	 * l'affichage ne s'effectuerait pas correctement
	 * @param model Modèle du MVC
	 */
	public ActionOutilTrait(Model model) {
		this.model = model;
		
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
	}
}