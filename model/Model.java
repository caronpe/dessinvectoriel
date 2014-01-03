package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;

/**
 * Partie Modèle du MVC. Gère l'ajout des formes (temporaires ou non) et tous
 * les outils qui peuvent être selectionnées par l'utilisateur à un moment t.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class Model extends Observable {
	
	private ArrayList<Calque> listCalque;
	private Calque calqueCourant;
	// Types de modification d'un objet forme
	private Color couleurCourante;
	private String typeCourant, objetCourant;
	// Enregistrement
	private String adresseEnregistrement, extension;
	private boolean travail_enregistre;
	// Touches
	private boolean keyShiftPressed,
					keyControlPressed;

	/**
	 * Couleur : noire,
	 * Objet : sélection,
	 * Type : plein Extension : .cth.
	 * 
	 * Indique qu'il n'y a pas besoin d'enregistrer lorsque le dessin est vide
	 * (au démarrage ou lorsqu'on ouvre un nouveau fichier)
	 */
	public Model() {
		super();
		this.calqueCourant = new Calque();
		//cr�e la liste qui contiendra les differents calques
		this.listCalque = new ArrayList<Calque>();
		this.listCalque.add(calqueCourant);
		this.couleurCourante = Color.BLACK;
		this.typeCourant = "plein";
		this.objetCourant = "selection";
		this.setEnregistre(true);
		this.extension = ".cth";
		this.adresseEnregistrement = null;
	}

	/**
	 * Ajoute une figure à la liste des formes présentes. Crée une forme
	 * spécifique selon l'objet courant. Lorsqu'une forme est ajoutée, l'objet
	 * Forme "courant" est envoyé à l'update des vues.
	 * 
	 * @param pointDebut
	 *            Point d'origine de la forme.
	 * @param pointArrivee
	 *            Point de fin de la forme.
	 * @param parfait
	 *            Indique si la forme est parfait ou non.
	 * 
	 * @see view.FenetrePrincipale#update
	 */
	public void addForme(Point pointDebut, Point pointArrivee, boolean parfait) {
		Forme courant = null;

		switch (this.objetCourant) {
		case "rectangle":
			courant = new FormeRectangle(pointDebut, pointArrivee, typeCourant,
					objetCourant, couleurCourante, parfait);
			break;
		case "ellipse":
			courant = new FormeEllipse(pointDebut, pointArrivee, typeCourant,
					objetCourant, couleurCourante, parfait);
			break;
		case "trait":
			courant = new FormeLine(pointDebut, pointArrivee, typeCourant,
					objetCourant, couleurCourante, parfait);
			break;
		}
		calqueCourant.add(courant);

		// Envoi de la notification aux vues
		setChanged();
		notifyObservers(courant);
		setEnregistre(false);
	}

	/**
	 * Cette méthode est appelée par mouseDragged dans le DessinListener, elle
	 * met à jour l'affichage de la zone de dessin en ajoutant une forme
	 * temporaire puis en la supprimant juste après.
	 * 
	 * @param pointDebut
	 *            Point d'origine de la forme.
	 * @param pointArrivee
	 *            Point d'arrivée de la forme.
	 * @param parfait
	 *            Dégigne une forme parfaite (carrée, cercle etc.).
	 * 
	 * @see controler.DessinListener
	 * @see view.ZoneDessin#update
	 */
	public void addTmpForme(Point pointDebut, Point pointArrivee,
			boolean parfait) {
		this.addForme(pointDebut, pointArrivee, parfait);
		this.delLastForme();
	}

	/**
	 * Définie la forme comme non sélectionnée. Notifie aux vues le changement
	 * pour qu'elle redessine les formes sans marqueur de sélection
	 * (pointillés).
	 * 
	 * @param forme
	 *            Formes sélectionnée
	 */
	public void selectionner(Forme forme) {
		forme.setSelected(true);
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}

	public void deselectionner(Forme f) {
		f.setSelected(false);
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}
	
	public void deselectionnerToutesLesFormes() {
		ListIterator<Forme> it = this.calqueCourant.iterator();
		while (it.hasNext()) {
			it.next().setSelected(false);
		}

		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}

	/**
	 * Supprime la dernière forme de la liste de formes
	 * 
	 * @see model.Calque#removeLast
	 */
	public void delLastForme() {
		this.calqueCourant.removeLast();
	}

	/**
	 * Supprime toutes les formes de la liste de formes
	 * 
	 * @see model.Calque#removeAll
	 */
	public void delAllFormes() {
		this.calqueCourant.removeAll();

		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
		setEnregistre(true);
	}

	/**
	 * S'occupe de la modification des coordonnées d'une forme lorsqu'elle est
	 * déplacée
	 * 
	 * @param forme
	 *            Forme qui a été sélectionnée par le DessinListener lors du
	 *            Dragging.
	 * @param pointDebut
	 *            Point de début de translation.
	 * @param pointArrivee
	 *            Point de fin de translation.
	 * 
	 * @see controler.DessinListener#mouseDragged
	 */
	public void formeModifiee(Forme forme, Point pointDebut, Point pointArrivee) {
		// Initialisation distances
		int distanceX = (int) (pointArrivee.getX() - pointDebut.getX()), distanceY = (int) (pointArrivee
				.getY() - pointDebut.getY());

		// Point Départ
		int tmpX = (int) (forme.getOrigin().getX() + distanceX), tmpY = (int) (forme
				.getOrigin().getY() + distanceY);
		forme.setOrigin(new Point(tmpX, tmpY));

		// Point Arrivée
		tmpX = (int) (forme.getFin().getX() + distanceX);
		tmpY = (int) (forme.getFin().getY() + distanceY);
		forme.setFin(new Point(tmpX, tmpY));

		// Envoi de l'objet au vues
		setChanged();
		notifyObservers();
		setEnregistre(false);
	}

	/**
	 * Si une ou plusieurs formes sont sélectionnées, change leur couleur mais
	 * ne change pas la couleur courante du modèle.
	 * 
	 * @category accessor
	 */
	public void setColor(Color couleur) {
		boolean ilYaDesFormesSelectionnes = false;

		// Parcours de toutes les formes
		ListIterator<Forme> it = this.calqueCourant.iterator();
		while (it.hasNext()) {
			Forme f = it.next();
			if (f.isSelected()) {
				f.setCouleur(couleur);
				ilYaDesFormesSelectionnes = true;
			}
		}
		
		// Définis le comportement du modèle
		if (!ilYaDesFormesSelectionnes) {
			this.couleurCourante = couleur;
		}
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
		setEnregistre(false);
	}
	
	/**
	 * Supprimes toutes les formes sélectionnées. Si aucune forme n'est sélectionné,
	 * il n'y a aucune notifications aux vues.
	 */
	public void supprimerFormes() {
		boolean ilYaDesFormesSelectionnes = false;
		
		// Parcours de toutes les formes
		ListIterator<Forme> it = this.calqueCourant.iterator();
		while (it.hasNext()) {
			Forme f = it.next();
			if (f.isSelected()) {
				it.remove();
				ilYaDesFormesSelectionnes = true;
			}
		}
		
		if (ilYaDesFormesSelectionnes) {
			// Envoi de la notification aux vues
			setChanged();
			notifyObservers();
			setEnregistre(false);
		}
	}

	/**
	 * @category accessor
	 */
	public boolean getEnregistre() {
		return travail_enregistre;
	}

	/**
	 * @category accessor
	 */
	public void setEnregistre(boolean travail) {
		this.travail_enregistre = travail;
	}

	/**
	 * @category accessor
	 */
	public Calque getListeDessin() {
		return this.calqueCourant;
	}

	/**
	 * @category accessor
	 */
	public void setListeDessin(Calque listeDessin) {
		this.calqueCourant = listeDessin;
		setChanged();
		notifyObservers();
	}

	/**
	 * @category accessor
	 * 
	 * @return L'extension du fichier
	 */
	public String getExtension() {
		return this.extension;
	}

	/**
	 * @category accessor
	 * 
	 * @return Le type actuel
	 */
	public String getTypeCourant() {
		return this.typeCourant;
	}

	/**
	 * @category accessor
	 */
	public boolean getShiftPressed() {
		return this.keyShiftPressed;
	}
	
	/**
	 * @category accessor
	 */
	public void setShiftPressed(boolean keyControlPressed) {
		this.keyShiftPressed = keyControlPressed;
	}
	
	/**
	 * @category accessor
	 */
	public boolean getControlPressed() {
		return this.keyControlPressed;
	}

	/**
	 * @category accessor
	 */
	public void setControlPressed(boolean keyShiftPressed) {
		this.keyControlPressed = keyShiftPressed;
	}

	/**
	 * @category accessor
	 * 
	 * @param objetCourant
	 *            L'objet à modifier
	 */
	public void setObjetCourant(String objetCourant) {
		this.objetCourant = objetCourant;
	}

	/**
	 * @category accessor
	 * 
	 * @return L'objet actuel
	 */
	public String getObjetCourant() {
		return this.objetCourant;
	}

	/**
	 * @category accessor
	 * @return La couleur actuelle
	 */
	public Color getColor() {
		return this.couleurCourante;
	}
	
	/**
	 * @category accessor
	 * 
	 * @param adresseEnregistrement Nouvelle adresse d'enregistrement
	 * 
	 */
	public void setAdresseEnregistrement(String adresseEnregistrement) {
		this.adresseEnregistrement = adresseEnregistrement;
	}
	
	/**
	 * @category accessor
	 * 
	 * @return L'adresse du précédent enregistrement
	 */
	public String getAdresseEnregistrement() {
		return this.adresseEnregistrement;
	}
	
	/**
	 * place le nouveau calque en calque courant et le stock dans la liste de calque
	 */
	public void calquer(){
		calqueCourant = new Calque(calqueCourant.getListeDessin());
		listCalque.add(calqueCourant);
		setChanged();
		notifyObservers(calqueCourant);
	}
	
}