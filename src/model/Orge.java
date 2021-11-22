package model;

import javafx.scene.image.ImageView;

public class Orge {

	private String[] options = {"JOUER", "CUSTOMISER", "OPTIONS"};
	private ImageView orge;
	private int selectedOpt;

	public Orge(ImageView orge) {
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
			this.orge.setY(295);
			this.orge.setX(495);
			System.out.println("Le sucre doit être en position 0");
			break;
		case 1:
			this.orge.setY(370);
			this.orge.setX(410);
			System.out.println("Le sucre doit être en position 1");
			break;
		case 2:
			this.orge.setY(450);
			this.orge.setX(470);
			System.out.println("Le sucre doit être en position 2");
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
	 * Retourne l'itération de l'option correspondant au tableau d'options
	 * @return l'itération (0 = JOUER, 1 = CUSTOMISATION, 2 = OPTIONS)
	 */
	public int getSelectedOpt() {
		return this.selectedOpt;
	}

	public void setSelectedOpt(int opt) {
		this.selectedOpt=opt;
	}
}
