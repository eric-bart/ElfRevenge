package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

public class Menu {

	private ImageView backgroundMenu = new ImageView(new Image("start-screen.png"));
	private ImageView orgeSelection = new ImageView(new Image("surcre_orge.png"));

	public Menu(Group root) {
		root.getChildren().clear();
		double taille = this.backgroundMenu.getLayoutBounds().getMaxX();
		double marge = (Screen.getPrimary().getBounds().getMaxX()-taille)/2;
		this.backgroundMenu.setX(marge);
		root.getChildren().addAll(this.backgroundMenu, this.orgeSelection);
	}

	public ImageView getBackground() {
		return this.backgroundMenu;
	}

	public ImageView getOrge() {
		return this.orgeSelection;
	}

}
