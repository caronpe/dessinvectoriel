package view;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Model;
import controler.ActionMenuCopier;
import controler.ActionMenuCouper;
import controler.ActionMenuEnregistrer;
import controler.ActionMenuEnregistrerSous;
import controler.ActionMenuNouveau;
import controler.ActionMenuOuvrir;
import controler.ActionMenuQuitter;
import controler.ActionMenuType;

public class MenuHaut extends JMenuBar {
	private JMenu fileMenu, editMenu, formeMenu;
	private JMenuItem nouveau, ouvrir, enregistrer, enregistrerSous, exit;
	private JMenuItem copy, paste;
	private JMenuItem plein;

	public MenuHaut(Model model){
		// Menus
		fileMenu = new JMenu("Fichier");
		editMenu = new JMenu("Édition");
		formeMenu = new JMenu("Forme");
		
		// Ajouts
		this.add(fileMenu);
		this.add(editMenu);
		this.add(formeMenu);
		
		// Items
		nouveau = new JMenuItem(new ActionMenuNouveau(model));
		ouvrir = new JMenuItem(new ActionMenuOuvrir(model));
		enregistrer = new JMenuItem(new ActionMenuEnregistrer(model));
		enregistrerSous = new JMenuItem(new ActionMenuEnregistrerSous(model));
		exit = new JMenuItem(new ActionMenuQuitter(model));
		copy = new JMenuItem(new ActionMenuCopier());
		paste = new JMenuItem(new ActionMenuCouper());
		plein = new JCheckBoxMenuItem(new ActionMenuType(model));
		
		// Menu : Fichiers
		fileMenu.addSeparator();
		fileMenu.add(nouveau);
		fileMenu.add(ouvrir);
		fileMenu.add(enregistrer);
		fileMenu.add(enregistrerSous);
		fileMenu.add(exit);

		// Menu : Édition
		editMenu.addSeparator();
		editMenu.add(copy);
		editMenu.add(paste);
		
		// Menu : Forme
		formeMenu.add(plein);
	}
}
