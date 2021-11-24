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

	/**
	 * M�thode r�dirigeant vers un niveau en fonction de l'�tat sur lequel se trouve le jeu.
	 */
	public void niveauController() {
		switch(etat) {
		case NIVEAU1:
			Niveau1 niveau1 = new Niveau1(this.root);
			deplacement(niveau1);
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
		Lutin lutin = new Lutin(niveau.getLutin(), 0, 300);
		ArrayList<ImageView> solidBricks = niveau.getColisionBlocks();
		try {
			AnimationTimer boucle = new AnimationTimer() {
				@Override
				public void handle(long arg0) {
					boolean imageTrouvee=false;
					boolean colisionSurDroite=false;
					boolean colisionSurGauche=false;
					ImageView block = new ImageView();
					
					//if(lutin.getLutin().getLayoutBounds().getMaxY()>niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinY()) {
					if(lutin.blocDessousLutin(niveau)!=null) {
						if(!lutin.blocDessousLutin(niveau).isHardBlock() || lutin.getLutin().getLayoutBounds().getMaxY()<=lutin.blocDessousLutin(niveau).getBlock().getLayoutBounds().getMinY()) {
							System.out.println("La je le consid�re comme dans le ciel !");
							lutin.getLutin().setY(lutin.getLutin().getY() + lutin.getVitesseY());
							lutin.setVitesseY(lutin.getVitesseY() + lutin.getG());
						}
					} else {
						lutin.getLutin().setY(lutin.getLutin().getY() + lutin.getVitesseY());
						lutin.setVitesseY(lutin.getVitesseY() + lutin.getG());
					}
					
					
					if(lutin.blocDroiteLutin(niveau)!=null) {
						if(!lutin.isColisionDroite(lutin.blocDroiteLutin(niveau))) {
							//Deplacement de l'elf
						if (lutin.isDeplacementDroite()) {
							lutin.seDeplace(niveau);
						}
						}
					} else {
						lutin.getLutin().setY(lutin.getLutin().getY() + lutin.getVitesseY());
						lutin.setVitesseY(lutin.getVitesseY() + lutin.getG());
					}
					
					if(lutin.blocGaucheLutin(niveau)!=null) {
						if(!lutin.isColisionGauche(lutin.blocGaucheLutin(niveau))) {
							if (lutin.isDeplacementGauche()) {
							lutin.seDeplace(niveau);
							
						}
						}
					} else {
						lutin.getLutin().setY(lutin.getLutin().getY() + lutin.getVitesseY());
						lutin.setVitesseY(lutin.getVitesseY() + lutin.getG());
					}
						
						
						
						
						
						
						
					//} else {
					//	System.out.println("La je le consid�re comme dans le ciel !");
					//	lutin.getLutin().setY(lutin.getLutin().getY() + lutin.getVitesseY());
					//	lutin.setVitesseY(lutin.getVitesseY() + lutin.getG());
					//}
					
					
					
					/**for(int i=0;i<solidBricks.size();i++) {
						ImageView image = solidBricks.get(i);
							if(lutin.isColisionDroite(image)) {
							//System.out.println("Le lutin est en colision depuis sa droite sur un bloc");
							colisionSurDroite=true;
						} else if(lutin.isColisionGauche(image)) {
							//System.out.println("Le lutin est en colision depuis sa gauche sur un bloc");
							colisionSurGauche=true;
						} else if(lutin.isColisionDessus(image)) {
							//System.out.println("Le lutin est en colision depuis le dessus sur un bloc");
							block = image;
							imageTrouvee=true;
							break;
							
						}						
					}
					if(imageTrouvee) {
						//System.out.println("Un block se trouve l� o� est le personnage");
						if(lutin.isDansLeCiel(block)) {
							System.out.println("La je le consid�re comme dans le ciel !");
							lutin.getLutin().setY(lutin.getLutin().getY() + lutin.getVitesseY());
							lutin.setVitesseY(lutin.getVitesseY() + lutin.getG());
						}
					} else {
						//System.out.println("Il n'y a pas de block en dessous du personnage");
						lutin.getLutin().setY(lutin.getLutin().getY() + lutin.getVitesseY());
						lutin.setVitesseY(lutin.getVitesseY() + lutin.getG());
					}
					if(!colisionSurDroite) {
						//Deplacement de l'elf
						if (lutin.isDeplacementDroite()) {
							lutin.seDeplace(niveau);
						}
					}
					
					if(!colisionSurGauche) {
						if (lutin.isDeplacementGauche()) {
							lutin.seDeplace(niveau);
						}
					}
				}**/
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
				}
			});
			
			boucle.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
