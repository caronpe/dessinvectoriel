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
		JLabel image;
		ZoneDessin zoneDessin;
		
		
		public CalqueView(Model model, ZoneDessin zoneDessin) {
			super();
			this.model = model;
			this.zoneDessin = zoneDessin;

			this.setPreferredSize(new DimensionMenuDroit());
			this.setMaximumSize(new DimensionMenuDroit());
			//met l'image représentative du calque sur le panel
			this.image = new JLabel(new ImageIcon());
			this.add(image);
			this.addMouseListener(new CalqueListener(model , calque));
		}

		public CalqueView(Model model , Calque calque , ZoneDessin zoneDessin){
			this.calque = calque;
			this.model = model;
			this.zoneDessin = zoneDessin;
			this.setPreferredSize(new DimensionMenuDroit());
			this.setMaximumSize(new DimensionMenuDroit());
			//met l'image représentative du calque sur le panel
			this.image = new JLabel(new ImageIcon(zoneDessin.getImage()));
			this.add(image);
			this.addMouseListener(new CalqueListener(model , calque));
		}
		
		/**
		 * met a jour l'image quand la zone de dessin est modifié
		 */
		public void update(){
			image.setIcon(new ImageIcon(zoneDessin.getImage()));
		}
}
