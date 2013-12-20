package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.Model;

public class zoneDessin_listener implements MouseListener {
	Point pointDebut, pointArrivee;
	JPanel panel;
	Model model;
	
	public zoneDessin_listener(JPanel pan, Model model) {
		super();
		this.panel = pan;
		this.model = model;
	}

	public void mousePressed(MouseEvent e) {
		pointDebut = e.getPoint();
	}

	public void mouseReleased(MouseEvent arg0) {
		pointArrivee = arg0.getPoint();
		model.addForme(pointDebut, pointArrivee, panel);
	}
	
	// Inutile
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
}
