package controler;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
// INTERNE
import ressources.URLIcons;
import model.Calque;
import model.Model;

/**
 * Régis les actions du bouton afficher/masquer des calques.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class ActionCalqueAfficher extends AbstractAction implements Observer{
	private Model model;
	private Calque calque;
	private JButton bouton;
	
	/**
	 * Paramètre le bouton (valeurs).
	 * 
	 * @param model Modèle du MVC
	 * @param calque Calque qui sera manipulé dans le modèle pour l'afficher ou non
	 * @param bouton Bouton dont on va modifier l'icône selon l'état du calque associé
	 */
	public ActionCalqueAfficher(Model model, Calque calque, JButton bouton){
		this.model = model;
		this.model.addObserver(this);
		this.bouton = bouton;
		this.calque = calque;
		
		// Values
		this.putValue(SHORT_DESCRIPTION, "Affiche/Masque le calque");
		this.putValue(SMALL_ICON, new ImageIcon(URLIcons.OPENED_EYE));
	}
	
	/**
	 * Affiche ou masque un calque dans le modèle.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.setAfficherCalque(this.calque);
	}

	/**
	 * Met à jour l'image en fonction de l'état du calque dans le modèle (affiché ou masqué).
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (calque.getAfficher() == true) {
			bouton.setIcon(new ImageIcon(URLIcons.OPENED_EYE));
		} else if(calque.getAfficher() == false) {
			bouton.setIcon(new ImageIcon(URLIcons.CLOSED_EYE));
		}
	}
}