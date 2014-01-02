package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * Gère la forme rectangle.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.3
 */
public class FormeRectangle extends Forme implements Serializable {
	/**
	 * Permets de renvoyer un contains correct.
	 * 
	 * @see #initialiserReferentiel()
	 */
	private Shape referentielPosition;
	
	/**
	 * Même constructeur que la classe abstraite Forme. 
	 * Instancie les coordonnées et appelle une méthode de calcul privée pour les initialiser.
	 * 
	 * @see model.Forme
	 * @see #initialiserVariables
	 */
	public FormeRectangle(Point pointDebut, Point pointArrivee, String type, String objet, Color couleur, boolean parfait) {
		super(pointDebut, pointArrivee, type, objet, couleur, parfait);
		
		this.marqueurs = new Rectangle2D.Double[4];
		calculVariables();
	}
	
	/**
	 * Calcule selon les différentes positions du point d'arrivée.
	 * Réagis à la touche SHIFT appuyé pour le cercle et le rectangle.
	 * Initialise les marqueurs de sélection du rectangle.
	 * en les définissant comme parfait.
	 */
	protected void calculVariables() {
		// Calculs pour l'initialisation du référentiel
		this.oX = Math.min( (int) pointDebut.getX(), (int) pointArrivee.getX() );
		this.oY = Math.min( (int) pointDebut.getY(), (int) pointArrivee.getY() );
		
		this.aX = Math.max( (int) pointDebut.getX(), (int) pointArrivee.getX() );
		this.aY = Math.max( (int) pointDebut.getY(), (int) pointArrivee.getY() );	
		
		this.width = (int) (aX - oX);
		this.height = (int) (aY - oY);
		
		System.out.println(oX + " " + oY + " " + aX + " " + aY + " " + width + " " + height);
		
		if ( this.parfait ) {
			this.width = height;
		}
		
		// On réinitialise les point de la forme
		this.pointDebut = new Point(oX, oY);
		this.pointArrivee = new Point(aX, aY);
		
		// Initialisation des marqueurs
		this.marqueurs[0] = new Rectangle2D.Double(oX - 13, oY - 13, 7, 7); // En haut à gauche
		this.marqueurs[1] = new Rectangle2D.Double(oX + width + 7, oY - 13, 7, 7); // En haut à droite
		this.marqueurs[2] = new Rectangle2D.Double(oX - 13, oY + height + 7, 7, 7); // En bas à gauche
		this.marqueurs[3] = new Rectangle2D.Double(oX + width + 7, oY + height + 7, 7, 7); // En bas à droite
		
		// Instanciation de la forme et du référentiel
		this.forme = new Rectangle2D.Double(oX, oY, width, height);
		this.referentielPosition = new Rectangle2D.Double(oX - 10, oY - 10, width + 20, height + 20);
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
		graphics.draw(referentielPosition);
		
		// Marqueurs
		for (Rectangle2D.Double rectangle : marqueurs) {
			graphics.fill(rectangle);
		}

		// Réinitialisation du graphics avec ses valeurs par défaut
		graphics.setStroke(strokeTmp);
		graphics.setColor(colorTmp); // Rétablissement de la couleur d'origine
	}
	
	public void setFin(Point pointArrivee) {
		this.pointArrivee = pointArrivee;
		this.calculVariables();
	}
	
	public void setOrigin(Point pointDebut) {
		this.pointDebut = pointDebut;
		this.calculVariables();
	}
	
	/**
	 * Le contains se réfère aux contains du référentiel mais aussi aux marqueurs
	 * pour permettre aux DessinListener d'appeler {@link #containsPointDeSelection(Point2D)}
	 * quand nécessaire.
	 * 
	 * @see controler.DessinListener
	 */
	public boolean contains(Point2D position) {
		if (referentielPosition.contains(position) || this.containsPointDeSelection(position)) {
			return true;
		}
		return false;
	}
	
	public boolean containsPointDeSelection(Point2D position) {
		boolean estContenu = false;
		System.out.println("Dans le contains");
		// Compare la position à tous les rectangle de pointsDeSelection
		for (Rectangle2D.Double marqueur : marqueurs) {
			if (marqueur.contains(position)) {
				estContenu = true;
			}
		}
		
		return estContenu;
	}
	
	public int getMarqueurs(Point2D position) {
		// Compare la position à tous les rectangle de pointsDeSelection
		for (int i = 0; i < marqueurs.length; i++) {
			if (marqueurs[i].contains(position)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public void draw(Graphics2D graphics) {
		super.draw(graphics);
		graphics.draw(forme);
	}
}