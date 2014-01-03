package controler;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.Model;

/**
 * Listener Bouton Calquer. Ordonne la création d'un calque.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Ã‰douard Caron
 * 
 * @version 0.2
 */

public class ActionCalquer extends AbstractAction{

		Model model;
		
	
	public ActionCalquer(Model model) {
			super();
			this.model = model;
		}


	/**
	 * calque la situation actuel
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {		
		model.calquer();
	}

}
