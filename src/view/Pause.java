package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import model.GameState;

public class Pause{

	private final String backgroundName="backgroundSelectNiveau.jpg";
	private final String orgeName="surcre_orge.png";
	private Group root;
    private Scene scene;
    private GameState etat;
	private ImageView pauseBackground;
	private ImageView orge;
	
	public Pause(Group root) {
		this.pauseBackground= new ImageView(backgroundName);
		this.orge = new ImageView(orgeName);
		root.getChildren().addAll(this.pauseBackground, this.orge);
		double tailleMargeBackgroundCote = this.pauseBackground.getLayoutBounds().getMaxX();
		double margeBackgroundCote = (Screen.getPrimary().getBounds().getMaxX()-tailleMargeBackgroundCote)/2;
		double tailleMargeBackgroundDessus = this.pauseBackground.getLayoutBounds().getMaxY();
		double margeBackgroundDessus = (Screen.getPrimary().getBounds().getMaxY()-tailleMargeBackgroundDessus)/2;
		double tailleMargeOrgeCote = this.orge.getLayoutBounds().getMaxX();
		double margeOrgeCote = (Screen.getPrimary().getBounds().getMaxX()-tailleMargeOrgeCote)/2;
		double tailleMargeOrgeDessus = this.orge.getLayoutBounds().getMaxY();
		double margeOrgeDessus = (Screen.getPrimary().getBounds().getMaxY()-tailleMargeOrgeDessus)/2;
		this.pauseBackground.setX(margeBackgroundCote);
		this.pauseBackground.setY(margeBackgroundDessus);
		this.orge.setX(margeOrgeCote);
		this.orge.setY(margeOrgeDessus);
	}
	
	
}
