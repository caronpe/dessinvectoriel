package fenetre;

import java.awt.Graphics;

import javax.swing.JPanel;

import model.Forme;

public class zoneDessin extends JPanel {
	Forme courante;
		
	public zoneDessin() {
		super();
		this.courante = null;
	}

	public void paintComponent(Graphics g){
		// Si la forme n'a pas été initialisé, on ne touche pas à g
		// Cela permet d'éviter les erreurs du type NullPointerException 
		// à la construction de zoneDessin dans la fenêtre principale
		if ( courante != null ) {
			g.setColor(courante.getCouleur());
		
			if(courante.getForme() == "rond"){
				g.fillOval((int)courante.getDeb().getX() , (int)courante.getDeb().getY(), (int)courante.getArr().getX(), (int)courante.getArr().getY());
			}
			if(courante.getForme() == "carre"){
				g.fillRect((int)courante.getDeb().getX() , (int)courante.getDeb().getY(), (int)courante.getArr().getX(), (int)courante.getArr().getY());
			}
			if(courante.getForme() == "droite"){
				g.drawLine((int)courante.getDeb().getX() , (int)courante.getDeb().getY(), (int)courante.getArr().getX(), (int)courante.getArr().getY());
			}		
		}
	}

	public Forme getCourante() {
		return courante;
	}

	public void setCourante(Forme courante) {
		this.courante = courante;
	}
	
}
