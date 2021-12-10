package model;
import javafx.scene.image.ImageView;
import application.Jeu;

/**
 * Classe du mod�le "OrgeCustomisation"
 */
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
				this.orge.setY(520);
				this.orge.setX((Jeu.monJeu.getGameStage().getWidth()/2)-380);
				break;
			case 1: 
				//Le sucre d'orge pointe sur "Rouge"
				this.orge.setY(520);
				this.orge.setX((Jeu.monJeu.getGameStage().getWidth()/2)-50);
				break;
			case 2:
				//Le sucre d'orge pointe sur "Vert"
				this.orge.setY(520);
				this.orge.setX((Jeu.monJeu.getGameStage().getWidth()/2)+290);
				break;
			default:
				//Le sucre d'orge pointe sur "Bleu"
				this.orge.setY(520);
				this.orge.setX((Jeu.monJeu.getGameStage().getWidth()/2)-380);
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
