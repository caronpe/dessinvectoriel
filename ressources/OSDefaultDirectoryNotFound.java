package ressources;

/**
 * Exception jetée lors de l'appel d'OSDetection.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class OSDefaultDirectoryNotFound extends Exception {
	public OSDefaultDirectoryNotFound() {
		System.err.println("OS non reconnu, répertoire par défaut par conséquent non trouvé");
	}
}