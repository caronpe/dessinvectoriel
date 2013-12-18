package model;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

public class Forme {
	Point deb , arr;
	String type , forme;
	Color couleur;
	

	//constructeur le plus basique de la forme a dessiner avec ses coordonnées vectoriels, sa couleur et une string représentant sa forme

	public Forme(Point deb , Point arr, String type, String forme, Color couleur) {
		super();
		this.deb = deb;
		this.arr = arr;
		this.type = type;
		this.forme = forme;
		this.couleur = couleur;
	}


	//dessine la figure dans le panel placé en parametre.



	public void dessiner(JPanel pan){
		if(type == "rond"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().fillOval((int)deb.getX() , (int)deb.getY(), (int)arr.getX(), (int)arr.getY());
		}
		if(type == "carre"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().fillRect((int)deb.getX() , (int)deb.getY(), (int)arr.getX(), (int)arr.getY());
		}
		if(type == "droite"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().drawLine((int)deb.getX() , (int)deb.getY(), (int)arr.getX(), (int)arr.getY());
		}		
	} 
	
	@Override
	public String toString() {
		return "Forme [deb=" + deb + ", arr=" + arr + ", type=" + type
				+ ", forme=" + forme + ", couleur=" + couleur + "]";
	}

}
