package model;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class Orge {

	private String[] options = {"JOUER", "CUSTOMISER", "OPTIONS"};
	private String[] niveaux = {"NIVEAU1", "NIVEAU2", "NIVEAU3"};
	private ImageView orge;
	private int selectedOpt;
	private int selectedNiveau;
	public boolean selectNiveau;

	/**
	 * Constructeur du sucre d'orge, ce qui correspond au s�lecteur sur le menu du jeu
	 * @param orge
	 */
	public Orge(ImageView orge) {
		this.orge = orge;
		this.orgeAnimation();
		this.selectedOpt = 0;
		this.selectedNiveau = 0;
		this.selectNiveau = false;
	}

	/**
	 * Cette fonction permet de d�placer l'orge en fonction
	 * de l'option du menu qui est en cours de s�lection
	 */
	public void orgeAnimation() {
		if(this.selectNiveau == false) {
			int selectedOpt = this.selectedOpt;
			switch(selectedOpt) {
			case 0:
				//Le sucre d'orge pointe sur "jouer"
				this.orge.setY(285);
				this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-260);
				break;
			case 1: 
				//Le sucre d'orge pointe sur "customisation"
				this.orge.setY(360);
				this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-325);
				break;
			case 2:
				//Le sucre d'orge pointe sur "param�tres"
				this.orge.setY(440);
				this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-310);
				break;
			default :
				//Le sucre d'orge pointe sur "jouer"
				this.orge.setY(285);
				this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-260);
				break;
			}
		}else {
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
