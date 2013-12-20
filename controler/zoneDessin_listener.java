package controler;

import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
// INTERNE
import model.*;

/**
 * Contient les Listeners de la zone de dessin. 
 * S'occupe donc des mouvements de la souris ainsi que des clics.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.1
 */
public class zoneDessin_listener implements MouseListener, MouseMotionListener {
	Point pointDebut, pointArrivee;
	JPanel panel;
	Model model;
	
	public zoneDessin_listener(JPanel panel, Model model) {
		super();
		this.panel = panel;
		this.model = model;
	}

	public void mousePressed(MouseEvent e) {
		pointDebut = e.getPoint();
	}

	public void mouseReleased(MouseEvent arg0) {
		pointArrivee = arg0.getPoint();
		model.addForme(pointDebut, pointArrivee, panel);
	}
	
	public void mouseDragged(MouseEvent arg0) {
		
	}

	public void mouseMoved(MouseEvent arg0) {
		
	}
	
	/**
	 * @category inutile
	 */
	public void mouseClicked(MouseEvent arg0) {
	}
	/**
	 * @category inutile
	 */
	public void mouseEntered(MouseEvent arg0) {
	}
	/**
	 * @category inutile
	 */
	public void mouseExited(MouseEvent arg0) {
	}
}
