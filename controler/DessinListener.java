package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
// INTERNE
import ressources.*;
import model.Model;
import fenetre.ZoneDessin;

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
 * @version 0.1
 */
public class DessinListener implements MouseListener, MouseMotionListener {
	private Point pointDebut, pointArrivee;
	private ZoneDessin zoneDessin;
	private Model model;
	
	/**
	 * @param zoneDessin Zone de dessin
	 * @param model Modèle du MVC
	 */
	public DessinListener(ZoneDessin zoneDessin, Model model) {
		super();
		this.model = model;
		this.zoneDessin = zoneDessin;
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
			System.out.println(this.pointDebut);
			model.addTmpForme(this.pointDebut, e.getPoint(), zoneDessin);
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
		model.addForme(this.pointDebut, this.pointArrivee, zoneDessin);
	}
	
	

	/**
	 * @category inutile
	 */
	public void mouseMoved(MouseEvent e) {
	}
	/**
	 * @category inutile
	 */
	public void mouseClicked(MouseEvent e) {
	}
	/**
	 * @category inutile
	 */
	public void mouseEntered(MouseEvent e) {
	}
	/**
	 * @category inutile
	 */
	public void mouseExited(MouseEvent e) {
	}
}
