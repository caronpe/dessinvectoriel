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
import ressources.URLIcons;
import model.Model;

/**
 * Listener pour le bouton sélection.
 *  
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class ActionOutilSelection extends AbstractAction implements Observer {
	private Model model;
	private JButton bouton;
	
	/**
	 * Ne comporte pas de nom, autrement
	 * l'affichage ne s'effectuerait pas correctement.
	 * 
	 * @param model Modèle du MVC
	 */
	public ActionOutilSelection(Model model, JButton bouton) {
		this.model = model;
		this.model.addObserver(this);
		this.bouton = bouton;
		
		// Values
		this.putValue(SHORT_DESCRIPTION, "Sélectionne l'outil sélection");
		this.putValue(SMALL_ICON, new ImageIcon(URLIcons.SELECTION));
	}

	/**
	 * Sélectionne l'outil selection dans le modèle.
	 */
	public void actionPerformed(ActionEvent e) {
		model.setObjetCourant("selection");
		model.deselectionnerToutesLesFormes();
	}

	/**
	 * Crée des bordures lorsque cet outil est sélectionné dans le modèle.
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (model.getObjetCourant().equals("selection")) {
			bouton.setBackground(new Color(220, 220, 220));
			bouton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
		} else {
			bouton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
			bouton.setBackground(Color.WHITE);
		}
	}
}