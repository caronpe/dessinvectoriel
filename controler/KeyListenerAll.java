package controler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Model;
// INTERNE

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
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			model.setGPressed(true);
			System.out.println("Shift appuyé");
		}
				
		if (e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()) {
			System.out.println("Ctrl-S pressé !");
			new ActionMenuEnregistrer(model).enregistrer();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_O && e.isControlDown()) {
			System.out.println("Ctrl-O pressé !");
			new ActionMenuOuvrir(model).ouvrir();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown()) {
			System.out.println("Ctrl-O pressé !");
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
			model.setGPressed(false);
			System.out.println("Shift relâché");
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
