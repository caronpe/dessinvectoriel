package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

import javax.swing.JPanel;

// INTERNE
import model.Model;
import ressources.Forme;

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
	 * Si la forme n'a pas été initialisé, on ne dessine rien
	 * Cela permet d'éviter les erreurs du type NullPointerException 
	 * à la construction de zoneDessin dans la fenêtre principale
	 * @category accessor
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Iterator<Forme> it = model.getListeDessin().iterator();
		while ( it.hasNext() ) { // Parcours de la liste pour redissiner toutes les formes
		      Forme forme = it.next();
		      dessiner(forme, g);
		      System.out.println("Formes de listeDessin toutes redessinées"); // DEBUG
		}
		if ( courante != null ) { // Si courante n'a pas encore été initialisée
			dessiner(courante, g);
			System.out.println("Forme courante dessinée"); // DEBUG
		}
	}
	
	/**
	 * Dessine les objets selon les formes qui lui sont envoyé

	 * 
	 * @param forme Forme qui va être dessinée
	 * @param g Graphics qui vient de paintComponent
	 */
	public void dessiner(Forme forme, Graphics g) {
		g.setColor(forme.getCouleur());
		this.oX = (int) forme.getOrigin().getX();
		this.oY = (int) forme.getOrigin().getY();
		this.aX = (int) forme.getFin().getX();
		this.aY = (int) forme.getFin().getY();
		this.width = (int)(aX - oX);
		this.height = (int)(aY - oY);
		this.parfait = forme.getParfait();
		
		
		switch (forme.getForme()) { // Sélectionne l'outil du modèle
		
		case "cercle" :
			initialiserVariables();
			g.fillOval(oX, oY, width, height);
			break;
		case "rectangle" :
			initialiserVariables();
			g.fillRect(oX, oY, width, height);
			break;
		case "trait" : 
			g.drawLine(oX, oY, aX, aY);
			break;
		}
		
		forme.setReferentiel(new Rectangle(oX, oY, width, height));
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
