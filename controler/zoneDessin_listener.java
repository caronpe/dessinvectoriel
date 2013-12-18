package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.Model;

public class zoneDessin_listener implements MouseListener {
	Point deb, arr ;
	JPanel pan;
	Model mod;
	
	public void mousePressed(MouseEvent arg0) {
		deb = arg0.getPoint();
	}

	public zoneDessin_listener(JPanel pan, Model mod) {
		super();
		this.pan = pan;
		this.mod = mod;
	}

	public void mouseReleased(MouseEvent arg0) {
		arr = arg0.getPoint();
		mod.addForme(deb, arr, pan);
	}
	
	// Inutiles pour le moment
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
}
