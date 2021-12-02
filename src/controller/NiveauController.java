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
import model.Personnage;
import model.Vie;
import view.Niveau;
import view.Niveau1;
import view.Niveau2;
import view.Niveau3;
import application.Main;

public class NiveauController {

	public static AnimationTimer boucle;
	private Group root;
	private static Scene scene;
	private GameState etat;
	private static BonhommeDeNeige bonhommeNeige;
	private static Chronometre chronometre;
	private static Vie vie;
	private static Lutin lutin;
	
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
			Lutin lutin1 = new Lutin(niveau1.getLutin().getImage(), 100, 100, niveau1);
			this.lutin=lutin1;
			deplacement(niveau1, lutin1);
			/**lutin.setVie(2);
			niveau1.getVie().setVie(lutin.getVie());**/
			setListeners();
			break;
		case NIVEAU2:
			Niveau2 niveau2 = new Niveau2(this.root);
			Lutin lutin2 = new Lutin(niveau2.getLutin().getImage(), 100, 100, niveau2);
			this.lutin=lutin2;
			deplacement(niveau2, lutin2);	
			/**lutin.setVie(2);
			niveau2.getVie().setVie(lutin.getVie());**/
			setListeners();
			break;
		case NIVEAU3:
			Niveau3 niveau3 = new Niveau3(this.root);
			Lutin lutin3 = new Lutin(niveau3.getLutin().getImage(), 100, 100, niveau3);
			this.lutin=lutin3;
			deplacement(niveau3, lutin3);	
			/**lutin.setVie(2);
			niveau3.getVie().setVie(lutin.getVie());**/
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
	public void deplacement(Niveau niveau, Lutin lutin) {
		chronometre = new Chronometre(niveau.getChronometre());
		ArrayList<Personnage> mobsMorts = new ArrayList<Personnage>();
		try {
			this.boucle = new AnimationTimer() {
				private long lastUpdate = 0;
				private long lastTouche = 0;
				private long lastSkinChange =0;
				@Override
				public void handle(long now) {
					if (now - lastUpdate >= 1000_000_000) { // delay de 1000 ms
						lutin.setRebond(false);
						chronometre.ajouter();
						lastUpdate = now;
					}
					if(lutin.blocDurDirectDessousLutin()!=null) {
						if(lutin.isDansLeCiel()) {
							lutin.tombe();
						}
					} else {
						lutin.tombe();
					}
					if (lutin.isDeplacementDroite()) {
						lutin.seDeplace();
					}
					if (lutin.isDeplacementGauche()) {
						lutin.seDeplace();
					}

					if(!niveau.getMobAffiche().isEmpty()) {
						niveau.getMobAffiche().forEach(e -> {
							e.getImage().toFront();
							if(e.blocDurDirectDessousLutin()!=null) {
								if(e.isDansLeCiel()) {
									e.tombe();
								} else {
									e.seDeplace();
								}
							} else {
								e.tombe();
							}
							if(lutin.isColisionMob(e)) {
								if(now - lastTouche >= 2000_000_000L) {
									lastTouche=now;
									lutin.setTouche(true);
								} else {
									lutin.setTouche(false);
								}
								if(lutin.isTouche()) {
									lutin.enleverVie();
									niveau.getVie().enleverVie();
									if(lutin.getVie()==0) {
										application.Main.setGameState(GameState.MENU);
										boucle.stop();
									}
								}
								if(!lutin.isTouche()) {
									if(now - lastSkinChange >= 500_000_000) {
										lastSkinChange=now;
										lutin.getImage().setVisible(false);
									} else{
										lutin.getImage().setVisible(true);
									}
								}
								
							}
							if(e.isMort(lutin)) {
								lutin.rebond();
								root.getChildren().remove(e.getImage());
								mobsMorts.add(e);
							}
						});
					}

					niveau.getMobAffiche().removeAll(mobsMorts);

					if(!niveau.getBossAffiche().isEmpty()) {
						niveau.getBossAffiche().forEach(e -> {
							e.getImage().toFront();
							if(e.blocDurDirectDessousLutin()!=null) {
								if(e.isDansLeCiel()) {
									e.tombe();
								} else {
									e.seDeplace();
								}
							} else {
								e.tombe();
							}
							if(lutin.isColisionMob(e)) {
								application.Main.setGameState(GameState.MENU);
								boucle.stop();
							}
							if(e.isMort(lutin)) {
								lutin.rebond();
								root.getChildren().remove(e.getImage());
								mobsMorts.add(e);
							}
						});
					}

					if(lutin.isSaut()&&lutin.getRaterri()) {
						lutin.sauter();
						lutin.setRaterri(false);
					}
					if(lutin.isMort()) {
						boucle.stop();
						scene.setFill(Color.web("9bbeff"));
						Main.setGameState(GameState.MENU);
					}

					if(lutin.niveauFini()) {
						boucle.stop();
						switch(etat) {
						case NIVEAU1 : 
							view.Niveau1.fini();
							break;
						case NIVEAU2 : 
							view.Niveau2.fini();
							break;
						case NIVEAU3 : 
							view.Niveau3.fini();
							break;
						}
						Main.setGameState(GameState.SELECT_NIVEAU);
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
