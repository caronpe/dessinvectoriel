package controler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Calque;
import model.Model;


/**
 * Listener sur les panel calque. gére toutes les action sur les calques (superposer , remplacer , supprimer , ...)
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Ã‰douard Caron
 * 
 * @version 0.2
 */

public class CalqueListener implements MouseListener {

	Model model;
	Calque calque;
	

	public CalqueListener(Model model, Calque calque) {
		super();
		this.model = model;
		this.calque = calque;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		model.setCalque(calque);
		System.out.println("click ");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
