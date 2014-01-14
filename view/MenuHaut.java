package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
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

/**
 * Définit tous les composant du menu du haut.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
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
		nouveau.setAccelerator(KeyStroke.getKeyStroke(
		        java.awt.event.KeyEvent.VK_N, 
		        java.awt.Event.CTRL_MASK));
		
		ouvrir = new JMenuItem(new ActionMenuOuvrir(model));
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(
		        java.awt.event.KeyEvent.VK_O, 
		        java.awt.Event.CTRL_MASK));
		
		enregistrer = new JMenuItem(new ActionMenuEnregistrer(model));
		enregistrer.setAccelerator(KeyStroke.getKeyStroke(
		        java.awt.event.KeyEvent.VK_S,
		        java.awt.Event.CTRL_MASK));
		
		enregistrerSous = new JMenuItem(new ActionMenuEnregistrerSous(model));
		enregistrerSous.setAccelerator(KeyStroke.getKeyStroke(
		        java.awt.event.KeyEvent.VK_S, 
		        java.awt.Event.CTRL_MASK | java.awt.Event.SHIFT_MASK));
		
		exit = new JMenuItem(new ActionMenuQuitter(model));
		exit.setAccelerator(KeyStroke.getKeyStroke(
		        java.awt.event.KeyEvent.VK_Q, 
		        java.awt.Event.CTRL_MASK));
		
		copy = new JMenuItem(new ActionMenuCopier(model));
		copy.setAccelerator(KeyStroke.getKeyStroke(
		        java.awt.event.KeyEvent.VK_C, 
		        java.awt.Event.CTRL_MASK));
		
		cut = new JMenuItem(new ActionMenuCouper(model));
		cut.setAccelerator(KeyStroke.getKeyStroke(
		        java.awt.event.KeyEvent.VK_X, 
		        java.awt.Event.CTRL_MASK));
		
		paste = new JMenuItem(new ActionMenuColler(model));
		paste.setAccelerator(KeyStroke.getKeyStroke(
		        java.awt.event.KeyEvent.VK_V, 
		        java.awt.Event.CTRL_MASK));
		
		image = new JMenuItem(new ActionMenuInsererImage(model));
		image.setAccelerator(KeyStroke.getKeyStroke(
		        java.awt.event.KeyEvent.VK_I, 
		        java.awt.Event.CTRL_MASK));
		
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
