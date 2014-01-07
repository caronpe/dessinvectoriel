package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 * Gère la forme rectangle.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.3
 */
public class FormeRectangle extends Forme {

	/**
	 * Même constructeur que la classe abstraite Forme. 
	 * Instancie les coordonnées et appelle une méthode de calcul privée pour les initialiser.
	 * 
	 * @see model.Forme
	 * @see #initialiserVariables
	 */
	public FormeRectangle(Point pointDebut, Point pointArrivee, String type, String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, type, objet, couleur, parfait);
		
		calculVariables();
	}
	
	@Override
	protected void calculVariables() {
		super.calculVariables();
		
		// Instanciation de la forme et du référentiel
		this.forme = new Rectangle2D.Double(oX, oY, width, height);
		this.referentielPosition = new Rectangle2D.Double(oX, oY, width, height);
	}
}