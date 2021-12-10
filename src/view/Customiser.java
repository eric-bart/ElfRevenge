package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe de la vue "CUSTOMISER"
 */
public class Customiser {

	private ImageView background = new ImageView(new Image("backgroundCustomisation.png"));
	private ImageView orgeSelection = new ImageView(new Image("surcre_orge.png"));

	/**
	 * Création de la vue "CUSTOMISER"
	 * @param root le contenu de notre fenêtre
	 */
	public Customiser(Group root) {
		root.getChildren().clear();
		root.getChildren().addAll(this.background, this.orgeSelection);
	}

	/**
	 * Retourne le fond qui compose notre vue "CUSTOMISER"
	 * @return ImageView le fond
	 */
	public ImageView getBackground() {
		return this.background;
	}

	/**
	 * Retourne l'orge. L'orge fait office de sélecteur.
	 * @return ImageView l'orge
	 */
	public ImageView getOrge() {
		return this.orgeSelection;
	}
}