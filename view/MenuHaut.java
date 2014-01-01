package view;

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

public class MenuHaut extends JMenuBar {
	
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenuItem nouveau;
	private JMenuItem ouvrir;
	private JMenuItem enregistrer;
	private JMenuItem enregistrerSous;
	private JMenuItem exitAction;
	private JMenuItem copy;
	private JMenuItem paste;
	
	
	public MenuHaut(Model model){
		// Création Barre de menu
		
				// Menus
				fileMenu = new JMenu("Fichier");
				editMenu = new JMenu("Édition");
				
				// Ajouts
				this.add(fileMenu);
				this.add(editMenu);
				
				// Items
				nouveau = new JMenuItem(new ActionMenuNouveau(model));
				ouvrir = new JMenuItem(new ActionMenuOuvrir(model));
				enregistrer = new JMenuItem(new ActionMenuEnregistrer(model));
				enregistrerSous = new JMenuItem(new ActionMenuEnregistrerSous(model));
				exitAction = new JMenuItem(new ActionMenuQuitter(model));
				copy = new JMenuItem(new ActionMenuCopier());
				paste = new JMenuItem(new ActionMenuCouper());
				
				// Menu : Fichiers
				fileMenu.addSeparator();
				fileMenu.add(nouveau);
				fileMenu.add(ouvrir);
				fileMenu.add(enregistrer);
				fileMenu.add(enregistrerSous);
				fileMenu.add(exitAction);

				// Menu : Édition
				editMenu.addSeparator();
				editMenu.add(copy);
				editMenu.add(paste);
	}
}
