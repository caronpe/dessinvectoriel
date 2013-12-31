package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * Gère la forme ellipse.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class FormeEllipse extends Forme implements Serializable {
	
	/**
	 * Même constructeur que la classe abstraite Forme. 
	 * Instancie les coordonnées et appelle une méthode de calcul privée pour les initialiser. 
	 * 
	 * @see model.Forme
	 * @see #initialiserVariables
	 */
	public FormeEllipse(Point pointDebut, Point pointArrivee, String type, String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, type, objet, couleur, parfait);
		
		initialiserVariables();
		this.forme = new Ellipse2D.Double(oX, oY, width, height);
	}
	
	/**
	 * Calcule selon les différentes positions du point d'arrivée.
	 * Réagis à la touche SHIFT appuyé pour le cercle et le rectangle
	 * en les définissant comme parfait.
	 */
	protected void initialiserVariables() {
		// Calculs pour l'initialisation du référentiel
		this.oX = (int) pointDebut.getX();
		this.oY = (int) pointDebut.getY();
		this.aX = (int) pointArrivee.getX();
		this.aY = (int) pointArrivee.getY();
		this.width = (int) (aX - oX);
		this.height = (int) (aY - oY);
				
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
		
		if ( this.parfait ) { // Forme parfaite en bas à droite du point d'origine
			System.out.println("Shift + cercle"); // DEBUG
			height = width;
		}
	}
	
	public void setFin(Point pointArrivee) {
		this.pointArrivee = pointArrivee;
		this.initialiserVariables();
		this.forme = new Ellipse2D.Double(oX, oY, width, height);
	}
	
	public void setOrigin(Point pointDebut) {
		this.pointDebut = pointDebut;
		this.initialiserVariables();
	}
	
	public boolean contains(Point2D position) {
		if (forme.contains(position)) {
			return true;
		}
		return false;
	}
}