package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe de la vue "SELECTION NIVEAU"
 */
public class SelectNiveau {

	private ImageView background = new ImageView(new Image("selecteurNiveau.png"));
	private ImageView orgeSelection = new ImageView(new Image("surcre_orge.png"));

	public SelectNiveau(Group root) {
		root.getChildren().clear();
		root.getChildren().addAll(this.background, this.orgeSelection);
	}

	/**
	 * Retourne le background "SELECT NIVEAU"
	 * @return ImageView background
	 */
	public ImageView getBackground() {
		return this.background;
	}

	/**
	 * Retourne l'image de l'orge notre vue "SELECT NIVEAU"
	 * @return ImageView orge
	 */
	public ImageView getOrge() {
		return this.orgeSelection;
	}
}