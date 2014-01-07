package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class FormeImage extends Forme {
	protected BufferedImage image, imageRedessinee;

	public FormeImage(Point pointDebut, Point pointArrivee, BufferedImage image){
		super(pointDebut, pointArrivee, "image", "image");
		this.image = image;
		
		calculVariables(); // Pour faire en sorte que l'image soit réinitialisée après sa création
	}
		
	public Image getImage(){
		return this.image;
	}
	
	protected void calculVariables() {
		super.calculVariables();
		
		// Instanciation de la forme et du référentiel
		this.referentielPosition = new Rectangle2D.Double(oX , oY , width, height);
		this.imageRedessinee = this.scale();
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(this.imageRedessinee, pointOrigin.x, pointOrigin.y, null);
	}
	
	public BufferedImage scale() {
		// On crée une nouvelle image aux bonnes dimensions
		BufferedImage buf = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);

		// On dessine sur le Graphics de l'image bufferisée
		Graphics2D g = buf.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(this.image, 0, 0, width, height, null);
		g.dispose();

		return buf;
	}
}
