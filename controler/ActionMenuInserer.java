package controler;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import model.FormeImage;
import model.Model;

/**
 * Listener pour le menu Copier. Contient toutes les fonctions de copie de forme.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Ã‰douard Caron
 * 
 * @version 0.2
 */
public class ActionMenuInserer extends AbstractAction {
	private Model model;
	private Image photo ;
	private Component parent;          //ouvre une boîte de dialogue d’ouverture de fichier.

	public ActionMenuInserer(Model model) {
		this.model=model;
		putValue(NAME, "Insérer");
		putValue(SHORT_DESCRIPTION, "Insère une image");
	}

	public void actionPerformed(ActionEvent arg0) {
		photo=null;

		try {

			JFileChooser choix = new JFileChooser();
			FileFilter ff = new FileFilter(){

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
			};

			choix.removeChoosableFileFilter(choix.getAcceptAllFileFilter());
			choix.setFileFilter(ff);

			int retour = choix.showOpenDialog(parent);
			if(retour == JFileChooser.APPROVE_OPTION) {
				photo=ImageIO.read(new File(choix.getSelectedFile().getAbsolutePath()));
			} else ;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addForme(new FormeImage(photo));
		System.out.println("passe1");
	}

}
