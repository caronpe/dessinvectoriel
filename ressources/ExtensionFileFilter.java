package ressources;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Gère l'extension lors de l'appel d'un FileChooser.
 * 
 * @author Developpez.net
 * 
 * @version 0.4 finale
 */
public class ExtensionFileFilter extends FileFilter {
	  String description, extensions[];
	  
	/**
	 * @param description La façon dont les extensions vont être affichées dans le JTextField du FileChooser.
	 * @param extension Extensions en elle-même
	 */
	public ExtensionFileFilter(String description, String extension) {
	    this(description, new String[] { extension });
	  }

	/**
	 * @param description La façon dont les extensions vont être affichées dans le JTextField du FileChooser.
	 * @param extensions Extensions en elle-même.
	 */
	public ExtensionFileFilter(String description, String extensions[]) {
		if (description == null) {
			this.description = extensions[0];
		} else {
			this.description = description;
		}
	this.extensions = (String[]) extensions.clone();
	toLower(this.extensions);
	}
	  
	private void toLower(String array[]) {
		for (int i = 0, n = array.length; i < n; i++) {
			array[i] = array[i].toLowerCase();
		}
	}
	
	public String getDescription() {
		return description;
	}

	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		} else {
			String path = file.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++) {
				String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
					return true;
				}
			}
		}
		return false;
	}
}