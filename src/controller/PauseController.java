package controller;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import model.GameState;
import model.OrgePause;
import view.Pause;

public class PauseController {

	private Group root;
    private Scene scene;
    private NiveauController niveauActuel;
	
	public PauseController(NiveauController niveauActuel) {
		this.root=Jeu.monJeu.getGameRoot();
		this.scene=Jeu.monJeu.getGameScene();
		this.niveauActuel = niveauActuel;
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
				//Selon la touche qui a été pressée
				switch(keyEvent.getCode()) {
				case UP:
					//Si la flèche "HAUT" a été pressée
					//Si l'orge n'est pas déjà à la position maximale, on décrémente l'index de 1
					if(orge.getSelectedOpt() > 0) {
						orge.setSelectedOpt(orge.getSelectedOpt()-1);
						orge.orgeAnimation();
					}
					break;
				case DOWN:
					//Si la flèche "BAS" a été pressée
					//Si l'orge n'est pas déjà à la position maximale, on incrémente l'index de 1
					if(orge.getSelectedOpt() < 1) {
						orge.setSelectedOpt(orge.getSelectedOpt()+1);
						orge.orgeAnimation();
					}
					break;
				case ENTER:
					//Si la touche entrer a été pressée, on détruit l'écoute d'évènement.
					keyEvent.consume();
					//Selon la position de l'orge rapportée au tableau contenant les options de l'écran de pause
					switch(orge.getOptions()[orge.getSelectedOpt()]) {
					case "REPRENDRE":
						//Si la chaîne de caractère est "REPRENDRE"
						//On redémarre la boucle du jeu
						//On remet les écoutes d'évènements
						//On retire l'affichage de la pause
						niveauActuel.getBoucle().start();
						niveauActuel.setListeners();
						root.getChildren().removeAll(pause.getPauseBackground(), orge.getOrge());
						break;
					case "QUITTER":
						//Si la chaîne de caractère est "QUITTER"
						//On met l'état du jeu sur "MENU"
						scene.setFill(Color.web("9bbeff"));
						Jeu.monJeu.changeGameState(GameState.MENU);
						break;
					}
					break;
				default:
					break;
				}
			}
		});
	}
	
	
}
