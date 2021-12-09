package controller;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import model.GameState;
import model.Orge;
import model.OrgeMenu;
import view.Menu;

/**
 * Classe de notre controlleur de la vue "MENU"
 */
public class MenuController {

	private Group root;
	private Scene scene;
	private OrgeMenu orge;
	private Menu menu;

	public MenuController() {
		this.root=Jeu.monJeu.getGameRoot();
		this.scene=Jeu.monJeu.getGameScene();
		this.menu = new Menu(this.root);
		this.orge = new OrgeMenu(menu.getOrge());
	}

	/**
	 * Méthode qui met sous écoute les touches du claviers et change l'état du jeu en fonction de l'option du menu qui est sélectionnée
	 */
	public void menu() {
		//On récupère les évenements de type "Touche pressée"
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
					if(orge.getSelectedOpt() < 2) {
						orge.setSelectedOpt(orge.getSelectedOpt()+1);
						orge.orgeAnimation();
					}
					break;
				case ENTER:
					//Si la touche entrer a été pressée, on détruit l'écoute d'évènement.
					keyEvent.consume();
					//Selon la position de l'orge rapportée au tableau contenant toutes les options disponibles sur le menu
					switch(orge.getOptions()[orge.getSelectedOpt()]) {
					case "JOUER":
						//Si la chaîne de caractère est "JOUER"
						//On met l'état du jeu à "SELECT_NIVEAU"
						Jeu.monJeu.changeGameState(GameState.SELECT_NIVEAU);
						break;
					case "CUSTOMISER":
						//Si la chaîne de caractère est "CUSTOMISER"
						//On met l'état du jeu à "CUSTOMISATION"
						Jeu.monJeu.changeGameState(GameState.CUSTOMISATION);
						break;
					case "CONTROLES":
						//Si la chaîne de caractère est "CONTROLES"
						//On met l'état du jeu à "CONTROLES"
						Jeu.monJeu.changeGameState(GameState.CONTROLES);
						break;
					default:
						break;
					}
				default:
					break;
				}
			}
		});
	}

}