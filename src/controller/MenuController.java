package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.GameState;
import model.OrgeMenu;
import view.Menu;

public class MenuController {

	private Group root;
	private Scene scene;
	private GameState etat;

	public MenuController(Group root, Scene scene) {
		this.root=root;
		this.scene=scene;
		this.etat = GameState.MENU;
	}

	/**
	 * M�thode qui met sous �coute les touches du claviers et change l'�tat du jeu en fonction de l'option du menu qui est s�lectionn�e
	 */
	public void menu() {
		Menu menu = new Menu(this.root);
		Orge orge = new Orge(menu.getOrge());
		orge.selectNiveau = false;

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ENTER) {
					keyEvent.consume();
					switch(orge.getOptions()[orge.getSelectedOpt()]) {
					case "JOUER":
						application.Main.setGameState(GameState.SELECT_NIVEAU);
						break;
					case "CUSTOMISER":
						application.Main.setGameState(GameState.CUSTOMISATION);
						break;
					case "OPTIONS":
						application.Main.setGameState(GameState.PARAMETRES);
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
					if(orge.getSelectedOpt() < 2) {
						orge.setSelectedOpt(orge.getSelectedOpt()+1);
						orge.orgeAnimation();
					}
					break;
				}
			}
		});
	}

}