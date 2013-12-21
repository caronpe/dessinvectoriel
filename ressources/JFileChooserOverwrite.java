package ressources;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class JFileChooserOverwrite extends JFileChooser {
	// FileChooser qui supporte l'écrasement de fichier
	
	@Override
	public void approveSelection() {
        File file = this.getSelectedFile(); // On récupère l'entrée du fichier du FileChooser
        
        if ( file.exists() ) {
            int answer = JOptionPane.showConfirmDialog(this, file + " existe déjà. Souhaitez-vous l'écraser ?", "Écraser",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (answer != JOptionPane.OK_OPTION) {
                return;
            }
        }
        super.approveSelection(); // Une fois écrasée, la méthode continue de fonctionner normalement
    }
}

