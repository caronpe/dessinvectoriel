package controler;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Listener pour le menu Couper. Contient toutes les fonctions de coupage de forme.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.0
 */
public class ActionMenuCouper extends AbstractAction {

	public ActionMenuCouper() {
		putValue(NAME, "Couper");
		putValue(SHORT_DESCRIPTION, "Coupe l'objet actuel");
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
