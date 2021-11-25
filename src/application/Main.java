package application;

import controller.MenuController;
import controller.NiveauController;
import controller.SelectionNiveauController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.GameState;
import view.Niveau1;


public class Main extends Application {

	public static Group root;
	public static Scene scene;
	public static GameState etat;
	public Stage fenetre;


	/**
	 * Initialise le lancement du jeu.
	 */
	public void init() throws Exception {
		etat=GameState.MENU;
		root = new Group();
		//On met en place le fond de la fenètre avec l'image background
		scene = new Scene(root, Screen.getPrimary().getBounds().getMaxX(), 720);
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
		scene.setFill(Color.web("9bbeff"));
		primaryStage.setTitle("ElfsRevenge");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
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
			System.out.println("Lancement de la pause");
			System.out.println(etat);
			break;
		case PARAMETRES:
			//Lancement des paramètres
			System.out.println("Lancement des paramètres");
			System.out.println(etat);
			break;
		case CUSTOMISATION:
			//Lancement de l'écran de customisation
			System.out.println("Lancement de l'écran de customisation");
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
			System.out.println("Lancement du niveau 2");
			System.out.println(etat);
			break;
		case NIVEAU3:
			//Lancement du niveau3
			System.out.println("Lancement du niveau 3");
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