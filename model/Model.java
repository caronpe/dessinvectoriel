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
	}
	
	//ajoute une figure a la liste des formes presente

	public void addForme(Point deb , Point arr, JPanel pan){
		Forme courant = new Forme(deb , arr , selectType, selectForme , selectColor);
		listeDessin.add(courant);
		courant.dessiner(pan);
	}	
}