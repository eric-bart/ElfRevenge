package model;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class OrgeMenu {

	private String[] options = {"JOUER", "CUSTOMISER", "CONTROLES"};
	private ImageView orge;
	private int selectedOpt;

	/**
	 * Constructeur du sucre d'orge, ce qui correspond au sélecteur sur le menu du jeu
	 * @param orge
	 */
	public OrgeMenu(ImageView orge) {
		this.orge = orge;
		this.orgeAnimation();
		this.selectedOpt = 0;
	}

	/**
	 * Cette fonction permet de déplacer l'orge en fonction
	 * de l'option du menu qui est en cours de sélection
	 */
	public void orgeAnimation() {
		int selectedOpt = this.selectedOpt;
		switch(selectedOpt) {
		case 0:
			//Le sucre d'orge pointe sur "jouer"
			this.orge.setY(285);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-510);
			break;
		case 1: 
			//Le sucre d'orge pointe sur "customisation"
			this.orge.setY(360);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-580);
			break;
		case 2:
			//Le sucre d'orge pointe sur "param�tres"
			this.orge.setY(440);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-560);
			break;
		default :
			//Le sucre d'orge pointe sur "jouer"
			this.orge.setY(285);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-260);
			break;
		}
	}

	/**
	 * Retourne le tableau contenant les différentes options disponibles sur le menu
	 * @return options, le tableau d'options
	 */
	public String[] getOptions() {
		return this.options;
	}

	/**
	 * Retourne l'itération du tableau options
	 * @return l'itération (0 = JOUER, 1 = CUSTOMISATION, 2 = OPTIONS)
	 */
	public int getSelectedOpt() {
		return this.selectedOpt;
	}

	/**
	 * Permet de mettre à jour l'itération du tableau "options" sur laquelle l'utilisateur pointe
	 * @param opt l'itération (0 = JOUER, 1 = CUSTOMISATION, 2 = OPTIONS)
	 */
	public void setSelectedOpt(int opt) {
		this.selectedOpt=opt;
	}
}
