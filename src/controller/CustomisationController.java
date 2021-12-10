package controller;

import java.util.HashMap;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import model.FileManager;
import model.GameState;
import model.OrgeCustomisation;
import view.Customiser;

/**
 * Classe de du controlleur de notre vue "CONTROLE"
 */
public class CustomisationController {

	private Group root;
	private Scene scene;
	private Customiser custom;
	private OrgeCustomisation orge;
	
	public CustomisationController() {
		this.root = Jeu.monJeu.getGameRoot();
		this.scene = Jeu.monJeu.getGameScene();
		this.custom = new Customiser(this.root);
		this.orge = new OrgeCustomisation(custom.getOrge());
	}

	/**
	 * Met en place les �coutes li�es aux touches du clavier et enregistre le skin du lutin qui a �t� choisi.
	 */
	public void selectionSkin() {
		//On r�cup�re les �venements de type "Touche press�e"
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@SuppressWarnings("unchecked")
			@Override
			public void handle(KeyEvent keyEvent) {
				//Selon la touche qui a �t� press�e
				switch(keyEvent.getCode()) {
				case LEFT:
					//Si la fl�che "GAUCHE" a �t� press�e
					//Si l'orge n'est pas d�j� � la position maximale, on d�cr�mente l'index de 1
					if(orge.getSelectedSkin() > 0) {
						orge.setSelectedSkin(orge.getSelectedSkin()-1);
						orge.orgeAnimation();
					}
					break;
				case RIGHT:
					//Si la fl�che "DROITE" a �t� press�e
					//Si l'orge n'est pas d�j� � la position maximale, on incr�mente l'index de 1
					if(orge.getSelectedSkin() < 2) {
						orge.setSelectedSkin(orge.getSelectedSkin()+1);
						orge.orgeAnimation();
					}
					break;
				case ESCAPE:
					//Si la touche "ECHAPE" a �t� press�e
					//On d�truit l'�coute d'�v�nement, et on met l'�tat du jeu � "MENU"
					keyEvent.consume();
					Jeu.monJeu.changeGameState(GameState.MENU);
					break;
				case ENTER:
					//Si la touche "ENTRER" a �t� press�e
					//On d�truit l'�coute d'�v�nement
					keyEvent.consume();
					//Selon la position de l'orge rapport�e au tableau contenant les couleurs du skin
					switch(orge.getSkins()[orge.getSelectedSkin()]) {
					case "BLEU":
						//Si la cha�ne de caract�re est "BLEU"
						//On sauvegarde la s�lection de ce skin dans le fileManager
						//On applique ce skin au lutin
						//On met l'�tat du jeu � "MENU"
						FileManager fileManager = new FileManager();
						HashMap<String, Integer> recup = (HashMap<String, Integer>)fileManager.readFile("skin");
						Integer skin = 0;
						recup.put("skin", skin);
						fileManager.setSkin("skin", recup);
						Jeu.monJeu.changeGameState(GameState.MENU);
						break;
					case "ROUGE":
						//Si la cha�ne de caract�re est "ROUGE"
						//On sauvegarde la s�lection de ce skin dans le fileManager
						//On applique ce skin au lutin
						//On met l'�tat du jeu � "MENU"
						FileManager fileManager1 = new FileManager();
						HashMap<String, Integer> recup1 = (HashMap<String, Integer>)fileManager1.readFile("skin");
						Integer skin1 = 1;
						recup1.put("skin", skin1);
						fileManager1.setSkin("skin", recup1);	
						Jeu.monJeu.changeGameState(GameState.MENU);
						break;
					case "VERT":
						//Si la cha�ne de caract�re est "VERT"
						//On sauvegarde la s�lection de ce skin dans le fileManager
						//On applique ce skin au lutin
						//On met l'�tat du jeu � "MENU"
						FileManager fileManager2 = new FileManager();
						HashMap<String, Integer> recup2 = (HashMap<String, Integer>)fileManager2.readFile("skin");
						Integer skin2 = 2;
						recup2.put("skin", skin2);
						fileManager2.setSkin("skin", recup2);	
						Jeu.monJeu.changeGameState(GameState.MENU);
						break;
					}
				default:
					break;
				}
			}
		});
	}
}
