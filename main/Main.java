package main;

// INTERNE
import model.Model;
import view.FenetrePrincipale;

/**
 * Éxecute l'affichage de la fenêtre.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 *
 */
public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		FenetrePrincipale paint = new FenetrePrincipale(model);
		paint.afficher();
	}
}