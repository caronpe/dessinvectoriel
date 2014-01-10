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
		
		if (e.getKeyCode() == KeyEvent.VK_DELETE) model.delFormes();
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
