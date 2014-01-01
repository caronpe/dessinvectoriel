package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * Gère la forme trait.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class FormeLine extends Forme implements Serializable {

	/**
	 * Permets de renvoyer un contains correct. Sera Rectangle2D.Double et Path2D
	 * puisqu'il contiendra des transformations (rotation et translations).
	 * 
	 * @see #initialiserReferentiel()
	 */
	private Shape referentielPosition;

	public FormeLine(Point pointDebut, Point pointArrivee, String type,
			String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, type, objet, couleur, parfait);

		initialiserVariables();
		this.forme = new Line2D.Double(oX, oY, aX, aY);
	}

	protected void initialiserVariables() {
		this.oX = (int) pointDebut.getX();
		this.oY = (int) pointDebut.getY();
		this.aX = (int) pointArrivee.getX();
		this.aY = (int) pointArrivee.getY();
	}
	
	/**
	 * Initialise le rectangle qui servira de reférentiel.
	 * Le référentiel est tout d'abord un rectangle dont l'épaisseur est défini par zoneDeClic.
	 * On calcule l'angle entre le point d'origine et le point d'arrivée.
	 * On effectue une rotation avec l'angle calculé précédemment et avec comme centre
	 * le point d'origine (oX et oY).
	 * On effectue une translation de moins la zone de clic / 2 (= zoneDeClic / (-2) )
	 * pour que le trait soir au milieu de la longueur du rectangle référentiel.
	 * On redéfinit ensuite le reférentiel avec le path auquel on a appliqué les transformations.
	 */
	protected void initialiserReferentiel() {
		// Initialisation par rapport aux 2 points
		int 	oX = (int) pointDebut.getX(),
				oY = (int) pointDebut.getY(),
				aX = (int) pointArrivee.getX(),
				aY = (int) pointArrivee.getY(),
				width = (int) (aX - oX),
				height = (int) (aY - oY),
				zoneDeClic = 40;
		
		// Redéfinition de la hauteur et de la longueur
		height = (int) Math.sqrt((height*height + width*width)); // Hypoténuse = Racine(height² + width²)
		width = zoneDeClic; // Épaisseur de la zone de clic pour le contains
		
		// Première initialisation du référentiel en rectangle classique
		this.referentielPosition = new Rectangle2D.Double(oX, oY, width, height);
		
		// Calcul de langle
		float angle = (float) Math.atan2(oX - aX, aY - oY);
		
		// Rotation
		AffineTransform at = new AffineTransform();
        at.rotate(angle, oX, oY);
        
        // Translation
        at.translate(zoneDeClic / (-2), 0);
        
        // Application des transformation
        PathIterator pi = referentielPosition.getPathIterator(at);
        Path2D path = new Path2D.Float();
        path.append(pi, true);
              
        // Redéfinition du référentiel
		referentielPosition = path;
	}

	/**
	 * Utilise le contains du referentielPosition.
	 */
	public boolean contains(Point2D position) {
		this.initialiserReferentiel();
		return this.referentielPosition.contains(position);
	}

	public void setOrigin(Point pointDebut) {
		this.pointDebut = pointDebut;
		initialiserVariables();
	}

	public void setFin(Point pointArrivee) {
		this.pointArrivee = pointArrivee;
		
		// Réinitialise la forme et le référentiel avec les nouvelles
		// coordonnées.
		initialiserVariables();
		initialiserReferentiel();
		this.forme = new Line2D.Double(oX, oY, aX, aY);
		
	}

	public void draw(Graphics2D graphics) {
		super.draw(graphics);
		graphics.draw(forme);
	}

	public void selectionner(Graphics2D graphics) {
		Color tmp = graphics.getColor();
		graphics.setColor(Color.BLACK);

		// Rectangles des extrémités
		graphics.fillRect(oX - 4, oY - 4, 8, 8);
		graphics.fillRect(aX - 4, aY - 4, 8, 8);
		
		// Rétablissement de la couleur d'origine
		graphics.setColor(tmp);
	}

	@Override
	public boolean containsPointDeSelection(Point2D position) {
		// TODO Auto-generated method stub
		return false;
	}
}