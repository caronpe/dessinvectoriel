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
 * Contient toutes les caractéristiques de l'objet Forme Chaque fois qu'une
 * forme est créée, un rectangle est créé, il facilitera les déplacements de la
 * forme
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.3
 */
public abstract class Forme implements Serializable, Cloneable {
	protected int oX, oY, aX, aY, width, height;
	protected Shape forme;
	/**
	 * Permets de renvoyer un contains correct.
	 * 
	 * @see #initialiserReferentiel()
	 */
	protected Rectangle2D.Double referentielPosition;
	/**
	 * Points intermédiaires calculés à partir des points principaux (d'origine et de fin)
	 */
	protected Point pointHautDroit, pointBasGauche;
	protected Point pointOrigin, pointFin;
	protected String objet;
	protected boolean plein;
	protected Color couleur;
	/**
	 * Ce booléen est utilisé comme appelation. L'algorithme vérifie régulièrement
	 * s'il a affaire à une forme parfaite ou non puisque que ce booléen peut changer à tout moment.
	 * /!\ Une fois les variables de la forme initialisées avec les coordonnées d'une forme parfaite,
	 * ce booléen n'est plus utile dans le sens où il ne sert en aucun cas au dessin de la forme pour
	 * permettre une certaine viabilité de l'algorithmique /!\
	 */
	protected boolean parfait;
	protected boolean selected;
	protected Rectangle2D.Double[] marqueurs;
	/**
	 * Ce marqueur est celui assigné lors de l'appel de la méthode {@link #resize(int, Point, boolean)}.
	 * Il ne sert que pour le resize d'une forme parfaite. En effet, les marqueurs du haut ne fonctionnent pas
	 * lors du resize si on fait un height = width au lieu de width = height
	 * 
	 * @see #calculVariablesParfait()
	 */
	protected int marqueurCourant;
	
	/**
	 * Epaisseur du trait de la forme
	 */
	protected Stroke stroke;
	protected float strokeFloat;

	/**
	 * Constructeur basique de la forme à dessiner avec ses coordonnées
	 * vectorielles, sa couleur, sa forme et son type. Définit la forme comme
	 * non sélectionné.
	 * 
	 * @param pointDebut
	 *            Point d'origine de l'objet
	 * @param pointArrivee
	 *            Point final de l'objet
	 * @param type
	 *            Plein, vide
	 * @param objet
	 *            Carré, rond, droite
	 * @param couleur
	 *            Couleur de l'objet
	 * @param parfait
	 *            Définit si la forme est temporaire
	 */
	public Forme(Point pointDebut, Point pointArrivee, float strokeFloat, boolean plein, String objet, Color couleur, boolean parfait) {
		this.pointOrigin = pointDebut;
		this.pointFin = pointArrivee;
		this.plein = plein;
		this.objet = objet;
		this.couleur = couleur;
		this.parfait = parfait;
		this.marqueurCourant = -1;
		this.selected = false;
		this.marqueurs = new Rectangle2D.Double[4];
		this.strokeFloat = strokeFloat;
		this.stroke=new BasicStroke(strokeFloat);
		calculVariables();
	}
	
	public Forme(Point pointDebut, Point pointArrivee, boolean plein, String objet, Color couleur, boolean parfait) {
		this(pointDebut, pointArrivee, 1f, plein, objet, couleur, parfait);
	}

	/**
	 * Constructeur basique de la forme à dessiner avec ses coordonnées
	 * vectorielles, sa couleur, sa forme et son type
	 * 
	 * @param pointDebut
	 *            Point d'origine de l'objet
	 * @param pointArrivee
	 *            Point final de l'objet
	 * @param type
	 *            Plein, vide
	 * @param objet
	 *            Carré, rond, droite
	 * @param couleur
	 *            Couleur de l'objet
	 */
	public Forme(Point pointDebut, Point pointArrivee, boolean plein, String objet, Color couleur) {
		this(pointDebut, pointArrivee, 1f, plein, objet, couleur, false);
	}
	
