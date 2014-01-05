package controler;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
// INTERNE
import model.Model;
import view.CalquePanel;

/**
 * Listener Bouton "Supprimer calque". Ordonne la suppression d'un calque.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */

public class ActionCalqueRemove extends AbstractAction {
	private Model model;
	private CalquePanel calquePanel;

	public ActionCalqueRemove(CalquePanel calquePanel, Model model) {
		this.model = model;
		this.calquePanel = calquePanel;
		
		this.putValue(SMALL_ICON, new ImageIcon("dessinvectoriel/ressources/removeCalque.png"));
		putValue(SHORT_DESCRIPTION, "retire le calque sélectionné");
	}

	/**
	 * Ajoute un calque vide qui devient le calque courant du modèle.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.calquePanel.removeCalque(model.removeCalque());
	}
}