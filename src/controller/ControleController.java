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
	 * Affiche la vue "CONTROLES" et mets en place l'�coute des touches au sein de la vue "CONTROLES"
	 */
	public void controles() {
		//On r�cup�re les �venements de type "Touche press�e"
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				// Si l'utilisateur appuie sur la touche "ECHAPE" ou "ENTRER", retour � l'�tat "MENU" et on d�truit l'�coute d'�v�nement.
				if(keyEvent.getCode() == KeyCode.ESCAPE || keyEvent.getCode() == KeyCode.ENTER) {
					keyEvent.consume();
					Jeu.monJeu.changeGameState(GameState.MENU);
					}
			}
		});
	}
}