package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SelectNiveau {

	private ImageView background = new ImageView(new Image("selecteurNiveau.png"));
	private ImageView orgeSelection = new ImageView(new Image("surcre_orge.png"));

	public SelectNiveau(Group root) {
		root.getChildren().clear();
		root.getChildren().addAll(this.background, this.orgeSelection);
	}

	public ImageView getBackground() {
		return this.background;
	}

	public ImageView getOrge() {
		return this.orgeSelection;
	}
}