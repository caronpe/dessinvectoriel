package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class FormeImage extends Forme {
	protected BufferedImage image;

	public FormeImage(Point pointDebut, Point pointArrivee, BufferedImage image){
		super(pointDebut, pointArrivee, "image", "image");
		this.image = image;
		
		this.marqueurs = new Rectangle2D.Double[4];
		calculVariables();
	}
	
	public boolean contains(Point2D position){
		return this.referentielPosition.contains(position);
	}
	
	public Image getImage(){
		return this.image;
	}
	
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
		this.referentielPosition = new Rectangle2D.Double(oX , oY , width, height);
	} 
	
	public void draw(Graphics2D g) {
		g.drawImage(this.image, pointOrigin.x, pointOrigin.y, null);
	}
}
