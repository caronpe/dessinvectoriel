package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
// INTERNE
import model.Calque;
import model.Model;
import ressources.DimensionMenuDroit;
import controler.ActionCalqueRemove;
import controler.ActionCalqueAfficher;
import controler.CalqueListener;

/**
 * Panel spécialisé correspondant à un calque.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class CalqueView extends JPanel {
	// MVC
	private Calque calque;
	private ZoneDessin zoneDessin;
	private CalquePanel calquePanel;
	// Panel
	private JLabel image;
	private JPanel button;
	private JButton afficher, supprimer;
	
	public CalqueView(Model model, Calque calque, ZoneDessin zoneDessin , CalquePanel calquePanel){
		this.calque = calque;
		this.zoneDessin = zoneDessin;
		this.calquePanel = calquePanel;
		this.addMouseListener(new CalqueListener(model, calque, calquePanel, this));
		this.initialiserPanel(model);
	}
	
	private void initialiserPanel(Model model) {
		// Initialisation
		this.setPreferredSize(new DimensionMenuDroit());
		this.setMaximumSize(new DimensionMenuDroit());
		this.setLayout(new BorderLayout());
		
		//Bouton afficher/masquer
		afficher = new JButton();
		ActionCalqueAfficher actionAfficher = new ActionCalqueAfficher(model, this.calque, afficher);
		afficher.setAction(actionAfficher);
		afficher.setPreferredSize(new Dimension(22,22));
		afficher.setFocusable(false);
		afficher.setBackground(new Color(220, 220, 220));setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		afficher.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		afficher.setVisible(false);
		
		// Bouton supprimer
		supprimer = new JButton(new ActionCalqueRemove(this.calquePanel, model, this.calque));
		supprimer.setPreferredSize(new Dimension(22,22));
		supprimer.setFocusable(false);
		supprimer.setBackground(new Color(220, 220, 220));setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		supprimer.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		supprimer.setVisible(false);
		
		// Panels intermédiaires
		JPanel tmp = new JPanel();
		tmp.setBackground(new Color(0, 0, 0, 0f));
		JPanel tmp2 = new JPanel();
		tmp2.setBackground(new Color(0, 0, 0, 0f));
		
		// Panel
		button = new JPanel();
		button.setPreferredSize(new Dimension(22,22));
		button.setLayout(new GridLayout(4, 1));
		button.setBackground(new Color(0, 0, 0, 0f));
		button.add(afficher);
		button.add(tmp);
		button.add(tmp2);
		button.add(supprimer);
		
		// Met l'image représentative du calque sur le panel
		this.image = new JLabel(new ImageIcon(zoneDessin.getImage(calque)));
		this.add(button, BorderLayout.WEST);
		this.add(image, BorderLayout.EAST);
	}
	
	public Calque getCalque() {
		return this.calque;
	}
	
	public JButton getAfficher() {
		return this.afficher;
	}
	
	public JButton getSupprimer() {
		return this.supprimer;
	}
	
	public JPanel getButtonPanel() {
		return this.button;
	}

	/**
	 * Met a jour l'image quand la zone de dessin est modifié.
	 * À chaque modification, la prévisualisation du calque est mise à jour.
	 */
	public void update() {
		image.setIcon(new ImageIcon(zoneDessin.getImage(calque)));
	}
}
