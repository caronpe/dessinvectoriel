package controler;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
//INTERNE
import model.Model;
import ressources.*;

/**
 * Listener pour le menu Enregistrer sous. Est appelé lorsqu'on appuie sur le bouton enregistrer sous mais aussi
 * lorsque l'on ferme la fenêtre et lorsque l'on clique sur le bouton enregistrer.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionMenuEnregistrerSous extends AbstractAction {
	private Model model;
	/** * L'extension est sous la forme ".extension" */
	private String extension;

	/**
	 * Récupère l'extension de fichier définie dans le modèle.
	 * 
	 * @param model Modèle du MVC
	 */
	public ActionMenuEnregistrerSous(Model model) {
		this.model = model;
		this.extension = model.getExtension();
		
		// Values
		putValue(NAME, "Enregistrer sous");
		putValue(SHORT_DESCRIPTION, "Enregistre-sous votre travail");
	}
	
	/**
	 * Permets de donner un nom au fichier dans le TextField et à choisir ~ comme répertoire par défaut
	 * Ne fonctionne pas sous Windows 8.1 (voir sous toute version de windows)
	 */
	public void enregistrerSous() {
		// Fichier
			String adresse_du_fichier = "Nom du fichier" + extension;
			File file = new File(adresse_du_fichier);
		
		// FileChooser
			JFileChooser filechoose = new JFileChooserOverwrite(new File(adresse_du_fichier));
			filechoose.setSelectedFile(file);
			String approve = new String("Enregistrer"); // Étiquette : "Enregistrer"
			
		// Extension
			ExtensionFileFilter filter = new ExtensionFileFilter("Fichiers .CTH", "cth");
		    filechoose.setFileFilter(filter);

		// DialogBox
		int resultatEnregistrer = filechoose.showDialog(filechoose,	approve);
		
		if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) { // Si l’utilisateur clique sur "Enregistrer", on applique la bonne extension
			adresse_du_fichier = new String(filechoose.getSelectedFile().toString());
			
			// Si mauvaise extension, renommer le fichier avec la bonne extension
			if(!adresse_du_fichier.endsWith(extension) && !adresse_du_fichier.endsWith(extension.toUpperCase())) {
				adresse_du_fichier = adresse_du_fichier + extension;
			}
			
			model.setAdresseEnregistrement(adresse_du_fichier);
			fluxEnregistrement(adresse_du_fichier); // On enregistre enfin le flux
		}
	}
	
	/**
	 * Méthode gérant le processus d'enregistrement par des flux
	 * @param adresse_du_fichier Adresse du fichier comprenant son nom complet
	 */
	public void fluxEnregistrement(String adresse_du_fichier) {
		System.out.println("Enregistré : " + adresse_du_fichier); // DEBUG
		
		// Initialisation avant enregistrement
		model.setEnregistre(true); // On marque dans le modèle que le fichier est enregistré
		model.deselectionnerToutesLesFormes();
		
		try {
			FileOutputStream fichier = new FileOutputStream(adresse_du_fichier);
			ObjectOutputStream output = new ObjectOutputStream(fichier);
			output.writeObject(model.getListeDessin());
			output.flush();
			output.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lorsque l'utilisateur clique sur le bouton "Enregistrer" du menu "Fichier".
	 */
	public void actionPerformed(ActionEvent e) {
		enregistrerSous();
	}
}