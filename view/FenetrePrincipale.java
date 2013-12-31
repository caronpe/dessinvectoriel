package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//INTERNE
import model.*;
import controler.*;

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
	// MVC
		private Model model;	
	// Menu
		private JMenuBar menuBar;
		private JMenu fileMenu;
		private JMenu editMenu;
		private JMenuItem nouveau;
		private JMenuItem ouvrir;
		private JMenuItem enregistrer;
		private JMenuItem exitAction;
		private JMenuItem copy;
		private JMenuItem paste;
	
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
		this.creation_menu();
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
		DessinListener dessinListener = new DessinListener(zoneDessin, model);
		KeyListenerAll keyListener = new KeyListenerAll(model);
		
		// Ajout des panels au container
		outils = new MenuOutils(model);
		zoneDessin = new ZoneDessin(model);
		zoneDessin.addMouseListener(dessinListener);
		zoneDessin.addMouseMotionListener(dessinListener);
		zoneDessin.setFocusable(true);
		zoneDessin.addKeyListener(keyListener);
		container.add(outils, BorderLayout.WEST);
		container.add(zoneDessin , BorderLayout.CENTER);

		// Configuration de la JFrame
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(keyListener);
		this.setTitle("logiciel de dessin vectoriel");
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new ActionMenuQuitter(model));
		this.setMinimumSize(new Dimension(1280, 720));
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Crée la barre de menu
	 * 
	 * @category init
	 */
	private void creation_menu() { 
		// Création Barre de menu
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		 
		// Menus
		fileMenu = new JMenu("Fichier");
		editMenu = new JMenu("Édition");
		
		// Ajouts
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		// Items
		nouveau = new JMenuItem(new ActionMenuNouveau(model));
		ouvrir = new JMenuItem(new ActionMenuOuvrir(model));
		enregistrer = new JMenuItem(new ActionMenuEnregistrer(model));
		exitAction = new JMenuItem(new ActionMenuQuitter(model));
		copy = new JMenuItem(new ActionMenuCopier());
		paste = new JMenuItem(new ActionMenuCouper());
		
		// Menu : Fichiers
		fileMenu.addSeparator();
		fileMenu.add(nouveau);
		fileMenu.add(ouvrir);
		fileMenu.add(enregistrer);
		fileMenu.add(exitAction);

		// Menu : Édition
		editMenu.addSeparator();
		editMenu.add(copy);
		editMenu.add(paste);
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
		if (arg1 != null ) { // S'il y a un argument lors de la notification du modèle
			System.out.println(arg1.toString()); // DEBUG
			Forme courant = null;
			
			if (arg1 instanceof Forme) { // Si l'argument est une forme, on le caste
				courant = (Forme) arg1;
			} else {
				System.err.println(	"Erreur lors du cast de la forme dans " +
									"la méthode Update() de la fenêtre principale.");
			}

			zoneDessin.setCourante(courant);
		} else { // S'il n'y a pas d'argument
			zoneDessin.setCourante(null);
		}
		System.out.println("update");
		zoneDessin.repaint();
	}
}
