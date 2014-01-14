package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import model.Forme;
import model.Model;
import controler.ActionMenuQuitter;
import controler.StrokeListener;
import controler.DessinListener;
import controler.KeyListenerAll;
//INTERNE

/**
 * Fenêtre principale contenant tous les éléments graphiques.
 * C'est la seule vue.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class FenetrePrincipale extends JFrame implements Observer {
	// Fenêtre
		private Container container; // Container principal de la fenêtre
		private MenuOutils outils; // Menu d'outils sur la gauche
		private ZoneDessin zoneDessin; // Zone où le dessin s'effectuera.
		private MenuDroit menuDroit; // zone ou les calques apparaitrons
	// MVC
		private Model model;
	
	/**
	 * @param model Modèle du MVC
	 * 
	 * @throws HeadlessException Aucun catch puisque paint nécessite un environnement graphique
	 * comprenant souris et clavier
	 */
	public FenetrePrincipale(Model model) throws HeadlessException {
		super();
		this.model = model;
		this.model.addObserver(this);
		this.initialiser();
		this.pack();
	}
	
	/**
	 * Affiche la fenêtre.
	 * 
	 * @category init
	 */
	public void afficher() {
		this.setVisible(true);
	}
	
	/**
	 * Initialise complètement une fenêtre de 400x400 non-centrée avec 
	 * une barre d'outil latérale, un menu et une zone de dessin
	 * 
	 * zoneDessin ne contient qu'un seul et unique DessinListener car cela permets de
	 * garder les points d'origine et d'arrivée en mémoire
	 * 
	 * @category init
	 */
	public void initialiser() {
		// Configuration du container
		container = this.getContentPane();
		container.setLayout(new BorderLayout());		
		
		// Listeners
		KeyListenerAll keyListener = new KeyListenerAll(model);
			
		// Création de la zone de dessin
		zoneDessin = new ZoneDessin(model);
		DessinListener dessinListener = new DessinListener(zoneDessin, model);
		zoneDessin.addMouseListener(dessinListener);
		zoneDessin.addMouseMotionListener(dessinListener);
		zoneDessin.setFocusable(true);
		zoneDessin.addKeyListener(keyListener);
		
		// Création le menu droit (celui des calques)
		menuDroit = new MenuDroit(model, zoneDessin);
				
		// Ajout des panels au container
		StrokeListener actionStroke = new StrokeListener(model);
		outils = new MenuOutils(model, actionStroke);
		zoneDessin.addMouseListener(actionStroke);
		
		// Ajouts
		container.add(outils, BorderLayout.WEST);
		container.add(zoneDessin , BorderLayout.CENTER);
		container.add(menuDroit , BorderLayout.EAST);
		
		// Configuration de la JFrame
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(keyListener);
		this.setTitle("Logiciel de dessin vectoriel");
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new ActionMenuQuitter(model));
		this.setMinimumSize(new Dimension(1280, 720));
		this.setLocationRelativeTo(null);
		
		// Barre de menu
		this.setJMenuBar(new MenuHaut(model));
	}
	
	
	/**
	 * Si le paramètre est une forme, on la set dans la zone de dessin,
	 * seule la fonction addForme() du Modèle envoie un paramètre
	 * Sinon, on la set à null. La fonction paintComponent de ZoneDessin gère
	 * localement les paramètres.
	 * 
	 * Cette gestion des paramètres permets de régler un bug d'affichage lorsqu'on
	 * ouvre un fichier. Elle évite les exceptions NullPointerException intervenant
	 * dans la classe ZoneDessin
	 * 
	 * @see view.ZoneDessin#paintComponent
	 * 
	 * @param arg1 Forme qui va être relayée à ZoneDessin
	 */
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Forme) { // Si l'argument est une forme, on le caste
			zoneDessin.setCourante((Forme) arg1); 
		} else {
			zoneDessin.setCourante(null);
		}

		outils.selectionCouleur.setBackground(model.getColor());
		zoneDessin.repaint();
	}
}
