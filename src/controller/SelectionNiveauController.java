package controller;

import java.util.HashMap;

import application.Jeu;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import model.DonneesNiveau;
import model.FileManager;
import model.GameState;
import model.OrgeSelectNiveau;
import view.SelectNiveau;
import model.FileManager;

/**
 * Classe de notre controlleur de la vue "SELECTION_NIVEAU"
 */
public class SelectionNiveauController {
	
	private Group root;
    private Scene scene;
    private OrgeSelectNiveau orge;
    private SelectNiveau select;
    
    public SelectionNiveauController() {
    	this.root=Jeu.monJeu.getGameRoot();
		this.scene=Jeu.monJeu.getGameScene();
		this.select = new SelectNiveau(this.root);
		this.orge = new OrgeSelectNiveau(select.getOrge());
    }

    /**
	 * Teste si le niveau précédent a été complété ou non.
	 * Si le niveau a bientôt fini, redirige vers le niveau selectionne.
	 */
	public void selectionNiveau() {
		//Sélection du niveau pas encore faite -> Je redirige vers le niveau1
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
	                        FileManager fileManager = new FileManager();
	                        HashMap<String, DonneesNiveau> read = (HashMap<String, DonneesNiveau>) fileManager.readFile("donnees");
	                        System.out.println(read.get("niveau1").isFini());
	                            if(read.get("niveau1").isFini() == true) {
	                            	Jeu.monJeu.changeGameState(GameState.NIVEAU2);
	                            }else {
	                                Label label = new Label("Vous devez finir le niveau 1 avant de lancer le niveau 2.");
	                                Font font = Font.loadFont(this.getClass().getClassLoader().getResource("MARIO-KART-DS.TTF").toString(), 50);
	                                label.setFont(font);
	                                label.snapPositionX(250);
	                                root.getChildren().add(label);
	                            }
	                        break;
	                    case "NIVEAU3":
	                        FileManager fileManager1 = new FileManager();
	                        HashMap<String, DonneesNiveau> read1 = (HashMap<String, DonneesNiveau>) fileManager1.readFile("donnees");
	                        System.out.println(read1.get("niveau2").isFini());
	                            if(read1.get("niveau2").isFini() == true) {
	                                Jeu.monJeu.changeGameState(GameState.NIVEAU3);
	                            } else {
	                            	Label label = new Label("Vous devez finir le niveau 2 avant de lancer le niveau 3.");
	                                Font font = Font.loadFont(this.getClass().getClassLoader().getResource("MARIO-KART-DS.TTF").toString(), 50);
	                                label.setFont(font);
	                                label.snapPositionX(250);
	                                root.getChildren().add(label);
	                            }
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
