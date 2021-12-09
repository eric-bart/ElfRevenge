package view;

import java.nio.file.Paths;

import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * Classe de notre vue "INTRO"
 *
 */
public class Intro {
    

    public MediaPlayer mediaPlayer;
    public MediaView mediaView;
    
    /**
     * Créer notre vue "INTRO"
     * @param root Le contenu de notre fenêtre.
     */
    public Intro(Group root) {
        String intro = "src\\intro.mp4";
        Media introurl = new Media(Paths.get(intro).toUri().toString());
        mediaPlayer = new MediaPlayer(introurl);
        mediaView = new MediaView(mediaPlayer);
        root.getChildren().add(mediaView);
        mediaPlayer.play();
        mediaPlayer.setMute(true);
        
    }


}