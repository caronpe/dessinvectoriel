package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Calque;
import model.Model;
import ressources.DimensionMenuDroit;
import controler.CalqueListener;

/**
 * Panel spécialisé correspondant à un calque.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Ã‰douard Caron
 * 
 * @version 0.2
 */


public class CalqueView extends JPanel{

		Calque calque;
		Model model;
		
		public CalqueView(Model model , Calque calque , Image im){
			this.calque = calque;
			this.model = model;
			this.setPreferredSize(new DimensionMenuDroit());
			this.setMaximumSize(new DimensionMenuDroit());
			//met l'image représentative du calque sur le panel
			this.add(new JLabel(new ImageIcon(im)));
			this.addMouseListener(new CalqueListener(model , calque));
		}
		
		public update(){
			
		}
}
