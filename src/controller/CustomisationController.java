package controller;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.DonneesNiveau;
import model.GameState;
import model.OrgeCustomisation;
import model.OrgeMenu;
import model.OrgeSelectNiveau;
import view.Customiser;
import view.Menu;
import view.SelectNiveau;


public class CustomisationController {
	
	private Group root;
    private Scene scene;
    private GameState etat;
    
    public CustomisationController(Group root, Scene scene) {
    	this.root = root;
    	this.scene = scene;
    	this.etat = GameState.CUSTOMISATION;
    }

	public void selectionSkin() {
		//Sélection du niveau pas encore faite -> Je redirige vers le niveau1
		Customiser custom = new Customiser(this.root);
		OrgeCustomisation orge = new OrgeCustomisation(custom.getOrge());
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ENTER) {
					keyEvent.consume();
					switch(orge.getSkins()[orge.getSelectedSkin()]) {
					case "BLEU":
						FileManager fileManager = new FileManager();
						HashMap<String, Integer> recup = (HashMap<String, Integer>)fileManager.readFile("skin");
						Integer skin = 0;
						recup.put("skin", skin);
						fileManager.setSkin("skin", recup);
						application.Main.setGameState(GameState.MENU);

						break;
					case "ROUGE":
						FileManager fileManager1 = new FileManager();
						HashMap<String, Integer> recup1 = (HashMap<String, Integer>)fileManager1.readFile("skin");
						Integer skin1 = 1;
						recup1.put("skin", skin1);
						fileManager1.setSkin("skin", recup1);	
						application.Main.setGameState(GameState.MENU);
						break;
					case "VERT":
						FileManager fileManager2 = new FileManager();
						HashMap<String, Integer> recup2 = (HashMap<String, Integer>)fileManager2.readFile("skin");
						Integer skin2 = 2;
						recup2.put("skin", skin2);
						fileManager2.setSkin("skin", recup2);	
						application.Main.setGameState(GameState.MENU);
						break;
					}
					return;
				}
				switch(keyEvent.getCode()) {
				case LEFT:
					if(orge.getSelectedSkin() > 0) {
						orge.setSelectedSkin(orge.getSelectedSkin()-1);
						orge.orgeAnimation();
					}
					break;
				case RIGHT:
					if(orge.getSelectedSkin() < 2) {
						orge.setSelectedSkin(orge.getSelectedSkin()+1);
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
