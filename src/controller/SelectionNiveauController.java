package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.GameState;
import model.OrgeMenu;
import model.OrgeSelectNiveau;
import view.Menu;
import view.SelectNiveau;


public class SelectionNiveauController {
	
	private Group root;
    private Scene scene;
    private GameState etat;
    
    public SelectionNiveauController(Group root, Scene scene) {
    	this.root = root;
    	this.scene = scene;
    	this.etat = GameState.SELECT_NIVEAU;
    }

	public void selectionNiveau() {
		//S�lection du niveau pas encore faite -> Je redirige vers le niveau1
		SelectNiveau select = new SelectNiveau(this.root);
		OrgeSelectNiveau orge = new OrgeSelectNiveau(select.getOrge());
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER) {
                    keyEvent.consume();
                    switch(orge.getNiveaux()[orge.getSelectedNiveau()]) {
                    case "NIVEAU1":
                        application.Main.setGameState(GameState.NIVEAU1);
                        break;
                    case "NIVEAU2":
                        FileManager fileManager = new FileManager();
                        HashMap<String, DonneesNiveau> read = (HashMap<String, DonneesNiveau>) fileManager.readFile("donnees");
                        System.out.println(read.get("niveau1").isFini());
                            if(read.get("niveau1").isFini() == true) {
                                application.Main.setGameState(GameState.NIVEAU2);
                            }else {
                                Label label = new Label("Vous devez d'abord finir le niveau 1 avant de lancer le niveau2.");
                                //Font font = Font.loadFont(this.getClass().getClassLoader().getResource("MARIO-KART-DS.TTF").toString(), 50);
                                //label.setFont(font);
                                label.snapPositionX(250);
                                root.getChildren().add(label);
                            }
                        break;
                    case "NIVEAU3":
                        FileManager fileManager1 = new FileManager();
                        HashMap<String, DonneesNiveau> read1 = (HashMap<String, DonneesNiveau>) fileManager1.readFile("donnees");
                        System.out.println(read1.get("niveau2").isFini());
                            if(read1.get("niveau2").isFini() == true) {
                                application.Main.setGameState(GameState.NIVEAU3);
                            }
                        break;
                    }
                    return;
                }
			}
		});
	}
}
