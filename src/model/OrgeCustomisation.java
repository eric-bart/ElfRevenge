package model;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class OrgeCustomisation {

		private String[] skins = {"BLEU", "ROUGE", "VERT"};
		private ImageView orge;
		private int selectedSkin;

		/**
		 * Constructeur du sucre d'orge, ce qui correspond au s�lecteur sur le menu du jeu
		 * @param orge
		 */
		public OrgeCustomisation(ImageView orge) {
			this.orge = orge;
			this.orgeAnimation();
			this.selectedSkin = 0;
		}

		/**
		 * Cette fonction permet de d�placer l'orge en fonction
		 * de l'option du menu qui est en cours de s�lection
		 */
		public void orgeAnimation() {
			int selectedSkin = this.selectedSkin;
			switch(selectedSkin) {
			case 0:
				//Le sucre d'orge pointe sur "Bleu"
				this.orge.setY(550);
				this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-750);
				break;
			case 1: 
				//Le sucre d'orge pointe sur "Rouge"
				this.orge.setY(550);
				this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-420);
				break;
			case 2:
				//Le sucre d'orge pointe sur "Vert"
				this.orge.setY(550);
				this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-80);
				break;
			default:
				this.orge.setY(550);
				this.orge.setX((Screen.getPrimary().getBounds().getMaxX()/2)-700);
				break;
			}
		}
		/**
		 * Retourne le tableau contenant les différentes options disponibles sur le menu
		 * @return options, le tableau d'options
		 */
		public String[] getSkins() {
			return this.skins;
		}

		/**
		 * Retourne l'itération du tableau options
		 * @return l'itération (0 = JOUER, 1 = CUSTOMISATION, 2 = OPTIONS)
		 */
		public int getSelectedSkin() {
			return this.selectedSkin;
		}

		/**
		 * Permet de mettre à jour l'itération du tableau "options" sur laquelle l'utilisateur pointe
		 * @param opt l'itération (0 = JOUER, 1 = CUSTOMISATION, 2 = OPTIONS)
		 */
		public void setSelectedSkin(int skin) {
			this.selectedSkin=skin;
		}

}
