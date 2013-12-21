package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import ressources.Forme;

public class ListeDessin implements Serializable {
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
	
	public Iterator iterator() {
		return this.listeDessin.iterator();
	}
	
	public String toString() {
		return this.listeDessin.toString();
	}
}
