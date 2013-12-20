package fenetre;

import java.awt.Color;
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
		this.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
		// Si la forme n'a pas été initialisé, on ne touche pas à g
		// Cela permet d'éviter les erreurs du type NullPointerException 
		// à la construction de zoneDessin dans la fenêtre principale
		super.paintComponent(g);
		if ( courante != null ) {
			dessiner(courante, g);
			System.out.println("Formes en cours de réinitialisation"); // DEBUG
		}
		
		Iterator<Forme> it = model.getListeDessin().iterator();
		while ( it.hasNext() ) {
		      Forme forme = it.next();
		      dessiner(forme, g);
		}
		System.out.println("Formes redessinées"); // DEBUG
	}
	
	public void dessiner(Forme forme, Graphics g) {
		g.setColor(forme.getCouleur());
		if(forme.getForme().equals("rond")){
			g.fillOval((int)forme.getDeb().getX() , (int)forme.getDeb().getY(), (int)forme.getArr().getX(), (int)forme.getArr().getY());
			System.out.println("rond");
		}
		if(forme.getForme().equals("carre")){
			g.fillRect((int)forme.getDeb().getX() , (int)forme.getDeb().getY(), (int)forme.getArr().getX(), (int)forme.getArr().getY());
			System.out.println("carre");
		}
		if(forme.getForme().equals("droite")){
			g.drawLine((int)forme.getDeb().getX() , (int)forme.getDeb().getY(), (int)forme.getArr().getX(), (int)forme.getArr().getY());
			System.out.println("droite");
		}
	}

	public Forme getCourante() {
		return courante;
	}

	public void setCourante(Forme courante) {
		this.courante = courante;
	}
	
}
