package controler;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
// INTERNE
import model.Model;

/**
 * Listener pour le bouton Nouveau.
 * Est appelé lorsqu'on appuie sur le bouton Nouveau.
 * Supprime tous les éléments de l'arrayList de formes dans le modèle
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionMenuNouveau extends AbstractAction {
	private Model model;
	
	/**
	 * @param model Modèle du MVC
	 */
	public ActionMenuNouveau(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Nouveau");
		putValue(SHORT_DESCRIPTION, "Crée un nouveau fichier");
	}
	
	/**
	 * Ouvre une fenêtre de dialogue si l'utilisateur n'a pas enregistré
	 * Puis, sauf si l'utilisateur annule, crée un nouveau fichier.
	 */
	public void nouveau_non_enregistre() {
		// DialogBox
		Object[] options = {"Enregistrer", "Ne pas enregistrer", "Annuler"};
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"Souhaitez-vous vraiment créer un nouveau fichier sans enregistrer ?",
				"Quitter", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		
		if ( n == 0 ) { // Si Enregistrer
			new ActionMenuEnregistrerSous(model).enregistrerSous();
			nouveau();	
		} else if (n == 1) { // Si Ne pas enregistrer
			nouveau();
		}
	}
	
	/**
	 * Vérifie si l'utilisateur a enregistré ou non
	 * puis ouvre un nouveau fichier.
	 */
	public void actionPerformed(ActionEvent e) {
		if (model.getEnregistre()) {
			nouveau();
		} else {
			nouveau_non_enregistre();
		}
	}
	
	/**
	 * Supprime tous les éléments de la liste de dessins du modèle.
	 */
	public void nouveau() {
		model.delAllFormes();
	}
}
