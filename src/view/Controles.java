package view;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controles {

	private ImageView background = new ImageView(new Image("fondControles.png"));

	public Controles(Group root) {
		root.getChildren().clear();
		root.getChildren().addAll(this.background);
	}

	public ImageView getBackground() {
		return this.background;
	}

}