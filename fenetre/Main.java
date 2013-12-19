package fenetre;

// INTERNE
import model.*;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		Fenetre_principale paint = new Fenetre_principale(model);
		paint.afficher();
	}
}