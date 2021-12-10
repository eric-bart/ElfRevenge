package controller;

import java.util.ArrayList;
import java.util.Random;

import application.Jeu;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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

/**
 * Classe de notre controlleur des vues "NIVEAU"
 */
public class NiveauController {

	private AnimationTimer boucle;
	private Group root;
	private Scene scene;
	private GameState etat;
	private Niveau niveau;

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
			niveau = new Niveau1(this.root, new Lutin(100, 0));
			deplacement(niveau, ((Niveau1) niveau).getLutin());
			break;
		case NIVEAU2:
			niveau = new Niveau2(this.root, new Lutin(100, 100));
			deplacement(niveau, ((Niveau2) niveau).getLutin());	
			break;
		case NIVEAU3:
			niveau = new Niveau3(this.root, new Lutin(100, 100));
			deplacement(niveau, ((Niveau3) niveau).getLutin());	
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
	public void deplacement(Niveau niveau, Lutin lutin) {
		setListeners(lutin);
		ArrayList<Personnage> mobsMorts = new ArrayList<Personnage>();
		Random r = new Random();
		int low = 750_000_000;
		int high = 1000_000_000;
		int result = r.nextInt(high-low) + low;
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
							niveau.getChronometre().ajouter();
							lastUpdate = now;
						}
						if(lutin.blocDurDessous(niveau)!=null) {
							System.out.println("coucou");
							if(lutin.isDansLeCiel(niveau)) {
								System.out.println("coucou2");
								lutin.tombe(niveau);
							}
						} else {
							System.out.println("coucou");
							lutin.tombe(niveau);
						}
						if (lutin.isDeplacementDroite()) {
							lutin.seDeplace(niveau);
						}
						if (lutin.isDeplacementGauche()) {
							lutin.seDeplace(niveau);
						}

						if(!niveau.getBonhommesNeige().isEmpty()) {
							niveau.getBonhommesNeige().forEach(e -> {
								e.getImage().toFront();
								if(e.blocDurDessous(niveau)!=null) {
									if(e.isDansLeCiel(niveau)) {
										e.tombe(niveau);
									} else {
										e.seDeplace(niveau);
									}
								} else {
									e.tombe(niveau);
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
									lutin.rebond(niveau);
									root.getChildren().remove(e.getImage());
									mobsMorts.add(e);
								}
							});
						}

						niveau.getBonhommesNeige().removeAll(mobsMorts);

						if(!niveau.getBoss().isEmpty()) {
							niveau.getBoss().forEach(e -> {
								e.getImage().toFront();
								if(now - lastUpdate >= result) { // delay de 1000 ms
									int result = r.nextInt(high-low) + low;
                                    e.sauter(niveau);
                                    lastUpdate = now;
                                }
								if(e.blocDurDessous(niveau)!=null) {
									if(e.isDansLeCiel(niveau)) {
										e.tombe(niveau);
									} else {
										e.seDeplace(niveau);
									}
								} else {
									e.tombe(niveau);
								}
								if(lutin.isColisionMob(e)) {
									Jeu.monJeu.changeGameState(GameState.MENU);
									boucle.stop();
								}
								if(e.isMort(lutin)) {
									lutin.rebond(niveau);
									root.getChildren().remove(e.getImage());
									mobsMorts.add(e);
								}
							});
						}

						if(lutin.isSaut()&&lutin.getRaterri()) {
							lutin.sauter(niveau);
							lutin.setRaterri(false);
						}
						if(lutin.isMort()) {
							boucle.stop();
							scene.setFill(Color.web("9bbeff"));
							Jeu.monJeu.changeGameState(GameState.MENU);
						}

						if(lutin.isSurDernierBlock(niveau)) {
							boucle.stop();
							switch(etat) {
							case NIVEAU1 : 
								points+=niveau.getChronometre().calculerPoints();
								System.out.println(points);
								((Niveau1) niveau).fini(points);
								break;
							case NIVEAU2 : 
								points+=niveau.getChronometre().calculerPoints();
								((Niveau2) niveau).fini(points);
								break;
							case NIVEAU3 : 
								points+=niveau.getChronometre().calculerPoints();
								((Niveau3) niveau).fini(points);
								break;
							default:
								break;
							}
							Jeu.monJeu.changeGameState(GameState.MENU);
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
	 * Cette méthode permet de mettre en place les écoutes des touches du clavier pour notre lutin
	 */
	public void setListeners(Lutin lutin) {
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

	/**
	 * Getter pour la boucle de notre niveau
	 * @return AnimationTimer boucle
	 */
	public AnimationTimer getBoucle() {
		return this.boucle;
	}

	/**
	 * Getter pour le group de notre jeu
	 * @return Group le root
	 */
	public Group getRoot() {
		return this.root;
	}

	/**
	 * Getter pour la scene de notre jeu
	 * @return Scene la scene
	 */
	public Scene getScene() {
		return this.scene;
	}

	/**
	 * Getter pour l'état de notre jeu
	 * @return GameState l'état du jeu
	 */
	public GameState getEtat() {
		return this.etat;
	}
}
