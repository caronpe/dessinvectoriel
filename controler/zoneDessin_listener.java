package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.Model;

public class zoneDessin_listener implements MouseListener {

	Point deb , arr ;
	JPanel pan;
	Model mod;
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		deb = arg0.getPoint();
	}

	public zoneDessin_listener(JPanel pan, Model mod) {
		super();
		this.pan = pan;
		this.mod = mod;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		arr = arg0.getPoint();
		mod.addForme(deb , arr, pan);
	}

}
