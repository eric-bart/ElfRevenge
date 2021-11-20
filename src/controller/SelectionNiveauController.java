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
    }

	public void selectionNiveau() {
		application.Main.setGameState(GameState.NIVEAU1);
	}
}
