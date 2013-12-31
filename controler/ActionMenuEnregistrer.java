package controler;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
//INTERNE
import model.Model;

/**
 * Listener pour le menu Enregistrer. Est appelé lorsqu'on appuie sur le bouton enregistrer.
 * 
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionMenuEnregistrer extends AbstractAction {
	private Model model;
       
	/**
	 * @param model Modèle du MVC
	 */
	public ActionMenuEnregistrer(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Enregistrer");
		putValue(SHORT_DESCRIPTION, "Enregistre votre travail");
	}
	
	/**
	 * Lorsque l'utilisateur clique sur le bouton "Enregistrer" du menu "Fichier".
	 * Vérifie si une adresse est présente dans le modèle. Si c'est le cas, il enregistre le flux
	 * sans intervention de l'utilisateur. Dans le cas contraire, 
	 * il appelle la fonction enregistrer sous de ActionMenuEnregistrerSous.
	 * 
	 * @see controler.ActionMenuEnregistrerSous#enregistrerSous
	 */
	public void actionPerformed(ActionEvent e) {
		if (model.getAdresseEnregistrement() == null) {
			new ActionMenuEnregistrerSous(model).enregistrerSous();
		} else {
			new ActionMenuEnregistrerSous(model).fluxEnregistrement(model.getAdresseEnregistrement());
		}
	}
}



