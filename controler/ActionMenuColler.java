package controler;

import java.awt.event.ActionEvent;
import java.util.ListIterator;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

// INTERNE
import model.Forme;
import model.Model;

/**
 * Listener pour le menu Couper. Contient toutes les fonctions de coupage de forme.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionMenuColler extends AbstractAction {
	private Model model;
	
	public ActionMenuColler(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Coller");
		putValue(SHORT_DESCRIPTION, "Colle la forme");
		this.putValue(SMALL_ICON, new ImageIcon("ressources/images/paste.png"));
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (model.getFormePending() != null) {
			ListIterator<Forme> it = model.getFormePending().listIterator();
			while (it.hasNext()) {
				model.addForme(it.next());
			}
		}

		model.setFormePending(null);
	}

}
