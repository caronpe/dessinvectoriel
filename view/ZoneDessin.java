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
			dessiner(courante, g);
			System.out.println("Formes en cours de réinitialisation"); // DEBUG
		}
		
		Iterator<Forme> it = model.getListeDessin().iterator();
		while ( it.hasNext() ) { // Parcours de la liste pour redissiner toutes les formes
		      Forme forme = it.next();
		      dessiner(forme, g);
		}
		System.out.println("Formes redessinées"); // DEBUG
	}
	
	/**
	 * Dessine les objets selon les formes qui lui sont envoyé
	 * 
	 * @param forme Forme qui va être dessinée
	 * @param g Graphics qui vient de paintComponent
	 */
	public void dessiner(Forme forme, Graphics g) {
		g.setColor(forme.getCouleur());
		int Ox = (int) forme.getOrigin().getX(), Oy = (int) forme.getOrigin().getY();

		
		// Dessins selon les types de formes sélectionnés
		if (forme.getForme().equals("cercle")) { // ROND PLEIN
			int width = (int)(forme.getFin().getX() - forme.getOrigin().getX());
			int height = (int)(forme.getFin().getY() - forme.getOrigin().getY());
			
			g.fillOval(	(int)forme.getOrigin().getX(), // X d'origine
						(int)forme.getOrigin().getY(), // Y d'origine
						(int)(forme.getFin().getX() - forme.getOrigin().getX()), // Longueur
						(int)(forme.getFin().getY() - forme.getOrigin().getY())); // Hauteur
			
		} else if (forme.getForme().equals("rectangle")) { // CARRÉ PLEIN
			int width = (int)(forme.getFin().getX() - forme.getOrigin().getX());
			int height = (int)(forme.getFin().getY() - forme.getOrigin().getY());
			
				if (width < 0) { // Si la longueur est négative, on permute l'origine et la fin
					Ox -= Math.abs(width);
					width = Math.abs(width);
				}
				if (height < 0) { // Si la hauteur est négative, on permute l'origine et la fin
					Oy -= Math.abs(height);
					height = Math.abs(height);
				}					
				
				g.fillRect(Ox, Oy, width, height);
			
		} else if (forme.getForme().equals("trait")) { // DROITE
			
			g.drawLine(Ox, Oy, (int)forme.getFin().getX(), (int)forme.getFin().getY());
			
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
