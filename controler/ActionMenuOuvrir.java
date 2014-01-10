package controler;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


//INTERNE
import model.Calque;
import model.Model;


/**
 * Listener pour le menu Ouvrir. Contient toutes les fonctions d'ouverture de fichier
 * dans l'extension définie dans le modèle
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class ActionMenuOuvrir extends AbstractAction {
	private Model model;
	private URL urlOpen;
	
	public ActionMenuOuvrir(Model model) {
		this.model = model;
		
		// URL
		this.urlOpen = ClassLoader.getSystemClassLoader().getResource("ressources/images/open.png");
		
		// Values
		putValue(NAME, "Ouvrir");
		putValue(SHORT_DESCRIPTION, "Ouvrir un fichier déjà existant");
		this.putValue(SMALL_ICON, new ImageIcon(urlOpen));
	}
	
	/**
	 * Vérifie si l'utilisateur a enregistré ou non et affiche une fenêtre
	 * de dialogue appropriée pour ensuite ouvrir la fenêtre d'ouverture.
	 * 
	 * @see #ouvrir
	 * 
	 */
	public void actionPerformed(ActionEvent arg0) {
		if ( model.getEnregistre() ) { // Si le travail est enregistré
			ouvrir();
		} else {
			// DialogBox
			Object[] options = {"Enregistrer", "Ne pas enregistrer", "Annuler"};
			int n = JOptionPane.showOptionDialog(new JFrame(), 
					"Souhaitez-vous vraiment ouvrir un fichier sans enregistrer ?", 
					"Ouvrir",
					JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					options, options[0]);
						
			if ( n == 0 ) { // Si Enregistrer
				new ActionMenuEnregistrerSous(model).enregistrerSous();
				ouvrir();
			} else if ( n == 1 ) { // Si Ne pas enregistrer
				ouvrir();
			}
			
		}
	}
	
	/**
	 * Gère la fenêtre d'ouverture.
	 */
	public void ouvrir() {
		// FileChooser
		JFileChooser filechoose = new JFileChooser();
		
		// Permets de donner un nom au fichier dans le TextField et à 
		String nom_du_fichier = ""; // choisit ~ comme répertoire par défaut
		filechoose.setSelectedFile(new File(nom_du_fichier));
		
		// Le répertoire source du JFileChooser est le répertoire d’où est lancé notre programme
		String approve = new String("Ouvrir");
		
		// Le bouton pour valider l’enregistrement portera la mention OUVRIR
		String monFichier = ""; // On ne sait pas pour l’instant quel sera le fichier à ouvrir
		int resultatOuvrir = filechoose.showDialog(filechoose, approve); // Pour afficher le JFileChooser
		
		// Si Ouvrir
		if(resultatOuvrir == JFileChooser.APPROVE_OPTION) { 
			monFichier = filechoose.getSelectedFile().toString(); // Récupérer le nom du fichier qu’il a spécifié
			this.fluxOuverture(monFichier);
		}
	}
	
	/**
	 * Gère le lien entre le fichier et le modèle en ajoutant toutes les formes à la liste
	 * de dessin du modèle.
	 * 
	 * @param monFichier Nom du fichier contenant l'adresse absolue du fichier.
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public void fluxOuverture(String monFichier) {
		try {
			FileInputStream fichier = new FileInputStream(monFichier);
			ObjectInputStream input = new ObjectInputStream(fichier);
			ArrayList<Calque> listeCalque = (ArrayList<Calque>) input.readObject();
			model.open(listeCalque);
			
			input.close();
		} catch (StreamCorruptedException e) {
			System.err.println("Extension non correcte.");
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(new JFrame(), "Extension du fichier non correcte.", "Extension", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Problème.");
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(new JFrame(), "Problème lors de l'ouverture du fichier.", "Erreur", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		}
	}
}