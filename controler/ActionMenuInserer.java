package controler;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JPanel;

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

	public ActionMenuInserer(Model model) {
		this.model=model;
		putValue(NAME, "Insérer");
		putValue(SHORT_DESCRIPTION, "Insère une image");
	}

	public void actionPerformed(ActionEvent arg0) {
		
		try {
			photo=ImageIO.read(new File("ressources/selection.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.add(photo);
		System.out.println("passe1");
	}

}
