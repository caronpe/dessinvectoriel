package controler;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
//INTERNE
import model.Model;
import ressources.*;

/**
 * Listener pour le menu Enregistrer. Est appelé lorsqu'on appuie sur le bouton enregistrer mais aussi
 * lorsque l'on ferme la fenêtre.
 * 
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionMenuEnregistrer extends AbstractAction {
	private Model model;
	/** * L'extension est sous la forme ".extension" */
	private String extension;
	// FileChooser
		private JFileChooser chooser;
		private FileNameExtensionFilter filter;
       
	/**
	 * Récupère l'extension de fichier définie dans le modèle.
	 * 
	 * @param model Modèle du MVC
	 */
	public ActionMenuEnregistrer(Model model) {
		this.model = model;
		this.extension = model.getExtension();
		
		// Values
		putValue(NAME, "Enregistrer");
		putValue(SHORT_DESCRIPTION, "Enregistre votre travail");
	}
	
	/**
	 * Permets de donner un nom au fichier dans le TextField et à choisir ~ comme répertoire par défaut
	 * Ne fonctionne pas sous Windows 8 (voir sous toute version de windows)
	 * 
	 */
	public void enregistrer() {
		// FileChooser
			String nom_du_fichier = "Nom du fichier" + extension;
			File file = (new File(nom_du_fichier));
			System.out.println("Enregistré : " + file.getAbsolutePath()); // DEBUG
			JFileChooser filechoose = new JFileChooserOverwrite(new File(nom_du_fichier));
			filechoose.setSelectedFile(new File(nom_du_fichier));
			String approve = new String("Enregistrer"); // Le bouton d'enregistrement aura pour étiquette "Enregistrer"
		// Extension
			ExtensionFileFilter filter = new ExtensionFileFilter("Fichiers .CTH", "cth");
		    filechoose.setFileFilter(filter);

		// DialogBox
		int resultatEnregistrer = filechoose.showDialog(filechoose,	approve);
		
		if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) { // Si l’utilisateur clique sur "Enregistrer", on applique la bonne extension
			nom_du_fichier = new String(filechoose.getSelectedFile().toString());
			
			// Si mauvaise extension, renommer le fichier avec la bonne extension
			if(!nom_du_fichier.endsWith(extension) && !nom_du_fichier.endsWith(extension.toUpperCase())) {
				nom_du_fichier = nom_du_fichier + extension;
			}
			
			model.setEnregistre(true); // On marque dans le modèle que le fichier est enregistré
			fluxEnregistrement(nom_du_fichier); // On enregistre enfin le flux
		}
	}
	
	/**
	 * Méthode gérant le processus d'enregistrement par des flux
	 * @param nom_du_fichier Adresse du fichier comprenant son nom complet
	 */
	private void fluxEnregistrement(String nom_du_fichier) {
		// Initialisation avant enregistrement
		model.deselectionnerToutesLesFormes();
		
		try {
			FileOutputStream fichier = new FileOutputStream(nom_du_fichier);
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(model.getListeDessin());
			oos.flush();
			oos.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lorsque l'utilisateur clique sur le bouton "Enregistrer" du menu "Fichier"
	 */
	public void actionPerformed(ActionEvent e) {
		enregistrer();
	}
}



