package model;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class OrgeSelectNiveau {

	private String[] niveaux = {"NIVEAU1", "NIVEAU2", "NIVEAU3"};
	private ImageView orge;
	private int selectedNiveau;

	/**
	 * Constructeur du sucre d'orge, ce qui correspond au sélecteur sur le menu du jeu
	 * @param orge
	 */
	public OrgeSelectNiveau(ImageView orge) {
		this.orge = orge;
		this.orgeAnimation();
		this.selectedNiveau = 0;
	}

	/**
	 * Cette fonction permet de déplacer l'orge en fonction
	 * de l'option du menu qui est en cours de sélection
	 */
	public void orgeAnimation() {
		int selectedNiveau = this.selectedNiveau;
		switch(selectedNiveau) {
		case 0:
			//Le sucre d'orge pointe sur "NIVEAU1"
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-320);
			break;
		case 1: 
			//Le sucre d'orge pointe sur "NIVEAU2"
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-50);
			break;
		case 2:
			//Le sucre d'orge pointe sur "NIVEAU3"
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)+235);
			break;
		default:
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-320);
			break;
		}
	}

	/**
	 * Retourne le tableau contenant les diffï¿½rents niveaux disponibles dans les sï¿½lï¿½ctions
	 * @return options, le tableau d'options
	 */
	public String[] getNiveaux() {
		return this.niveaux;
	}

	/**
	 * Retourne l'itération du tableau niveaux
	 * @return l'itération (0 = NIVEAU1, 1 = NIVEAU2, 2 = NIVEAU3)
	 */
	public int getSelectedNiveau() {
		return this.selectedNiveau;
	}

	/**
	 * Permet de mettre à jour l'itération du tableau "niveaux" sur laquelle l'utilisateur pointe
	 * @param opt l'itération (0 = NIVEAU1, 1 = NIVEAU2, 2 = NIVEAU3)
	 */
	public void setSelectedNiveau(int niveau) {
		this.selectedNiveau = niveau;
	}
}
