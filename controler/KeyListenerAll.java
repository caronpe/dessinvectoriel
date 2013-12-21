package controler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Model;
// INTERNE

/**
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 *
 * @version v0.2
 */
public class KeyListenerAll extends KeyAdapter {
	private Model model;
	
	public KeyListenerAll(Model model) {
		this.model = model;
	}
	
	/**
	 * Modifie le modèle en fonction de la touche appuyée
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			model.setGPressed(true);
			System.out.println("Shift appuyé");
		}
		
		if ((e.getModifiers() == KeyEvent.CTRL_DOWN_MASK) && (e.getKeyCode()==KeyEvent.VK_S)) {
            System.out.println("Ctrl-S pressé !");
        }
	}

	/**
	 * Modifie le modèle en fonction de la touche relâchée
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			model.setGPressed(false);
			System.out.println("Shift relâché");
		}
	}
	
	/**
	 * @category unused
	 */
	public void keyTyped(KeyEvent arg0) {
	}
}
