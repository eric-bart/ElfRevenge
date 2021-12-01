package view;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Customiser {

	private ImageView background = new ImageView(new Image("backgroundCustomisation.png"));
	private ImageView orgeSelection = new ImageView(new Image("surcre_orge.png"));

	public Customiser(Group root) {
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