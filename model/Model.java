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

	public Model() {
		super();
		listeDessin = new ArrayList<Forme>();
		selectColor = Color.BLACK;
		selectType = "plein";
		selectForme = "rond";
	}
	
	//ajoute une figure a la liste des formes presente

	public void addForme(Point deb , Point arr, JPanel pan){
		Forme courant = new Forme(deb , arr , selectType, selectForme , selectColor);
		listeDessin.add(courant);
		System.out.println("forme ajouté");
		courant.dessiner(pan);
		System.out.println("forme painte");
		setChanged();
		notifyObservers(courant);
	}	
}