package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class FormeEllipse extends Forme implements Serializable {
	private int oX, oY, aX, aY, height, width;
	
	public FormeEllipse(Point pointDebut, Point pointArrivee, String type, String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, type, objet, couleur, parfait);
		
		// Calculs pour l'initialisation du référentiel
		this.oX = (int) pointDebut.getX();
		this.oY = (int) pointDebut.getY();
		this.aX = (int) pointArrivee.getX();
		this.aY = (int) pointArrivee.getY();
		this.width = (int) (aX - oX);
		this.height = (int) (aY - oY);
		
		initialiserVariables();
		this.referentielPosition = new Ellipse2D.Double(oX, oY, width, height);
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
		
		if ( this.parfait ) { // Forme parfaite en bas à droite du point d'origine
			System.out.println("Shift + cercle"); // DEBUG
			height = width;
		}
	}
	
	public boolean contains(Point position) {
		if (referentielPosition.contains(position)) {
			return true;
		}
		return false;
	}
}