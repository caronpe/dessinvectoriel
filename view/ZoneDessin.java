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
	private int oX, oY, aX, aY, height, width;
	private boolean parfait;
	// Dashed
		final BasicStroke dashed;
	
	
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
		// Dashed
		final float dash1[] = { 10.0f };
		this.dashed = new BasicStroke(1.0f, 
					BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
	}

	/**
	 * Si la forme n'a pas été initialisé, on ne dessine rien
	 * Cela permet d'éviter les erreurs du type NullPointerException 
	 * à la construction de zoneDessin dans la fenêtre principale
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
			dessiner(forme, g2d);
			System.out.println("Formes de listeDessin toutes redessinées"); // DEBUG
		}
		
		if ( courante != null ) { // Si courante n'a pas encore été initialisée
			dessiner(courante, g2d);
			System.out.println("Forme courante dessinée"); // DEBUG
		}
		System.out.println("paint");
	}
	
	/**
	 * Dessine les objets selon les formes qui lui sont envoyé.
	 * Définie également le référentiel qui servira lors de la sélection d'une forme.

	 * 
	 * @param forme Forme qui va être dessinée
	 * @param g2d Graphics qui vient de paintComponent
	 */
	public void dessiner(Forme forme, Graphics2D g2d) {
		g2d.setColor(forme.getCouleur());
		this.oX = (int) forme.getOrigin().getX();
		this.oY = (int) forme.getOrigin().getY();
		this.aX = (int) forme.getFin().getX();
		this.aY = (int) forme.getFin().getY();
		this.width = (int)(aX - oX);
		this.height = (int)(aY - oY);
		this.parfait = forme.getParfait();
		
		switch(forme.getObjet()) {
		case "trait" :
			g2d.draw(forme.getShape());
			break;
		default :
			g2d.fill(forme.getShape());
			break;
		}
			
//		if ( forme.isSelected() ) {
//			g2d.setStroke(dashed);
//			g2d.drawRect(oX - 10, oY - 10, width + 20, height + 20);
//		}
	}
	
	/**
	 * Calcule selon les différentes positions du point d'arrivée.
	 * Réagis à la touche SHIFT appuyé pour le cercle et le rectangle
	 * en les définissant comme parfait.
	 */
	private void initialiserVariables() {
		if ( height < 0 && width < 0 && parfait ) { // Si on va en haut à gauche du point d'origine
			oY -= Math.abs(height);
			oX -= Math.abs(height);
			height = Math.abs(height);
			width = height;
		}
		
		if (width < 0) { // Si on part à gauche du point d'origine
			oX -= Math.abs(width); 
			width = Math.abs(width);
			
			if ( parfait ) { // Forme parfaite
				System.out.println("Shift + cercle"); // DEBUG
				height = width;
			}
		}
		
		if (height < 0) { // Si on part au dessus du point d'origine
			oY -= Math.abs(height);
			height = Math.abs(height);
			
			if ( parfait ) { // Forme parfait
				System.out.println("Shift + cercle"); // DEBUG
				width = height;
			}
		}
		
		if ( parfait ) { // Forme parfaite en bas à droite du point d'origine
			System.out.println("Shift + cercle"); // DEBUG
			height = width;
		}
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
