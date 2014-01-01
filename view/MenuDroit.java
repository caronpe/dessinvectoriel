package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ressources.DimensionMenuGauche;
import model.Model;

/**
 * Partie droite de la fenetre
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Ã‰douard Caron
 * 
 * @version 0.2
 */

public class MenuDroit extends JPanel {
	private Model model;
	private JScrollPane zoneCalque; // zone ou les calques apparaitrons
	private JButton calquer; //bouton qui créera un calque et l'ajoutera à ma liste
	
	public MenuDroit(Model model) {
		super();
		this.model = model;
		this.setLayout(new BoxLayout(this, 1));		
		calquer = new JButton("calquer");
		calquer.setPreferredSize(new DimensionMenuGauche());
		this.add(calquer);
		zoneCalque = new JScrollPane(new CalquePanel(model));
		this.add(zoneCalque);
	}
	
	
}
