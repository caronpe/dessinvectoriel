package controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
// INTERNE
import model.Model;
import ressources.URLIcons;

/**
 * Listener pour le bouton cercle.
 *  
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class ActionOutilEllipse extends AbstractAction  implements Observer {
	private Model model;
	private JButton bouton;
	private boolean plein;
	private ImageIcon urlCercleVide, urlCerclePlein;
	
	/**
	 * Ne comporte pas de nom, autrement
	 * l'affichage ne s'effectuerait pas correctement
	 * 
	 * @param model Modèle du MVC
	 */
	public ActionOutilEllipse(Model model, JButton bouton) {
		this.model = model;
		this.model.addObserver(this);
		this.bouton = bouton;
		this.plein = false;
		
		// ImageIcon
		this.urlCercleVide = new ImageIcon(URLIcons.CERCLEVIDE);
		this.urlCerclePlein = new ImageIcon(URLIcons.CERCLEPLEIN);
		
		// Values
		this.putValue(SHORT_DESCRIPTION, "Sélectionne l'outil cercle");
		this.putValue(SMALL_ICON, urlCercleVide);
	}
	
	/**
	 * Sélectionne l'outil cercle dans le modèle
	 */
	public void actionPerformed(ActionEvent e) {
		if (model.getObjetCourant().equals("ellipse")) {
			this.plein = !this.plein;
		}
		
		model.setObjetCourant("ellipse");
		model.setTypeCourant(this.plein);
		model.deselectionnerToutesLesFormes();
	}

	/**
	 * Met à jour l'icône du bouton en fonction du type du modèle (vide/plein).
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// Définition du bouton rectangle comme outil courant
		if (model.getObjetCourant().equals("ellipse")) {
			bouton.setBackground(new Color(220, 220, 220));
			bouton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
		} else {
			bouton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
			bouton.setBackground(Color.WHITE);
		}
		
		// Modification de l'icône en fonction du booléen "plein"
		if (model.getObjetCourant().equals("ellipse") && model.getPleinCourant()) {
			bouton.setIcon(urlCerclePlein);
		} else if (model.getObjetCourant().equals("ellipse") && !model.getPleinCourant()) {
			bouton.setIcon(urlCercleVide);
		}
	}
}