package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Model;

/**
 * Fenetre contenant les different calques manipulables.
 * ce paneaux sera sous la forme d'une liste deroulante de calques
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */

public class CalquePanel  extends JPanel{

		Model model;

		public CalquePanel(Model model){
			
			this.model = model;
			this.setLayout(new BoxLayout(this , 1));
			this.add(new Calque(Color.black));
			this.add(new Calque(Color.green));
			
		}

	
}
