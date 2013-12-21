package ressources;

public class OSDefaultDirectoryNotFound extends Exception {
	public OSDefaultDirectoryNotFound() {
		System.err.println("OS non reconnu, répertoire par défaut par conséquent non trouvé");
	}
}