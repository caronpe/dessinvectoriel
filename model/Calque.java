package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
// INTERNE

/**
 * Gère une arrayList de Formes. Encapsule ArrayList pour implémenter Serializable
 * qui gère l'enregistrement et l'ouverture de fichiers.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.1
 */
public class Calque implements Serializable {
	

	static final long serialVersionUID = 1L;
	private ArrayList<Forme> listeDessin;
	
	public Calque() {
		this.listeDessin = new ArrayList<Forme>();
	}
	
	public Calque(ArrayList<Forme> liste) {
		this.listeDessin = liste;
	}
	
	/**
	 * Ajoute une forme à l'arrayList
	 * 
	 * @param forme Forme que l'on souhaite ajouter.
	 */
	public void add(Forme forme) {
		this.listeDessin.add(forme);
	}
	
	public Forme getIdx(int index) {
		return this.listeDessin.get(index);
	}
	
	public void remove(Forme f) {
		this.listeDessin.remove(f);
	}
	
	public void remove(int i) {
		this.listeDessin.remove(i);
	}
	
	/**
	 * Supprime la dernière forme de l'arrayList
	 */
	public void removeLast() {
		this.listeDessin.remove(this.listeDessin.size() - 1);
	}
	
	/**
	 * Supprime toutes les formes de l'arrayList
	 * 
	 */
	public void removeAll() {
		this.listeDessin.removeAll(listeDessin);
	}
	
	/**
	 * @return Un ListIterator qui permets d'être parcourus par next() et previous().
	 * 
	 * @category accessor
	 * 
	 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html">java.util.ListIterator</a>
	 */
	public ListIterator<Forme> iterator() {
		return this.listeDessin.listIterator();
	}
	
	public String toString() {
		return this.listeDessin.toString();
	}
	
	/**
	 * @return La dernière forme de la liste.
	 * 
	 * @category accessor
	 */
	public Forme getLast() {
		if ( listeDessin.size() != 0 ) {
			return this.listeDessin.get(this.listeDessin.size() - 1);
		}
		return null;
	}
	
	/**
	 * @return La taille de la liste.
	 * 
	 * @category accessor
	 */
	public int getSize() {
		return listeDessin.size();
	}
	
	
	/**
	 * 
	 * @return la liste de Forme
	 * @category accessor
	 */
	public ArrayList<Forme> getListeDessin() {
		return listeDessin;
	}

	
	/**
	 *  remplace la liste de forme par une autre
	 * @return void
	 * 
	 */
	public void setListeDessin(Calque listeDessin) {
		this.listeDessin = listeDessin.getListeDessin();
	}
	
	
	/**
	 *  rajoute une liste de forme devant la pr�cedente
	 * @return void
	 * 
	 */
	public void ajouteDevant(Calque listeDessin) {
		this.listeDessin.addAll(listeDessin.getListeDessin());
	}
	
	
	/**
	 *  rajoute une liste de forme derriere la pr�cedente
	 * @return void
	 * 
	 */
	public void ajouteDerriere(Calque listeDessin) {
		this.listeDessin.addAll(0 ,listeDessin.getListeDessin());
	}
	

}
