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
 * @version 0.4 finale
 */
public class ActionCalqueAdd extends AbstractAction {
	Model model;

	/**
	 * Paramètre le bouton (valeurs).
	 * 
	 * @param model Modèle du MVC
	 */
	public ActionCalqueAdd(Model model) {
		this.model = model;
		
		// Values
		this.putValue(NAME, "Nouveau Calque");
		putValue(SHORT_DESCRIPTION, "Ajoute un nouveau calque");
	}

	/**
	 * Ajoute un nouveau calque dans le modèle.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.addCalque();
	}
}