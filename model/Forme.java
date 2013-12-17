package model;

import java.awt.Color;

import javax.swing.JPanel;

public class Forme {
	int xdep, ydep, xarr ,yarr;
	String type , forme;
	Color couleur;
	

	//constructeur le plus basique de la forme a dessiner avec ses coordonnées vectoriels, sa couleur et une string représentant sa forme

	public Forme(int xdep, int ydep, int xarr, int yarr, String type, String forme ,
			Color couleur) {
		super();
		this.xdep = xdep;
		this.ydep = ydep;
		this.xarr = xarr;
		this.yarr = yarr;
		this.type = type;
		this.forme = forme;
		this.couleur = couleur;
	}


	//dessine la figure dans le panel placé en parametre.

	public void dessiner(JPanel pan){
		if(type == "rond"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().fillOval(xdep ,ydep , xarr ,yarr);
		}
		if(type == "carre"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().fillRect(xdep ,ydep , xarr ,yarr);
		}
		if(type == "droite"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().drawLine(xdep ,ydep , xarr ,yarr);
		}		
	} 
}
