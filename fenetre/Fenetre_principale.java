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
import javax.swing.JPanel;

// INTERNE
import model.Model;
import controler.*;

public class Fenetre_principale extends JFrame implements Observer {
	// FENETRE
	private Container container; // Container principal de la fenêtre
	private MenuG gauche; // Menu d'outils sur la gauche
	private JPanel zoneDessin; // Zone où le dessin s'effectuera.
	
	// MVC
	private Model model;
		
	// MENU
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenuItem nouveau;
	private JMenuItem enregistrer;
	private JMenuItem exitAction;
	private JMenuItem copy;
	private JMenuItem paste;
	
	public Fenetre_principale(Model model) throws HeadlessException {
		super();
		this.model = model;
		this.model.addObserver(this);
		this.initialiser();
		this.creation_menu();
		this.pack();
	}
	
	public void afficher() {
		this.setVisible(true);
	}
	
	public void initialiser() {
		// Configuration du container
		container = this.getContentPane();
		container.setLayout(new BorderLayout());
		
		// Ajout des panels au container
		gauche = new MenuG();
		zoneDessin = new JPanel();
		container.add(gauche, BorderLayout.WEST);
		container.add(zoneDessin , BorderLayout.CENTER);

		this.setTitle("logiciel de dessin vectoriel");
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new ActionQuitter(model.getEnregistre()));
		this.setMinimumSize(new Dimension(400, 400));
	}
	
	public void creation_menu() {		 
		// CRÉATION MENU BAR
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		 
		// MENUS
		fileMenu = new JMenu("Fichier");
		editMenu = new JMenu("Édition");
		// AJOUTS
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		// MENUITEMS
		nouveau = new JMenuItem(new ActionNouveau());
		enregistrer = new JMenuItem(new ActionEnregistrer());
		exitAction = new JMenuItem(new ActionQuitter(model.getEnregistre()));
		copy = new JMenuItem(new ActionCopier());
		paste = new JMenuItem(new ActionCouper());
		
		// FILE MENU
		fileMenu.addSeparator();
		fileMenu.add(nouveau);
		fileMenu.add(enregistrer);
		fileMenu.add(exitAction);

		// EDIT MENU
		editMenu.addSeparator();
		editMenu.add(copy);
		editMenu.add(paste);
	}
	
	public JMenuItem getExitButton() {
		return this.exitAction;
	}
	
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}