package controller;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import view.Outro;
import model.GameState;
import javafx.scene.media.MediaView;

/**
 * Classe de notre controlleur de la vue "OUTRO"
 */
public class OutroController {

	private Group root;
	private Scene scene;
	public MediaView mediaView;
	@SuppressWarnings("unused")
	private Outro outro;
	
	public OutroController() {
		this.root=Jeu.monJeu.getGameRoot();
		this.scene=Jeu.monJeu.getGameScene();
		this.outro = new Outro(this.root);
	}

	/**
	 * Affiche la vue "OUTRO" et mets en place l'écoute des touches au sein de la vue "OUTRO"
	 */
	public void outro() {
		// Si l'utilisateur appuie sur n'importe quelle touche, retour à l'état "MENU" et effacement de l'eventHandler de la page "OUTRO"
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				keyEvent.consume();
				Jeu.monJeu.changeGameState(GameState.MENU);
			}
		});
	}

}
