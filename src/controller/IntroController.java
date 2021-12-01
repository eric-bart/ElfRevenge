package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import view.Intro;
import model.GameState;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class IntroController {

	private Group root;
    private Scene scene;
    private GameState etat;
	public MediaView mediaView;
	
	public IntroController(Group root, Scene scene) {
		this.root=root;
		this.scene=scene;
		this.etat = GameState.INTRO;
	}
	
	/**
	 * Méthode qui met sous écoute les touches du claviers et change l'état du jeu en fonction de l'option du menu qui est sélectionnée
	 */
	public void intro() {
		Intro intro = new Intro(this.root);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		public void handle(KeyEvent keyEvent) {
					application.Main.setGameState(GameState.MENU);
			}
	});
	}
	
}
