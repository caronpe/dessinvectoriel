package controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
// INTERNE
import model.*;

public class ActionCouleur extends AbstractAction {
	Color couleur_actuelle;
	Model model;
	
	public ActionCouleur(Model model, String couleur) {
		this.model = model;
		
		switch (couleur) {
		  	case "rouge":
		  		this.couleur_actuelle = Color.RED;
		  		break;
		  	case "vert":
		  		this.couleur_actuelle = Color.GREEN;
		  		break;  
		  	case "bleu":
		  		this.couleur_actuelle = Color.BLUE;
		  		break;  
		  	default :
		  		this.couleur_actuelle = Color.BLACK;     
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		this.model.setColor(couleur_actuelle);
	}
}