package model;

import java.awt.Color;
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
		this.setEnregistre(false);
	}
	
	//ajoute une figure a la liste des formes presente

	public void addForme(int xdep, int ydep, int xarr, int yarr, JPanel pan){
		Forme courant = new Forme(xdep, ydep, xarr, yarr, selectType, selectForme , selectColor);
		listeDessin.add(courant);
		courant.dessiner(pan);
		this.setEnregistre(false);
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

