package controler;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import ressources.URLIcons;
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
public class ActionMenuCouper extends AbstractAction {
	private Model model;
	
	public ActionMenuCouper(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Couper");
		putValue(SHORT_DESCRIPTION, "Coupe l'objet actuel");
		this.putValue(SMALL_ICON, new ImageIcon(URLIcons.CUT));
	}
	
	public void actionPerformed(ActionEvent arg0) {
		ArrayList<Forme> formePending = new ArrayList<Forme>();
		
		Forme f = null;
		ListIterator<Forme> it = model.getCalqueCourant().listIterator();
		while (it.hasNext()) {
			f = it.next();
			if (f.isSelected()) {
				formePending.add(f);
				it.remove();
			}
		}
		this.model.setFormePending(formePending);
	}

}
