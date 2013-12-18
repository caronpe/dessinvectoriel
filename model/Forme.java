package model;

import java.awt.Color;

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
			pan.getGraphics().fillOval(deb.getX() , deb.getY(), arr.getX(), arr.getY());
		}
		if(type == "carre"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().fillRect(deb.getX() , deb.getY(), arr.getX(), arr.getY());
		}
		if(type == "droite"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().drawLine(deb.getX() , deb.getY(), arr.getX(), arr.getY());
		}		
	} 
}
