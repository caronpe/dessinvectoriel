package model;

import java.awt.AlphaComposite;
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
	 * Points intermédiaires calculés à partir des points principaux (d'origine et de fin)
	 */
	private Point 	pointHautDroit,
					pointBasGauche;
	/**
	 * Ce marqueur est celui assigné lors de l'appel de la méthode {@link #resize(int, Point, boolean)}.
	 * Il ne sert que pour le resize d'une forme parfaite. En effet, les marqueurs du haut ne fonctionnent pas
	 * lors du resize si on fait un height = width au lieu de width = height
	 * 
	 * @see #calculVariablesParfait()
	 */
	private int marqueurCourant;
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
		this.marqueurCourant = -1;
		calculVariables();
	}
	
	/**
	 * Calcule selon les différentes positions du point d'arrivée et du point d'origine.
	 * Réagis à la touche SHIFT appuyé pour créer une forme parfaite.
	 * Initialise les marqueurs de sélection du rectangle.
	 * en les définissant comme parfait.
	 */
	protected void calculVariables() {
		if (this.parfait) {
			calculVariablesParfait();
		} else {
			// Calculs pour l'initialisation du référentiel
			this.oX = Math.min( (int) pointOrigin.getX(), (int) pointFin.getX() );
			this.oY = Math.min( (int) pointOrigin.getY(), (int) pointFin.getY() );
			
			this.aX = Math.max( (int) pointOrigin.getX(), (int) pointFin.getX() );
			this.aY = Math.max( (int) pointOrigin.getY(), (int) pointFin.getY() );
			
			this.width = (int) (aX - oX);
			this.height = (int) (aY - oY);
		}
		
		// On réinitialise les points de la forme
		this.pointOrigin = new Point(oX, oY);
		this.pointFin = new Point(aX, aY);
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
	
	protected void calculVariablesParfait() {
		// Variables initialisées par défaut
		this.oX = (int) pointOrigin.getX();
		this.oY = (int) pointOrigin.getY();
		this.aX = (int) pointFin.getX();
		this.aY = (int) pointFin.getY();
		this.width = (int) (aX - oX);
		this.height = (int) (aY - oY);
					
		// Si on va en haut à gauche du point d'origine
		if ( height < 0 && width < 0 ) { 
			// Le point d'arrivée devient le point d'origine
			this.aX = oX;
			this.aY = oY;
			
			// On prend la valeur absolue de la hauteur
			height = Math.abs(height);
			
			// On enlève la hauteur au point d'origine pour qu'il devienne le point d'arrivée.
			this.oX -= height;
			this.oY -= height;
			
			width = height;
		// Si on part en haut à droite du point d'origine
		} else if ( height < 0 && width > 0) { 
			// On prend la valeur absolue de la hauteur
			height = Math.abs(height);
			
			// On enlève la hauteur au point d'origine 
			oY -= height;
			
			// On ajoute la hauteur au point d'origine			
			aX = oX + height;
			aY += height;
			
			width = height;
		// Si on part en bas à gauche du point d'origine
		} else if ( width < 0 && height > 0) { 
			aX = oX;
			oX -= height;
			
			width = height;
		// Si on part en bas à droite du point d'origine
		} else if (width > 0 && height > 0) { 
			if (marqueurCourant == 0) {
				width = height;
				
				oX = aX - width;
				oY = aY - width;
			} else if (marqueurCourant == 1) {
				width = height;
				aX = oX + width;
				aY = oY + width;
			} else {
				height = width;
				
				aX = oX + width;
				aY = oY + width;
			}
		}
		
		// On réinitialise le marqueur courant
		this.marqueurCourant = -1;
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
	
	public void setFin(Point pointArrivee) {
		this.pointFin = pointArrivee;
		this.calculVariables();
	}
	
	public void setOrigin(Point pointDebut) {
		this.pointOrigin = pointDebut;
		this.calculVariables();
	}
	
	/**
	 * @param pointArrivee
	 */
	private void setHautDroit(Point pointArrivee) {
		this.pointHautDroit = pointArrivee;
		
		this.pointOrigin = new Point( (int) this.pointOrigin.getX(), (int) this.pointHautDroit.getY() );
		this.pointFin = new Point( (int) this.pointHautDroit.getX(), (int) this.pointFin.getY() );

		this.calculVariables();
	}
	
	/**
	 * @param pointDebut
	 */
	private void setBasGauche(Point pointDebut) {
		this.pointBasGauche = pointDebut;
		
		this.pointOrigin = new Point( (int) this.pointBasGauche.getX(), (int) this.pointOrigin.getY() );
		this.pointFin = new Point( (int) this.pointFin.getX(), (int) this.pointBasGauche.getY() );
		
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
	
	/**
	 * Pour chacun des marqueurs, il y a un algorithme différent.
	 * Tout d'abord une sauvegarde des points du rectangle est faites.
	 * On redéfinit les coordonnées en prenant en compte le fait que la position pointResize
	 * n'est pas celle du point du rectangle (Ex pour le haut-gauche : X + 10 et Y + 10 puisque le rectangle référentiel a X - 10 et Y - 10).
	 * On vérifie que les longueur et hauteur ne sont pas négative, sinon le redimensionnement est incorrect.
	 * On vérifie également que le point opposé n'a pas été modifié, sinon lorsque le redimensionnement dépasse ce point, le rectangle
	 * change de position.
	 * 
	 * @param marqueur Le marqueur sélectionné
	 * @param pointResize Les nouvelles coordonnées du marqueur
	 * @param parfait Détermine si c'est une forme parfait ou non
	 * 
	 * @see model.Forme#resize(int, java.awt.Point, boolean)
	 */
	public void resize(int marqueur, Point pointResize, boolean parfait) {
		// Initialisation variables
		this.marqueurCourant = marqueur;
		this.parfait = parfait;
		Point 	tmpOrigin = this.pointOrigin,
				tmpFin = this.pointFin,
				tmpHautDroit = this.pointHautDroit,
				tmpBasGauche= this.pointBasGauche;
		
		switch (marqueur) {
		
		case 0 : // En haut à gauche
			pointResize.setLocation(pointResize.getX() + 10, pointResize.getY() + 10);
			this.setOrigin(pointResize);
			
			if ( ( !(this.getHeight() > 0) && !(this.getWidth() > 0) ) ) { // Test de la taille de la nouvelle forme
				this.setOrigin(tmpOrigin);
			} else if (!this.pointFin.equals(tmpFin)) { // Test du point opposé
				this.setOrigin(tmpOrigin);
				this.setFin(tmpFin);
			}
			break;
			
		case 1 : // En haut à droite
			pointResize.setLocation(pointResize.getX() - 10, pointResize.getY() + 10);
			this.setHautDroit(pointResize);
			
			if ( ( !(this.getHeight() > 0) && !(this.getWidth() > 0) ) ) { // Test de la taille de la nouvelle forme
				this.setHautDroit(tmpHautDroit);
			} else if (!this.pointBasGauche.equals(tmpBasGauche)) { // Test du point opposé
				this.setOrigin(tmpOrigin);
				this.setFin(tmpFin);
			}
			break;
			
		case 2 : // En bas à gauche
			pointResize.setLocation(pointResize.getX() + 10, pointResize.getY() - 10);
			this.setBasGauche(pointResize);
						
			if ( ( !(this.getHeight() > 0) && !(this.getWidth() > 0) ) ) { // Test de la taille de la nouvelle forme
				this.setBasGauche(tmpBasGauche);
			} else if (!this.pointHautDroit.equals(tmpHautDroit)) { // Test du point opposé
				this.setOrigin(tmpOrigin);
				this.setFin(tmpFin);
			}
			break;
			
		case 3 : // En bas à droite
			pointResize.setLocation(pointResize.getX() - 10, pointResize.getY() - 10);
			this.setFin(pointResize);

			if ( ( !(this.getHeight() > 0) && !(this.getWidth() > 0) ) ) { // Test de la taille de la nouvelle forme
				this.setFin(tmpFin);
			} else if (!this.pointOrigin.equals(tmpOrigin)) { // Test du point opposé
				this.setOrigin(tmpOrigin);
				this.setFin(tmpFin);
			}
			break;
			
		default :
			System.err.println("Marqueur non trouvé, problème d'algorithmique.");
			break;
		}
		
		System.out.println("Début : " + pointOrigin + "\nArrivé : " + pointFin); // DEBUG
	}

	public void draw(Graphics2D graphics) {
		super.draw(graphics);
		graphics.draw(forme);
	}
}