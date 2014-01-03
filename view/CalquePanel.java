package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Calque;
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
			this.add(new CalqueView(Color.black));
			this.add(new CalqueView(Color.green));
			
		}

		public void addCalque(Calque calque) {
			// TODO Auto-generated method stub
			System.out.println("ajout de calque");
			this.add(new CalqueView(Color.black));
		}
		
		

	
}
