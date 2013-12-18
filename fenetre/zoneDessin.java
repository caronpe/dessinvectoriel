package fenetre;

import java.awt.Graphics;

import javax.swing.JPanel;

import model.Forme;

public class zoneDessin extends JPanel {
	
	Forme courante;
	
	
	public zoneDessin() {
		super();
	}


	public void paintComponent(Graphics g){
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


	public Forme getCourante() {
		return courante;
	}


	public void setCourante(Forme courante) {
		this.courante = courante;
	}
	
}
