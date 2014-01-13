package controler;

import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
 * @version 0.2
 */

public class ActionCalqueRemove extends AbstractAction {
	private Model model;
	private CalquePanel calquePanel;
	private Calque calque;
	private URL urlRemove;

	public ActionCalqueRemove(CalquePanel calquePanel, Model model, Calque calque) {
		this.model = model;
		this.calquePanel = calquePanel;
		this.calque = calque;
		
		// URL
		this.urlRemove = ClassLoader.getSystemClassLoader().getResource("ressources/images/delCalque.png");
		
		this.putValue(SMALL_ICON, new ImageIcon(urlRemove));
		putValue(SHORT_DESCRIPTION, "retire le calque sélectionné");
	}

	/**
	 * Ajoute un calque vide qui devient le calque courant du modèle.
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