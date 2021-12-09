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
	private NiveauController niveauActuel;
	private HashMap<String, DonneesNiveau> map;
	private HashMap<String, Integer> skin;
    
    private Jeu(Group root, Stage fenetre, Scene scene) {
    	this.monJeu = this;
        this.root=root;
        this.fenetre=fenetre;
        this.scene=scene;
    }

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
			IntroController intro = new IntroController();
			intro.intro();
			fenetre.setHeight(720);
			break;
		case MENU:
			//Lancement de du menu
			MenuController menu = new MenuController();
			menu.menu();
			fenetre.setHeight(720);
			break;
		case PAUSE:
			//Lancement de la pause
			PauseController pause = new PauseController(niveauActuel);
			pause.pause();
			break;
		case CONTROLES:
			//Lancement des controles
			ControleController ctrl = new ControleController();
			ctrl.controles();
			fenetre.setHeight(720);
			break;
		case CUSTOMISATION:
			//Lancement de l'écran de customisation
			CustomisationController custom = new CustomisationController();
			custom.selectionSkin();
			fenetre.setHeight(720);
			break;
		case SELECT_NIVEAU:
			//Lancement de l'écran de sélection du niveau
			SelectionNiveauController slctNiveau = new SelectionNiveauController();
			slctNiveau.selectionNiveau();
			fenetre.setHeight(720);
			break;
		case NIVEAU1:
			//Lancement du niveau1
			NiveauController niveauController = new NiveauController(etat);
			niveauActuel = niveauController;
			fenetre.setHeight(1000);
			break;
		case NIVEAU2:
			//Lancement du niveau2
			NiveauController niveauController2 = new NiveauController(etat);
			niveauActuel = niveauController2;
			fenetre.setHeight(1000);
			break;
		case NIVEAU3:
			//Lancement du niveau3
			NiveauController niveauController3 = new NiveauController(etat);
			niveauActuel = niveauController3;
			fenetre.setHeight(1000);
			break;
		}
	}
	
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
	
	public Group getGameRoot() {
		return this.root;
	}
	
	public Stage getGameStage() {
		return this.fenetre;
	}
	
	public Scene getGameScene() {
		return this.scene;
	}

}
