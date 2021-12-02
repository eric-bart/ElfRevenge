package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import model.GameState;

public class Pause{

	private final String backgroundName="imagePause.png";
	private final String orgeName="surcre_orge.png";
	private Group root;
    private Scene scene;
    private GameState etat;
	private ImageView pauseBackground;
	private ImageView orge;
	
	public Pause(Group root) {
		this.pauseBackground= new ImageView(backgroundName);
		this.orge = new ImageView(orgeName);
		root.getChildren().addAll(this.pauseBackground,this.orge);
		this.pauseBackground.setX((application.Main.fenetre.getWidth()-this.pauseBackground.getLayoutBounds().getMaxX())/2);
		this.pauseBackground.setY((application.Main.fenetre.getHeight()-this.pauseBackground.getLayoutBounds().getMaxY())/2);
		/**this.orge.setX((application.Main.fenetre.getWidth()-this.orge.getLayoutBounds().getMaxX())/2);
		this.orge.setY((application.Main.fenetre.getHeight()-this.orge.getLayoutBounds().getMaxY())/2);**/
	}
	
	public ImageView getPauseBackground() {
		return this.pauseBackground;
	}
	
	public ImageView getOrge() {
		return this.orge;
	}
	
	
}
