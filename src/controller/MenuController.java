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
	 * M�thode qui met sous �coute les touches du claviers et change l'�tat du jeu en fonction de l'option du menu qui est s�lectionn�e
	 */
	public void menu() {
		//On r�cup�re les �venements de type "Touche press�e"
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
					if(orge.getSelectedOpt() < 2) {
						orge.setSelectedOpt(orge.getSelectedOpt()+1);
						orge.orgeAnimation();
					}
					break;
				case ENTER:
					//Si la touche entrer a �t� press�e, on d�truit l'�coute d'�v�nement.
					keyEvent.consume();
					//Selon la position de l'orge rapport�e au tableau contenant toutes les options disponibles sur le menu
					switch(orge.getOptions()[orge.getSelectedOpt()]) {
					case "JOUER":
						//Si la cha�ne de caract�re est "JOUER"
						//On met l'�tat du jeu � "SELECT_NIVEAU"
						Jeu.monJeu.changeGameState(GameState.SELECT_NIVEAU);
						break;
					case "CUSTOMISER":
						//Si la cha�ne de caract�re est "CUSTOMISER"
						//On met l'�tat du jeu � "CUSTOMISATION"
						Jeu.monJeu.changeGameState(GameState.CUSTOMISATION);
						break;
					case "CONTROLES":
						//Si la cha�ne de caract�re est "CONTROLES"
						//On met l'�tat du jeu � "CONTROLES"
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