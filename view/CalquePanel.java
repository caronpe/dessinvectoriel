package view;

import javax.swing.JPanel;

import model.Model;

/**
 * Fenetre contenant les different calques manipulables.
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
			
		}
}
