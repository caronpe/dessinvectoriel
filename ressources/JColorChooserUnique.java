package ressources;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
// INTERNE
import model.Model;

/**
 * Réécrite du JColorChooser pour n'inclure que l'onglet qui nous intéresse : Echantillons.
 * Possède 2 boutons : OK qui applique la couleur sélectionnée et Annuler.
 * A le comportement classique d'une fenêtre
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.3
 */
public class JColorChooserUnique extends JFrame implements ActionListener {
	// MVC
		private Model model;
	// FENETRE
		private Container container;
		private JColorChooser colorChooser;
	// Buttons
		private JPanel buttons;
		private JButton ok, annuler;

	public JColorChooserUnique(Model model) {
		super();
		this.model = model;
		this.initialiserColorChooser();
		this.initialiserFrame();
	}

	/**
	 * Initialise le colorChooser.
	 * Récupère la couleur du modèle pour la préselectionner.
	 * Récupère l'onglet Échantillons et l'ajoute au coloChooser.
	 * Retire le preview.
	 */
	private void initialiserColorChooser() {
		// Initialisation du ColorChooser
		colorChooser = new JColorChooser();
		colorChooser.setColor(model.getColor());
		colorChooser.setName("Sélection de la couleur");
		colorChooser.setPreviewPanel(new JPanel()); // Aucun preview

		// On remplace les onglets par celui par défaut : Échantillons
		AbstractColorChooserPanel[] oldPanels = colorChooser.getChooserPanels();
		AbstractColorChooserPanel[] newPanels = { oldPanels[0] };
		colorChooser.setChooserPanels(newPanels);
	}

	/**
	 * Initialise la fenêtre : Comportement classique.
	 * 2 boutons : OK et Annuler.
	 * 
	 * @see #actionPerformed
	 * 
	 */
	private void initialiserFrame() {
		// Buttons
		ok = new JButton("OK");
		ok.addActionListener(this);
		annuler = new JButton("Annuler");
		annuler.addActionListener(this);

		// Panel buttons
		buttons = new JPanel();
		buttons.add(ok);
		buttons.add(annuler);

		// Ajouts
		container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(colorChooser, BorderLayout.NORTH);
		container.add(buttons, BorderLayout.SOUTH);
		
		// Frame
		this.setUndecorated(true);
		this.pack();
		this.setTitle("Sélection de la couleur");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}

	/**
	 * Affiche la fenêtre.
	 */
	public void afficher() {
		this.setVisible(true);
	}
	
	/**
	 * Quand on clique sur OK, on applique la couleur sélectionnée pui masque la fenêtre.
	 * Annuler ne fait que masquer la fenêtre.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ok) {
			model.setColor(colorChooser.getColor());
		}
		this.dispose();
	}
}


