package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class FormeImage extends Forme {
	/** transient : pour ne pas afficher d'erreur de NonSerializable */
	protected transient BufferedImage image, imageRedessinee;
	/** Sera enregistré dans le fichier */
	private ImageIcon icon;

	public FormeImage(Point pointDebut, Point pointArrivee, BufferedImage image){
		super(pointDebut, pointArrivee, false, "image", Color.WHITE, false);
		this.image = image;
		this.icon = new ImageIcon(image);
		
		calculVariables(); // Pour faire en sorte que l'image soit réinitialisée après sa création
	}
		
	public Image getImage(){
		return this.image;
	}
	
	protected void calculVariables() {
		super.calculVariables();
		
		// Instanciation de la forme, de l'image et du référentiel
		if (this.icon != null) {
			this.image = this.iconToImage();
		}
		
		this.referentielPosition = new Rectangle2D.Double(oX , oY , width, height);
		this.imageRedessinee = this.scale();
	}
	
	public void draw(Graphics2D g) {
		calculVariables();
		g.drawImage(this.imageRedessinee, pointOrigin.x, pointOrigin.y, null);
	}
	
	public BufferedImage scale() {
		if (!(this.width <= 0 || this.height <= 0)) {
			// On crée une nouvelle image aux bonnes dimensions
			BufferedImage buf = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);

			// On dessine sur le Graphics de l'image bufferisée
			Graphics2D g = buf.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(this.image, 0, 0, width, height, null);
			g.dispose();

			return buf;
		}
		
		return null;
	}
	
	private BufferedImage iconToImage() {
		BufferedImage newImage = new BufferedImage(this.icon.getIconWidth(),
				this.icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = newImage.createGraphics();
		// paint the Icon to the BufferedImage.
		this.icon.paintIcon(null, g, 0, 0);
		g.dispose();

		return newImage;
	}
	
	@Override
	public void resize(int marqueur, Point pointResize, boolean parfait) {
		super.resize(marqueur, pointResize, false);
	}
}
