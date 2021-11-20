package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Niveau1 {

	private ImageView background = new ImageView(new Image("background2.png"));
	private ImageView elf = new ImageView(new Image("lutin4.png"));

	public Niveau1(Group root) {
		root.getChildren().clear();
		root.getChildren().addAll(this.background, this.elf);
	}

	public ImageView getBackground() {
		return this.background;
	}
	
	public ImageView getElf() {
		return this.elf;
	}
}
