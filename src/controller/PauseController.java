package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.GameState;
import model.Orge;
import model.OrgePause;
import view.Menu;
import view.Pause;

public class PauseController {

	private Group root;
    private Scene scene;
    private GameState etat;
	private ImageView pauseBackground;
	
	public PauseController(Group root, Scene scene) {
		this.root=root;
		this.scene=scene;
		this.etat = GameState.PAUSE;
	}
	
	/**
	 * Méthode qui met sous écoute les touches du claviers et change l'état du jeu en fonction de l'option du menu qui est sélectionnée
	 */
	public void pause() {
		Pause pause = new Pause(this.root);
		OrgePause orge = new OrgePause(pause.getOrge());
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ENTER) {
					keyEvent.consume();
					switch(orge.getOptions()[orge.getSelectedOpt()]) {
					case "REPRENDRE":
						NiveauController.boucle.start();
						NiveauController.setListeners();
						root.getChildren().removeAll(pause.getPauseBackground(), orge.getOrge());
						break;
					case "QUITTER":
						application.Main.setGameState(GameState.MENU);
						break;
					}
					return;
				}
				switch(keyEvent.getCode()) {
				case UP:
					if(orge.getSelectedOpt() > 0) {
						orge.setSelectedOpt(orge.getSelectedOpt()-1);
						orge.orgeAnimation();
					}
					break;
				case DOWN:
					if(orge.getSelectedOpt() < 1) {
						orge.setSelectedOpt(orge.getSelectedOpt()+1);
						orge.orgeAnimation();
					}
					break;
				}
			}
		});
	}
	
	
}
