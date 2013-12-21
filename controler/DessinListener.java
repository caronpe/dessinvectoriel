package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
// INTERNE
import ressources.*;
import view.ZoneDessin;
import model.Model;

/**
 * Contient les Listeners de la zone de dessin. 
 * S'occupe donc des mouvements de la souris ainsi que des clics.
 * Nécessite d'avoir une seule et même instance par Panel sans quoi
 * la méthode mouseDragged ne fonctionne pas car elles n'auraient pas
 * mutuellement accès au point d'origine du mousePressed
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class DessinListener implements MouseListener, MouseMotionListener {
	private Point pointDebut, pointArrivee;
	private Model model;
	
	/**
	 * @param zoneDessin Zone de dessin
	 * @param model Modèle du MVC
	 */
	public DessinListener(ZoneDessin zoneDessin, Model model) {
		super();
		this.model = model;
	}

	/**
	 * Dessine une forme temporaire en récupérant les données du modèle,
	 * le point d'origine (lorsque la souris est cliquée) 
	 * et le point final (l'actuel)
	 * 
	 * @category mouseListeners
	 * 
	 * @param e Coordonnées de la souris pendant le déplacement
	 */
	public void mousePressed(MouseEvent e) {
		this.pointDebut = new Point(e.getPoint());
		System.out.println(this.pointDebut);
	}

	/**
	 * Dessine une forme temporaire en récupérant les données du modèle,
	 * le point d'origine (lorsque la souris est cliquée) 
	 * et le point final (l'actuel)
	 * 
	 * @category mouseListeners
	 * 
	 * @param e Coordonnées de la souris pendant le déplacement
	 */
	public void mouseDragged(MouseEvent e) {
		pointArrivee = e.getPoint();
		
		if ( model.getShiftPressed() ) {
			model.addTmpForme(this.pointDebut, e.getPoint(), true);
		} else {
			model.addTmpForme(this.pointDebut, e.getPoint(), false);
		}
		System.out.println(this.pointDebut);
			
	}
	
	/**
	 * Dessine une forme temporaire en récupérant les données du modèle,
	 * le point d'origine (lorsque la souris est cliquée) 
	 * et le point final (l'actuel)
	 * 
	 * @category mouseListeners
	 * 
	 * @param e Coordonnées de la souris au relâchement
	 */
	public void mouseReleased(MouseEvent e) {
		pointArrivee = e.getPoint();
		
		if ( model.getShiftPressed() ) {
			model.addForme(this.pointDebut, e.getPoint(), true);
		} else {
			model.addForme(this.pointDebut, e.getPoint(), false);
		}
	}
	
	

	/**
	 * @category unused
	 */
	public void mouseMoved(MouseEvent e) {
	}
	/**
	 * @category unused
	 */
	public void mouseClicked(MouseEvent e) {
	}
	/**
	 * @category unused
	 */
	public void mouseEntered(MouseEvent e) {
	}
	/**
	 * @category unused
	 */
	public void mouseExited(MouseEvent e) {
	}
}
