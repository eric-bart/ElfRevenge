package controller;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import view.Intro;
import model.GameState;
import javafx.scene.media.MediaView;

/**
 * Classe de notre controlleur de la vue "INTRO"
 */
public class IntroController {

	private Group root;
	private Scene scene;
	public MediaView mediaView;
	@SuppressWarnings("unused")
	private Intro intro;
	
	public IntroController() {
		this.root=Jeu.monJeu.getGameRoot();
		this.scene=Jeu.monJeu.getGameScene();
		this.intro = new Intro(this.root);
	}

	/**
	 * Affiche la vue "INTRO" et mets en place l'écoute des touches au sein de la vue "INTRO"
	 */
	public void intro() {
		// Si l'utilisateur appuie sur n'importe quelle touche, retour à l'état "MENU" et effacement de l'eventHandler de la page "INTRO"
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				keyEvent.consume();
				Jeu.monJeu.changeGameState(GameState.MENU);
			}
		});
	}

}
