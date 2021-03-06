package controler;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
// INTERNE
import model.Calque;
import model.Model;
import view.CalquePanel;
import view.CalqueView;

/**
 * Listener sur les panel calque. Gère toutes les action sur les calques : 
 * superposer, remplacer, supprimer, etc.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class CalqueListener implements MouseListener {
	private Model model;
	private Calque calque;
	private CalquePanel calquePanel;
	private CalqueView calqueView;

	/**
	 * @param model Modèle du MVC.
	 * @param calque Calque associé au calqueView
	 * @param calquePanel est appelé lors de la suppression d'un calque
	 * @param calqueView CalqueView concerné par le listener
	 */
	public CalqueListener(Model model, Calque calque, CalquePanel calquePanel, CalqueView calqueView ) {
		this.model = model;
		this.calque = calque;
		this.calquePanel= calquePanel;
		this.calqueView = calqueView;
	}

	/** 
	 * Ouverture d'un menu Contextuel sur clic droit donnant la possibilité de supprimer le calque.
	 * Gère également la 
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// Clic gauche
		if(arg0.getButton() == MouseEvent.BUTTON1){
			model.deselectionnerToutesLesFormes();
			model.setCalque(calque);
		}
		
		// Clic droit
		if(arg0.getButton() == MouseEvent.BUTTON3) {
			JPopupMenu menu = new JPopupMenu();
			JMenuItem rem = new JMenuItem("Supprimer");
			rem.addActionListener(new ActionCalqueRemove(calquePanel, model, calque));
			menu.add(rem);
			menu.show(calqueView, arg0.getX(), arg0.getY());
		}
	}
	
	/**
	 * Lorsque le cuseur entre dans le calqueView, les icônes s'affichent.
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent arg0) {
		calqueView.getAfficher().setVisible(true);
		calqueView.getSupprimer().setVisible(true);
		calqueView.repaint();
	}

	/**
	 * Lorsque le cuseur quitte le calqueView, les icônes ne sont plus visibles.
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {
		// Si le point du MouseEvent est dans un des boutons, on 
		Point event = e.getPoint();
		if ( !calqueView.getButtonPanel().contains(event)  ) {
			calqueView.getAfficher().setVisible(false);
			calqueView.getSupprimer().setVisible(false);
			calqueView.repaint();
		}
	}
	
	/**
	 * @category unused
	 * @deprecated
	 */
	public void mouseClicked(MouseEvent arg0) {
	}
	/**
	 * @category unused
	 * @deprecated
	 */
	public void mouseReleased(MouseEvent arg0) {
	}
}