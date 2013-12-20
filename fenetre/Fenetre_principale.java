package fenetre;

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

import ressources.Forme;
//INTERNE
import model.*;
import controler.*;

public class Fenetre_principale extends JFrame implements Observer {
	// Fenêtre
		private Container container; // Container principal de la fenêtre
		private MenuG gauche; // Menu d'outils sur la gauche
		private ZoneDessin zonedessin; // Zone où le dessin s'effectuera.
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
	 * @param model Modèle du MVCd
	 * 
	 * @throws HeadlessException Aucun catch puisque paint nécessite un environnement graphique
	 * comprenant souris et clavier
	 */
	public Fenetre_principale(Model model) throws HeadlessException {
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
	 * @category init
	 */
	public void initialiser() {
		// Configuration du container
		container = this.getContentPane();
		container.setLayout(new BorderLayout());
		
		// Ajout des panels au container
		gauche = new MenuG(model);
		zonedessin = new ZoneDessin(model);
		zonedessin.addMouseListener(new zoneDessin_listener(zonedessin, model));
		container.add(gauche, BorderLayout.WEST);
		container.add(zonedessin , BorderLayout.CENTER);

		// Configuration de la JFrame
		this.setTitle("logiciel de dessin vectoriel");
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new ActionQuitter(model));
		this.setMinimumSize(new Dimension(400, 400));
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
		nouveau = new JMenuItem(new ActionNouveau());
		ouvrir = new JMenuItem(new ActionOuvrir(model));
		enregistrer = new JMenuItem(new ActionEnregistrer(model));
		exitAction = new JMenuItem(new ActionQuitter(model));
		copy = new JMenuItem(new ActionCopier());
		paste = new JMenuItem(new ActionCouper());
		
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
	 * @see ZoneDessin.paintComponent()
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

			zonedessin.setCourante(courant);
		} else { // S'il n'y a pas d'argument
			zonedessin.setCourante(null);
		}
		
		zonedessin.repaint();
	}
}
