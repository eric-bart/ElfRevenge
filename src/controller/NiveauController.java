package controller;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import model.GameState;
import model.Lutin;
import view.Niveau1;

public class NiveauController {

	private Group root;
	private Scene scene;
	private GameState etat;

	public NiveauController(Group root, Scene scene, GameState etat) {
		this.root = root;
		this.scene = scene;
		this.etat = etat;
		this.niveauController();
	}

	public void niveauController() {
		switch(etat) {
		case NIVEAU1:
			Niveau1 niveau1 = new Niveau1(this.root);
			deplacement(niveau1);
			break;
		}
	}

	public void deplacement(Niveau1 niveau) {
		Lutin lutin = new Lutin(niveau.getElf(), 0, 300);
		ArrayList<ImageView> solidBricks = niveau.getColisionBlocks();
		try {
			AnimationTimer boucle = new AnimationTimer() {
				@Override
				public void handle(long arg0) {
					boolean imageTrouvee=false;
					ImageView block = new ImageView();
					for(int i=0;i<solidBricks.size();i++) {
						ImageView image = solidBricks.get(i);
						if(lutin.getLutin().getLayoutBounds().getMinX()>=image.getLayoutBounds().getMinX() && lutin.getLutin().getLayoutBounds().getMinX()<=image.getLayoutBounds().getMaxX()) {
							block = image;
							imageTrouvee=true;
							break;
						} else {
							imageTrouvee=false;
						}
					}
					if(imageTrouvee) {
						System.out.println("Un block se trouve l� o� est le personnage");
						if(lutin.isDansLeCiel(block)) {
							lutin.getLutin().setY(lutin.getLutin().getY() + lutin.getVitesseY());
							lutin.setVitesseY(lutin.getVitesseY() + lutin.getG());
						}
					} else {
						System.out.println("Il n'y a pas de block en dessous du personnage");
						lutin.getLutin().setY(lutin.getLutin().getY() + lutin.getVitesseY());
						lutin.setVitesseY(lutin.getVitesseY() + lutin.getG());
					}
					//Deplacement de l'elf
					if (lutin.isDeplacementDroite()) {
						lutin.seDeplace();
					}
					if (lutin.isDeplacementGauche()) {
						lutin.seDeplace();
					}
				}
			};
			this.scene.setOnKeyPressed(e-> {
				switch (e.getCode()) {
				case LEFT:
					lutin.setDeplacementGauche(true);
					break;
				case RIGHT:
					lutin.setDeplacementDroite(true);
					break;
				default:
					break;
				}
			});
			
			this.scene.setOnKeyReleased(e-> {
				switch (e.getCode()) {
				case LEFT:
					lutin.setDeplacementGauche(false);
					break;
				case RIGHT:
					lutin.setDeplacementDroite(false);
					break;
					/**case SPACE:
                     if(!personnageElf.isSaut()) {
                     personnageElf.setSaut(true);
                     personnageElf.setTimerSaut(25);
                     personnageElf.setVitesseY(personnageElf.getVitesseY()-personnageElf.getVITESSESAUT());
                     } else {
                     System.out.println("Il est déjà entrain de sauter la!!!!!!!");
                     }**/
				default:
					break;
				}
			});
			
			boucle.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		/**try {
			AnimationTimer boucle = new AnimationTimer() {
				@Override
				public void handle(long l) {
					//Gravité de l'elf.
					if (lutin.getLutin().getY() < background.getLayoutBounds().getMaxY() * 0.7d) {
						if (!lutin.isSaut()) {
							lutin.getLutin().setY(lutin.getLutin().getY() + lutin.getVitesseY());
							lutin.setVitesseY(lutin.getVitesseY() + lutin.getG());
						}
					}
					if (lutin.getLutin().getY() > background.getLayoutBounds().getMaxY() * 0.7d) {
						lutin.getLutin().setY(background.getLayoutBounds().getMaxY() * 0.7d);
					}

					//Deplacement de l'elf
					if (lutin.isDeplacementDroite()) {
						lutin.seDeplace(background);
					}
					if (lutin.isDeplacementGauche()) {
						lutin.seDeplace(background);
					}

					/**if(personnageElf.isSaut()) {
                     for(int i=personnageElf.getTimerSaut();i>0;i--) {
                     personnageElf.setY(personnageElf.getY() - personnageElf.getVitesseY());
                     }
                     personnageElf.setSaut(false);
                     }
				}
			};
			scene.setOnKeyPressed(e -> {
				switch (e.getCode()) {
				case LEFT:
					lutin.setDeplacementGauche(true);
					break;
				case RIGHT:
					lutin.setDeplacementDroite(true);
					break;
				default:
					break;
				}
			});
			scene.setOnKeyReleased(e -> {
				switch (e.getCode()) {
				case LEFT:
					lutin.setDeplacementGauche(false);
					break;
				case RIGHT:
					lutin.setDeplacementDroite(false);
					break;
					/**case SPACE:
                     if(!personnageElf.isSaut()) {
                     personnageElf.setSaut(true);
                     personnageElf.setTimerSaut(25);
                     personnageElf.setVitesseY(personnageElf.getVitesseY()-personnageElf.getVITESSESAUT());
                     } else {
                     System.out.println("Il est déjà entrain de sauter la!!!!!!!");
                     }
				default:
					break;
				}
			});
			boucle.start();
		} catch(Exception e) {
			e.printStackTrace();
		}**/
	}
}