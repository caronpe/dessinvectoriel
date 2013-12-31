package controler;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Listener pour le menu Copier. Contient toutes les fonctions de copie de forme.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionMenuCopier extends AbstractAction {
	
	public ActionMenuCopier() {
		putValue(NAME, "Copier");
		putValue(SHORT_DESCRIPTION, "Copie l'objet actuel");
	}

	public void actionPerformed(ActionEvent arg0) {

	}
}