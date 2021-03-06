package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Gère une arrayList de Formes. Encapsule ArrayList pour implémenter Serializable
 * qui gère l'enregistrement et l'ouverture de fichiers.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class Calque implements Serializable {
	private ArrayList<Forme> listeDessin;
	private boolean afficher;
	
	/**
	 * Initialise la visibilité à afficher.
	 */
	public Calque() {
		this.listeDessin = new ArrayList<Forme>();
		this.afficher=true;
	}
	
	/**
	 * @param liste Liste de formes que l'on souhaite appliquer au calque.
	 */
	public Calque(ArrayList<Forme> liste) {
		this.listeDessin = new ArrayList<Forme>();
		this.listeDessin.addAll(liste);
		this.afficher=true;
	}
	
	/**
	 * Ajoute une forme à l'arrayList
	 * 
	 * @param forme Forme que l'on souhaite ajouter.
	 */
	public void add(Forme forme) {
		this.listeDessin.add(forme);
	}
	
	/**
	 * @param index Indice à partir duquel on souhaite récupérer la forme
	 * 
	 * @return La forme à l'indice
	 * 
	 * @category accessor
	 */
	public Forme get(int index) {
		return this.listeDessin.get(index);
	}
	
	/**
	 * @param f Forme à supprimer
	 */
	public void remove(Forme f) {
		this.listeDessin.remove(f);
	}
	
	/**
	 * @param i Indice à partir duquel on va supprimer la forme
	 */
	public void remove(int i) {
		this.listeDessin.remove(i);
	}
	
	/**
	 * Supprime la dernière forme de l'arrayList.
	 */
	public void removeLast() {
		this.listeDessin.remove(this.listeDessin.size() - 1);
	}
	
	/**
	 * Supprime toutes les formes de l'arrayList.
	 */
	public void removeAll() {
		this.listeDessin.removeAll(listeDessin);
	}
	
	/**
	 * @return Un ListIterator qui permet d'être parcouru par next() et previous().
	 * 
	 * @category accessor
	 * 
	 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html">java.util.ListIterator</a>
	 */
	public ListIterator<Forme> listIterator() {
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
	 * @return la liste de Forme
	 * 
	 * @category accessor
	 */
	public ArrayList<Forme> getListeDessin() {
		return listeDessin;
	}

	/**
	 * Remplace la liste de forme par une autre.
	 * 
	 * @param listeDessin Liste de dessin à remplacer.
	 * 
	 * @category accessor
	 */
	public void setListeDessin(Calque listeDessin) {
		this.listeDessin = listeDessin.getListeDessin();
	}
	
	/**
	 * Rajoute une liste de forme avant la precédente.
	 * 
	 * @param listeDessin Liste de dessin à ajouter devant.
	 */
	public void ajouteDevant(Calque listeDessin) {
		this.listeDessin.addAll(listeDessin.getListeDessin());
	}
	
	/**
	 * Rajoute une liste de forme derrière la précedente.
	 * 
	 * @param listeDessin Liste de dessin à ajouter derrière.
	 */
	public void ajouteDerriere(Calque listeDessin) {
		this.listeDessin.addAll(0 ,listeDessin.getListeDessin());
	}
	
	/**
	 * @return La visibilité du calque
	 * 
	 * @category accessor
	 */
	public boolean getAfficher(){
		return this.afficher;
	}
	
	/**
	 * @param afficher Booléen déterminant la visibilité du calque
	 * 
	 * @category accessor
	 */
	public void setAfficher(boolean afficher){
		this.afficher = afficher;
	}

}
