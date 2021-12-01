package view;

import java.nio.file.Paths;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import model.GameState;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Intro {
	

	public MediaPlayer mediaPlayer;
	public MediaView mediaView;
    private GameState etat;
	
	public Intro(Group root) {
		String intro = "src\\intro.mp4";
		Media introurl = new Media(Paths.get(intro).toUri().toString());
		mediaPlayer = new MediaPlayer(introurl);
		mediaView = new MediaView(mediaPlayer);
		

		root.getChildren().add(mediaView);
	        
	        
		mediaPlayer.play();
		
		System.out.println(introurl);
		
	}


}
