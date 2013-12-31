package ressources;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Réécriture du FileChooser pour supporter l'écrasement de fichier.
 * 
 * @author Alexandre Thorez
 * @author Fabien Huitelec
 * @author Pierre-Édouard Caron
 * 
 * @version 0.2
 */
public class JFileChooserOverwrite extends JFileChooser {
	// FileChooser qui supporte l'écrasement de fichier
	
	public JFileChooserOverwrite(File file) {
		super(file);
	}
	
	@Override
	public void approveSelection() {
		File file = this.getSelectedFile(); // On récupère l'entrée du fichier
											// du FileChooser

		if (file.exists()) {
			int answer = JOptionPane.showConfirmDialog(this, file
					+ " existe déjà. Souhaitez-vous l'écraser ?", "Écraser",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (answer != JOptionPane.OK_OPTION) {
				return;
			}
		}

		// Une fois écrasée, la méthode continue de fonctionner normalement
		super.approveSelection();
	}
}