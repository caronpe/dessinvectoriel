package controler;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
//INTERNE
import model.Model;
import ressources.*;

/**
 * Listener pour le menu Enregistrer. Est appelé lorsqu'on appuie sur le bouton enregistrer mais aussi
 * lorsque l'on ferme la fenêtre.
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
	 * Récupère l'extension de fichier définie dans le modèle.
	 * 
	 * @param model Modèle du MVC
	 */
	public ActionMenuEnregistrer(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Enregistrer");
		putValue(SHORT_DESCRIPTION, "Enregistre votre travail");
	}
	
	/**
	 * Si l'adresse du fichier existe dans le modèle, il ne fait qu'enregistrer,
	 * autrement il fait appel à la méthode d'enregistrement de ActionMenuEnregistrerSous.
	 * 
	 * @see controler/ActionMenuEnregisterSous#enregistrer
	 * 
	 */
	public void enregistrer() {
		if (model.getAdresseEnregistrement() == null) {
			new ActionMenuEnregistrerSous(model).enregistrer();
		} else {
			new ActionMenuEnregistrerSous(model).fluxEnregistrement(model.getAdresseEnregistrement());
		}
	}
	
	/**
	 * Lorsque l'utilisateur clique sur le bouton "Enregistrer" du menu "Fichier"
	 */
	public void actionPerformed(ActionEvent e) {
		enregistrer();
	}
}



