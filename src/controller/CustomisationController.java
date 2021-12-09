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
	 * Met en place les écoutes liées aux touches du clavier et enregistre le skin du lutin qui a été choisi.
	 */
	public void selectionSkin() {
		//On récupère les évenements de type "Touche pressée"
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@SuppressWarnings("unchecked")
			@Override
			public void handle(KeyEvent keyEvent) {
				//Selon la touche qui a été pressée
				switch(keyEvent.getCode()) {
				case LEFT:
					//Si la flèche "GAUCHE" a été pressée
					//Si l'orge n'est pas déjà à la position maximale, on décrémente l'index de 1
					if(orge.getSelectedSkin() > 0) {
						orge.setSelectedSkin(orge.getSelectedSkin()-1);
						orge.orgeAnimation();
					}
					break;
				case RIGHT:
					//Si la flèche "DROITE" a été pressée
					//Si l'orge n'est pas déjà à la position maximale, on incrémente l'index de 1
					if(orge.getSelectedSkin() < 2) {
						orge.setSelectedSkin(orge.getSelectedSkin()+1);
						orge.orgeAnimation();
					}
					break;
				case ESCAPE:
					//Si la touche "ECHAPE" a été pressée
					//On détruit l'écoute d'évènement, et on met l'état du jeu à "MENU"
					keyEvent.consume();
					Jeu.monJeu.changeGameState(GameState.MENU);
					break;
				case ENTER:
					//Si la touche "ENTRER" a été pressée
					//On détruit l'écoute d'évènement
					keyEvent.consume();
					//Selon la position de l'orge rapportée au tableau contenant les couleurs du skin
					switch(orge.getSkins()[orge.getSelectedSkin()]) {
					case "BLEU":
						//Si la chaîne de caractère est "BLEU"
						//On sauvegarde la sélection de ce skin dans le fileManager
						//On applique ce skin au lutin
						//On met l'état du jeu à "MENU"
						FileManager fileManager = new FileManager();
						HashMap<String, Integer> recup = (HashMap<String, Integer>)fileManager.readFile("skin");
						Integer skin = 0;
						recup.put("skin", skin);
						fileManager.setSkin("skin", recup);
						Jeu.monJeu.changeGameState(GameState.MENU);
						break;
					case "ROUGE":
						//Si la chaîne de caractère est "ROUGE"
						//On sauvegarde la sélection de ce skin dans le fileManager
						//On applique ce skin au lutin
						//On met l'état du jeu à "MENU"
						FileManager fileManager1 = new FileManager();
						HashMap<String, Integer> recup1 = (HashMap<String, Integer>)fileManager1.readFile("skin");
						Integer skin1 = 1;
						recup1.put("skin", skin1);
						fileManager1.setSkin("skin", recup1);	
						Jeu.monJeu.changeGameState(GameState.MENU);
						break;
					case "VERT":
						//Si la chaîne de caractère est "VERT"
						//On sauvegarde la sélection de ce skin dans le fileManager
						//On applique ce skin au lutin
						//On met l'état du jeu à "MENU"
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
