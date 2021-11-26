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
import model.BonhommeDeNeige;
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

	public NiveauController(Group root, Scene scene, GameState etat) {
		this.root = root;
		this.scene = scene;
		this.etat = etat;
		this.niveauController();
	}

	/**
	 * M�thode r�dirigeant vers un niveau en fonction de l'�tat sur lequel se trouve le jeu.
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
	 * M�thode mettant en place l'�coute des touches du clavier pour le d�placement du lutin
	 * Cette m�thode permet �galement de g�rer les cas de colision du lutin avec les blocks "solides"
	 *!\ A REFACTORER /!
	 * @param niveau
	 */
	public void deplacement(Niveau1 niveau) {
		lutin = new Lutin(niveau.getLutin(), 0, 400);
		bonhommeNeige = new BonhommeDeNeige(niveau.getBonhommeNeige(), 0, 400);
		try {
			this.boucle = new AnimationTimer() {
				@Override
				public void handle(long arg0) {
					if(lutin.blocDessousLutin(niveau)!=null) {
						if(lutin.isDansLeCiel(niveau)) {
							lutin.tombe(niveau);
						}
					} else {
						lutin.tombe(niveau);
					}
					if(lutin.blocDroiteLutin(niveau)!=null) {
						if(!lutin.isColisionDroite(lutin.blocDroiteLutin(niveau))) {
							if (lutin.isDeplacementDroite()) {
								lutin.seDeplace(niveau);
							}
						}
					} else {
						lutin.tombe(niveau);
					}

					if(lutin.blocGaucheLutin(niveau)!=null) {
						if(!lutin.isColisionGauche(lutin.blocGaucheLutin(niveau))) {
							if (lutin.isDeplacementGauche()) {
								lutin.seDeplace(niveau);
							}
						}
					} else {
						lutin.tombe(niveau);
					}
					
					if(lutin.isSaut()) {
						lutin.sauter(niveau);
					}
					if(lutin.isMort(niveau)) {
						boucle.stop();
						Main.setGameState(GameState.MENU);
					}
				}
			};
			boucle.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

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
				/*case SPACE:
					lutin.setSaut(true);
					break;*/
				default:
					break;
				}
			}
		});
		
		scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if(keyEvent.getCode() == KeyCode.ESCAPE) {
					keyEvent.consume();
				}
				switch (keyEvent.getCode()) {
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
					break;
				default:
					break;
				}
			}
		});
	}

}
