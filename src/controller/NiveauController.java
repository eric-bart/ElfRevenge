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
	private long lastUpdate = 0;
	private long lastTouche = 0;
	private long lastSkinChange =0;
	private long lastSautUpdate=0;
	private int points = 0;
	private long lastFrame = 0;
	private boolean estSurBlocSpecial = false;
	private ArrayList<Personnage> mobsMorts;

	public NiveauController(GameState etat) {
		this.root=Jeu.monJeu.getGameRoot();
		this.scene=Jeu.monJeu.getGameScene();
		this.etat = etat;
		this.mobsMorts = new ArrayList<Personnage>();
		this.niveauController();
	}

	/**
	 * Méthode rédirigeant vers un niveau en fonction de l'état sur lequel se trouve le jeu.
	 */
	public void niveauController() {
		switch(etat) {
		case NIVEAU1:
			niveau = new Niveau1(this.root, new Lutin(100, 0));
			animationGenerale(niveau, ((Niveau1) niveau).getLutin());
			break;
		case NIVEAU2:
			niveau = new Niveau2(this.root, new Lutin(100, 100));
			animationGenerale(niveau, ((Niveau2) niveau).getLutin());	
			break;
		case NIVEAU3:
			niveau = new Niveau3(this.root, new Lutin(100, 100));
			animationGenerale(niveau, ((Niveau3) niveau).getLutin());	
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
	public void animationGenerale(Niveau niveau, Lutin lutin) {
		setListeners(lutin);
		try {
			boucle = new AnimationTimer() {
				@Override
				public void handle(long now) {
					if(now - lastFrame >= 001_600_000) { // delay de 16,33ms
						lastFrame=now;
						//Ajoute une seconde au chrono toutes les 1s
						if (now - lastUpdate >= 1000_000_000) { // delay de 1000 ms
							lutin.setRebond(false);
							niveau.getChronometre().ajouter();
							lastUpdate = now;
						}
						lutinAnimation(now, lutin);
						bonhommeNeigeAnimation(now, lutin);
						pereNoelAnimation(now, lutin);
					}
				}
			};
			boucle.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gère la partie boucle d'animation de notre lutin
	 * @param now le temps actuel en milisecondes de la boucle animationTimer
	 * @param lutin le lutin
	 */
	public void lutinAnimation(long now, Lutin lutin) {
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
		if(lutin.isSaut()&&lutin.getRaterri()) {
			lutin.sauter(niveau);
			lutin.setRaterri(false);
		}
		if(lutin.isMort()) {
			boucle.stop();
			scene.setFill(Color.web("9bbeff"));
			Jeu.monJeu.changeGameState(GameState.MENU);
		}
		if (lutin.blocDurDessous(niveau) != null) {
				if(lutin.blocDurDessous(niveau).getBlockName().equals("blocSpecial.png")) {
					lastSautUpdate=now;
					estSurBlocSpecial = true;
					lutin.setVitesse(7);
				} else if(lutin.blocDurDessous(niveau).getBlockName().equals("blocSpecial2.png")) {
					lastSautUpdate=now;
					estSurBlocSpecial = true;
					lutin.setG(0.3);
				}
		}
		if(estSurBlocSpecial){
			if (now - lastSautUpdate >= 5000_000_000L) { 
				lastSautUpdate = now;
				lutin.setVitesse(5);
			}
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
			Jeu.monJeu.changeGameState(GameState.SELECT_NIVEAU);
		}
	}
	
	/**
	 * Gère la partie boucle d'animation des bonhommes de neiges au sein de notre niveau
	 * @param now le temps actuel en milisecondes de la boucle animationTimer
	 * @param lutin le lutin
	 */
	public void bonhommeNeigeAnimation(long now, Lutin lutin) {
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
					/**if(!lutin.isTouche()) {
						if(now - lastSkinChange >= 500_000_000) {
							lastSkinChange=now;
							lutin.getImage().setVisible(false);
						} else{
							lutin.getImage().setVisible(true);
						}
					}**/

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
	}
	
	/**
	 * Gère la partie boucle d'animation du père noel au sein de notre niveau
	 * @param now le temps actuel en milisecondes de la boucle animationTimer
	 * @param lutin le lutin
	 */
	public void pereNoelAnimation(long now, Lutin lutin) {
		if(!niveau.getBoss().isEmpty()) {
			niveau.getBoss().forEach(e -> {
				e.getImage().toFront();
				if(now - lastUpdate >= 1000_000_000) {
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
					points+=niveau.getChronometre().calculerPoints();
					((Niveau3) niveau).fini(points);
					Jeu.monJeu.changeGameState(GameState.OUTRO);
					boucle.stop();
				}
			});
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
