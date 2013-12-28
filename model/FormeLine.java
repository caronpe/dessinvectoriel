package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class FormeLine extends Forme implements Serializable {
	private int oX, oY, aX, aY;
	
	public FormeLine(Point pointDebut, Point pointArrivee, String type, String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, type, objet, couleur, parfait);
		
		// Calculs pour l'initialisation du référentiel
		this.oX = (int) pointDebut.getX();
		this.oY = (int) pointDebut.getY();
		this.aX = (int) pointArrivee.getX();
		this.aY = (int) pointArrivee.getY();
		
		this.referentielPosition = new Line2D.Double(oX, oY, aX, aY);
	}
	/**
	 * Le contains d'une ligne renvoie toujours faux.
	 * TODO Créer une nouvelle fonction qui gère correctement le contains
	 * 
	 * @see <a ref="http://stackoverflow.com/questions/1797209/how-to-select-a-line">tuto</a>
	 */
	public boolean contains(Point2D position) {
		if (referentielPosition.contains(position)) {
			return true;
		}
		return false;
	}

	@Override
	public void setOrigin(Point pointDebut) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFin(Point pointArrivee) {
		// TODO Auto-generated method stub
		
	}
}