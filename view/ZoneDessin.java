package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

import javax.swing.JPanel;



import ressources.DimensionMenuDroit;

//INTERNE
import model.Forme;
import model.Model;

/**
 * Listener qui régit les actions de la souris sur la zone de dessin
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ZoneDessin extends JPanel {
	private Forme courante;
	private Model model;

	/**
	 * Panel de couleur blanche Aucune forme n'est sélectionnée par défaut
	 * 
	 * @param model
	 *            Modèle du MVC
	 */
	public ZoneDessin(Model model) {
		super();
		this.courante = null;
		this.model = model;
		this.setBackground(Color.WHITE);
	}

	/**
	 * Parcours la liste de dessin si elle n'est pas vide et redessine toutes
	 * les formes, puis dessine la forme courante. Si la forme n'a pas été
	 * initialisé, on ne dessine rien Cela permet d'éviter les erreurs du type
	 * NullPointerException à la construction de zoneDessin dans la fenêtre
	 * principale.
	 * 
	 * @category accessor
	 * 
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		ListIterator<Forme> it = model.getListeDessin().iterator();
		
		// Parcours de la liste pour redessiner toutes les formes
		while (model.getListeDessin().getSize() != 0 && it.hasNext()) {
			Forme forme = it.next();
			forme.draw(g2d);
			if (forme.isSelected()) {
				forme.selectionner(g2d);
			}
		}

		if (courante != null) { // Si courante n'a pas encore été initialisée
			courante.draw(g2d);
		}
	}

	/**
	 * @category accessor
	 * 
	 * @return Forme courante
	 */
	public Forme getCourante() {
		return courante;
	}

	/**
	 * @category accessor
	 * 
	 * @param courante
	 *            Modifie la forme courante
	 */
	public void setCourante(Forme courante) {
		this.courante = courante;
	}
	
	
	/**
	 * Transforme la zone le panel actuel en une image au format du calque view
	 * 
	 * @return Image
	 */
	public Image getImage(){
		   int width = this.getWidth();
		   int height = this.getHeight();
		   BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		   Graphics2D g = image.createGraphics();
		   this.paintAll(g);
		   g.dispose();
		   
		
		   return scale(image);
		}

	/** 
	 * Redimensionne une image.
	 * 
	 * @param source Image � redimensionner.
	 * @param width Largeur de l'image cible.
	 * @param height Hauteur de l'image cible.
	 * @return Image redimensionn�e.
	 */
	public static Image scale(Image source) {
		int width = new DimensionMenuDroit().width;
		int height = new DimensionMenuDroit().height;
	    /* On cr�e une nouvelle image aux bonnes dimensions. */
	    BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	 
	    /* On dessine sur le Graphics de l'image bufferis�e. */
	    Graphics2D g = buf.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(source, 0, 0, width, height, null);
	    g.dispose();
	 
	    /* On retourne l'image bufferis�e, qui est une image. */
	    return buf;
	}
}