package controller;

import java.util.ArrayList;

import application.Jeu;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import model.Chronometre;
import model.GameState;
import model.Lutin;
import model.Personnage;
import view.Niveau;
import view.Niveau1;
import view.Niveau2;
import view.Niveau3;

public class NiveauController {

	private AnimationTimer boucle;
	private Group root;
	private Scene scene;
	private GameState etat;
	private Chronometre chronometre;
	private Lutin lutin;

	public NiveauController(GameState etat) {
		this.root=Jeu.monJeu.getGameRoot();
		this.scene=Jeu.monJeu.getGameScene();
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
			deplacement(niveau1);
			break;
		case NIVEAU2:
			Niveau2 niveau2 = new Niveau2(this.root);
			Lutin lutin2 = new Lutin(niveau2.getLutin().getImage(), 100, 100, niveau2);
			this.lutin=lutin2;
			deplacement(niveau2);	
			break;
		case NIVEAU3:
			Niveau3 niveau3 = new Niveau3(this.root);
			Lutin lutin3 = new Lutin(niveau3.getLutin().getImage(), 100, 100, niveau3);
			this.lutin=lutin3;
			deplacement(niveau3);	
			break;
		default:
			break;
		}
	}

	/**
	 * Méthode métant en place la boucle de notre jeu. Durant les niveaux, c'est cette boucle qui gère
	 * toutes les situations.
	 * @param niveau le niveau sur lequel on execute la boucle
	 */
	public void deplacement(Niveau niveau) {
		setListeners();
		chronometre = new Chronometre(niveau.getChronometre());
		ArrayList<Personnage> mobsMorts = new ArrayList<Personnage>();
		try {
			boucle = new AnimationTimer() {
				private long lastUpdate = 0;
				private long lastTouche = 0;
				private long lastSkinChange =0;
				int points = 0;
				//private long lastFrame = 0;
				@Override
				public void handle(long now) {
					//if(now - lastFrame >= 001_600_000) { // delay de 16,33ms
						//lastFrame=now;
						if (now - lastUpdate >= 1000_000_000) { // delay de 1000 ms
							lutin.setRebond(false);
							chronometre.ajouter();
							lastUpdate = now;
						}
						if(lutin.blocDurDessous()!=null) {
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

						if(!niveau.getBonhommesNeige().isEmpty()) {
							niveau.getBonhommesNeige().forEach(e -> {
								e.getImage().toFront();
								if(e.blocDurDessous()!=null) {
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
											Jeu.monJeu.changeGameState(GameState.MENU);
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
								if(e.isMortColision(lutin)) {
									points+=100;
									lutin.rebond();
									root.getChildren().remove(e.getImage());
									mobsMorts.add(e);
								}
							});
						}

						niveau.getBonhommesNeige().removeAll(mobsMorts);

						if(!niveau.getBoss().isEmpty()) {
							niveau.getBoss().forEach(e -> {
								e.getImage().toFront();
								if(e.blocDurDessous()!=null) {
									if(e.isDansLeCiel()) {
										e.tombe();
									} else {
										e.seDeplace();
									}
								} else {
									e.tombe();
								}
								if(lutin.isColisionMob(e)) {
									Jeu.monJeu.changeGameState(GameState.MENU);
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
							Jeu.monJeu.changeGameState(GameState.MENU);
						}

						if(lutin.isSurDernierBlock()) {
							boucle.stop();
							switch(etat) {
							case NIVEAU1 : 
								points+=chronometre.calculerPoints();
								((Niveau1) niveau).fini();
								break;
							case NIVEAU2 : 
								points+=chronometre.calculerPoints();
								((Niveau2) niveau).fini();
								break;
							case NIVEAU3 : 
								points+=chronometre.calculerPoints();
								((Niveau3) niveau).fini();
								break;
							default:
								break;
							}
							Jeu.monJeu.changeGameState(GameState.SELECT_NIVEAU);
						}
					}
				//}
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
	public void setListeners() {
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
					Jeu.monJeu.changeGameState(GameState.PAUSE);
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

	public AnimationTimer getBoucle() {
		return this.boucle;
	}

	public Group getRoot() {
		return this.root;
	}

	public Scene getScene() {
		return this.scene;
	}

	public GameState getEtat() {
		return this.etat;
	}

	public Chronometre getChronometre() {
		return this.chronometre;
	}

	public Lutin getLutin() {
		return this.lutin;
	}
	
	

}
