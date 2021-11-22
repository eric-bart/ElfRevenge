package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Menu {

	private ImageView backgroundMenu = new ImageView(new Image("start-screen.png"));
	private ImageView orgeSelection = new ImageView(new Image("surcre_orge.png"));

	public Menu(Group root) {
		root.getChildren().clear();
		StackPane sp = new StackPane();
		root.getChildren().add(sp);
		sp.getChildren().add(this.backgroundMenu);
		root.getChildren().addAll(this.orgeSelection);
	}

	public ImageView getBackground() {
		return this.backgroundMenu;
	}

	public ImageView getOrge() {
		return this.orgeSelection;
	}

}
