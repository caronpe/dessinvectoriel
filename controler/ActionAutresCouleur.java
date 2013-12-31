package controler;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;

import model.Model;

public class ActionAutresCouleur extends AbstractAction{

	Model model;
	
	public ActionAutresCouleur(Model model) {
		this.model = model;
	}

	/*
	 * crée la fenetre de selection et passe la couleur au modele
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		model.setColor(JColorChooser.showDialog(null, "fenetre de selection de la couleur", model.getColor()));
	}

}
