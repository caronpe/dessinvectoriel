package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
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
		
		calculVariables();
		this.forme = new Ellipse2D.Double(oX, oY, width, height);
	}
	
	/**
	 * Calcule selon les différentes positions du point d'arrivée.
	 * Réagis à la touche SHIFT appuyé pour le cercle et le rectangle
	 * en les définissant comme parfait.
	 */
	protected void calculVariables() {
		// Calculs pour l'initialisation du référentiel
		this.oX = (int) pointOrigin.getX();
		this.oY = (int) pointOrigin.getY();
		this.aX = (int) pointFin.getX();
		this.aY = (int) pointFin.getY();
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
				height = width;
			}
		}
		
		if (height < 0) { // Si on part au dessus du point d'origine
			oY -= Math.abs(height);
			height = Math.abs(height);
			
			if ( parfait ) { // Forme parfait
				width = height;
			}
		}
		
		if ( this.parfait ) { // Forme parfaite en bas à droite du point d'origine
			height = width;
		}
	}
	
	public void selectionner(Graphics2D graphics) {
		// Initialisation du stroke en pointillé
		final float dash1[] = { 10.0f };
		BasicStroke dashed = 	new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
								BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		Stroke strokeTmp = graphics.getStroke();
		graphics.setStroke(dashed);
		Color colorTmp = graphics.getColor();
		graphics.setColor(Color.BLACK);

		// Rectangle en pointillés
		graphics.drawRect(oX - 10, oY - 10, width + 20, height + 20);

		// Rectangles des extrémités
		graphics.fillRect(oX - 13, oY - 13, 7, 7);
		graphics.fillRect(oX + width + 7, oY - 13, 7, 7);
		graphics.fillRect(oX - 13, oY + height + 7, 7, 7);
		graphics.fillRect(oX + width + 7, oY + height + 7, 7, 7);

		// Réinitialisation du graphics avec ses valeurs par défaut
		graphics.setStroke(strokeTmp);
		graphics.setColor(colorTmp); // Rétablissement de la couleur d'origine
	}
	
	public void setFin(Point pointArrivee) {
		this.pointFin = pointArrivee;
		this.calculVariables();
		this.forme = new Ellipse2D.Double(oX, oY, width, height);
	}
	
	public void setOrigin(Point pointDebut) {
		this.pointOrigin = pointDebut;
		this.calculVariables();
	}
	
	public boolean contains(Point2D position) {
		if (forme.contains(position)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsPointDeSelection(Point2D position) {
		// TODO Auto-generated method stub
		return false;
	}
}