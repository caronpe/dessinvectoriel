package ressources;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public class Forme implements Serializable {
	Point pointDebut, pointArrivee;
	String type , objet;
	Color couleur;
	
	/**
	 * Constructeur basique de la forme à dessiner avec ses coordonnées vectorielles,
	 * sa couleur, sa forme et son type 
	 * @param pointDebut Point d'origine de l'objet
	 * @param pointArrivee Point final de l'objet
	 * @param type Plein, vide
	 * @param objet Carré, rond, droite
	 * @param couleur Couleur de l'objet
	 */
	public Forme(Point pointDebut , Point pointArrivee, String type, String objet, Color couleur) {
		super();
		this.pointDebut = pointDebut;
		this.pointArrivee = pointArrivee;
		this.type = type;
		this.objet = objet;
		this.couleur = couleur;
	}

	/**
	 * @category accessor
	 */
	public Point getOrigin() {
		return pointDebut;
	}

	/**
	 * @category accessor
	 */
	public void setOrigin(Point pointDebut) {
		this.pointDebut = pointDebut;
	}

	/**
	 * @category accessor
	 */
	public Point getFin() {
		return this.pointArrivee;
	}

	/**
	 * @category accessor
	 */
	public void setFin(Point pointArrivee) {
		this.pointArrivee = pointArrivee;
	}

	/**
	 * @category accessor
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @category accessor
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @category accessor
	 */
	public String getForme() {
		return this.objet;
	}

	/**
	 * @category accessor
	 */
	public void setForme(String forme) {
		this.objet = forme;
	}

	/**
	 * @category accessor
	 */
	public Color getCouleur() {
		return this.couleur;
	}

	/**
	 * @category accessor
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public String toString() {
		return "Forme [deb=" + pointDebut + ", arr=" + pointArrivee + ", type=" + type
				+ ", forme=" + objet + ", couleur=" + couleur + "]";
	}

}
