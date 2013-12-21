package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import ressources.Forme;
// INTERNE
import model.*;

public class ZoneDessin extends JPanel {
	Forme courante;
	Model model;
	
	/**
	 * Panel de couleur blanche
	 * Aucune forme n'est sélectionnée par défaut
	 * 
	 * @param model Modèle du MVC
	 */
	public ZoneDessin(Model model) {
		super();
		this.courante = null;
		this.model = model;
		this.setBackground(Color.WHITE);
	}

	/**
	 * Si la forme n'a pas été initialisé, on ne dessine rien
	 * Cela permet d'éviter les erreurs du type NullPointerException 
	 * à la construction de zoneDessin dans la fenêtre principale
	 * @category accessor
	 * 
	 * @return Forme courante
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if ( courante != null ) { // Si courante n'a pas encore été initialisée
			Iterator<Forme> it = model.getListeDessin().iterator();
			while ( it.hasNext() ) { // Parcours de la liste pour redissiner toutes les formes
			      Forme forme = it.next();
			      dessiner(forme, g);
			      System.out.println("Formes redessinées"); // DEBUG
			}
			dessiner(courante, g);
			System.out.println("Forme courante"); // DEBUG
		}
	}
	
	/**
	 * Dessine les objets selon les formes qui lui sont envoyé
	 * 
	 * @param forme Forme qui va être dessinée
	 * @param g Graphics qui vient de paintComponent
	 */
	public void dessiner(Forme forme, Graphics g) {
		g.setColor(forme.getCouleur());
		int oX = (int) forme.getOrigin().getX(), oY = (int) forme.getOrigin().getY();
		int aX = (int) forme.getFin().getX(), aY = (int) forme.getFin().getY();
		int width = (int)(aX - oX);
		int height = (int)(aY - oY);
		
		switch (forme.getForme()) { // Sélectionne l'outil du modèle
		
		case "cercle" :
			
			if (width < 0) { // On soustrait la longueur absolue à X origine, on prend la longueur absolue
				oX -= Math.abs(width); 
				width = Math.abs(width);
			}
			if (height < 0) { // On soustrait la hauteur absolue aux Y origine, on prend la hauteur absolue
				oY -= Math.abs(height);
				height = Math.abs(height);
			}
			
			g.fillOval(oX, oY, width, height);
			break;
			
		case "rectangle" :
			
			if (width < 0) { // On soustrait la longueur absolue à X origine, on prend la longueur absolue
				oX -= Math.abs(width);
				width = Math.abs(width);
			}
			if (height < 0) { // On soustrait la hauteur absolue aux Y origine, on prend la hauteur absolue
				oY -= Math.abs(height);
				height = Math.abs(height);
			}					
			
			g.fillRect(oX, oY, width, height);
			break;
			
		case "trait" : 
			
			g.drawLine(oX, oY, aX, aY);
			break;
			
		}
	}

	/**
	 * @category accessor
	 * 
	 * @return Forme courante
	 */
	public Forme getCourante() {
		return courante;
	}

	/**
	 * @category accessor
	 * 
	 * @param courante Modifie la forme courante
	 */
	public void setCourante(Forme courante) {
		this.courante = courante;
	}
	
}
