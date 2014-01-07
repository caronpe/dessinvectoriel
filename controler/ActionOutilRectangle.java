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

/**
 * Listener pour le bouton rectangle
 *  
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionOutilRectangle extends AbstractAction implements Observer {
	private Model model;
	private JButton bouton;
	private String type;
	
	/**
	 * Ne comporte pas de nom, autrement
	 * l'affichage ne s'effectuerait pas correctement.
	 * 
	 * @param model Modèle du MVC
	 */
	public ActionOutilRectangle(Model model, JButton bouton) {
		this.model = model;
		this.model.addObserver(this);
		this.bouton = bouton;
		this.type = "vide";
		
		// Values
		this.putValue(SHORT_DESCRIPTION, "Sélectionne l'outil rectangle");
		this.putValue(SMALL_ICON, new ImageIcon("ressources/images/rectangleVide.png"));
	}

	/**
	 * Sélectionne l'outil rectangle dans le modèle
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if (model.getObjetCourant().equals("rectangle") && this.type.equals("plein")) {
			this.type = "vide";
		} else if (model.getObjetCourant().equals("rectangle") && this.type.equals("vide")) {
			this.type = "plein";
		}
		
		model.setObjetCourant("rectangle");
		model.setTypeCourant(this.type);
		model.deselectionnerToutesLesFormes();
 	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (model.getObjetCourant().equals("rectangle")) {
			bouton.setBackground(new Color(220, 220, 220));
			bouton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
		} else {
			bouton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
			bouton.setBackground(Color.WHITE);
		}
		
		if (model.getObjetCourant().equals("rectangle") && model.getTypeCourant().equals("plein")) {
			bouton.setIcon(new ImageIcon("ressources/images/rectanglePlein.png"));
		} else if (model.getObjetCourant().equals("rectangle") && model.getTypeCourant().equals("vide")) {
			bouton.setIcon(new ImageIcon("ressources/images/rectangleVide.png"));
		}
	}
}