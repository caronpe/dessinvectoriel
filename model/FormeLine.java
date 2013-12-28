package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
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
	private int oX, oY, aX, aY, width, height;
	/**
	 * Permets de renvoyer un contains correct.
	 */
	private FormeRectangle referentielPosition;
	
	public FormeLine(Point pointDebut, Point pointArrivee, String type, String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, type, objet, couleur, parfait);
		this.referentielPosition = new FormeRectangle(pointDebut, pointArrivee, type, objet, couleur, parfait);
		
		initialiserVariables();
		this.forme = new Line2D.Double(oX, oY, aX, aY);
	}
	
	/**
	 * Calcule selon les différentes positions du point d'arrivée.
	 * Sans réel intérêt pour cette classe mais pourrait servir à l'avenir.
	 * 
	 */
	private void initialiserVariables() {
		this.oX = (int) pointDebut.getX();
		this.oY = (int) pointDebut.getY();
		this.aX = (int) pointArrivee.getX();
		this.aY = (int) pointArrivee.getY();
	}
	
	/**
	 * Utilise le referentielPosition.
	 * 
	 * @see <a href="http://stackoverflow.com/questions/1797209/how-to-select-a-line">http://stackoverflow.com</a>
	 */
	public boolean contains(Point2D position) {
		return referentielPosition.contains(position);
	}

	@Override
	public void setOrigin(Point pointDebut) {
		this.pointDebut = pointDebut;
		initialiserVariables();
	}

	@Override
	public void setFin(Point pointArrivee) {
		this.pointArrivee = pointArrivee;
		initialiserVariables();

		// Réinitialise la forme et le référentiel avec les nouvelles coordonnées.
		this.forme = new Line2D.Double(oX, oY, aX, aY);
		this.referentielPosition = new FormeRectangle(pointDebut, pointArrivee, type, objet, couleur, parfait);
	}
}