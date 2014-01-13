package controler;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import ressources.URLIcons;
//INTERNE
import model.Model;

/**
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionMenuAPropos extends AbstractAction {
	private Model model;
       
	/**
	 * @param model Modèle du MVC
	 */
	public ActionMenuAPropos(Model model) {
		this.model = model;

		// Values
		putValue(NAME, "À propos");
		putValue(SHORT_DESCRIPTION, "À propos du projet");
		this.putValue(SMALL_ICON, new ImageIcon(URLIcons.ABOUT));
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
		// TODO
	}
}

