package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;

/**
 * Partie Modèle du MVC. S'occupe de la gestion des formes (temporaires ou non), des calques 
 * et de tous les outils.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class Model extends Observable {
	private ArrayList<Calque> listCalque;
	private Calque calqueCourant;
	// Types de modification d'un objet forme
	private Color couleurCourante;
	private String objetCourant;
	private boolean pleinCourant;
	// Enregistrement
	private String adresseEnregistrement, extension;
	private boolean travail_enregistre;
	private ArrayList<Forme> formePending;
	// Touches
	private boolean keyShiftPressed, keyControlPressed;
	// Curseur
	public final int DEFAULT_CURSOR = 0, NORTH_WEST_CURSOR = 1, NORTH_EAST_CURSOR = 2;
	private int redimensionnementPotentiel;
	private boolean creationPotentielle;
	protected float strokeFloat;
	
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
		this.pleinCourant = false;
		this.objetCourant = "selection";
		this.setEnregistre(true);
		this.extension = ".cth";
		this.adresseEnregistrement = null;
		this.strokeFloat = 1f;
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
			courant = new FormeRectangle(pointDebut, pointArrivee, this.strokeFloat, pleinCourant, objetCourant,couleurCourante, parfait);
			break;
		case "ellipse":
			courant = new FormeEllipse(pointDebut, pointArrivee, this.strokeFloat, pleinCourant, objetCourant, couleurCourante, parfait);
			break;
		case "trait":
			courant = new FormeLine(pointDebut, pointArrivee, this.strokeFloat, false, objetCourant,couleurCourante, parfait);
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
		setEnregistre(false);
	}

	/**
	 * @param photo image que l'on souhaite ajouter à la liste de formes
	 */
	public void addForme(BufferedImage photo){
		Point pointOrigin = new Point(0,0), pointFin = new Point(photo.getWidth(),photo.getHeight());
		FormeImage image = new FormeImage(pointOrigin, pointFin, photo);
		calqueCourant.add(image);

		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
		setEnregistre(false);
	}
	
	/**
	 * Méthode utilisée lors de l'ouverture d'un fichier.
	 * 
	 * @param f forme que l'on souhaite ajouter
	 */
	public void addForme(Forme f) {
		calqueCourant.add((Forme) f.clone());

		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
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
	public void addTmpForme(Point pointDebut, Point pointArrivee, boolean parfait) {
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

	/**
	 * @param f forme à déselectionner
	 */
	public void deselectionner(Forme f) {
		f.setSelected(false);

		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}

	/**
	 * Marque toutes les formes de tous les calques comme non sélectionnées.
	 */
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
			setEnregistre(false);
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
	 * TODO Réécrire
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
		setEnregistre(false);
	}

	/**
	 * Méthode gérant le redimensionnement.
	 * 
	 * @param marqueur Marqueur concerné par le resize
	 * @param forme Forme que l'on souhaite redimensionner
	 * @param pointArrivee Nouveau point à définir au marqueur indiqué 
	 */
	public void resizeForme(int marqueur, Forme forme, Point pointArrivee) {
		forme.resize(marqueur, pointArrivee, this.getShiftPressed());

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
		} else {
			setEnregistre(false);
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
	public void setFormePending(ArrayList<Forme> formePending) {
		if (formePending != null && formePending.size() != 0 ) {
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
	
	public ArrayList<Calque> getListCalque() {
		return this.listCalque;
	}

	/**
	 * @category accessor
	 * 
	 * @return Toutes les formes de tous les calques
	 */
	public ArrayList<Forme> getAllFormes() {
		Calque calque = null;
		ArrayList<Forme> formes = new ArrayList<Forme>();
		ListIterator<Calque> it = listCalque.listIterator();
		while (it.hasNext()) {
			calque = it.next();
			if(calque.getAfficher()==true){
				ListIterator<Forme> itF = calque.listIterator();
				while (itF.hasNext()) {
					formes.add(itF.next());
				}
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
	public boolean getPleinCourant() {
		return this.pleinCourant;
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
	public void setTypeCourant(boolean pleinCourant) {
		this.pleinCourant = pleinCourant;

		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}
	
	public void setListeCalque(ArrayList<Calque> listCalque) {
		this.listCalque = listCalque;
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers(this.listCalque);
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
		setEnregistre(false);
	}

	/**
	 * Supprime le calque qui lui est envoyé.
	 * Peu importe le calque supprimé, il y aura
	 * toujours un calque de sélectionné.
	 */
	public void delCalque(Calque calque) {
		this.listCalque.remove(calque);
		this.deselectionnerToutesLesFormes();

		if (listCalque.size() < 1) {
			this.resetCalques();
		} else {
			this.calqueCourant = listCalque.get(listCalque.size() - 1);
		}
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
		setEnregistre(false);
	}
	
	/**
	 * Supprime tous les calques et envoie le nouveau calque
	 * courant aux observer
	 */
	private void resetCalques() {
		this.calqueCourant = new Calque();
		this.listCalque.add(calqueCourant);
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers(listCalque);
	}

	/**
	 * @param calque Calque à marquer comme visible
	 */
	public void setAfficherCalque(Calque calque) {
		calque.setAfficher(!calque.getAfficher());
		
		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Méthode rassemblant toutes les actions à effectuer
	 * lors de la création d'un nouveau fichier.
	 */
	public void newFile() {
		this.listCalque = new ArrayList<Calque>();
		this.resetCalques();
		this.adresseEnregistrement = null;
	}
	
	public ArrayList<Calque> save() {
		return this.getListCalque();
	}
	
	/**
	 * Méthode rassemblant toutes les actions à effectuer
	 * lors de l'ouverture d'un fichier.
	 * 
	 * @param listCalque liste des calques du fichier ouvert à appliquer à la liste de calques
	 */
	public void open(ArrayList<Calque> listCalque) {
		this.setListeCalque(listCalque);
		this.calqueCourant = this.listCalque.get(0);
	}
	
	/**
	 * @category accessor
	 * 
	 * @return le Stroke actuel
	 */
	public Stroke getStroke() {
		return new BasicStroke(this.strokeFloat);
	}
	
	/**
	 * @category accessor
	 * 
	 * @return le strokeFloat associé au stroke actuel
	 */
	public float getStrokeFloat() {
		return this.strokeFloat;
	}
	
	/**
	 * Si une ou plusieurs formes sont sélectionnées,
	 * le stroke leur est appliqué. Sinon elle est appliqué
	 * au modèle.
	 * Même fonctionnement que pour le changement de couleur.
	 * 
	 * @category accessor
	 * 
	 * @param strokeFloat nouveau StroakFloat
	 */
	public void setStroke(float strokeFloat){
		boolean ilYaDesFormesSelectionnes = false;

		// Parcours de toutes les formes
		ListIterator<Forme> it = this.calqueCourant.listIterator();
		while (it.hasNext()) {
			Forme f = it.next();
			if (f.isSelected()) {
				f.setStroke(strokeFloat);
				ilYaDesFormesSelectionnes = true;
			}
		}

		// Définis le comportement du modèle
		if (!ilYaDesFormesSelectionnes) {
			this.strokeFloat = strokeFloat;
		} else {
			setEnregistre(false);
		}

		// Envoi de la notification aux vues
		setChanged();
		notifyObservers();
	}
	
}