package controler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
// INTERNE
import model.Calque;
import model.Model;


/**
 * Listener sur les panel calque. Gère toutes les action sur les calques : 
 * superposer, remplacer, supprimer, etc.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.3
 */
public class CalqueListener implements MouseListener {
	private Model model;
	private Calque calque;

	public CalqueListener(Model model, Calque calque) {
		this.model = model;
		this.calque = calque;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		model.deselectionnerToutesLesFormes();
		model.setCalque(calque);
	}
	
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {	
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
}
