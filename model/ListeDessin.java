package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import ressources.Forme;

public class ListeDessin implements Serializable {
	static final long serialVersionUID = 1L;
	private ArrayList<Forme> listeDessin;
	
	public ListeDessin() {
		this.listeDessin = new ArrayList<Forme>();
	}
	
	public void add(Forme forme) {
		this.listeDessin.add(forme);
	}
	
	/**
	 * Supprime la dernière forme de l'arrayList
	 */
	public void removeLast() {
		this.listeDessin.remove(this.listeDessin.size() - 1);
	}
	
	public void removeAll() {
		this.listeDessin.removeAll(listeDessin);
	}
	
	public ListIterator iterator() {
		return this.listeDessin.listIterator();
	}
	
	public String toString() {
		return this.listeDessin.toString();
	}
	
	public Forme getLast() {
		if ( listeDessin.size() != 0 ) {
			return this.listeDessin.get(this.listeDessin.size() - 1);
		}
		return null;
	}
}
