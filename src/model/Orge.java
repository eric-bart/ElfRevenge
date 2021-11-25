package model;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class Orge {

	private String[] options = {"JOUER", "CUSTOMISER", "OPTIONS"};
	private ImageView orge;
	private int selectedOpt;

	/**
	 * Constructeur du sucre d'orge, ce qui correspond au s�lecteur sur le menu du jeu
	 * @param orge
	 */
	public Orge(ImageView orge) {
		this.orge = orge;
		this.orgeAnimation();
		this.selectedOpt = 0;
	}

	/**
	 * Cette fonction permet de d�placer l'orge en fonction
	 * de l'option du menu qui est en cours de s�lection
	 */
	public void orgeAnimation() {
		int selectedOpt = this.selectedOpt;
		switch(selectedOpt) {
		case 0:
			//Le sucre d'orge pointe sur "jouer"
			this.orge.setY(295);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-150);
			break;
		case 1: 
			//Le sucre d'orge pointe sur "customisation"
			this.orge.setY(370);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-230);
			break;
		case 2:
			//Le sucre d'orge pointe sur "param�tres"
			this.orge.setY(450);
			this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-180);
			break;
		}
	}

	/**
	 * Retourne le tableau contenant les diff�rentes options disponibles sur le menu
	 * @return options, le tableau d'options
	 */
	public String[] getOptions() {
		return this.options;
	}

	/**
	 * Retourne l'it�ration du tableau options
	 * @return l'it�ration (0 = JOUER, 1 = CUSTOMISATION, 2 = OPTIONS)
	 */
	public int getSelectedOpt() {
		return this.selectedOpt;
	}

	/**
	 * Permet de mettre � jour l'it�ration du tableau "options" sur laquelle l'utilisateur pointe
	 * @param opt l'it�ration (0 = JOUER, 1 = CUSTOMISATION, 2 = OPTIONS)
	 */
	public void setSelectedOpt(int opt) {
		this.selectedOpt=opt;
	}
}
