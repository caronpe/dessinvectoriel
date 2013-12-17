package fenetre;

// INTERNE
import controler.Controler;
import model.Model;

//EXTERNE
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Fenetre_principale extends JFrame implements Observer {
	private Container container;
	private MenuG gauche;
	
	// MVC
	private Controler controler;
	private Model model;
		
	// MENU
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenuItem exitAction;
	private JRadioButtonMenuItem radioAction1 = new JRadioButtonMenuItem("Radio Button1");
//	private JRadioButtonMenuItem radioAction2 = new JRadioButtonMenuItem("Radio Button2");
	
	
	public Fenetre_principale(Model model) throws HeadlessException {
		super();
		this.model = model;
		this.model.addObserver(this);
		this.initialiser();
		this.setTitle("logiciel de dessin vectoriel");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void afficher() {
		this.setVisible(true);
	}
	
	public void initialiser() {
		container = this.getContentPane();
		gauche = new MenuG();
		container.add(gauche);
		
		this.setTitle("logiciel de dessin vectoriel");
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400, 400));
		
		this.creation_menu();
	}
	
	public void creation_menu() {		 
		// CRÉATION MENU BAR
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		 
		// MENUS
		fileMenu = new JMenu("File ");
//		editMenu = new JMenu("Edit");
		// AJOUTS
		menuBar.add(fileMenu);
//		menuBar.add(editMenu);
		
		// MENUITEMS
		exitAction = new JMenuItem("Exit");
		
		// RADIO
		radioAction1 = new JRadioButtonMenuItem("Radio Button1");
//		radioAction2 = new JRadioButtonMenuItem("Radio Button2");
		
		// BUTTON GROUP
		ButtonGroup bg = new ButtonGroup();
		// AJOUTS
		bg.add(radioAction1);
//		bg.add(radioAction2);
		
		// FILE MENU
		fileMenu.addSeparator();
		fileMenu.add(exitAction);

		// EDIT MENU
//		editMenu.addSeparator();
//		editMenu.add(radioAction1);
//		editMenu.add(radioAction2);
	}
	
	public JMenuItem getExitButton() {
		return this.exitAction;
	}
	
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
