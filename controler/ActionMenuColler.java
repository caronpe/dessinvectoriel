package controler;

import java.awt.event.ActionEvent;
import java.util.ListIterator;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
// INTERNE
import ressources.URLIcons;
import model.Forme;
import model.Model;

/**
 * Listener pour le menu Coller. Contient toutes les fonctions de collage de forme.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class ActionMenuColler extends AbstractAction {
	private Model model;
	
	/**
	 * Paramètre le bouton (valeurs).
	 * 
	 * @param model Modèle du MVC
	 */
	public ActionMenuColler(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Coller");
		putValue(SHORT_DESCRIPTION, "Colle la forme sur le calque courant");
		this.putValue(SMALL_ICON, new ImageIcon(URLIcons.PASTE));
	}
	
	/** 
	 * Parcours la zone de pending du modèle à la recherche de forme à ajouter
	 * au calque courant du modèle.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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
