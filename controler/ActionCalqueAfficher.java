package controler;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import ressources.URLIcons;
import model.Calque;
import model.Model;

public class ActionCalqueAfficher extends AbstractAction implements Observer{
	private Model model;
	private Calque calque;
	private JButton bouton;
	
	public ActionCalqueAfficher(Model model, Calque calque, JButton bouton){
		this.model=model;
		this.model.addObserver(this);
		this.bouton=bouton;
		this.calque= calque;
		
		this.putValue(SHORT_DESCRIPTION, "Affiche/Masque le calque");
		this.putValue(SMALL_ICON, new ImageIcon(URLIcons.OPENED_EYE));
	}	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.setAfficherCalque(this.calque);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(calque.getAfficher()==true){
			bouton.setIcon(new ImageIcon(URLIcons.OPENED_EYE));
		}else if(calque.getAfficher()==false){
			bouton.setIcon(new ImageIcon(URLIcons.CLOSED_EYE));
		}
	}
}
