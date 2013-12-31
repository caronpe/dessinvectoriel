package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ListIterator;

import model.Forme;
import model.Model;
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
	Forme draggingForme; /**  Lorsqu'une forme est déplacée, on la déplace dans cette variable. */ 
	boolean dragging; /** Lorsque une forme est sélectionnée et déplacée, ce booléen est à vrai. */
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
		// Servira aux outils de création
		this.pointDebut = new Point(e.getPoint());
		
		// Vérification de la touche contrôle
		if ( !model.getControlPressed() ) {
			model.deselectionnerToutesLesFormes();
		}
		
		// Si Sélection
		if (model.getObjetCourant().equals("selection")) {			
			// Initialisation
			boolean trouve = false;
			ListIterator<Forme> it = model.getListeDessin().iterator();
			while (it.hasNext()) it.next(); // On déroule la liste pour commencer par la fin
			
			// Parcours de la liste à la recherche d'une forme correspondante si elle n'est pas déjà trouvée
			while (it.hasPrevious() && !trouve) {
				Forme f = it.previous();
				
				// Une forme contient les coordonnées du clic
				if ( f.contains((Point2D)e.getPoint())) {
					// Sélection
					if (f.isSelected()) {
						model.deselectionner(f);
					} else {
						model.selectionner(f);
					}
					
					
					// Définition du dragging ...
					this.pointDebut = e.getPoint();
					this.draggingForme = f;
					this.dragging = true;
					
					// Fin de boucle
					trouve = true;
				}
			}
		}
	}

	/**
	 * Lorqu'un outil de création est sélectionné dans le modèle,
	 * dessine une forme temporaire en récupérant les données du modèle,
	 * le point d'origine (lorsque la souris est cliquée)  et le point final (l'actuel).
	 * Quand l'outil est sélection, gère le déplacement de l'objet :
	 * Appelle la modification de forme du modèle avec la draggingForme courante.
	 * 
	 * @category mouseListeners
	 * 
	 * @param e Coordonnées de la souris pendant le déplacement.
	 */
	public void mouseDragged(MouseEvent e) {
		// Si pas Sélection
		if (!model.getObjetCourant().equals("selection")) { 
			pointArrivee = e.getPoint();
			
			// Si c'est une forme parfaite
			if ( model.getShiftPressed() ) {
				model.addTmpForme(this.pointDebut, e.getPoint(), true);
			} else {
				model.addTmpForme(this.pointDebut, e.getPoint(), false);
			}
		// Si Sélection
		} else {
			// Si une forme est en train d'être déplacée
			if ( this.dragging ) {
				// ... Définition du dragging ...
				this.pointArrivee = e.getPoint();
				
				// Envoi au model
				model.formeModifiee(this.draggingForme, this.pointDebut, this.pointArrivee);
				
				// ... Redéfinition du dragging
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
		this.pointArrivee = e.getPoint();
		
		// Si pas Sélection
		if (!model.getObjetCourant().equals("selection")) {
			// Si c'est une forme parfaite
			if ( model.getShiftPressed() ) {
				model.addForme(this.pointDebut, this.pointArrivee, true);
			} else {
				model.addForme(this.pointDebut, this.pointArrivee, false);
			}
		}
		
		// Réinitialisation du dragging
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