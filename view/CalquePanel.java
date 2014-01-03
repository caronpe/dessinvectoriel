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
 * @author Pierre-Ã‰douard Caron
 * 
 * @version 0.2
 */

public class CalquePanel  extends JPanel{

		Model model;
		ZoneDessin zoneDessin;

		public CalquePanel(Model model , ZoneDessin zoneDessin){
			
			this.model = model;
			this.zoneDessin = zoneDessin;
			this.setLayout(new BoxLayout(this , 1));
			
			
		}

		/**
		 *crée une représentation du calque et l'ajoute au panel
		 * @param calque
		 */
		public void addCalque(Calque calque) {
			// TODO Auto-generated method stub
			this.add(new CalqueView(model, calque , zoneDessin.getImage()));
		}
		
		

	
}
