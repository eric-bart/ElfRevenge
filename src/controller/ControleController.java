package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.GameState;
import model.OrgeMenu;
import model.OrgeSelectNiveau;
import view.Menu;
import view.Controles;


public class ControleController {
	
	private Group root;
    private Scene scene;
    private GameState etat;
    
    public ControleController(Group root, Scene scene) {
    	this.root = root;
    	this.scene = scene;
    	this.etat = GameState.CONTROLES;
    }

	public void controles() {
		//Sélection du niveau pas encore faite -> Je redirige vers le niveau1
		Controles ctrl = new Controles(this.root);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ESCAPE || keyEvent.getCode() == KeyCode.ENTER) {
					keyEvent.consume();
					application.Main.setGameState(GameState.MENU);
					}
			}
		});
	}
}