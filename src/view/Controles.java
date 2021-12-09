package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe de la vue "CONTROLES"
 *
 */
public class Controles {

	private ImageView background = new ImageView(new Image("fondControles.png"));

	/**
	 * Cr�ation de la vue controles.
	 * @param root le contenu de notre fen�tre.
	 */
	public Controles(Group root) {
		root.getChildren().clear();
		root.getChildren().addAll(this.background);
	}

	/**
	 * Retourne le fond qui compose notre vue.
	 * @return ImageView le fond
	 */
	public ImageView getBackground() {
		return this.background;
	}

}