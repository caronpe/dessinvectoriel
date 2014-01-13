package controler;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Calque;
import model.Model;

public class ActionCalqueView extends AbstractAction implements Observer{
	
	private Model model;
	private Calque calque;
	private JButton bouton;
	private URL urlOpenedEyes, urlClosedEyes;
	
	public ActionCalqueView(Model model, Calque calque, JButton bouton){
		this.model=model;
		this.model.addObserver(this);
		this.bouton=bouton;
		this.calque= calque;
		
		// URL
		this.urlOpenedEyes = ClassLoader.getSystemClassLoader().getResource("ressources/images/openedEye.png");
		this.urlClosedEyes = ClassLoader.getSystemClassLoader().getResource("ressources/images/closedEye.png");
		
		this.putValue(SHORT_DESCRIPTION, "Affiche/Masque le calque");
		this.putValue(SMALL_ICON, new ImageIcon(urlOpenedEyes));
	}	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.setAfficherCalque(this.calque);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(calque.getAfficher()==true){
			bouton.setIcon(new ImageIcon(urlOpenedEyes));
		}else if(calque.getAfficher()==false){
			bouton.setIcon(new ImageIcon(urlClosedEyes));
		}
	}
}
