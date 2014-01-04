package view;

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
		ZoneDessin zoneDessin;
		CalqueView dernierCalque;

		public CalquePanel(Model model , ZoneDessin zoneDessin){
			
			this.model = model;
			this.zoneDessin = zoneDessin;
			this.setLayout(new BoxLayout(this , 1));
			dernierCalque = new CalqueView(model,  zoneDessin);		
		}

		/**
		 *cr�e une repr�sentation du calque et l'ajoute au panel
		 * @param calque
		 */
		public void addCalque(Calque calque) {
			// TODO Auto-generated method stub
			dernierCalque = new CalqueView(model, calque , zoneDessin);
			this.add(dernierCalque);
		}
		
		/**
		 * met � jour l'affichage du Calqueview
		 */
		public void updateCalqueView(){
			dernierCalque.update();
		}
		
		

	
}
