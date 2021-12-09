package view;

import application.Jeu;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Pause{

	private final String backgroundName="imagePause.png";
	private final String orgeName="surcre_orge.png";
	private ImageView pauseBackground;
	private ImageView orge;
	
	public Pause(Group root) {
		this.pauseBackground= new ImageView(backgroundName);
		this.orge = new ImageView(orgeName);
		root.getChildren().addAll(this.pauseBackground,this.orge);
		this.pauseBackground.setX((Jeu.monJeu.getGameStage().getWidth()-this.pauseBackground.getLayoutBounds().getMaxX())/2);
		this.pauseBackground.setY((Jeu.monJeu.getGameStage().getWidth()-this.pauseBackground.getLayoutBounds().getMaxY())/2);
	}
	
	public ImageView getPauseBackground() {
		return this.pauseBackground;
	}
	
	public ImageView getOrge() {
		return this.orge;
	}
	
	
}
