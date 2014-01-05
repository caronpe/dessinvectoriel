package controler;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
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

public class ActionCalqueAdd extends AbstractAction {
	Model model;

	public ActionCalqueAdd(Model model) {
		this.model = model;
		
		// Values
		this.putValue(NAME, "Nouveau Calque");
		putValue(SHORT_DESCRIPTION, "Ajoute un calque");
	}

	/**
	 *Ajoute un nouveau calque
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.addCalque();
	}
}