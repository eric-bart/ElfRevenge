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
	 * M�thode qui met sous �coute les touches du claviers et change l'�tat du jeu en fonction de l'option du menu qui est s�lectionn�e
	 */
	public void pause() {
		Pause pause = new Pause(this.root);
		OrgePause orge = new OrgePause(pause.getOrge());
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				//Selon la touche qui a �t� press�e
				switch(keyEvent.getCode()) {
				case UP:
					//Si la fl�che "HAUT" a �t� press�e
					//Si l'orge n'est pas d�j� � la position maximale, on d�cr�mente l'index de 1
					if(orge.getSelectedOpt() > 0) {
						orge.setSelectedOpt(orge.getSelectedOpt()-1);
						orge.orgeAnimation();
					}
					break;
				case DOWN:
					//Si la fl�che "BAS" a �t� press�e
					//Si l'orge n'est pas d�j� � la position maximale, on incr�mente l'index de 1
					if(orge.getSelectedOpt() < 1) {
						orge.setSelectedOpt(orge.getSelectedOpt()+1);
						orge.orgeAnimation();
					}
					break;
				case ENTER:
					//Si la touche entrer a �t� press�e, on d�truit l'�coute d'�v�nement.
					keyEvent.consume();
					//Selon la position de l'orge rapport�e au tableau contenant les options de l'�cran de pause
					switch(orge.getOptions()[orge.getSelectedOpt()]) {
					case "REPRENDRE":
						//Si la cha�ne de caract�re est "REPRENDRE"
						//On red�marre la boucle du jeu
						//On remet les �coutes d'�v�nements
						//On retire l'affichage de la pause
						niveauActuel.getBoucle().start();
						niveauActuel.setListeners();
						root.getChildren().removeAll(pause.getPauseBackground(), orge.getOrge());
						break;
					case "QUITTER":
						//Si la cha�ne de caract�re est "QUITTER"
						//On met l'�tat du jeu sur "MENU"
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
