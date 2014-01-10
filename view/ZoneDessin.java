package view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

import javax.swing.JPanel;

// INTERNE
import model.Calque;
import model.Forme;
import model.FormeRectangle;
import model.Model;
import ressources.DimensionMenuDroit;


/**
 * Listener qui régit les actions de la souris sur la zone de dessin.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.3
 */
public class ZoneDessin extends JPanel {
	private Forme courante;
	private Model model;
	// Selection
	private FormeRectangle rectangleSelection;

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
		this.rectangleSelection = null;
	}

	/**
	 * Parcours la liste de dessin si elle n'est pas vide et redessine toutes
	 * les formes, puis dessine la forme courante. Si la forme n'a pas été
	 * initialisé, on ne dessine rien Cela permet d'éviter les erreurs du type
	 * NullPointerException à la construction de zoneDessin dans la fenêtre
	 * principale.
	 * Gère également la modification du curseur
	 * 
	 * @category accessor
	 * 
	 */
	public void paintComponent(Graphics g) {	
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		ListIterator<Forme> it = model.getAllFormes().listIterator();

		// Parcours de la liste pour redessiner toutes les formes
		while (it.hasNext()) {
			Forme forme = it.next();
			g2d.setStroke(forme.getStroke());
			forme.draw(g2d);
			if (forme.isSelected()) {
				forme.selectionner(g2d);
			}
		}

		// Si courante a été initialisée
		if (courante != null) {
			g2d.setStroke(courante.getStroke());
			courante.draw(g2d);
		}

		// Gestion du curseur de redimensionnement
		if (this.model.getRedimensionnement() == model.NORTH_WEST_CURSOR) {
			this.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
		} else if (this.model.getRedimensionnement() == model.NORTH_EAST_CURSOR ) {
			this.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
		} else if (this.model.getCreation()) {
			this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		} else {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		if (this.rectangleSelection != null) {
			g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
			rectangleSelection.draw(g2d);
			g2d.setComposite(AlphaComposite.SrcOver.derive(1.0f));
		}
	}

	private void paintCalque(Graphics g, Calque calque) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// Strokes
		BasicStroke tmp = (BasicStroke) g2d.getStroke();		
		
		// Parcours de la liste pour redessiner toutes les formes
		ListIterator<Forme> it = calque.listIterator();
		while (it.hasNext()) {
			Forme forme = it.next();
			g2d.setStroke(forme.getStroke());
			forme.draw(g2d);
		}
		
		// Rétablissement de l'épaisseur de graphics
		g2d.setStroke(tmp);
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

	public void dessinMultiSelection(FormeRectangle rectangleSelection) {
		this.rectangleSelection = rectangleSelection;
		this.repaint();
	}

	/**
	 * Transforme la zone le panel actuelle en une image au format du calque view.
	 * Elle est initialisée de sorte de créer un rectangle bland si le calque est
	 * vide (comme au démarrage de l'application).
	 * 
	 * @return Image
	 */
	public Image getImage(Calque calque){
		int width = this.getWidth();
		int height = this.getHeight();

		// Si la zone de dessin est vide, elle a des dimensions nulles, On corrige donc ce problème
		if (width == 0 || height == 0) {
			width = DimensionMenuDroit.width;
			height = DimensionMenuDroit.height;
		}

		// Création de l'image
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// Initialisation du graphics
		Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// On applique le calque au graphics
		this.paintCalque(g, calque);
		g.dispose();

		return scale(image, calque);
	}

	/** 
	 * Redimensionne une image.
	 * Crée un voile transparent si le calque reçu
	 * est le même que le calque courant du modèle.
	 * 
	 * @param source Image à redimensionner.
	 * @param calque Calque qui est à dessiner sur l'image
	 * 
	 * @return Image redimensionnée.
	 */
	public Image scale(Image source, Calque calque) {
		int width = DimensionMenuDroit.width;
		int height = DimensionMenuDroit.height;
		// On crée une nouvelle image aux bonnes dimensions
		BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// On dessine sur le Graphics de l'image bufferisée
		Graphics2D g = buf.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(source, 0, 0, width, height, null);

		// Dessin d'un voile transparent pour notifier le calque courant
		if (calque == this.model.getCalqueCourant()) {
			g.setComposite(AlphaComposite.SrcOver.derive(0.5f));
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, width, height);
		}
		g.dispose();

		// On retourne l'image bufferis�e, qui est une image
		return buf;
	}
}