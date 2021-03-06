package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
// INTERNE
import model.Model;
import ressources.DimensionMenuDroit;
import controler.ActionCalqueAdd;

/**
 * Partie droite de la fenêtre
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 *
 * @version 0.4 finale
 */
public class MenuDroit extends JPanel {
	private JPanel buttons; // Panel qui recevra les boutons
	private JButton addCalque; // Bouton qui créera un calque et l'ajoutera à la liste
	
	public MenuDroit(Model model , ZoneDessin zoneDessin) {
		this.setLayout(new BorderLayout());
		
		// Zone où les calques apparaitront
		CalquePanel calquePanel = new CalquePanel(model, zoneDessin);
		
		// JButton
		addCalque = new JButton(new ActionCalqueAdd(model));
		addCalque.setPreferredSize(new Dimension(DimensionMenuDroit.width, 22));
		addCalque.setFocusable(false);
		addCalque.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		addCalque.setBackground(Color.WHITE);
		
		// Panel boutons
		buttons = new JPanel();
		initialiserPanelBouton();
		buttons.add(addCalque);
	
		// JScrollPane
		JScrollPane scrollPane = new JScrollPane(calquePanel);
		
		// Ajouts
		this.add(buttons, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Initialise la taille du panel
	 * @category init
	 */
	public void initialiserPanelBouton() {
		// Taille
		int 	width = DimensionMenuDroit.width,
				height = 33;
		
		buttons.setPreferredSize(new Dimension(width, height));
		buttons.setMinimumSize(new Dimension(width, height));
		buttons.setMaximumSize(new Dimension(width, height));
	}
}
