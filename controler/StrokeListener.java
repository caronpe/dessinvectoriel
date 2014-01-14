package controler;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
// INTERNE
import model.Model;

/**
 * Classe permettant de gérer les interactions entre l'utilisateur et
 * le JTextField de stroke.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class StrokeListener implements KeyListener, MouseListener {
	private Model model;
	private JTextField strokeField;
	private boolean exited;
	
	/**
	 * Le programme démarre avec le JTextField n'ayant pas le focus.
	 * 
	 * @param model Modèle du MVC
	 */
	public StrokeListener(Model model){
		this.model = model;
		this.exited = true;
	}
	
	/**
	 * @param strokeField JTextField qu'on paramètre
	 */
	public void setStrokeField(JTextField strokeField) {
		this.strokeField = strokeField;
		this.strokeField.setInputVerifier(new StrokeCheck(model, strokeField));
		this.strokeField.addKeyListener(this);
		this.strokeField.setCaretColor(Color.BLUE);
		this.strokeField.setForeground(Color.BLACK);
		initNewTraversalKey();
	}
	
	/**
	 * Cette méthode permet de définir Entrée comme étant une touche de validation du strokeField.
	 * On recupère la touche par défaut (TAB), on ajoute dans la nouvelle liste la liste précédente,
	 * on recupère la touche Entrée et on ajoute la touche Entrée.
	 */
	public void initNewTraversalKey(){
		Set<AWTKeyStroke> touche, newTouches;
		
		touche = strokeField.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		newTouches = new HashSet<AWTKeyStroke>(touche);
		newTouches.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		strokeField.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newTouches);
	}
	
	/**
	 * Exited est à false et le TextField devient focusable.
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == this.strokeField ) {
			this.exited = false;
			this.strokeField.setFocusable(true);
		}
	}

	/**
	 * Quand le curseur quitte le TextField, exited est à true.
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == this.strokeField ) {
			this.exited = true;
		}
	}

	/**
	 * Si le curseur est hors du TextField lors du clic, le TextField devient non
	 * focusable et perd donc le focus. 
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		if (this.exited) {
			this.strokeField.setFocusable(false);
		}
	}
	
	/**
	 * @category unused
	 * @deprecated
	 */
	public void mouseClicked(MouseEvent arg0) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void mouseReleased(MouseEvent arg0) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void keyPressed(KeyEvent arg0) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void keyReleased(KeyEvent e) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void keyTyped(KeyEvent arg0) {
	}
}

/**
 * Vérifie l'intégrité des données du TextField stroke.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
class StrokeCheck extends InputVerifier {
	private Model model;
	private JTextField strokeField;
	
	/**
	 * @param model Modèle du MVC
	 * @param strokeField JTextField dont on va tester les données.
	 */
	public StrokeCheck(Model model, JTextField strokeField) {
		this.model = model;
		this.strokeField = strokeField;
	}
	
	/**
	 * Affiche un message si les données entrées ne sont pas correct et
	 * émets un son. Si les données sont correctes, application au modèle.
	 * 
	 * @see javax.swing.InputVerifier#shouldYieldFocus(javax.swing.JComponent)
	 */
	public boolean shouldYieldFocus(JComponent input) {
		boolean valid = verify(input);

		if (valid) {
			model.setStroke(new Float(strokeField.getText()));
			return true;
		} else {
			// Beep
			Toolkit.getDefaultToolkit().beep();
			
			// Fenêtre
			JOptionPane.showConfirmDialog(new JFrame(), "Épaisseur non correcte. Entrez un chiffre entre 1 et 100. \nUtilisez un point pour les nombres décimaux.", "Erreur",
					JOptionPane.PLAIN_MESSAGE, JOptionPane.WARNING_MESSAGE);
			
			// On le rend non focusable
			this.strokeField.setFocusable(false);
			
			return false;
		}
	}

	/**
	 * Vérifie l'intégrité des données du TextField pour coller
	 * à la définition d'un stroke.
	 * 
	 * @see javax.swing.InputVerifier#verify(javax.swing.JComponent)
	 */
	@Override
	public boolean verify(JComponent e) {
		JTextField field = (JTextField) e;
		String value = field.getText();
		
		try {
			float number = Float.parseFloat(value);
			
			if (number < 1 || number > 100) {
				return false;
			}
		} catch (NumberFormatException pe) {
			return false;
		}

		return true;
	}
}
