package ressources;

import java.awt.Color;

/**
 * Partie Modèle du MVC
 * Fournit des utilitaires permettant de gérer la compatibilité
 * avec différents OS
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class OSDetection {
	private static final String OS = System.getProperty("os.name").toLowerCase();
	
	/**
	 * Gère le dossier par défaut de l'utilisateur
	 * @return La variable définissant le dossier de l'utilisateur
	 * @throws OSDefaultDirectoryNotFound Répertoire utilisateur non trouvé
	 */
	public static String getUserDirectory() throws OSDefaultDirectoryNotFound {
		if (isWindows()) {
			return "%USERPROFILE%";
		} else if (isUnix() || isMac()) {
			return "~";
		} else {
			throw new OSDefaultDirectoryNotFound();
		}
	}
	
	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
	
	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}
 
	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
 
	public static boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}
}
