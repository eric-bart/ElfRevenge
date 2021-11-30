package model;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class OrgeSelectNiveau {

	private String[] niveaux = {"NIVEAU1", "NIVEAU2", "NIVEAU3"};
	private ImageView orge;
	private int selectedNiveau;

	/**
	 * Constructeur du sucre d'orge, ce qui correspond au s�lecteur sur le menu du jeu
	 * @param orge
	 */
	public OrgeSelectNiveau(ImageView orge) {
		this.orge = orge;
		this.orgeAnimation();
		this.selectedNiveau = 0;
	}

	/**
	 * Cette fonction permet de d�placer l'orge en fonction
	 * de l'option du menu qui est en cours de s�lection
	 */
	public void orgeAnimation() {
		int selectedNiveau = this.selectedNiveau;
		switch(selectedNiveau) {
		case 0:
			//Le sucre d'orge pointe sur "NIVEAU1"
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-580);
			break;
		case 1: 
			//Le sucre d'orge pointe sur "NIVEAU2"
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-300);
			break;
		case 2:
			//Le sucre d'orge pointe sur "NIVEAU3"
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-20);
			break;
		default:
			this.orge.setY(600);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-420);
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
	 */
	public int getSelectedNiveau() {
		return this.selectedNiveau;
	}

	/**
	 * Permet de mettre � jour l'it�ration du tableau "niveaux" sur laquelle l'utilisateur pointe
	 * @param opt l'it�ration (0 = NIVEAU1, 1 = NIVEAU2, 2 = NIVEAU3)
	 */
	public void setSelectedNiveau(int niveau) {
		this.selectedNiveau = niveau;
	}
}
