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
	public FormeRectangle(Point pointDebut, Point pointArrivee, float strokeFloat, boolean plein, String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, strokeFloat, plein, objet, couleur, parfait);
	}
	
	public FormeRectangle(Point pointDebut, Point pointArrivee, boolean plein, String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, plein, objet, couleur, parfait);
	}
	
	
	@Override
	protected void calculVariables() {
		super.calculVariables();
		
		// Instanciation de la forme et du référentiel
		this.forme = new Rectangle2D.Double(oX, oY, width, height);
		this.referentielPosition = new Rectangle2D.Double(oX - strokeFloat / 2, oY - strokeFloat / 2, width + strokeFloat, height + strokeFloat);
	}
}