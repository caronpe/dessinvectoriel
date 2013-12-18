package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JPanel;

public class Model extends Observable {
	ArrayList<Forme> listeDessin ;
	Color selectColor;
	String selectType;
	String selectForme;
	boolean travail_enregistre;

	public Model() {
		super();
		listeDessin = new ArrayList<Forme>();
		selectColor = Color.BLACK;
		selectType = "plein";
		selectForme = "droite";
		this.setEnregistre(false);
	}
	
	//ajoute une figure a la liste des formes presente

	public void addForme(Point deb , Point arr, JPanel pan){
		Forme courant = new Forme(deb , arr , selectType, selectForme , selectColor);
		listeDessin.add(courant);
		System.out.println("forme ajoutée");
		courant.dessiner(pan);
		System.out.println("forme peinte");
		setChanged();
		notifyObservers(courant);
	}	

	public boolean getEnregistre() {
		return travail_enregistre;
	}
	
	public void setEnregistre(boolean travail) {
		this.travail_enregistre = travail;
		setChanged();
		notifyObservers();
	}
}