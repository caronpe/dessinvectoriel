package controler;
import fenetre.Fenetre_principale;

import java.awt.event.ActionEvent;

public class Controler {
	
	private Fenetre_principale fenetre;
	
	public Controler(Fenetre_principale fenetre) {
		super();
		this.fenetre = fenetre;
	}
	
	public void actionPerformed(ActionEvent e) {				
		// QUITTER
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equals("Exit") ) {
			System.out.println("Quitter");
		}
	}
}
