package controler;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
// INTERNE
import ressources.JColorChooserUnique;
import model.Model;

/**
 * Listener pour le bouton des couleurs.
 * Gère la définition de la couleur du modèle 
 * lors du clic sur le bouton de couleur.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.3
 */
public class ActionOutilCouleurs extends AbstractAction {
	private JColorChooserUnique color;
	
	public ActionOutilCouleurs(Model model) {
		this.color = new JColorChooserUnique(model);
	}

	/**
	 * Affiche le ColorChooser maison.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		color.afficher();
	}
}
