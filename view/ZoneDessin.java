package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JPanel;



import model.Forme;
// INTERNE
import model.Model;

/**
 * Listener qui régit les actions de la souris sur la zone de dessin
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ZoneDessin extends JPanel {
	private Forme courante;
	private Model model;
	
	/**
	 * Panel de couleur blanche
	 * Aucune forme n'est sélectionnée par défaut
	 * 
	 * @param model Modèle du MVC
	 */
	public ZoneDessin(Model model) {
		super();
		this.courante = null;
		this.model = model;
		this.setBackground(Color.WHITE);
	}

	/**
	 * Parcours la liste de dessin si elle n'est pas vide et redessine toutes les formes,
	 * puis dessine la forme courante.
	 * Si la forme n'a pas été initialisé, on ne dessine rien
	 * Cela permet d'éviter les erreurs du type NullPointerException 
	 * à la construction de zoneDessin dans la fenêtre principale.
	 * 
	 * @category accessor
	 * 
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		ListIterator<Forme> it = model.getListeDessin().iterator();
		System.out.println("Taille : " + model.getListeDessin().getSize());
		while ( model.getListeDessin().getSize() != 0 && it.hasNext() ) { // Parcours de la liste pour redessiner toutes les formes
			System.out.println("dans la boucle");
			Forme forme = it.next();
			forme.draw(g2d);
			if ( forme.isSelected() ) {
				forme.selectionner(g2d);
				System.out.println(forme + " est sélectionnée."); // DEBUG
			}
			System.out.println("Formes de listeDessin toutes redessinées"); // DEBUG
		}
		
		if ( courante != null ) { // Si courante n'a pas encore été initialisée
			courante.draw(g2d);
			System.out.println("Forme courante dessinée"); // DEBUG
		}
		System.out.println("paint");
		
		
	}

	/**
	 * @category accessor
	 * 
	 * @return Forme courante
	 */
	public Forme getCourante() {
		return courante;
	}

	/**
	 * @category accessor
	 * 
	 * @param courante Modifie la forme courante
	 */
	public void setCourante(Forme courante) {
		this.courante = courante;
	}
	
}
