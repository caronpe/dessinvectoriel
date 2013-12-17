package fenetre;

// Interne
import controler.*;

import model.*;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		Fenetre_principale paint = new Fenetre_principale(model);
		Controler controler = new Controler(paint);
		paint.afficher();
	}
}