package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
// INTERNE
import model.Model;
import ressources.DimensionMenuDroit;
import controler.ActionCalqueAdd;
import controler.ActionCalqueRemove;

/**
 * Partie droite de la fenêtre
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 *
 * @version 0.3
 */
public class MenuDroit extends JPanel {
	private JPanel buttons; // Panel qui recevra les boutons
	private JButton addCalque, removeCalque; // Bouton qui créera un calque et l'ajoutera à la liste
	
	public MenuDroit(Model model , ZoneDessin zoneDessin) {

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new DimensionMenuDroit());
		
		// Zone où les calques apparaitront
		CalquePanel calquePanel = new CalquePanel(model, zoneDessin);
		
		// JButton
		addCalque = new JButton(new ActionCalqueAdd(model));
		addCalque.setPreferredSize(new Dimension(DimensionMenuDroit.width,22));
		addCalque.setFocusable(false);
		removeCalque = new JButton(new ActionCalqueRemove(calquePanel, model));
		removeCalque.setPreferredSize(new Dimension(22,22));
		removeCalque.setFocusable(false);
		
		// Panel boutons
		buttons = new JPanel();
		initialiserPanelBouton();
		buttons.add(addCalque);
	
		// Ajouts
		this.add(buttons, BorderLayout.NORTH);
		this.add(new JScrollPane(calquePanel), BorderLayout.CENTER);

	}
	
	/**
	 * Initialise la taille du panel
	 * @category init
	 */
	public void initialiserPanelBouton() {
		// Taille
		int width = 45,
			height = 33;
		
		buttons.setPreferredSize(new Dimension(DimensionMenuDroit.width, height));
		buttons.setMinimumSize(new Dimension(DimensionMenuDroit.width, height));
		buttons.setMaximumSize(new Dimension(DimensionMenuDroit.width, height));
	}
}
