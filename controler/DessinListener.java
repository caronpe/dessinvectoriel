package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ListIterator;

import model.Model;
import ressources.Forme;
import view.ZoneDessin;
// INTERNE

/**
 * Contient les Listeners de la zone de dessin. 
 * S'occupe des mouvements de la souris ainsi que des clics.
 * Nécessite d'avoir une seule et même instance par Panel sans quoi
 * la méthode mouseDragged ne fonctionne pas car elles n'auraient pas
 * mutuellement accès au point d'origine du mousePressed.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @see view.FenetrePrincipale#initialiser
 * 
 * @version 0.2
 */
public class DessinListener implements MouseListener, MouseMotionListener {
	private Point pointDebut, pointArrivee;
	/**
	 * Lorsqu'une forme est déplacée, on la déplace dans cette variable.
	 */
	Forme draggingForme;
	/**
	 * Lorsque une forme est sélectionnée et déplacée, ce booléen est à vrai.
	 */
	boolean dragging;
	private Model model;
	
	/**
	 * Initialise le booléen dragging à faux et la draggingForme à null.
	 * 
	 * @param zoneDessin Zone de dessin
	 * @param model Modèle du MVC
	 */
	public DessinListener(ZoneDessin zoneDessin, Model model) {
		super();
		this.model = model;
		this.dragging = false;
		this.draggingForme = null;
	}

	/**
	 * Définit le point d'origine du départ lorsqu'un outil de création est sélectionné dans le modèle
	 * Lorsque c'est l'outil sélection qui est sélectionné, il vérifie si ses coordonnées sont "contenues"
	 * dans une forme de la liste de dessins. /!\ La liste est parcourue à partir de la fin pour
	 * sélectionner la forme la plus récente (et donc la plus visible).
	 * 
	 * @category mouseListeners
	 * 
	 * @param e Coordonnées de la souris au clic.
	 */
	public void mousePressed(MouseEvent e) {
		this.pointDebut = new Point(e.getPoint());
		System.out.println(this.pointDebut); // DEBUG	
		
		if (model.getObjetCourant().equals("selection")) { // Si l'outil est "sélection"
			
			
			boolean trouve = false;
			ListIterator<Forme> it = model.getListeDessin().iterator();
			
			while (it.hasNext()) { // On déroule la liste pour commencer par la fin
				it.next();
			}
			
			while (it.hasPrevious() && !trouve) { /* 	On parcours la liste de dessin à la recherche d'une 
														forme correspondante si elle n'est pas déjà trouvée */
				Forme f = it.previous();		

				if ( f.contains(e.getPoint())) {
					draggingForme = f;
					this.pointDebut = e.getPoint();
					
					// Vérifications interne 
					this.dragging = true;
					trouve = true; 
				} else { // Sinon on définit toutes les formes comme non sélectionnées
					model.getListeDessin().getLast().setSelected(false);
				}
			}
		}
	}

	/**
	 * Lorqu'un outil de création est sélectionné dans le modèle,
	 * dessine une forme temporaire en récupérant les données du modèle,
	 * le point d'origine (lorsque la souris est cliquée)  et le point final (l'actuel).
	 * Quand l'outil est sélection, gère le déplacement de l'objet :
	 * Appele la modification de forme du modèle avec la draggingForme courante.
	 * 
	 * @category mouseListeners
	 * 
	 * @param e Coordonnées de la souris pendant le déplacement.
	 */
	public void mouseDragged(MouseEvent e) {
		if (!model.getObjetCourant().equals("selection")) { // Si l'outil n'est pas "sélection"
			pointArrivee = e.getPoint();
			if ( model.getShiftPressed() ) {
				model.addTmpForme(this.pointDebut, e.getPoint(), true);
			} else {
				model.addTmpForme(this.pointDebut, e.getPoint(), false);
			}
			System.out.println(this.pointDebut);
		} else { // Si l'outil est "sélection"
			if ( this.dragging ) { // Si une forme est en train d'être déplacée
				this.pointArrivee = e.getPoint();
				model.formeModifiee(this.draggingForme, this.pointDebut, this.pointArrivee);
				this.pointDebut = this.pointArrivee;
			}
		}
	}
	
	/**
	 * Si un outil de création est sélectionné, dessine la forme avec le point d'arrivée final.
	 * S'il y a eu un quelconque dragging de forme, les variables associées sont réinitialisés.
	 * 
	 * @category mouseListeners
	 * 
	 * @param e Coordonnées de la souris au relâchement.
	 */
	public void mouseReleased(MouseEvent e) {
		pointArrivee = e.getPoint();
		
		if (!model.getObjetCourant().equals("selection")) { // Si l'outil n'est pas "sélection"
			if ( model.getShiftPressed() ) {
				model.addForme(this.pointDebut, e.getPoint(), true);
			} else if ( !model.getObjetCourant().equals("selection") ) {
				model.addForme(this.pointDebut, e.getPoint(), false);
			}
		}
				
		this.dragging = false;
		this.draggingForme = null;
	}
	
	/**
	 * @category unused
	 * @deprecated
	 */
	public void mouseMoved(MouseEvent e) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void mouseClicked(MouseEvent e) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void mouseEntered(MouseEvent e) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void mouseExited(MouseEvent e) {
	}
}
