package view;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Calque;
import model.Model;
import ressources.DimensionMenuDroit;
import controler.ActionViewCalque;
import controler.CalqueListener;

/**
 * Panel spécialisé correspondant à un calque.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.3
 */
public class CalqueView extends JPanel {
	private Calque calque;
	private JLabel image;
	private ZoneDessin zoneDessin;
	private JButton afficher;
	
	public CalqueView(Model model, Calque calque, ZoneDessin zoneDessin , CalquePanel calquePanel){
		this.calque = calque;
		this.zoneDessin = zoneDessin;
		
		//Bouton permettant d'afficher ou masquer un calque
		this.afficher = new JButton();
		ActionViewCalque actionAfficher = new ActionViewCalque(model, this.calque, afficher);
		this.afficher.setAction(actionAfficher);
		afficher.setPreferredSize(new Dimension(22,22));
		afficher.setFocusable(false);
		
		// Ajoute les actions possibles sur les calques
		this.addMouseListener(new CalqueListener(model, calque, calquePanel, this));
		
		// Initialisation
		this.setPreferredSize(new DimensionMenuDroit());
		this.setMaximumSize(new DimensionMenuDroit());
		
		// Met l'image représentative du calque sur le panel
		this.image = new JLabel(new ImageIcon(zoneDessin.getImage(calque)));
		this.add(afficher);
		this.add(image);
	}
	
	public Calque getCalque() {
		return this.calque;
	}

	/**
	 * Met a jour l'image quand la zone de dessin est modifié.
	 * À chaque modification, la prévisualisation du calque est mise à jour.
	 */
	public void update() {
		image.setIcon(new ImageIcon(zoneDessin.getImage(calque)));
	}
}
