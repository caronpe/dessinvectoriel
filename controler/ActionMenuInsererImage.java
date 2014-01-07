package controler;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import ressources.ExtensionFileFilter;
import ressources.JFileChooserOverwrite;
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
	private Component parent; // Ouvre une boite de dialogue réouverture de fichier.
	private String extensions[];
	
	public ActionMenuInsererImage(Model model) {
		this.model = model;
		this.extensions = new String[]{ "jpg", "png", "gif", "bmp"};
		
		putValue(NAME, "Insérer");
		putValue(SHORT_DESCRIPTION, "Insère une image");
	}

	public void actionPerformed(ActionEvent arg0) {
		photo = null;

		// Fichier
			String adresse_du_fichier = "";
			File file = new File(adresse_du_fichier);
		
		// FileChooser
			JFileChooser filechoose = new JFileChooserOverwrite(new File(adresse_du_fichier));
			filechoose.setSelectedFile(file);
			String approve = new String("Insérer"); // Étiquette : "Enregistrer"
			
		// Extension
			ExtensionFileFilter filter = new ExtensionFileFilter("Fichiers  .jpg, .png, .gif, .bmp", extensions);
		    filechoose.setFileFilter(filter);
		
		 // DialogBox
		try {
			JFileChooser choix = new JFileChooser();
			FileFilter ff = new FileFilter(){
				public boolean accept(File f){
					if(f.isDirectory()) return true;
					else if(f.getName().endsWith(".jpg")) return true;
					else if(f.getName().endsWith(".")) return true;
					else if(f.getName().endsWith(".")) return true;
					else if(f.getName().endsWith(".")) return true;
					else return false;
				}

				public String getDescription(){
					return ".jpg .png .gif .bmp";

				}
			};

			choix.removeChoosableFileFilter(choix.getAcceptAllFileFilter());
			choix.setFileFilter(ff);

			int retour = choix.showOpenDialog(parent);
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
