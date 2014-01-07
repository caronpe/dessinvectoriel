package controler;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// INTERNE
import model.*;

/**
 * Listener pour le menu Quitter. Contient toutes les fonctions pour quitter le programme.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionMenuQuitter extends AbstractAction implements WindowListener {
	private Model model;
	
	public ActionMenuQuitter(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Quitter");
		putValue(SHORT_DESCRIPTION, "Quitte le logiciel");
		this.putValue(SMALL_ICON, new ImageIcon("ressources/images/quit.png"));
	}
	
	/**
	 * Vérifie si l'utilisateur a enregistré ou non puis quitte
	 * lorsque l'utilisateur clique sur "Quitter" dans le menu "Fichier" et
	 * éxecute la méthode appropriée.
	 * 
	 * @see #quitter_non_enregistre
	 * @see #quitter_enregistre
	 * 
	 */
	public void quitter() {
		if (model.getEnregistre()) {
			quitter_enregistre();
		} else {
			quitter_non_enregistre();
		}
	}
	
	/**
	 * Vérifie si l'utilisateur a enregistré ou non puis quitte
	 * lorsque l'utilisateur clique sur le bouton Quitter du menu Fichier
	 */
	public void actionPerformed(ActionEvent e) {
		quitter();
	}
	
	/**
	 * Vérifie si l'utilisateur a enregistré ou non puis quitte
	 * lorsque l'utilisateur clique sur la croix.
	 */
	public void windowClosing(WindowEvent arg0) {
		quitter();
	}
	
	/**
	 * Si l'utilisateur a enregistré. Affiche une fenêtre de dialogue.
	 */
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
	 * Ouvre une fenêtre de dialogue si l'utilisateur n'a pas enregistré.
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
			new ActionMenuEnregistrerSous(model).enregistrerSous();
		} else if ( n == 1 ) {
			System.exit(0);
		}
	}

	/**
	 * @category unused
	 * @deprecated
	 */
	public void windowActivated(WindowEvent arg0) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void windowClosed(WindowEvent arg0) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void windowDeactivated(WindowEvent arg0) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void windowDeiconified(WindowEvent arg0) {	
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void windowIconified(WindowEvent arg0) {	
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void windowOpened(WindowEvent arg0) {	
	}
}
