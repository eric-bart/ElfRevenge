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
		try {
			AnimationTimer boucle = new AnimationTimer() {
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
