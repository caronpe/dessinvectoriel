package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * Contient toutes les caractéristiques de l'objet Forme
 * Chaque fois qu'une forme est créée, un rectangle est créé, 
 * il facilitera les déplacements de la forme
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public abstract class Forme implements Serializable {
	protected Shape forme;
	protected Point pointDebut, pointArrivee;
	protected String type, objet;
	protected Color couleur;
	protected boolean parfait, selected;
	
	/**
	 * Constructeur basique de la forme à dessiner avec ses coordonnées vectorielles,
	 * sa couleur, sa forme et son type. Définit la forme comme non sélectionné.
	 * @param pointDebut Point d'origine de l'objet
	 * @param pointArrivee Point final de l'objet
	 * @param type Plein, vide
	 * @param objet Carré, rond, droite
	 * @param couleur Couleur de l'objet
	 * @param parfait Définit si la forme est temporaire
	 */
	public Forme(Point pointDebut , Point pointArrivee, String type, String objet, Color couleur, boolean parfait) {
		super();
		this.pointDebut = pointDebut;
		this.pointArrivee = pointArrivee;
		this.type = type;
		this.objet = objet;
		this.couleur = couleur;
		this.parfait = parfait;
		this.selected = false;
	}
	
	/**
	 * Constructeur basique de la forme à dessiner avec ses coordonnées vectorielles,
	 * sa couleur, sa forme et son type 
	 * @param pointDebut Point d'origine de l'objet
	 * @param pointArrivee Point final de l'objet
	 * @param type Plein, vide
	 * @param objet Carré, rond, droite
	 * @param couleur Couleur de l'objet
	 */
	public Forme(Point pointDebut , Point pointArrivee, String type, String objet, Color couleur) {
		this(pointDebut, pointArrivee, type, objet, couleur, false);
	}

	/**
	 * Utilise le comparateur du rectangle référentiel qui permet
	 * de savoir si le curseur est dans la forme ou non.
	 * @param position Position du curseur quand la forme est cliquée ou survolée.
	 * @return Si le point est contenu ou non dans la forme.
	 */
	
	public abstract boolean contains(Point2D position);
	
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
	 */
	public Point getOrigin() {
		return pointDebut;
	}

	/**
	 * Reféfinis le point de début de la forme. Cette méthode a pour but de redéfinir le point d'origine
	 * en recréant un referentielPosition avec les nouvelles coordonnées en les initialisant préalablement.
	 * 
	 * @category accessor
	 * 
	 * @param pointDebut Nouveau point d'origine de la forme
	 */
	public abstract void setOrigin(Point pointDebut);

	/**
	 * @category accessor
	 */
	public Point getFin() {
		return this.pointArrivee;
	}

	/**
	 * Reféfinis le point de fin de la forme. Cette méthode a pour but de redéfinir le point de fin
	 * en recréant un referentielPosition avec les nouvelles coordonnées en les initialisant préalablement.
	 * 
	 * @category accessor
	 * 
	 * @param pointArrivee Nouveau point d'origine de la forme
	 */
	public abstract void setFin(Point pointArrivee);

	/**
	 * @category accessor
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @category accessor
	 */
	public void setType(String type) {
		this.type = type;
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
	public void setForme(String forme) {
		this.objet = forme;
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
	
	public String toString() {
		return "Forme [deb=" + pointDebut + ", arr=" + pointArrivee + ", type=" + type
				+ ", forme=" + objet + ", couleur=" + couleur + "]";
	}
}
