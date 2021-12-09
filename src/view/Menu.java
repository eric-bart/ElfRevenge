package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe de notre vue "MENU"
 *
 */
public class Menu {

	private ImageView backgroundMenu = new ImageView(new Image("start-screen.png"));
	private ImageView orgeSelection = new ImageView(new Image("surcre_orge.png"));

	/**
	 * Création de notre vue "MENU"
	 * @param root le contenu de notre fenêtre
	 */
	public Menu(Group root) {
		root.getChildren().clear();
		root.getChildren().addAll(this.backgroundMenu, this.orgeSelection);
	}

	/**
	 * Retourne l'image qui compose le fond de notre vue "MENU"
	 * @return ImageView notre background
	 */
	public ImageView getBackground() {
		return this.backgroundMenu;
	}
	
	
	/**
	 * Retourne l'orge qui fait office de sélecteur sur notre Menu.
	 * @return ImageView l'orge
	 */
	public ImageView getOrge() {
		return this.orgeSelection;
	}

}
