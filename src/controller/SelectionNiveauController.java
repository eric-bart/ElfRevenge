package controller;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.GameState;
import model.OrgeSelectNiveau;
import view.SelectNiveau;


public class SelectionNiveauController {
	
	private Group root;
    private Scene scene;
    
    public SelectionNiveauController() {
    	this.root=Jeu.monJeu.getGameRoot();
		this.scene=Jeu.monJeu.getGameScene();
    }
	/**
	 * Teste si le niveau précédent a été complété ou non.
	 * Si le niveau a bien été fini, redirige vers le niveau séléctionné.
	 */
	public void selectionNiveau() {
		SelectNiveau select = new SelectNiveau(this.root);
		OrgeSelectNiveau orge = new OrgeSelectNiveau(select.getOrge());
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ENTER) {
					keyEvent.consume();
					switch(orge.getNiveaux()[orge.getSelectedNiveau()]) {
					case "NIVEAU1":
						Jeu.monJeu.changeGameState(GameState.NIVEAU1);
						break;
					case "NIVEAU2":
						Jeu.monJeu.changeGameState(GameState.NIVEAU2);
						break;
					case "NIVEAU3":
						Jeu.monJeu.changeGameState(GameState.NIVEAU3);
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
					Jeu.monJeu.changeGameState(GameState.MENU);
					break;
				default:
					break;
				}
			}
		});
	}
}
