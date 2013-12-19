package fenetre;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;
// INTERNE
import model.*;

public class zoneDessin extends JPanel {
	Forme courante;
	Model model;
		
	public zoneDessin(Model model) {
		super();
		this.courante = null;
		this.model = model;
	}

	public void paintComponent(Graphics g){
		// Si la forme n'a pas été initialisé, on ne touche pas à g
		// Cela permet d'éviter les erreurs du type NullPointerException 
		// à la construction de zoneDessin dans la fenêtre principale
		if ( courante != null ) {
			dessiner(courante, g);
			System.out.println("Formes en cours de réinitialisation");
			Iterator<Forme> it = model.getListeDessin().iterator();
			
			while (it.hasNext()) {
			      Forme forme = it.next();
			      dessiner(forme, g);
			}
			System.out.println("Formes redessinées");
		}
	}
	
	public void dessiner(Forme courante, Graphics g) {
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
