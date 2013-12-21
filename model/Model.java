package model;

import java.awt.Color;
import java.awt.Point;
import java.util.Observable;
//INTERNE
import ressources.Forme;
import controler.DessinListener;

/**
 * Partie Modèle du MVC
 * Gère l'ajout des formes (temporaires ou non)
 * et tous les outils qui peuvent être selectionnées
 * par l'utilisateur à un moment t
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.1
 */
public class Model extends Observable {
	private ListeDessin listeDessin;
	// Types de modification d'un objet forme
		private Color couleurCourante;
		private String typeCourant, objetCourant; 
		private String extension;
		private boolean travail_enregistre, keyGpressed;

	/**
	 * Couleur : noire
	 * Objet : droite
	 * Type : plein
	 * Extension : .cth
	 * 
	 * Indique qu'il n'y a pas besoin d'enregistrer lorsque le dessin est vide
	 * (au démarrage ou lorsqu'on ouvre un nouveau fichier)
	 * 
	 */
	public Model() {
		super();
		this.listeDessin = new ListeDessin();
		this.couleurCourante = Color.BLACK;
		this.typeCourant = "plein";
		this.objetCourant = "trait";
		this.setEnregistre(true); // 
		this.extension = ".cth";
	}
	
	/**
	 * Ajoute une figure à la liste des formes présentes.
	 * Lorsqu'une forme est ajoutée, l'objet Forme "courant" est envoyé à l'update des vues
	 * 
	 * @see FenetrePrincipale.update()
	 */
	public void addForme(Point pointDebut, Point pointArrivee, boolean parfait) {
		Forme courant = new Forme(pointDebut, pointArrivee, typeCourant, objetCourant, couleurCourante, parfait);
		listeDessin.add(courant);
		System.out.println("Forme ajoutée"); // DEBUG
		setChanged();
		notifyObservers(courant); // Envoi de l'objet au vues
		setEnregistre(false);
	}
	
	/**
	 * Cette méthode est appelée par mouseDragged dans le DessinListener,
	 * elle met à jour l'affichage de la zone de dessin en ajoutant une forme temporaire
	 * puis en la supprimant juste après
	 * 
	 * @param pointDebut Point d'origine de la forme
	 * @param pointArrivee Point d'arrivée de la forme
	 * @param zoneDessin Panel sur lequel on va dessiner la forme
	 * 
	 * @see DessinListener
	 */
	public void addTmpForme(Point pointDebut, Point pointArrivee, boolean parfait) {
		addForme(pointDebut, pointArrivee, parfait);
		this.delLastForme();
	}
	
	public void delLastForme() {
		this.listeDessin.removeLast();
	}

	public void delAllFormes() {
		this.listeDessin.removeAll();
		setChanged();
		notifyObservers(); // Envoi de l'objet au vues
		setEnregistre(true);
	}
	
	/**
	 * @category accessor
	 */
	public void setColor (Color couleur) {
		this.couleurCourante = couleur;
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
	public ListeDessin getListeDessin() {
		return this.listeDessin;
	}
	
	/**
	 * @category accessor
	 */
	public void setListeDessin(ListeDessin listeDessin) {
		this.listeDessin = listeDessin;
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
	 * 
	 * @param L'objet à modifier
	 */
	public boolean getShiftPressed() {
		return this.keyGpressed;
	}

	public void setGPressed(boolean keyGpressed) {
		this.keyGpressed = keyGpressed; 
	}
	/**
	 * @category accessor
	 * 
	 * @param L'objet à modifier
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
}