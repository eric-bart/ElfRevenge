package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import model.GameState;
import view.Pause;

public class PauseController {

	private final String imageName="pauseBackground.png";
	private Group root;
    private Scene scene;
    private GameState etat;
	private ImageView pauseBackground;
	
	public PauseController(Group root, Scene scene) {
		Pause pause = new Pause(root);
	}
	
	
}
