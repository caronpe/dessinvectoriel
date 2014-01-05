package controler;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
// INTERNE
import model.Model;

/**
 * Listener Bouton "Ajouter calque". Ordonne la création d'un calque.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */

public class ActionMenuType extends AbstractAction {
	Model model;

	public ActionMenuType(Model model) {
		this.model = model;
		
		// Values
		this.putValue(NAME, "Forme pleine");
		putValue(SHORT_DESCRIPTION, "Ajoute un calque");
	}

	/**
	 *Ajoute un nouveau calque
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (model.getTypeCourant().equals("vide")) {
			model.setTypeCourant("plein");
		} else {
			model.setTypeCourant("vide");
		}
	}
}