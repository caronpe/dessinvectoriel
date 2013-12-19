package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JPanel;

public class Model extends Observable {
	private ArrayList<Forme> listeDessin ;
	private Color selectColor;
	private String selectType, selectForme, extension;
	private boolean travail_enregistre;

	public Model() {
		super();
		listeDessin = new ArrayList<Forme>();
		selectColor = Color.BLACK;
		selectType = "plein";
		selectForme = "droite";
		this.setEnregistre(false);
		this.extension = ".cth";
	}
	
	// Ajoute une figure à la liste des formes présentes
	public void addForme(Point deb , Point arr, JPanel pan){
		Forme courant = new Forme(deb, arr, selectType, selectForme, selectColor);
		listeDessin.add(courant);
		System.out.println("Forme ajoutée"); // DEBUG
		
		courant.dessiner(pan);
		System.out.println("Forme peinte"); // DEBUG
		
		setChanged();
		notifyObservers(courant); // Lorsqu'une forme est ajoutée, l'objet Forme "courant" est envoyé à l'update des vues
		
		setEnregistre(false);
	}	

	public Color getColor() {
		return this.selectColor; 
	}
	
	public void setColor (Color couleur) {
		this.selectColor = couleur;
	}
	
	public boolean getEnregistre() {
		return travail_enregistre;
	}
	
	public String getExtension() {
		return this.extension;
	}
	
	public void setEnregistre(boolean travail) {
		this.travail_enregistre = travail;
	}
	
	public String informationsEnregistrement() {
		return listeDessin.toString() + "\n";
	}
}