package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
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
        protected int oX, oY, aX, aY, width, height;
        protected Shape forme;
        protected Point pointDebut, pointArrivee;
        protected String type, objet;
        protected Color couleur;
        protected boolean parfait, selected;
        // Dashed
                final BasicStroke dashed;
        
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
                
                // Dashed
                final float dash1[] = { 10.0f };
                this.dashed =         new BasicStroke(1.0f, 
                                                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
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
        
        /**
         * Dessine les objets selon les formes qui lui sont envoyé.
         * Définie également le référentiel qui servira lors de la sélection d'une forme.
         * 
         * @param graphics Graphics qui vient de paintComponent
         */
        public void draw(Graphics2D graphics) {
                graphics.setColor(this.couleur);
                
                switch (this.type) {
                case "plein" :
                        graphics.fill(this.forme);
                        break;
                case "vide" :
                        graphics.draw(this.forme);
                        break;
                }
        }
        
        /**
         * Dessine les marqueurs de sélection pour notifier à l'utilisateur qu'une
         * ou plusieurs formes ont été sélectionnés.
         * 
         * @param graphics Zone de dessin dans laquelle le tout sera dessiné.
         */
        public void selectionner(Graphics2D graphics) {
                Color tmp = graphics.getColor();
                graphics.setColor(Color.BLACK);
                
                graphics.setStroke(dashed);
                graphics.drawRect(oX - 10, oY - 10, width + 20, height + 20);
                
                // Rectangles des extrémités
                                
                graphics.fillRect(oX - 13, oY - 13, 7, 7);
                graphics.fillRect(oX + width + 7, oY - 13, 7, 7);
                graphics.fillRect(oX - 13, oY + height + 7, 7, 7);
                graphics.fillRect(oX + width + 7, oY + height + 7, 7, 7);
                
                graphics.setColor(tmp); // Rétablissement de la couleur d'origine
        }
        
        /**
         * Calcule selon les différentes positions du point d'arrivée.
         * Réagis à la touche SHIFT appuyé pour le cercle et le rectangle
         * en les définissant comme parfait.
         */
        protected abstract void initialiserVariables();
        
        public abstract boolean contains(Point2D position);
        
        /**
         * @category accessor
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