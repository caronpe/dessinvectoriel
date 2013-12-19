package controler;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
//INTERNE
import model.Model;
import ressources.ExtensionFileFilter;
import ressources.JFileChooserOverwrite;


public class ActionEnregistrer extends AbstractAction {
		private Model model;
	// FileChooser
		private JFileChooser chooser;
		private FileNameExtensionFilter filter;
       
	public ActionEnregistrer(Model model) {
		this.model = model;
		putValue(NAME, "Enregistrer");
		putValue(SHORT_DESCRIPTION, "Enregistre votre travail");
	}
	
	public void enregistrer() {
			String extension = model.getExtension(), nom_du_fichier = "Nom du fichier" + extension;
		// FileChooser
			JFileChooser filechoose = new JFileChooserOverwrite();
			filechoose.setSelectedFile(new File(nom_du_fichier)); // Répertoire par défaut : où est lancé notre programme
			String approve = new String("Enregistrer"); // portera la mention ENREGISTRER
			ExtensionFileFilter filter = new ExtensionFileFilter("Fichiers .CTH", "cth");
		    filechoose.setFileFilter(filter);
	    
		int resultatEnregistrer = filechoose.showDialog(filechoose,	approve);
		
		if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) { // Si l’utilisateur clique sur le bouton ENREGISTRER
			nom_du_fichier = new String(filechoose.getSelectedFile().toString()); //Récupérer le nom du fichier qu’il a spécifié
			
			// Si ne se finit pas par extension ou extention en majuscule, renommer le fichier avec la bonne extension
			if(!nom_du_fichier.endsWith(extension) && !nom_du_fichier.endsWith(extension.toUpperCase())) {
				nom_du_fichier = nom_du_fichier + extension;
			}
			
			model.setEnregistre(true); // On marque dans le modèle que le fichier est enregistré
			fluxEnregistrement(nom_du_fichier); // On enregistre enfin le flux
		}
	}
	
	private void fluxEnregistrement(String nom_du_fichier) {
		try {
			FileWriter lu = new FileWriter(nom_du_fichier);
			BufferedWriter out = new BufferedWriter(lu); //Met le flux en tampon en cache
			out.write(model.informationsEnregistrement()); // Envoie les informations nécessaires au model
			out.close(); // Fermer le flux (c’est toujours mieux de le fermer explicitement)

		} catch (IOException e) {
			System.err.println("Erreur lors de l'enregistrement.");
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		enregistrer();
	}
}