	public Forme(Point pointDebut, Point pointArrivee, boolean plein, String objet) {
		this(pointDebut, pointArrivee, 1f, plein, objet, Color.WHITE, false);
	}
	
	/**
	 * Calcule selon les différentes positions du point d'arrivée et du point d'origine.
	 * Cette approche de la forme est optimisée pour le redimensionnement. En effet,
	 * le point d'origine sera toujours celui le plus près du point (0, 0), tandis que
	 * le point de fin sera toujours celui le plus loin. Cette approche permet en outre
	 * de calculer les point haut-gauche et bas-droit de manière à ce que le redimensionnement
	 * soit plus souple. 
	 * Concernant le {@link #calculVariablesParfait()}, il en devient plus compliqué
	 * puisque les seules variables sur lesquels les test sont viables sont la relativité de la 
	 * longueur et celle de la hauteur.
	 * 
	 * Initialise les marqueurs de sélection du rectangle.
	 * 
	 * @see #calculVariablesParfait()
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
		// On initialiser/réinitialise les points secondaires de la forme
		this.pointBasGauche = new Point(oX, aY);
		this.pointHautDroit= new Point(aX ,oY);
		
		// Instanciation des marqueurs
		this.marqueurs[0] = new Rectangle2D.Double(oX - 4 - strokeFloat/2, oY - 4 - strokeFloat/2, 8, 8); // En haut à gauche
		this.marqueurs[1] = new Rectangle2D.Double(oX + width - 4 + strokeFloat/2, oY - 4 - strokeFloat/2, 8, 8); // En haut à droite
		this.marqueurs[2] = new Rectangle2D.Double(oX - 4 - strokeFloat/2, oY + height - 4 + strokeFloat/2, 8, 8); // En bas à gauche
		this.marqueurs[3] = new Rectangle2D.Double(oX + width - 4 + strokeFloat/2, oY + height - 4 + strokeFloat/2, 8, 8); // En bas à droite
	}
	
	/**
	 * Initialise les variables de la forme en prenant en compte le fait qu'elle est parfaite.
	 * On initialise les variables X et Y des points principaux de manière classique contrairement à
	 * {@link #calculVariables()}. 
	 * Ce sont les variables de hauteur et de longueur qui vont nous permettre dans les tests de détecter
	 * la direction de la forme parfaite. Dans ces tests, nous vérifierons si ces variables sont positives
	 * ou non.
	 * Selon les cas, ensuite, nous travaillerons sur les X et les Y des points principaux pour rendre la forme
	 * la plus conventionnelle possible : comme dans {@link #calculVariables()}, le point d'origine sera toujours 
	 * celui le plus près du point (0, 0), tandis que le point de fin sera toujours celui le plus loin.
	 * Dans le dernier cas : le curseur en bas à droite du point d'origine, nous avons besoin de connaître le marqueur
	 * de redimensionnement pour connaître le comportement à adopter. En effet, une fois le premier appel du 
	 * {@link #resize(int, Point, boolean)}, la forme devient conventionnelle.
	 * 
	 * @see #resize(int, Point, boolean)
	 * @see #calculVariables()
	 */
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
			this.aX = oX;
			this.aY = oY;
			
			height = Math.abs(height);
			
			this.oX -= height;
			this.oY -= height;
			
			width = height;
			
