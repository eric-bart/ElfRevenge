package view;

import java.nio.file.Paths;

import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * Classe de notre vue "OUTRO"
 *
 */
public class Outro {
    

    public MediaPlayer mediaPlayer;
    public MediaView mediaView;
    
    /**
     * Créer notre vue "Outro"
     * @param root Le contenu de notre fenêtre.
     */
    public Outro(Group root) {
        String intro = "src\\outro.mp4";
        Media introurl = new Media(Paths.get(intro).toUri().toString());
        mediaPlayer = new MediaPlayer(introurl);
        mediaView = new MediaView(mediaPlayer);
        root.getChildren().add(mediaView);
        mediaPlayer.play();
        mediaPlayer.setMute(true);
    }
}