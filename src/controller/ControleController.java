package controller;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.GameState;
import view.Controles;

/**
 * Classe de notre controlleur de la vue "CONTROLE"
 */
public class ControleController {
	
	private Group root;
    private Scene scene;
    @SuppressWarnings("unused")
	private Controles controle;
    
    public ControleController() {
    	this.root = Jeu.monJeu.getGameRoot();
    	this.scene = Jeu.monJeu.getGameScene();
		this.controle = new Controles(this.root);
    }

    /**
	 * Affiche la vue "CONTROLES" et mets en place l'écoute des touches au sein de la vue "CONTROLES"
	 */
	public void controles() {
		//On récupère les évenements de type "Touche pressée"
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				// Si l'utilisateur appuie sur la touche "ECHAPE" ou "ENTRER", retour à l'état "MENU" et on détruit l'écoute d'évènement.
				if(keyEvent.getCode() == KeyCode.ESCAPE || keyEvent.getCode() == KeyCode.ENTER) {
					keyEvent.consume();
					Jeu.monJeu.changeGameState(GameState.MENU);
					}
			}
		});
	}
}