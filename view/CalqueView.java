package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ressources.DimensionMenuGauche;

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

		public CalqueView(Color c){
			this.setPreferredSize(new DimensionMenuGauche());
			this.setBackground(c);
			this.setMaximumSize(new DimensionMenuGauche());

		}
}
