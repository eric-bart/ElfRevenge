package application;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.ControleController;
import controller.CustomisationController;
import controller.FileManager;
import controller.MenuController;
import controller.NiveauController;
import controller.PauseController;
import controller.SelectionNiveauController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.DonneesNiveau;
import model.GameState;


public class Main extends Application {

	public static Group root;
	public static Scene scene;
	public static GameState etat;
	public Stage fenetre;
	HashMap<String, DonneesNiveau> map;
	HashMap<String, Integer> skin;

	/**
	 * Initialise le lancement du jeu.
	 */
	public void init() throws Exception {
		etat=GameState.MENU;
		root = new Group();
		//On met en place le fond de la fenètre avec l'image background
		scene = new Scene(root, 1280, 720);
	}

	/**
	 * Cette fonction se lance aprés la fonction init. Elle met en place les paramètres de notre fenètre
	 * et lance l'affichage de la fenètre.
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		changeGameState();
		scene.setFill(Color.web("99c6da"));
		primaryStage.setTitle("ElfsRevenge");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
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
		HashMap<String, DonneesNiveau> read = (HashMap<String, DonneesNiveau>) fileManager.readFile("donnees");
			for(Map.Entry<String, DonneesNiveau> entry : read.entrySet()) {
			    DonneesNiveau donneeNiveau = entry.getValue();
			    System.out.println(donneeNiveau.toString());
				}	
		}
	}

	/**
	 * Cette fonction permet de rediriger vers d'autres vues et controlleurs en fonction de l'état du jeu.
	 */
	public static void changeGameState() {
		switch(etat) {
		case MENU:
			//Lancement de du menu
			System.out.println("Lancement du menu");
			MenuController menu = new MenuController(root, scene);
			menu.menu();
			break;
		case PAUSE:
			//Lancement de la pause
			PauseController pause = new PauseController(root, scene);
			pause.pause();
			break;
		case CONTROLES:
			//Lancement des controles
			ControleController ctrl = new ControleController(root, scene);
			ctrl.controles();
			System.out.println(etat);
			break;
		case CUSTOMISATION:
			//Lancement de l'écran de customisation
			CustomisationController custom = new CustomisationController(root,scene);
			custom.selectionSkin();
			System.out.println(etat);
			break;
		case SELECT_NIVEAU:
			//Lancement de l'écran de sélection du niveau
			SelectionNiveauController slctNiveau = new SelectionNiveauController(root, scene);
			slctNiveau.selectionNiveau();
			System.out.println(etat);
			break;
		case NIVEAU1:
			//Lancement du niveau1
			NiveauController niveauController = new NiveauController(root, scene, etat);
			System.out.println(etat);
			break;
		case NIVEAU2:
			//Lancement du niveau2
			NiveauController niveauController2 = new NiveauController(root, scene, etat);
			System.out.println(etat);
			break;
		case NIVEAU3:
			//Lancement du niveau3
			NiveauController niveauController3 = new NiveauController(root, scene, etat);
			System.out.println(etat);
			break;
		}
	}

	/**
	 * Fonction permettant de changer l'état du jeu
	 * @param state l'état du jeu dans lequel on souhaite le changer
	 */
	public static void setGameState(GameState state) {
		etat = state;
		changeGameState();
	}

	public static void main(String[] args) {
		launch(args);
	}
}