package model;

import java.awt.Color;

import javax.swing.JPanel;

public class Forme {
	int xdep, ydep, xarr ,yarr;
	String type , forme;
	Color couleur;
	
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
