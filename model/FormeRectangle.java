package model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
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
		
		this.marqueurs = new Rectangle2D.Double[4];
		calculVariables();
	}
	
	@Override
	protected void calculVariables() {
		super.calculVariables();
		
		// On initialiser/réinitialise les points secondaires de la forme
		this.pointBasGauche = new Point(oX, aY);
		this.pointHautDroit= new Point(aX ,oY);
		
		// Instanciation des marqueurs
		this.marqueurs[0] = new Rectangle2D.Double(oX - 13, oY - 13, 7, 7); // En haut à gauche
		this.marqueurs[1] = new Rectangle2D.Double(oX + width + 7, oY - 13, 7, 7); // En haut à droite
		this.marqueurs[2] = new Rectangle2D.Double(oX - 13, oY + height + 7, 7, 7); // En bas à gauche
		this.marqueurs[3] = new Rectangle2D.Double(oX + width + 7, oY + height + 7, 7, 7); // En bas à droite
		
		// Instanciation de la forme et du référentiel
		this.forme = new Rectangle2D.Double(oX, oY, width, height);
		this.referentielPosition = new Rectangle2D.Double(oX - 10, oY - 10, width + 20, height + 20);
	}
	
	public void selectionner(Graphics2D graphics) {
		// Sauvegarde des variables
		Stroke strokeTmp = graphics.getStroke();
		Color colorTmp = graphics.getColor();
		
		// Initialisation du stroke en pointillé
		final float dash1[] = { 10.0f };
		BasicStroke dashed = 	new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
								BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		
		graphics.setComposite(AlphaComposite.SrcOver.derive(0.9f));
		graphics.setStroke(dashed);
		graphics.setColor(Color.GRAY);

		// Rectangle en pointillés
		graphics.draw(referentielPosition);
		
		// Marqueurs
		for (Rectangle2D.Double rectangle : marqueurs) {
			graphics.fill(rectangle);
		}

		// Réinitialisation du graphics avec ses valeurs par défaut
		graphics.setStroke(strokeTmp);
		graphics.setColor(colorTmp); // Rétablissement de la couleur d'origine
		graphics.setComposite(AlphaComposite.SrcOver); // Rétablissement de la transparence d'origine
	}

	
	
	
}