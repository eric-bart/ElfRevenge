package model;

import java.util.HashMap;

import application.Jeu;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import model.FileManager;

public class OrgeSelectNiveau {
	private Group root;
	private String[] niveaux = {"NIVEAU1", "NIVEAU2", "NIVEAU3"};
	private ImageView orge;
	private int selectedNiveau;

	/**
	 * Constructeur du sucre d'orge, ce qui correspond au s�lecteur sur le menu du jeu
	 * Constructeur du sucre d'orge, ce qui correspond au s�lecteur sur le menu du jeu
	 * @param orge
	 */
	public OrgeSelectNiveau(ImageView orge) {
		this.root = Jeu.monJeu.getGameRoot();
		this.orge = orge;
		this.orgeAnimation();
		this.selectedNiveau = 0;
	}
	
	/**
	 * Cette fonction permet de d�placer l'orge en fonction
	 * de l'option du menu qui est en cours de s�lection
	 * Cette fonction permet de d�placer l'orge en fonction
	 * de l'option du menu qui est en cours de s�lection
	 */
	public void afficherScore() {
		FileManager fileManager = new FileManager();
		HashMap<String, DonneesNiveau> recup = (HashMap<String, DonneesNiveau>)fileManager.readFile("donnees");
		DonneesNiveau d1 = recup.get("niveau1");
		DonneesNiveau d2 = recup.get("niveau2");
		DonneesNiveau d3 = recup.get("niveau3");
		Label label = new Label("Score max : " + d1.scoreString());
		Label label1 = new Label("Score max : " + d2.scoreString());
		Label label2 = new Label("Score max : " + d3.scoreString());
		Font font = new Font("SansSerif",  22);
		label.setTextFill(Color.WHITE);
		label1.setTextFill(Color.WHITE);
		label2.setTextFill(Color.WHITE);
        label.setFont(font);
        label1.setFont(font);
        label2.setFont(font);
		label.setLayoutX(470);
		label.setLayoutY(550);
		label1.setLayoutX(780);
		label1.setLayoutY(550);
		label2.setLayoutX(1050);
		label2.setLayoutY(550);
		root.getChildren().add(label);
		root.getChildren().add(label1);
		root.getChildren().add(label2);
	
	}
	public void orgeAnimation() {
		int selectedNiveau = this.selectedNiveau;
		switch(selectedNiveau) {
		case 0:
			//Le sucre d'orge pointe sur "NIVEAU1"
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-320);
			this.afficherScore();
			break;
		case 1: 
			//Le sucre d'orge pointe sur "NIVEAU2"
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-50);
			this.afficherScore();
			break;
		case 2:
			//Le sucre d'orge pointe sur "NIVEAU3"
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)+235);
			this.afficherScore();
			break;
		default:
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-320);
			this.afficherScore();
			break;
		}
	}

	/**
	 * Retourne le tableau contenant les diff�rents niveaux disponibles dans les s�l�ctions
	 * @return options, le tableau d'options
	 */
	public String[] getNiveaux() {
		return this.niveaux;
	}

	/**
	 * Retourne l'it�ration du tableau niveaux
	 * @return l'it�ration (0 = NIVEAU1, 1 = NIVEAU2, 2 = NIVEAU3)
	 * Retourne l'it�ration du tableau niveaux
	 * @return l'it�ration (0 = NIVEAU1, 1 = NIVEAU2, 2 = NIVEAU3)
	 */
	public int getSelectedNiveau() {
		return this.selectedNiveau;
	}

	/**
	 * Permet de mettre � jour l'it�ration du tableau "niveaux" sur laquelle l'utilisateur pointe
	 * @param opt l'it�ration (0 = NIVEAU1, 1 = NIVEAU2, 2 = NIVEAU3)
	 * Permet de mettre � jour l'it�ration du tableau "niveaux" sur laquelle l'utilisateur pointe
	 * @param opt l'it�ration (0 = NIVEAU1, 1 = NIVEAU2, 2 = NIVEAU3)
	 */
	public void setSelectedNiveau(int niveau) {
		this.selectedNiveau = niveau;
	}
}