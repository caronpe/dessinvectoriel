package controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
// INTERNE
import model.*;

/**
 * Gère la définition de la couleur par défaut du modèle 
 * lors du clic sur un bouton de couleur.
 * Est créé lors de la création de chaque bouton de couleur
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @see view/FenetrePrincipale
 * 
 * @version 0.1
 */
public class ActionCouleur extends AbstractAction {
	Color couleur_actuelle;
	Model model;
	
	/**
	 * Assigne la couleur du bouton selon la couleur reçue en paramètre.
	 * Est lié à la fenêtre principale lors de la création des boutons.
	 * 
	 * 
	 * @param model Modèle du MVC
	 * @param couleur Couleur que l'on veut assigner au bouton
	 * 
	 * @see view/FenetrePrincipale
	 */
	public ActionCouleur(Model model, Color color) {
		this.model = model;
		this.couleur_actuelle = color;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		this.model.setColor(couleur_actuelle);
	}
}