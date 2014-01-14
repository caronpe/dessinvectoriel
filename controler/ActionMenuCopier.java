package controler;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
// INTERNE
import ressources.URLIcons;
import model.Forme;
import model.Model;

/**
 * Listener pour le menu Copier. Contient toutes les fonctions de copie de forme.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class ActionMenuCopier extends AbstractAction {
	private Model model;
	
	/**
	 * Paramètre le bouton (valeurs).
	 * 
	 * @param model Modèle du MVC
	 */
	public ActionMenuCopier(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Copier");
		putValue(SHORT_DESCRIPTION, "Copie l'objet actuel");
		this.putValue(SMALL_ICON, new ImageIcon(URLIcons.COPY));
	}

	/**
	 * Parcourt toutes les formes du modèle à la recherche de celles sélectionnées.
	 * S'il en trouve une, il l'ajoute à la zone de pending du modèle.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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