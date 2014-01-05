package view;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
// INTERNE
import model.Calque;
import model.Model;

/**
 * Fenêtre contenant les différents calques manipulables. 
 * Ce panneau sera sous la forme d'une liste déroulante de calques.
 * Envoie toutes les notifications aux CalqueView.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4
 */
public class CalquePanel extends JPanel implements Observer {
	Model model;
	ArrayList<CalqueView> listCalquesView;
	ZoneDessin zoneDessin;

	public CalquePanel(Model model, ZoneDessin zoneDessin) {
		super();
		this.model = model;
		this.model.addObserver(this);
		this.zoneDessin = zoneDessin;
		this.setLayout(new BoxLayout(this, 1));
		this.listCalquesView = new ArrayList<CalqueView>();
		this.addCalque(model.getCalqueCourant());
	}

	/**
	 * Récupère le calque envoyé par le modèle et l'ajoute au panel et à la liste de calque.
	 * /!\ Le modèle envoie un calque vide.  
	 * 
	 * @param calque
	 */
	public void addCalque(Calque calque) {
		CalqueView tmp = new CalqueView(model, calque, zoneDessin , this);
		this.listCalquesView.add(tmp);
		this.add(tmp);
	}
	
	public void removeCalque(Calque calque) {		
		// Avertissement à tous les calques
		boolean trouve = false;
		CalqueView calqueView = null;
		ListIterator<CalqueView> it = this.listCalquesView.listIterator();
		
		while (calque != null && it.hasNext() && !trouve) {
			calqueView = it.next();
			if (calqueView.getCalque() == calque) {
				this.remove(calqueView);
				it.remove();
				this.revalidate();
			}
		}
	}

	/**
	 * Ajoute un calque à la zone de calque.
	 * Informe également tous les calques
	 * qu'il y a eu une modification (utile pour la suppression).
	 */
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Calque) {
			this.addCalque((Calque) arg1);
			this.revalidate();
		}
		
		// Avertissement à tous les calques
		ListIterator<CalqueView> it = this.listCalquesView.listIterator();
		while (it.hasNext()) {
			it.next().update();
		}
	}
}
