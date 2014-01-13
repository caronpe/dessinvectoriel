package controler;

import java.awt.event.ActionEvent;
import java.net.URL;

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
public class ActionMenuHelp extends AbstractAction {
	private Model model;
       
	/**
	 * @param model Modèle du MVC
	 */
	public ActionMenuHelp(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Aide");
		putValue(SHORT_DESCRIPTION, "Besoin d'aide ?");
		this.putValue(SMALL_ICON, new ImageIcon(URLIcons.HELP));
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

