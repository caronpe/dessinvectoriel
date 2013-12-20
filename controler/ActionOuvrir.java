package controler;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.ListeDessin;
import model.Model;
// INTERNE

public class ActionOuvrir extends AbstractAction {
	private Model model;
	
	public ActionOuvrir(Model model) {
		this.model = model;
		putValue(NAME, "Ouvrir");
		putValue(SHORT_DESCRIPTION, "Ouvrir un fichier déjà existant");
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if ( model.getEnregistre() ) {
			ouvrir();
		} else {
			System.out.println("Ouvrir, travail non enregistré"); // DEBUG
			Object[] options = {"Enregistrer", "Ne pas enregistrer", "Annuler"};
			int n = JOptionPane.showOptionDialog(new JFrame(), "Souhaitez-vous vraiment ouvrir un fichier sans enregistrer ?", "Ouvrir", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			
			// Si on sélectionne l'option Enregistrer
			if ( n == 0 ) {
				new ActionEnregistrer(model).enregistrer();
				ouvrir();
			} else if ( n == 1 ) {
				ouvrir();
			}
		}
	}
	
	public void ouvrir() {
		JFileChooser filechoose = new JFileChooser();
		// Permets de donner un nom au fichier dans le TextField et à choisir ~ comme répertoire par défaut
		String extension = model.getExtension(), nom_du_fichier = "Nom du fichier" + extension;
		filechoose.setSelectedFile(new File(nom_du_fichier));
		// Le répertoire source du JFileChooser est le répertoire d’où est lancé notre programme
		String approve = new String("Ouvrir");
		// Le bouton pour valider l’enregistrement portera la mention OUVRIR
		String monFichier = ""; // On ne sait pas pour l’instant quel sera le fichier à ouvrir
		int resultatOuvrir = filechoose.showDialog(filechoose, approve); // Pour afficher le JFileChooser
		if(resultatOuvrir == JFileChooser.APPROVE_OPTION) { // Si l’utilisateur clique sur le bouton OUVRIR
			monFichier = filechoose.getSelectedFile().toString(); // Récupérer le nom du fichier qu’il a spécifié
			this.fluxOuverture(monFichier);
		}
	}
	
	public void fluxOuverture(String monFichier) {
		
		try {
			FileInputStream fichier = new FileInputStream(monFichier);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			ListeDessin listeDessin = (ListeDessin) ois.readObject();
			model.setListeDessin(listeDessin);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
				
		}
	}
	
	public void oldFluxOuverture(String monFichier) {
		try { 
			FileInputStream fis = new FileInputStream(monFichier);
			// Créer un flux d’entrée avec comme paramètre le nom du fichier à ouvrir
			int n;
			while ( (n = fis.available()) > 0 ) { // tant qu’il y a des données dans le flux
				byte[] b = new byte[n]; // récupérer le byte à l’endroit n et le stocker dans un tableau de bytes
				int result = fis.read(b); // lire ce tableau de byte à l’endroit désiré
				if (result == -1) break; // si le byte est -1, c’est que le flux est arrivé à sa fin (par définition)
				String s = new String(b); // assembler les bytes pour former une chaîne
				System.out.println(s);
			}
		} catch (Exception err) {
			System.err.println("Erreur lors de l'ouverture du fichier");
		}
	}
}
