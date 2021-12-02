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
import view.SelectNiveau;


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
		SelectNiveau select = new SelectNiveau(this.root);
		OrgeSelectNiveau orge = new OrgeSelectNiveau(select.getOrge());
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ENTER) {
					keyEvent.consume();
					switch(orge.getNiveaux()[orge.getSelectedNiveau()]) {
					case "NIVEAU1":
						application.Main.setGameState(GameState.NIVEAU1);
						break;
					case "NIVEAU2":
						application.Main.setGameState(GameState.NIVEAU2);
						break;
					case "NIVEAU3":
						application.Main.setGameState(GameState.NIVEAU3);
						break;
					}
					return;
				}
				switch(keyEvent.getCode()) {
				case LEFT:
					if(orge.getSelectedNiveau() > 0) {
						orge.setSelectedNiveau(orge.getSelectedNiveau()-1);
						orge.orgeAnimation();
					}
					break;
				case RIGHT:
					if(orge.getSelectedNiveau() < 2) {
						orge.setSelectedNiveau(orge.getSelectedNiveau()+1);
						orge.orgeAnimation();
					}
					break;
				case ESCAPE:
					application.Main.setGameState(GameState.MENU);
					break;
				}
			}
		});
	}
}
