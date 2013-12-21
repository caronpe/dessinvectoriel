package main;

// INTERNE
import model.Model;
import view.FenetrePrincipale;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		FenetrePrincipale paint = new FenetrePrincipale(model);
		paint.afficher();
	}
}