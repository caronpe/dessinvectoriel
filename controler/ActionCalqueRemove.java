package controler;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ressources.URLIcons;
import model.Calque;
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
 * @version 0.4 finale
 */
public class ActionCalqueRemove extends AbstractAction {
	private Model model;
	private CalquePanel calquePanel;
	private Calque calque;

	/**
	 * Paramètre le bouton (valeurs).
	 * 
	 * @param model Modèle du MVC
	 * @param calquePanel Panel de calque contenant tous les calqueView
	 * @param calque Calque associé au calqueView qui va être supprimé
	 */
	public ActionCalqueRemove(CalquePanel calquePanel, Model model, Calque calque) {
		this.model = model;
		this.calquePanel = calquePanel;
		this.calque = calque;

		// Values
		this.putValue(SMALL_ICON, new ImageIcon(URLIcons.DELCALQUE));
		putValue(SHORT_DESCRIPTION, "retire le calque sélectionné");
	}

	/**
	 * Supprime le calque dans le Panel de calqueView et dans le modèle.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int answer = JOptionPane.showConfirmDialog(new JFrame(), "Souhaitez-vous vraiment supprimer ce calque ?", "Supprimer",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (answer == JOptionPane.OK_OPTION) {
			this.calquePanel.removeCalque(calque);
			this.model.delCalque(calque);
		}		
	}
}