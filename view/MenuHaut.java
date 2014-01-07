package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
// INTERNE
import model.Model;
import controler.ActionMenuColler;
import controler.ActionMenuCopier;
import controler.ActionMenuCouper;
import controler.ActionMenuEnregistrer;
import controler.ActionMenuEnregistrerSous;
import controler.ActionMenuInsererImage;
import controler.ActionMenuNouveau;
import controler.ActionMenuOuvrir;
import controler.ActionMenuQuitter;

public class MenuHaut extends JMenuBar {
	private JMenu fileMenu, editMenu, insertionMenu;
	private JMenuItem nouveau, ouvrir, enregistrer, enregistrerSous, exit;
	private JMenuItem copy, cut, paste;
	private JMenuItem image;

	public MenuHaut(Model model){
		// Menus
		fileMenu = new JMenu("Fichier");
		editMenu = new JMenu("Édition");
		insertionMenu = new JMenu("Insertion");
		
		// Ajouts
		this.add(fileMenu);
		this.add(editMenu);
		this.add(insertionMenu);
		
		// Items
		nouveau = new JMenuItem(new ActionMenuNouveau(model));
		ouvrir = new JMenuItem(new ActionMenuOuvrir(model));
		enregistrer = new JMenuItem(new ActionMenuEnregistrer(model));
		enregistrerSous = new JMenuItem(new ActionMenuEnregistrerSous(model));
		exit = new JMenuItem(new ActionMenuQuitter(model));
		copy = new JMenuItem(new ActionMenuCopier(model));
		cut = new JMenuItem(new ActionMenuCouper(model));
		paste = new JMenuItem(new ActionMenuColler(model));
		image = new JMenuItem(new ActionMenuInsererImage(model));
		
		// Menu : Fichiers
		fileMenu.addSeparator();
		fileMenu.add(nouveau);
		fileMenu.add(ouvrir);
		fileMenu.add(enregistrer);
		fileMenu.add(enregistrerSous);
		fileMenu.add(exit);

		// Menu : Édition
		editMenu.addSeparator();
		editMenu.add(cut);	
		editMenu.add(copy);
		editMenu.add(paste);
		
		// Menu : Insertion
		insertionMenu.add(image);
	}
}
