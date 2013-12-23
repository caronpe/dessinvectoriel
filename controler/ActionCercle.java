package controler;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
// INTERNE
import model.*;

/**
 * Listener pour le bouton cercle
 *  
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.1
 */
public class ActionCercle extends AbstractAction {
	private Model model;
	
	/**
	 * Ne comporte pas de nom, autrement
	 * l'affichage ne s'effectuerait pas correctement
	 * @param model Modèle du MVC
	 */
	public ActionCercle(Model model) {
		this.model = model;
		
		// Values
		this.putValue(SHORT_DESCRIPTION, "Sélectionne l'outil cercle");
		this.putValue(SMALL_ICON, new ImageIcon("dessinvectoriel/ressources/cercle.png"));
	}

	
	public void actionPerformed(ActionEvent e) {
		model.setObjetCourant("cercle");
	}
}


