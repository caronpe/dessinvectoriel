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
	private Shape referentielPositionLine;
	
	public FormeLine(Point pointDebut, Point pointArrivee, String type, String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, type, objet, couleur, parfait);

		this.marqueurs = new Rectangle2D.Double[2];
		this.calculVariables();
	}

	@Override
	protected void calculVariables() {
		// Variables initialisées par défaut
		this.oX = (int) pointOrigin.getX();
		this.oY = (int) pointOrigin.getY();
		this.aX = (int) pointFin.getX();
		this.aY = (int) pointFin.getY();
		
		this.initialiserReferentiel();
		
		// Instanciation des marqueurs
		this.marqueurs[0] = new Rectangle2D.Double(oX - 4, oY - 4, 8, 8); // Point d'origine
		this.marqueurs[1] = new Rectangle2D.Double(aX - 4, aY - 4, 8, 8); // Point de fin
		
		// Instanciation de la forme et du référentiel
		this.forme = new Line2D.Double(oX, oY, aX, aY);
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
		int 	oX = (int) pointOrigin.getX(),
				oY = (int) pointOrigin.getY(),
				aX = (int) pointFin.getX(),
				aY = (int) pointFin.getY(),
				width = (int) (aX - oX),
				height = (int) (aY - oY),
				zoneDeClic = 40;
		
		// Redéfinition de la hauteur et de la longueur
		height = (int) Math.sqrt((height*height + width*width)); // Hypoténuse = Racine(height² + width²)
		width = zoneDeClic; // Épaisseur de la zone de clic pour le contains
		
		// Première initialisation du référentiel en rectangle classique
		this.referentielPositionLine = new Rectangle2D.Double(oX, oY, width, height);
		
		// Initialisation du référentiel en rectangle classique
		FormeRectangle tmp = new FormeRectangle(pointOrigin, pointFin, "plein", "rectangle", Color.black, false);
		this.referentielPosition = (Rectangle2D.Double)tmp.getShape();
		
		// Calcul de langle
		float angle = (float) Math.atan2(oX - aX, aY - oY);
		
		// Rotation
		AffineTransform at = new AffineTransform();
        at.rotate(angle, oX, oY);
        
        // Translation
        at.translate(zoneDeClic / (-2), 0);
        
        // Application des transformation
        PathIterator pi = referentielPositionLine.getPathIterator(at);
        Path2D path = new Path2D.Float();
        path.append(pi, true);
              
        // Redéfinition du référentiel
		referentielPositionLine = path;
	}


	@Override
	public void setOrigin(Point pointDebut) {
		this.pointOrigin = pointDebut;
		
		calculVariables();
	}

	@Override
	public void setFin(Point pointArrivee) {
		this.pointFin = pointArrivee;
		
		calculVariables();
	}

	@Override
	public void selectionner(Graphics2D graphics) {
		Color tmp = graphics.getColor();
		graphics.setColor(Color.GRAY);

		// Marqueurs
		for (Rectangle2D.Double rectangle : marqueurs) {
			graphics.fill(rectangle);
		}
		
		// Rétablissement de la couleur d'origine
		graphics.setColor(tmp);
	}
	
	@Override
	public void resize(int marqueur, Point pointResize, boolean parfait) {
		// Initialisation variables
		Point 	tmpOrigin = this.pointOrigin,
				tmpFin = this.pointFin;
		
		switch (marqueur) {
		case 0 : // Origine
			this.setOrigin(pointResize);
			break;
			
		case 1 : // Fin
			this.setFin(pointResize);
			break;
		}
	}
	
	@Override
	public boolean contains(Point2D point) {
		return referentielPositionLine.contains(point);
	}
}