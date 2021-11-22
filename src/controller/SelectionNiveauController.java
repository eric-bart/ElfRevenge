package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import model.GameState;

public class SelectionNiveauController {
	
	private Group root;
    private Scene scene;
    private GameState etat;
    
    public SelectionNiveauController(Group root, Scene scene) {
    	this.root = root;
    	this.scene = scene;
    	this.etat = GameState.SELECT_NIVEAU;
    }

	public void selectionNiveau() {
		//Sélection du niveau pas encore faite -> Je redirige vers le niveau1
		application.Main.setGameState(GameState.NIVEAU1);
	}
}