		// Si on part en haut à droite du point d'origine
		} else if ( height < 0 && width > 0) { 
			height = Math.abs(height);
			
			oY -= height;			
			aX = oX + height;
			aY += height;
			
			width = height;
		// Si on part en bas à gauche du point d'origine
		} else if ( width < 0 && height > 0) { 
			aX = oX;
			oX -= height;
			
			width = height;
		} else if (width > 0 && height > 0) { 
			if (marqueurCourant == 0) { // Si marqueur en haut à gauche
				width = height;
				
				oX = aX - width;
				oY = aY - width;
			} else if (marqueurCourant == 1) { // Si marqueur en haut à droite
				width = height;
				aX = oX + width;
				aY = oY + width;
			} else { // Si le marqueur est en bas (droite ou gauche) ou s'il ne s'agit pas d'un redimensionnement
				height = width;
				
				aX = oX + width;
				aY = oY + width;
			}
		}
		
		// On réinitialise le marqueur courant
		this.marqueurCourant = -1;
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
			pointResize.setLocation(pointResize.getX() + strokeFloat / 2, pointResize.getY() + strokeFloat / 2);
			this.setOrigin(pointResize);
			
			if ( ( !(this.getHeight() > 0) && !(this.getWidth() > 0) ) ) { // Test de la taille de la nouvelle forme
				this.setOrigin(tmpOrigin);
			} else if (!this.pointFin.equals(tmpFin)) { // Test du point opposé
				this.setOrigin(tmpOrigin);
				this.setFin(tmpFin);
			}
			break;
			
		case 1 : // En haut à droite
			pointResize.setLocation(pointResize.getX() - strokeFloat / 2, pointResize.getY() + strokeFloat / 2);
			this.setHautDroit(pointResize);
			
			if ( ( !(this.getHeight() > 0) && !(this.getWidth() > 0) ) ) { // Test de la taille de la nouvelle forme
				this.setHautDroit(tmpHautDroit);
			} else if (!this.pointBasGauche.equals(tmpBasGauche)) { // Test du point opposé
				this.setOrigin(tmpOrigin);
				this.setFin(tmpFin);
			}
			break;
			
		case 2 : // En bas à gauche
			pointResize.setLocation(pointResize.getX() + strokeFloat / 2, pointResize.getY() - strokeFloat / 2);
			this.setBasGauche(pointResize);
						
			if ( ( !(this.getHeight() > 0) && !(this.getWidth() > 0) ) ) { // Test de la taille de la nouvelle forme
				this.setBasGauche(tmpBasGauche);
			} else if (!this.pointHautDroit.equals(tmpHautDroit)) { // Test du point opposé
				this.setOrigin(tmpOrigin);
				this.setFin(tmpFin);
			}
			break;
			
		case 3 : // En bas à droite
			pointResize.setLocation(pointResize.getX() - strokeFloat / 2, pointResize.getY() - strokeFloat / 2);
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
	}
	
	/**
	 * Dessine les marqueurs de sélection pour notifier à l'utilisateur qu'une
	 * ou plusieurs formes ont été sélectionnés. Les marqueurs sont noirs, en pointillés
	 * et sont bordés de carrés plein (4 par défaut).
	 * Crée un stroke pointillé et l'applique au graphics. Sauvegarde préalablement la couleur
	 * et le stroke courant de graphics pour les réappliquer après la création du marqueur :
	 * évite d'appliquer le stroke pointillé aux formes redessinnées ensuite.
	 * 
	 * @param graphics
	 *            Zone de dessin dans laquelle le tout sera dessiné.
	 */
	public void selectionner(Graphics2D graphics) {
		// Sauvegarde des variables
		Color colorTmp = graphics.getColor();
		Stroke strokeTmp = graphics.getStroke();
		
		graphics.setComposite(AlphaComposite.SrcOver.derive(0.7f));
		graphics.setColor(Color.BLACK);
		graphics.setStroke(new BasicStroke());
		
		// Marqueurs
		for (Rectangle2D.Double rectangle : marqueurs) {
			graphics.draw(rectangle);
		}

		// Réinitialisation du graphics avec ses valeurs par défaut
		graphics.setColor(colorTmp); // Rétablissement de la couleur d'origine
		graphics.setStroke(strokeTmp); // Rétablissement du stroke d'origine
		graphics.setComposite(AlphaComposite.SrcOver); // Rétablissement de la transparence d'origine
	}
	
	/**
	 * Dessine les objets selon les formes qui lui sont envoyé. Définie
	 * également le référentiel qui servira lors de la sélection d'une forme.
	 * 
	 * @param graphics
	 *            Graphics qui vient de paintComponent
	 */
	public void draw(Graphics2D graphics) {
		graphics.setColor(this.couleur);

		if (this.plein) {
			graphics.fill(this.forme);
		} else {
			graphics.draw(this.forme);
		}
	}
	
	/**
	 * @category accessor
	 * 
	 * @return Si la forme est sélectionnée ou non
	 */
	public boolean isSelected() {
		return this.selected;
	}

	/**
	 * @category accessor
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * @category accessor
	 * 
	 * @return La hauteur de la forme
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * @category accessor
	 * 
	 * @return la longueur de la forme
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @category accessor
	 * 
	 * Reféfinis le point de début de la forme. Cette méthode a pour but de
	 * redéfinir le point d'origine en recréant un referentielPosition avec les
	 * nouvelles coordonnées en les initialisant préalablement.
	 * 
	 * @param pointDebut
	 *            Nouveau point d'origine de la forme
	 */
	public void setOrigin(Point pointDebut) {
		this.pointOrigin = pointDebut;
		this.calculVariables();
	}

	/**
	 * @category accessor
	 * 
	 * @return Le point d'origine de la forme (haut gauche)
	 */
	public Point getOrigin() {
		return this.pointOrigin;
	}
	
	/**
	 * @category accessor
	 * 
	 * Reféfinis le point de fin de la forme. Cette méthode a pour but de
	 * redéfinir le point de fin en recréant un referentielPosition avec les
	 * nouvelles coordonnées en les initialisant préalablement.
	 * 
	 * @param pointArrivee
	 *            Nouveau point d'origine de la forme
	 */
	public void setFin(Point pointArrivee) {
		this.pointFin = pointArrivee;
		this.calculVariables();
	}
	
	/**
	 * @category accessor
	 * 
	 * @return Le point de fin de la forme (bas droite)
	 */
	public Point getFin() {
		return this.pointFin;
	}
	
	/**
	 * @category accessor
	 * 
	 * @param pointArrivee Nouveau point haut droit de la forme
	 */
	protected void setHautDroit(Point pointDebut) {
		this.pointHautDroit = pointDebut;
		
		this.pointOrigin = new Point( (int) this.pointOrigin.getX(), (int) this.pointHautDroit.getY() );
		this.pointFin = new Point( (int) this.pointHautDroit.getX(), (int) this.pointFin.getY() );

		this.calculVariables();
	}
	
	/**
	 * @category accessor
	 * 
	 * @return Le point situé en haut à droite de la forme
	 */
	public Point getHautDroit() {
		return this.pointHautDroit;
	}
	
	/**
	 * @category accessor
	 * 
	 * @param pointDebut Nouveau point bas gauche de la forme
	 */
	protected void setBasGauche(Point pointDebut) {
		this.pointBasGauche = pointDebut;
		
		this.pointOrigin = new Point( (int) this.pointBasGauche.getX(), (int) this.pointOrigin.getY() );
		this.pointFin = new Point( (int) this.pointFin.getX(), (int) this.pointBasGauche.getY() );
		
		this.calculVariables();
	}	

	/**
	 * @category accessor
	 */
	public boolean getType() {
		return this.plein;
	}

	/**
	 * @category accessor
	 */
	public void setType(boolean plein) {
		this.plein = plein;
	}
	
	/**
	 * @category accessor
	 */
	public String getObjet() {
		return this.objet;
	}

	/**
	 * @category accessor
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}
	
	/**
	 * @category accessor
	 * 
	 * Compare la position du curseur au rectangle de marqueurs de la sélection.
	 * Utile les contains() des rectangle2D.Double.
	 * 
	 * @param position Position du curseur
	 * 
	 * @return Si le curseur est sur l'un des points de sélection
	 * 
	 * @see java.awt.geom.Rectangle2D#contains(Point2D)
	 */
	public boolean containsPointDeSelection(Point2D position) {
		boolean estContenu = false;
		
		// Compare la position à tous les rectangle de pointsDeSelection
		for (Rectangle2D.Double marqueur : marqueurs) {
			if (marqueur.contains(position)) {
				estContenu = true;
			}
		}
		
		return estContenu;
	}
	
	/**
	 * @category accessor
	 * 
	 * Compare la position du curseur au rectangle de marqueurs de la sélection.
	 * Utile les contains() des rectangle2D.Double.
	 * 
	 * @param position Position du curseur
	 * 
	 * @return Si le curseur est sur l'un des points de sélection
	 * 
	 * @see java.awt.geom.Rectangle2D#contains(Point2D)
	 */
	public boolean contains(Rectangle2D referentiel) {
		return this.referentielPosition.contains(referentiel);
	}
	
	/**
	 * @category accessor
	 * 
	 * Renvoi le marqueur concerné par le resizing.
	 * 
	 * @param position
	 *            Position du curseur quand un marqueur est cliqué.
	 * @return Le marqueur provenant de la position du curseur.
	 */
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
	 * @category accessor
	 */
	public Color getCouleur() {
		return this.couleur;
	}

	/**
	 * @category accessor
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
	 * @category accessor
	 */
	public boolean getParfait() {
		return this.parfait;
	}

	/**
	 * @category accessor
	 */
	public void setTemporaire(boolean temporaire) {
		this.parfait = temporaire;
	}

	/**
	 * @category accessor
	 */
	public Shape getShape() {
		return this.forme;
	}
	
	/**
	 * @category accessor
	 */
	public Rectangle2D.Double getReferentiel() {
		return this.referentielPosition;
	}

	/**
	 * Le contains se réfère aux contains du référentiel mais aussi aux marqueurs
	 * pour permettre aux DessinListener d'appeler {@link #containsPointDeSelection(Point2D)}
	 * quand nécessaire.
	 * 
	 * @param position
	 *            Position du curseur quand la forme est cliquée ou survolée.
	 * 
	 * @return Si le point est contenu ou non dans la forme.
	 * 
	 * @see controler.DessinListener
	 */
	public boolean contains(Point2D position) {
		if (referentielPosition.contains(position) || this.containsPointDeSelection(position)) {
			return true;
		}
		return false;
	}
	
	public Object clone() {
		Forme forme = null;
		try {
	    	// On récupère l'instance à renvoyer par l'appel de la 
	      	// méthode super.clone()
			forme  = (Forme) super.clone();
	    } catch(CloneNotSupportedException cnse) {
	      	// Ne devrait jamais arriver car nous implémentons 
	      	// l'interface Cloneable
	      	cnse.printStackTrace(System.err);
	    }
		
		forme.marqueurs = (Rectangle2D.Double[]) marqueurs.clone();
		forme.marqueurCourant = (int) marqueurCourant;
		forme.selected = (boolean) selected;
		forme.parfait = (boolean) parfait;
		forme.pointOrigin = (Point) pointOrigin.clone();
		forme.pointHautDroit = (Point) pointBasGauche.clone();
		forme.pointFin = (Point) pointFin.clone();
		forme.plein = (boolean) plein;
		forme.objet = (String) objet;
		forme.couleur = (Color) couleur;
		forme.calculVariables();
		return forme;
	}
	
	public Stroke getStroke(){
		return this.stroke;
	}
	
	public void setStroke(float strokeStroke){
		this.strokeFloat = strokeStroke;
		this.stroke = new BasicStroke(strokeFloat);
		calculVariables();
	}
	
	public String toString() {
		return "Forme [deb : " + pointOrigin + ", arr : " + pointFin + "]";
	}
}