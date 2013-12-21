package main;
// INTERNE
import model.*;
import view.*;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		FenetrePrincipale paint = new FenetrePrincipale(model);
		paint.afficher();
	}
}