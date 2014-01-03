package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Calque;
import ressources.DimensionMenuDroit;

/**
 * Panel sp�cialis� correspondant � un calque.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */


public class CalqueView extends JPanel{

		public CalqueView(Calque calque , Image im){
			this.setPreferredSize(new DimensionMenuDroit());
			this.setMaximumSize(new DimensionMenuDroit());
			this.add(new JLabel(new ImageIcon(im)));
		}
}
