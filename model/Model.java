package model;

import java.util.Observable;

public class Model extends Observable {

import java.awt.Color;
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

	public void addForme(int xdep, int ydep, int xarr, int yarr, JPanel pan){
		Forme courant = new Forme(xdep, ydep, xarr, yarr, selectType, selectForme , selectColor);
		listeDessin.add(courant);
		courant.dessiner(pan);

	}

	
}