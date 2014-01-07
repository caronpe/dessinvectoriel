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

import model.Model;

/**
 * Listener pour le menu Copier. Contient toutes les fonctions de copie de forme.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionMenuInsererImage extends AbstractAction {
	private Model model;
	private BufferedImage photo ;
//	private Component parent; // Ouvre une boite de dialogue réouverture de fichier.
	
	public ActionMenuInsererImage(Model model) {
		this.model = model;
		
		putValue(NAME, "Insérer");
		putValue(SHORT_DESCRIPTION, "Insère une image");
		this.putValue(SMALL_ICON, new ImageIcon("ressources/images/insert.png"));
	}

	public void actionPerformed(ActionEvent arg0) {
		photo = null;

		// DialogBox
		try {
			String approve = new String("Insérer"); // Étiquette : "Enregistrer"
			JFileChooser choix = new JFileChooser(new File(""));
			choix.removeChoosableFileFilter(choix.getAcceptAllFileFilter());
			choix.setFileFilter(new ImageFilter());
			
//			int retour = choix.showOpenDialog(parent); // AVANT
			
			int retour = choix.showDialog(choix, approve);
			if(retour == JFileChooser.APPROVE_OPTION) {
				photo=ImageIO.read(new File(choix.getSelectedFile().getAbsolutePath()));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addForme(photo);
		System.out.println("passe1");
	}
}

class ImageFilter extends FileFilter {
	public boolean accept(File f){
		if(f.isDirectory()) return true;
		else if(f.getName().endsWith(".jpg")) return true;
		else if(f.getName().endsWith(".png")) return true;
		else if(f.getName().endsWith(".gif")) return true;
		else if(f.getName().endsWith(".bmp")) return true;
		else return false;
	}

	public String getDescription(){
		return ".jpg .png .gif .bmp";

	}
}
