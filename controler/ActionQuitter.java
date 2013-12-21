package controler;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
// INTERNE
import model.*;

/**
 * @author fabien
 *
 */
public class ActionQuitter extends AbstractAction implements WindowListener {
	private Model model;
	
	public ActionQuitter(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Quitter");
		putValue(SHORT_DESCRIPTION, "Quitte le logiciel");
	}
	
	/**
	 * Vérifie si l'utilisateur a enregistré ou non puis quitte
	 * lorsque l'utilisateur clique sur "Quitter" dans le menu "Fichier"
	 */
	public void actionPerformed(ActionEvent e) {
		if (model.getEnregistre()) {
			quitter_enregistre();
		} else {
			quitter_non_enregistre();
		}
	}
	
	/**
	 * Vérifie si l'utilisateur a enregistré ou non puis quitte
	 * lorsque l'utilisateur clique sur la croix
	 */
	public void windowClosing(WindowEvent arg0) {
		if (model.getEnregistre()) {
			quitter_enregistre();
		} else {
			quitter_non_enregistre();
		}
	}
	
	private void quitter_enregistre() {
		Object[] options = {"Quitter", "Annuler"};
		int n = JOptionPane.showOptionDialog(new JFrame(), "Souhaitez-vous vraiment quitter ?", "Quitter", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		// Si on sélectionne l'option oui
		if ( n == 0 ) {
			System.exit(0);
		}
	}
	
	/**
	 * Ouvre une fenêtre de dialogue si l'utilisateur n'a pas enregistré
	 */
	private void quitter_non_enregistre() {
		// DialogBox
		Object[] options = {"Enregistrer", "Ne pas enregistrer", "Annuler"};
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"Souhaitez-vous vraiment quitter sans enregistrer ?",
				"Quitter",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		
		
		if ( n == 0 ) { // Si "Enregistrer"
			new ActionEnregistrer(model).enregistrer();
		} else if ( n == 1 ) {
			System.exit(0);
		}
	}

	/**
	 * @category inutile
	 */
	public void windowActivated(WindowEvent arg0) {
	}
	/**
	 * @category inutile
	 */
	public void windowClosed(WindowEvent arg0) {
	}
	/**
	 * @category inutile
	 */
	public void windowDeactivated(WindowEvent arg0) {
	}
	/**
	 * @category inutile
	 */
	public void windowDeiconified(WindowEvent arg0) {	
	}
	/**
	 * @category inutile
	 */
	public void windowIconified(WindowEvent arg0) {	
	}
	/**
	 * @category inutile
	 */
	public void windowOpened(WindowEvent arg0) {	
	}
}
