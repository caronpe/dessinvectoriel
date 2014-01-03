package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controler.ActionCalquer;
import ressources.DimensionMenuGauche;
import model.Calque;
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
	private CalquePanel zoneCalque; // zone ou les calques apparaitrons
	private JButton calquer; //bouton qui créera un calque et l'ajoutera à ma liste
	
	public MenuDroit(Model model) {
		super();
		this.model = model;
		this.setLayout(new BoxLayout(this, 1));		
		calquer = new JButton("calquer");
		calquer.setPreferredSize(new DimensionMenuGauche());
		calquer.addActionListener(new ActionCalquer(model));
		this.add(calquer);
		zoneCalque = new CalquePanel(model);
		this.add(new JScrollPane(zoneCalque));
	}

	public void addCalque(Calque calque) {
		// TODO Auto-generated method stub
		zoneCalque.addCalque(calque);
		this.revalidate();
	}
	
	
}
