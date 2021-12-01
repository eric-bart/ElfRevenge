package application;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import controller.ControleController;
import controller.CustomisationController;
import controller.FileManager;
import controller.IntroController;
import controller.MenuController;
import controller.NiveauController;
import controller.PauseController;
import controller.SelectionNiveauController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.DonneesNiveau;
import model.GameState;
import view.Niveau1;


public class Main extends Application {

	public static Group root;
	public static Scene scene;
	public static GameState etat;
	public static Stage fenetre;
	public static MediaPlayer mediaPlayer;
	public static HashMap<String, DonneesNiveau> map;
	public static HashMap<String, Integer> skin;


	/**
	 * Initialise le lancement du jeu.
	 */
	public void init() throws Exception {
		etat=GameState.INTRO;
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
		fenetre=primaryStage;
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
		}
		HashMap<String, DonneesNiveau> read = (HashMap<String, DonneesNiveau>) fileManager.readFile("donnees");
		for(Map.Entry<String, DonneesNiveau> entry : read.entrySet()) {
			DonneesNiveau donneeNiveau = entry.getValue();
			System.out.println(donneeNiveau.toString());
		}	
	}

	/**
	 * Cette fonction permet de rediriger vers d'autres vues et controlleurs en fonction de l'état du jeu.
	 */
	public static void changeGameState() {
		switch(etat) {
		case INTRO:
			//Lancement de l'intro
			System.out.println("Lancement de l'intro");
			IntroController intro = new IntroController(root, scene);
			intro.intro();
			fenetre.setHeight(720);
			break;
		case MENU:
			//Lancement de du menu
			System.out.println("Lancement du menu");
			MenuController menu = new MenuController(root, scene);
			menu.menu();
			fenetre.setHeight(720);
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
			fenetre.setHeight(720);
			break;
		case CUSTOMISATION:
			//Lancement de l'écran de customisation
			CustomisationController custom = new CustomisationController(root,scene);
			custom.selectionSkin();
			System.out.println(etat);
			fenetre.setHeight(720);
			break;
		case SELECT_NIVEAU:
			//Lancement de l'écran de sélection du niveau
			SelectionNiveauController slctNiveau = new SelectionNiveauController(root, scene);
			slctNiveau.selectionNiveau();
			System.out.println(etat);
			fenetre.setHeight(720);
			break;
		case NIVEAU1:
			//Lancement du niveau1
			NiveauController niveauController = new NiveauController(root, scene, etat);
			System.out.println(etat);
			fenetre.setHeight(1000);
			break;
		case NIVEAU2:
			//Lancement du niveau2
			NiveauController niveauController2 = new NiveauController(root, scene, etat);
			System.out.println(etat);
			fenetre.setHeight(1000);
			break;
		case NIVEAU3:
			//Lancement du niveau3
			NiveauController niveauController3 = new NiveauController(root, scene, etat);
			System.out.println(etat);
			fenetre.setHeight(1000);
			break;
		}
	}
	
	/**
	 * Fonction permettant de lancer la musique du jeu
	 */
	public static void Music() {
		String song = "src\\songmenu.mp3";
		Media songurl = new Media(Paths.get(song).toUri().toString());
		mediaPlayer = new MediaPlayer(songurl);
		mediaPlayer.play();
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