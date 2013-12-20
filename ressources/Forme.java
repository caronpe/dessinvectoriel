package ressources;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public class Forme implements Serializable {
	Point pointDebut, pointArrivee;
	String type , objet;
	Color couleur;
	
	/* 	Constructeur basique de la forme à dessiner avec ses coordonnées vectorielles,
		sa couleur, sa forme et son type */
	public Forme(Point pointDebut , Point pointArrivee, String type, String objet, Color couleur) {
		super();
		this.pointDebut = pointDebut;
		this.pointArrivee = pointArrivee;
		this.type = type;
		this.objet = objet;
		this.couleur = couleur;
	}

	public Point getDeb() {
		return pointDebut;
	}

	public void setDeb(Point pointDebut) {
		this.pointDebut = pointDebut;
	}

	public Point getArr() {
		return this.pointArrivee;
	}

	public void setArr(Point pointArrivee) {
		this.pointArrivee = pointArrivee;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getForme() {
		return this.objet;
	}

	public void setForme(String forme) {
		this.objet = forme;
	}

	public Color getCouleur() {
		return this.couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public String toString() {
		return "Forme [deb=" + pointDebut + ", arr=" + pointArrivee + ", type=" + type
				+ ", forme=" + objet + ", couleur=" + couleur + "]";
	}

}
