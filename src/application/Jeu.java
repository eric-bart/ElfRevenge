package application;

import java.util.HashMap;
import java.util.Map;

import controller.ControleController;
import controller.CustomisationController;
import model.FileManager;
import controller.IntroController;
import controller.MenuController;
import controller.NiveauController;
import controller.PauseController;
import controller.SelectionNiveauController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DonneesNiveau;
import model.GameState;

public class Jeu {

	public static Jeu monJeu = null;
	private Group root;
	private Stage fenetre;
	private Scene scene;
	private HashMap<String, DonneesNiveau> map;
	private HashMap<String, Integer> skin;
	private IntroController intro;
	private MenuController menu;
	private PauseController pause;
	private ControleController controle;
	private CustomisationController customisation;
	private SelectionNiveauController selectionNiveau;
	private NiveauController niveau;
	
    
    private Jeu(Group root, Stage fenetre, Scene scene) {
    	this.monJeu = this;
        this.root=root;
        this.fenetre=fenetre;
        this.scene=scene;
    }

    /**
     * Créer une instance de notre Jeu si elle n'existe pas déjà.
     * Si elle existe déjà, on retourne le jeu.
     * @param root Group
     * @param fenetre Stage
     * @param scene Scene
     * @return le jeu.
     */
    public static synchronized Jeu getInstance(Group root, Stage fenetre, Scene scene) {
		if(monJeu == null) {
			monJeu = new Jeu(root, fenetre, scene);
		}
		return monJeu;
	}
    
    /**
	 * Redirige vers les différentes vues du jeu en fonction de l'état dans lequel il se trouve
	 */
	public void changeGameState(GameState etat) {
		switch(etat) {
		case INTRO:
			//Lancement de l'intro
			intro = new IntroController();
			intro.intro();
			fenetre.setHeight(720);
			break;
		case MENU:
			//Lancement de du menu
			menu = new MenuController();
			menu.menu();
			fenetre.setHeight(720);
			break;
		case PAUSE:
			//Lancement de la pause
			pause = new PauseController();
			pause.pause();
			break;
		case CONTROLES:
			//Lancement des controles
			controle = new ControleController();
			controle.controles();
			fenetre.setHeight(720);
			break;
		case CUSTOMISATION:
			//Lancement de l'écran de customisation
			customisation = new CustomisationController();
			customisation.selectionSkin();
			fenetre.setHeight(720);
			break;
		case SELECT_NIVEAU:
			//Lancement de l'écran de sélection du niveau
			selectionNiveau = new SelectionNiveauController();
			selectionNiveau.selectionNiveau();
			fenetre.setHeight(720);
			break;
		case NIVEAU1:
			//Lancement du niveau1
			niveau = new NiveauController(etat);
			fenetre.setHeight(1000);
			break;
		case NIVEAU2:
			//Lancement du niveau2
			niveau = new NiveauController(etat);
			fenetre.setHeight(1000);
			break;
		case NIVEAU3:
			//Lancement du niveau3
			niveau = new NiveauController(etat);
			fenetre.setHeight(1000);
			break;
		}
	}
	
	/**
	 * 
	 */
	public void createSaveFile() {
		FileManager fileManager = new FileManager();
		if(!fileManager.isFileCreated("skin")) {
			skin = new HashMap<String, Integer>();
			skin.put("skin", 0);
			fileManager.setSkin("skin", skin);
		}
		if(!fileManager.isFileCreated("donnees")) {
			DonneesNiveau donneeNiveau1 = new DonneesNiveau(false, 0);
			DonneesNiveau donneeNiveau2 = new DonneesNiveau(false, 0);
			DonneesNiveau donneeNiveau3 = new DonneesNiveau(false, 0);
			map = new HashMap<String, DonneesNiveau>();
			map.put("niveau1", donneeNiveau1);
			map.put("niveau2", donneeNiveau2);
			map.put("niveau3", donneeNiveau3);
			fileManager.writeToFile("donnees", map);
		}
		@SuppressWarnings("unchecked")
		HashMap<String, DonneesNiveau> read = (HashMap<String, DonneesNiveau>) fileManager.readFile("donnees");
		for(Map.Entry<String, DonneesNiveau> entry : read.entrySet()) {
			DonneesNiveau donneeNiveau = entry.getValue();
			System.out.println(donneeNiveau.toString());
		}	
	}
	
	/**
	 * Getter pour notre attribut root
	 * @return Group notre root
	 */
	public Group getGameRoot() {
		return this.root;
	}
	
	/**
	 * Getter pour notre attribut fenetre
	 * @return Stage fenetre
	 */
	public Stage getGameStage() {
		return this.fenetre;
	}
	
	/**
	 * Getter pour notre attribut scene
	 * @return Scene notre scene
	 */
	public Scene getGameScene() {
		return this.scene;
	}

}
