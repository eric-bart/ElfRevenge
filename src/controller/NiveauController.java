package controller;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import model.BonhommeDeNeige;
import model.Chronometre;
import model.GameState;
import model.Lutin;
import view.Niveau1;
import application.Main;

public class NiveauController {

	public static AnimationTimer boucle;
	private Group root;
	private static Scene scene;
	private GameState etat;
	private static BonhommeDeNeige bonhommeNeige;
	private static Lutin lutin;
	private static Chronometre chronometre;

	public NiveauController(Group root, Scene scene, GameState etat) {
		this.root = root;
		this.scene = scene;
		this.etat = etat;
		this.niveauController();
	}

	/**
	 * Méthode rédirigeant vers un niveau en fonction de l'état sur lequel se trouve le jeu.
	 */
	public void niveauController() {
		switch(etat) {
		case NIVEAU1:
			Niveau1 niveau1 = new Niveau1(this.root);
			deplacement(niveau1);
			setListeners();
			break;
		}
	}

	/**
	 * Méthode mettant en place l'écoute des touches du clavier pour le déplacement du lutin
	 * Cette méthode permet également de gérer les cas de colision du lutin avec les blocks "solides"
	 *!\ A REFACTORER /!
	 * @param niveau
	 */
	public void deplacement(Niveau1 niveau) {
		lutin = new Lutin(niveau.getLutin(), 0, 400, niveau);
		bonhommeNeige = new BonhommeDeNeige(niveau.getBonhommeNeige(), 100, 400, niveau);
		chronometre = new Chronometre(niveau.getChronometre());
		try {
			this.boucle = new AnimationTimer() {
				private long lastUpdate = 0;
				@Override
				public void handle(long now) {
					if (now - lastUpdate >= 1000_000_000) { // delay de 1000 ms
	                    chronometre.ajouter();
	                    lastUpdate = now;
	                }
					if(lutin.blocDessousLutin()!=null) {
						if(lutin.isDansLeCiel()) {
							lutin.tombe();
						}
					} else {
						lutin.tombe();
					}
					if(lutin.blocDroiteLutin()!=null) {
						if(!lutin.isColisionDroite(lutin.blocDroiteLutin())) {
							if (lutin.isDeplacementDroite()) {
								lutin.seDeplace();
							}
						}
					} else {
						lutin.tombe();
					}

					if(lutin.blocGaucheLutin()!=null) {
						if(!lutin.isColisionGauche(lutin.blocGaucheLutin())) {
							if (lutin.isDeplacementGauche()) {
								lutin.seDeplace();
							}
						}
					} else {
						lutin.tombe();
					}
					
					/**if(bonhommeNeige.blocDessousLutin()!=null) {
						if(bonhommeNeige.isDansLeCiel()) {
							bonhommeNeige.tombe();
						}
					} else {
						bonhommeNeige.tombe();
					}
					
					if(bonhommeNeige.isDansFenetre()) {
						bonhommeNeige.seDeplace();
					} else {
						bonhommeNeige.recule();
					}**/
					
					if(lutin.isSaut()&&lutin.getRaterri()) {
						lutin.sauter();
						lutin.setRaterri(false);
					}
					if(lutin.isMort()) {
						boucle.stop();
						scene.setFill(Color.web("9bbeff"));
						Main.setGameState(GameState.MENU);
					}
				}
			};
			boucle.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cette méthode permet de mettre en place les écoutes des touches du clavier et d'effectuer des actions
	 * en fonction de ce qui se passe.
	 */
	public static void setListeners() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ESCAPE) {
					keyEvent.consume();
				}
				switch (keyEvent.getCode()) {
				case LEFT:
					lutin.setDeplacementGauche(true);
					break;
				case RIGHT:
					lutin.setDeplacementDroite(true);
					break;
				case ESCAPE:
					boucle.stop();
					Main.setGameState(GameState.PAUSE);
					break;
				case SPACE:
					lutin.setSaut(true);
					break;
				default:
					break;
				}
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ESCAPE) {
					keyEvent.consume();
				}
				switch (keyEvent.getCode()) {
				case LEFT:
					lutin.setDeplacementGauche(false);
					break;
				case RIGHT:
					lutin.setDeplacementDroite(false);
					break;
				case SPACE:
					lutin.setSaut(false);
					lutin.setRaterri(true);
					break;
				default:
					break;
				}
			}
		});
	}

}
