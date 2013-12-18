package controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
// INTERNE
import model.*;

public class ChangerCouleur extends AbstractAction {
	Color couleur_actuelle;
	Model model;
	
	public ChangerCouleur(Model model, String couleur) {
		this.model = model;
		
		if ( couleur == "rouge" ) {
			this.couleur_actuelle = Color.RED;
		} else if ( couleur == "bleu" ) {
			this.couleur_actuelle = Color.BLUE;
		} else if ( couleur == "vert" ) {
			this.couleur_actuelle = Color.GREEN;
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		this.model.setColor(couleur_actuelle);
	}
}