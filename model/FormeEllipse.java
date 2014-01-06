package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Gère la forme ellipse.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class FormeEllipse extends Forme {
		
	/**
	 * Même constructeur que la classe abstraite Forme. 
	 * Instancie les coordonnées et appelle une méthode de calcul privée pour les initialiser. 
	 * 
	 * @see model.Forme
	 * @see #initialiserVariables
	 */
	public FormeEllipse(Point pointDebut, Point pointArrivee, String type, String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, type, objet,couleur, parfait);

		this.marqueurs = new Rectangle2D.Double[4];
		calculVariables();
	}
	
	/**
	 * Calcule selon les différentes positions du point d'arrivée.
	 * Réagis à la touche SHIFT appuyé pour le cercle et le rectangle
	 * en les définissant comme parfait.
	 */
	protected void calculVariables() {
		super.calculVariables();
		
		// On initialiser/réinitialise les points secondaires de la forme
		this.pointBasGauche = new Point(oX, aY);
		this.pointHautDroit= new Point(aX ,oY);
		
		// Instanciation des marqueurs
				this.marqueurs[0] = new Rectangle2D.Double(oX - 4, oY - 4, 8, 8); // En haut à gauche
				this.marqueurs[1] = new Rectangle2D.Double(oX + width - 4, oY - 4, 8, 8); // En haut à droite
				this.marqueurs[2] = new Rectangle2D.Double(oX - 4, oY + height - 4, 8, 8); // En bas à gauche
				this.marqueurs[3] = new Rectangle2D.Double(oX + width - 4, oY + height - 4, 8, 8); // En bas à droite
				
		// Instanciation de la forme et du référentiel
		this.forme = new Ellipse2D.Double(oX, oY, width, height);
		this.referentielPosition = new Rectangle2D.Double(oX , oY , width, height);
	}
}