package controler;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.AbstractAction;

import model.Forme;
import model.Model;

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
	private Model model;
	
	public ActionMenuCopier(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Copier");
		putValue(SHORT_DESCRIPTION, "Copie l'objet actuel");
	}

	public void actionPerformed(ActionEvent arg0) {
		ArrayList<Forme> formePending = new ArrayList<Forme>();
		
		Forme f = null;
		ListIterator<Forme> it = model.getAllFormes().listIterator();
		while (it.hasNext()) {
			f = it.next();
			if (f.isSelected()) {
				formePending.add(f);
			}
		}
		this.model.setFormePending(formePending);
	}
}