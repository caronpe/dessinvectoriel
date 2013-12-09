package fenetre;

import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class fenetre extends JFrame implements Observer {

	
	public fenetre() throws HeadlessException {
		super();
		this.setTitle("logiciel de dessin vectoriel");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
