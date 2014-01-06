package model;

import java.awt.Color;
import java.awt.Image;
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
	private ArrayList<Forme> formePending;
	// Touches
	private boolean keyShiftPressed,
					keyControlPressed;
	// Curseur
	public final int DEFAULT_CURSOR = 0, NORTH_WEST_CURSOR = 1, NORTH_EAST_CURSOR = 2;
	private int redimensionnementPotentiel;
	private boolean creationPotentielle;

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
		// Crée la liste qui contiendra les differents calques
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
	public void addForme(Point pointDebut, Point pointArrivee, boolean parfait, boolean tmp) {
		Forme courant = null;

		switch (this.objetCourant) {
		case "rectangle":
			courant = new FormeRectangle(pointDebut, pointArrivee, typeCourant, objetCourant,couleurCourante, parfait);
			break;
		case "ellipse":
			courant = new FormeEllipse(pointDebut, pointArrivee, typeCourant, objetCourant, couleurCourante, parfait);
			break;
		case "trait":
			courant = new FormeLine(pointDebut, pointArrivee, "vide", objetCourant,couleurCourante, parfait);
			break;
		}
		calqueCourant.add(courant);
		
		// Envoi de la notification aux vues
		setChanged();
		if (tmp) {
			
			notifyObservers(courant);
		} else {
			notifyObservers();
		}
	}
	
	public void addForme(Forme f) {
		calqueCourant.add((Forme) f.clone());
		System.out.println("adding : " + f); // DEBUG
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
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
		this.addForme(pointDebut, pointArrivee, parfait, true);
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
		ListIterator<Forme> it = this.getAllFormes().listIterator();
		while (it.hasNext()) {
			it.next().setSelected(false);
		}

		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Supprimes toutes les formes sélectionnées. Si aucune forme n'est sélectionné,
	 * il n'y a aucune notifications aux vues.
	 */
	public void delFormes() {
		boolean ilYaDesFormesSelectionnes = false;
		
		// Parcours de toutes les formes
		ListIterator<Forme> it = this.calqueCourant.listIterator();
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
		}
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
	public void deplacementForme(Forme forme, Point pointDebut, Point pointArrivee) {
		// Initialisation distances
		int 	distanceX = (int) (pointArrivee.getX() - pointDebut.getX()),
				distanceY = (int) (pointArrivee.getY() - pointDebut.getY());

		// Point Départ
		int 	tmpX = (int) (forme.getOrigin().getX() + distanceX),
				tmpY = (int) (forme.getOrigin().getY() + distanceY);
		forme.setOrigin(new Point(tmpX, tmpY));

		// Point Arrivée
		tmpX = (int) (forme.getFin().getX() + distanceX);
		tmpY = (int) (forme.getFin().getY() + distanceY);
		forme.setFin(new Point(tmpX, tmpY));

		// Envoi de l'objet au vues
		setChanged();
		notifyObservers();
	}
	
	public void resizeForme(int marqueur, Forme forme, Point pointArrivee) {
		forme.resize(marqueur, pointArrivee, this.getShiftPressed());
		
		// Envoi de l'objet au vues
		setChanged();
		notifyObservers();
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
		ListIterator<Forme> it = this.calqueCourant.listIterator();
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
	public ArrayList<Forme> getFormePending() {
		return this.formePending;
	}

	/**
	 * @category accessor
	 */
	public void setFormePending(ArrayList formePending) {
		if (formePending != null) {
			this.formePending = formePending;
		}
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}

	/**
	 * @category accessor
	 * 
	 * @return Le calque courant
	 */
	public Calque getCalqueCourant() {
		return this.calqueCourant;
	}
	
	/**
	 * @category accessor
	 * 
	 * @return Toutes les formes de tous les calques
	 */
	public ArrayList getAllFormes() {
		ArrayList<Forme> formes = new ArrayList<Forme>();
		ListIterator<Calque> it = listCalque.listIterator();
		while (it.hasNext()) {
			ListIterator<Forme> itF = it.next().listIterator();
			while (itF.hasNext()) {
				formes.add(itF.next());
			}
		}
		
		return formes;
	}


	/**
	 * @category accessor
	 * 
	 * @param calque Calque que l'on souhaite définir comme courant
	 */
	public void setCalque(Calque calque) {
		this.calqueCourant = calque;
		
		// Envoi de la notification aux vues
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
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @category accessor
	 * 
	 * @param objetCourant
	 *            L'objet à modifier
	 */
	public void setTypeCourant(String typeCourant) {
		this.typeCourant = typeCourant;
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
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
	 * @return S'il y a un redimensionnement potentiel et si différent de 0, quel type de redimensionnement
	 */
	public int getRedimensionnement() {
		return this.redimensionnementPotentiel;
	}
	
	/**
	 * @category accessor
	 * 
	 * @param redimensionnementPotentiel Indique s'il y a un redimensionnement potentiel ou non
	 * 
	 */
	public void setRedimensionnement(int redimensionnementPotentiel) {
		this.redimensionnementPotentiel = redimensionnementPotentiel;
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @category accessor
	 * 
	 * @return S'il y a une création potentielle ou non
	 */
	public boolean getCreation() {
		return this.creationPotentielle;
	}
	
	/**
	 * @category accessor
	 * 
	 * @param redimensionnementPotentiel Indique s'il y a une création potentielle ou non
	 * 
	 */
	public void setCreation(boolean creationPotentielle) {
		this.creationPotentielle = creationPotentielle;
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
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
	 * Place le nouveau calque en calque courant et l'ajoute dans la liste de calque.
	 * Il ajoute donc un calque vide.
	 */
	public void addCalque() {
		this.deselectionnerToutesLesFormes();
		calqueCourant = new Calque();
		listCalque.add(calqueCourant);
				
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers(calqueCourant);
	}
	
	/**
	 * Supprime le calque qui lui est envoyé.
	 * Peu importe le calque supprimé, il y aura
	 * toujours un calque de sélectionné.
	 */
	public void  delCalque(Calque calque) {
		this.listCalque.remove(calque);
		this.deselectionnerToutesLesFormes();
			
		// Envoi de la notification aux vues
		setChanged();
		if (listCalque.size() < 1) {
			this.calqueCourant = new Calque();
			notifyObservers(calqueCourant);
		} 
		this.calqueCourant = listCalque.get(listCalque.size() - 1);
		notifyObservers();
	}
	
	@Override
	public void setChanged() {
		super.setChanged();
		setEnregistre(false);
	}

	public void add(Image image) {
		// TODO Auto-generated method stub
		calqueCourant.add(image);
		setChanged();
		notifyObservers();
	}

	public ArrayList<Image> getAllImages() {
		ArrayList<Image> formes = new ArrayList<Image>();
		ListIterator<Calque> it = listCalque.listIterator();
		while (it.hasNext()) {
			ListIterator<Image> itF = it.next().listIteratorImg();
			while (itF.hasNext()) {
				formes.add(itF.next());
			}
		}
		System.out.println("passe4");
		return formes;
	}
	
}