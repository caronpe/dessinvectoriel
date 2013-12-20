package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JPanel;
// INTERNE
import ressources.Forme;

public class Model extends Observable {
	private ListeDessin listeDessin;
	// Types de modification d'un objet forme
		private Color couleurCourante;
		private String typeCourant, objetCourant; 
		private String extension;
		private boolean travail_enregistre;

	public Model() {
		super();
		this.listeDessin = new ListeDessin();
		this.couleurCourante = Color.BLACK;
		this.typeCourant = "plein";
		this.objetCourant = "droite";
		this.setEnregistre(true); // Pas besoin d'enregistrer lorsque le dessin est vide
		this.extension = ".cth";
	}
	
	// Ajoute une figure à la liste des formes présentes
	// Lorsqu'une forme est ajoutée, l'objet Forme "courant" est envoyé à l'update des vues
	public void addForme(Point pointDebut, Point pointArrivee, JPanel pan){
		Forme courant = new Forme(pointDebut, pointArrivee, typeCourant, objetCourant, couleurCourante);
		listeDessin.add(courant);
		System.out.println("Forme ajoutée"); // DEBUG
		setChanged();
		notifyObservers(courant); // Envoi de l'objet au vues
		setEnregistre(false);
	}

	public Color getColor() {
		return this.couleurCourante; 
	}
	
	public void setColor (Color couleur) {
		this.couleurCourante = couleur;
	}
	
	public boolean getEnregistre() {
		return travail_enregistre;
	}
	
	public void setEnregistre(boolean travail) {
		this.travail_enregistre = travail;
	}
	
	public ListeDessin getListeDessin() {
		return this.listeDessin;
	}
	
	public void setListeDessin(ListeDessin listeDessin) {
		this.listeDessin = listeDessin;
		setChanged();
		notifyObservers();
	}
	 
	public String getExtension() {
		return this.extension;
	}
}