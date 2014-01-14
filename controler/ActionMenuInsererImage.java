package controler;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
// INTERNE
import ressources.URLIcons;
import model.Model;

/**
 * Listener pour le menu Copier. Contient toutes les fonctions de copie de forme.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
public class ActionMenuInsererImage extends AbstractAction {
	private Model model;
	private BufferedImage photo ;
	
	public ActionMenuInsererImage(Model model) {
		this.model = model;
		
		// Values
		putValue(NAME, "Insérer");
		putValue(SHORT_DESCRIPTION, "Insère une image");
		this.putValue(SMALL_ICON, new ImageIcon(URLIcons.INSERT));
	}

	/**
	 * Affiche un FileChooser qui importe l'image en fonction des extensions gérées
	 * pas ImageFilter.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		photo = null;

		// DialogBox
		try {
			String approve = new String("Insérer"); 
			JFileChooser choix = new JFileChooser(new File(""));
			choix.removeChoosableFileFilter(choix.getAcceptAllFileFilter());
			choix.setFileFilter(new ImageFilter());
			
			int retour = choix.showDialog(choix, approve);
			if (retour == JFileChooser.APPROVE_OPTION) {
				photo=ImageIO.read(new File(choix.getSelectedFile().getAbsolutePath()));
				model.addForme(photo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * S'occupe des extension d'import d'image et les filtre dans le FileChooser.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.4 finale
 */
class ImageFilter extends FileFilter {
	public boolean accept(File f){
		if(f.isDirectory()) return true;
		else if(f.getName().endsWith(".jpg")) return true;
		else if(f.getName().endsWith(".png")) return true;
		else if(f.getName().endsWith(".gif")) return true;
		else if(f.getName().endsWith(".bmp")) return true;
		else return false;
	}

	/**
	 * Retourne la description des extensions telles
	 * qu'elles seront affichées dans le FileChooser.
	 * 
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	public String getDescription(){
		return "Fichiers .jpg, .png, .gif et .bmp";
	}
}
