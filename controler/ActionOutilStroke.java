package controler;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Model;

public class ActionOutilStroke implements KeyListener,FocusListener,MouseListener{

	public Model model;
	public JTextField field;
	public Border border;

	public ActionOutilStroke(Model model,JTextField field){
		this.model=model;
		this.field=field;
		this.field.addFocusListener(this);
		this.field.setCaretColor(Color.BLUE);
		this.field.setForeground(Color.RED);
		this.field.setSelectedTextColor(Color.GREEN);
		this.field.setSelectionColor(Color.YELLOW);
	}

	//INCOMING => Un beau texte filter

	public boolean verifierTextField(String s){

		if(s.equals("")){
			return false;
		}

		for(int i=0;i<s.length();i++){
			if(!Character.isDigit(s.charAt(i))){
				return false;
			}
		}
		return true;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(this.verifierTextField(field.getText())) {
				model.setStroke(field.getText());
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("passe5");
		if(this.verifierTextField(field.getText())) {
			model.setStroke(field.getText());
		}
	}


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

	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
