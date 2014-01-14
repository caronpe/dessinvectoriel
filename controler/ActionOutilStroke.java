package controler;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import model.Model;

public class ActionOutilStroke  implements KeyListener{

	public Model model;
	public JTextField field;
	public Border border;
	public Set<AWTKeyStroke> touche, newTouche;
	
	public ActionOutilStroke(Model model,JTextField field){
		this.model=model;
		this.field=field;
		this.field.setInputVerifier(new Verifier());
		this.field.addKeyListener(this);
		this.field.setCaretColor(Color.BLUE);
		this.field.setForeground(Color.RED);
		initNewTraversalKey();
	}
	
	public void initNewTraversalKey(){
		
		//On recupère la touche par defaut (ici tab)
		Set<AWTKeyStroke> touche = field.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		
		//On ajoute dans la nouvelle liste la liste précédente
		Set<AWTKeyStroke> newTouche = new HashSet<AWTKeyStroke>(touche);
		
		//On recupère la touche entree
		newTouche.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		
		//On ajoute la touche entree
		field.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newTouche);
	
	}
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	

	public class Verifier extends InputVerifier{
		public boolean shouldYieldFocus(JComponent input) {
	        boolean valid = verify(input);
	 
	        if (valid) {
	        	System.out.println("passe");
	        	model.setStroke(field.getText());
	            return true;
	        } else {
	            Toolkit.getDefaultToolkit().beep();
	            return false;
	        }
	    }
	 
	@Override
	public boolean verify(JComponent e) {
		JTextField field = (JTextField)e;
		System.out.println("passe");
    	String value = field.getText();
    	try {
    		Integer.parseInt(value);
        } catch (NumberFormatException pe) {
            return false;
        }
 
    	
 
        return true;
	}
		
	}
}
