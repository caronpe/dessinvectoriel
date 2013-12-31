package controler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//INTERNE
import model.Model;


/**
 * Contient tous les listeners concernant le clavier.
 * Gère la touche shift pour dessiner des formes parfaites. 
 * Gère également les raccourcis clavier.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 *
 * @version 0.2
 */
public class KeyListenerAll extends KeyAdapter {
	private Model model;
	
	public KeyListenerAll(Model model) {
		this.model = model;
	}
	
	/**
	 * Modifie le modèle en fonction de la touche appuyée et gère les raccourcis.
	 */
	public void keyPressed(KeyEvent e) {
		// Fonctions
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) model.setShiftPressed(true);
		
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) model.setControlPressed(true);
		
		if (e.getKeyCode() == KeyEvent.VK_DELETE) model.supprimerFormes();
		
		// Raccourcis
		if (e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()) {
			System.out.println("Ctrl-S pressé !");
			new ActionMenuEnregistrerSous(model).enregistrerSous();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_O && e.isControlDown()) {
			System.out.println("Ctrl-O pressé !");
			new ActionMenuOuvrir(model).ouvrir();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_Q && e.isControlDown()) {
			System.out.println("Ctrl-Q pressé !");
			new ActionMenuQuitter(model).quitter();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown()) {
			System.out.println("Ctrl-N pressé !");
			if (model.getEnregistre()) {
				new ActionMenuNouveau(model).nouveau();
			} else {
				new ActionMenuNouveau(model).nouveau_non_enregistre();
			}
			new ActionMenuNouveau(model).nouveau();
		}
	}

	/**
	 * Modifie le modèle en fonction de la touche relâchée.
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			model.setShiftPressed(false);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			model.setControlPressed(false);
		}
	}
	
	/**
	 * L'usage de keytyped est déconseillé.
	 * 
	 * @category unused
	 *
	 * @see #keyPressed
	 * @see #keyReleased
	 * 
	 * @deprecated
	 */
	public void keyTyped(KeyEvent arg0) {
	}
}
