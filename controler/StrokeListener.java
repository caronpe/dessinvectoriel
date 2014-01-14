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

import model.Model;

public class StrokeListener implements KeyListener, MouseListener {
	private Model model;
	private JTextField strokeField;
	private boolean exited;
	
	public StrokeListener(Model model){
		this.model=model;
		this.exited = true;
	}
	
	public void setStrokeField(JTextField strokeField) {
		this.strokeField = strokeField;
		this.strokeField.setInputVerifier(new StrokeCheck(model, strokeField));
		this.strokeField.addKeyListener(this);
		this.strokeField.setCaretColor(Color.BLUE);
		this.strokeField.setForeground(Color.RED);
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
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == this.strokeField ) {
			this.exited = false;
			this.strokeField.setFocusable(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == this.strokeField ) {
			this.exited = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (this.exited) {
			this.strokeField.setFocusable(false);
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent arg0) {
	}
}

class StrokeCheck extends InputVerifier {
	private Model model;
	private JTextField strokeField;
	
	public StrokeCheck(Model model, JTextField strokeField) {
		this.model = model;
		this.strokeField = strokeField;
	}
	
	public boolean shouldYieldFocus(JComponent input) {
		boolean valid = verify(input);

		if (valid) {
			model.setStroke(strokeField.getText());
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
