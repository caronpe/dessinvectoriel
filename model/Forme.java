package model;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public class Forme implements Serializable {
	Point deb , arr;
	String type , forme;
	Color couleur;
	

	// Constructeur le plus basique de la forme a dessiner avec ses coordonnées vectoriels,
	// sa couleur et une string représentant sa forme
	public Forme(Point deb , Point arr, String type, String forme, Color couleur) {
		super();
		this.deb = deb;
		this.arr = arr;
		this.type = type;
		this.forme = forme;
		this.couleur = couleur;
	}

	public Point getDeb() {
		return deb;
	}

	public void setDeb(Point deb) {
		this.deb = deb;
	}

	public Point getArr() {
		return arr;
	}

	public void setArr(Point arr) {
		this.arr = arr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getForme() {
		return forme;
	}

	public void setForme(String forme) {
		this.forme = forme;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public String toString() {
		return "Forme [deb=" + deb + ", arr=" + arr + ", type=" + type
				+ ", forme=" + forme + ", couleur=" + couleur + "]";
	}

}
