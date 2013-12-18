package fenetre;

import java.awt.Graphics;

import javax.swing.JPanel;

import model.Forme;

public class zoneDessin extends JPanel {
	
	public void paintComponent(Graphics g , Forme courante){
		
		if(type == "rond"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().fillOval((int)deb.getX() , (int)deb.getY(), (int)arr.getX(), (int)arr.getY());
		}
		if(type == "carre"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().fillRect((int)deb.getX() , (int)deb.getY(), (int)arr.getX(), (int)arr.getY());
		}
		if(type == "droite"){
			pan.getGraphics().setColor(couleur);
			pan.getGraphics().drawLine((int)deb.getX() , (int)deb.getY(), (int)arr.getX(), (int)arr.getY());
		}		
	}
}
