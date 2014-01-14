package ressources;

import java.net.URL;

/**
 * Singleton d'URL utilisés pour les ImageIcon.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class URLIcons {
	public static URL ABOUT = ClassLoader.getSystemClassLoader().getResource("ressources/images/about.png");
	public static URL HELP = ClassLoader.getSystemClassLoader().getResource("ressources/images/questionMark.png");
	
	public static URL CLOSED_EYE = ClassLoader.getSystemClassLoader().getResource("ressources/images/closedEye.png");
	public static URL OPENED_EYE = ClassLoader.getSystemClassLoader().getResource("ressources/images/openedEye.png");
	public static URL DELCALQUE = ClassLoader.getSystemClassLoader().getResource("ressources/images/delCalque.png");
	
	public static URL COPY = ClassLoader.getSystemClassLoader().getResource("ressources/images/copy.png");
	public static URL CUT = ClassLoader.getSystemClassLoader().getResource("ressources/images/cut.png");
	public static URL PASTE = ClassLoader.getSystemClassLoader().getResource("ressources/images/paste.png");
	
	public static URL NEWFILE = ClassLoader.getSystemClassLoader().getResource("ressources/images/newFile.png");
	public static URL OPEN = ClassLoader.getSystemClassLoader().getResource("ressources/images/open.png");
	public static URL SAVE = ClassLoader.getSystemClassLoader().getResource("ressources/images/save.png");
	public static URL SAVEAS = ClassLoader.getSystemClassLoader().getResource("ressources/images/saveAs.png");
	public static URL QUIT = ClassLoader.getSystemClassLoader().getResource("ressources/images/quit.png");
	
	public static URL INSERT = ClassLoader.getSystemClassLoader().getResource("ressources/images/insert.png");

	public static URL SELECTION = ClassLoader.getSystemClassLoader().getResource("ressources/images/selection.png");
	public static URL CERCLEPLEIN = ClassLoader.getSystemClassLoader().getResource("ressources/images/cerclePlein.png");
	public static URL CERCLEVIDE = ClassLoader.getSystemClassLoader().getResource("ressources/images/cercleVide.png");
	public static URL RECTANGLEVIDE = ClassLoader.getSystemClassLoader().getResource("ressources/images/rectangleVide.png");
	public static URL RECTANGLEPLEIN = ClassLoader.getSystemClassLoader().getResource("ressources/images/rectanglePlein.png");
	public static URL CRAYON = ClassLoader.getSystemClassLoader().getResource("ressources/images/crayon.png");
}
