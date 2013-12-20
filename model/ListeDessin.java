package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ListeDessin implements Serializable {
	private ArrayList<Forme> listeDessin;
	
	public ListeDessin() {
		this.listeDessin = new ArrayList<Forme>();
	}
	
	public void add(Forme forme) {
		this.listeDessin.add(forme);
	}
	
	public Iterator iterator() {
		return this.listeDessin.iterator();
	}
	
	public String toString() {
		return this.listeDessin.toString();
	}
}
