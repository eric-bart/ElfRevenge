package application;

import java.nio.file.Paths;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.GameState;


public class Main extends Application {

	public static Group root;
	public static Scene scene;
	public static GameState etat;
	public static MediaPlayer mediaPlayer;

	/**
	 * Initialise le lancement du jeu.
	 */
	public void init() throws Exception {
		etat=GameState.INTRO;
		root = new Group();
		//On met en place le fond de la fenètre avec l'image background
		scene = new Scene(root, 1280, 720);
		scene.setFill(Color.web("99c6da"));
	}

	/**
	 * Met en place la fenêtre de notre jeu, et récupère les données sauvegardées issues de la précédente partie.
	 * @param primaryStage la fenêtre de notre jeu
	 */
	@Override
	public void start(Stage primaryStage) {
		Music();
		@SuppressWarnings("unused")
		Jeu j = Jeu.getInstance(root, primaryStage, scene);
		Jeu.monJeu.changeGameState(etat);
		Jeu.monJeu.createSaveFile();
		primaryStage.setTitle("ElfsRevenge");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
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

	public static void main(String[] args) {
		launch(args);
	}
}