package com.rtai.elfsrevenge.elfsrevenge;

import com.rtai.elfsrevenge.elfsrevenge.personnages.Elf;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public Group root;
    public Elf personnageElf;
    public Scene scene;
    public ImageView background;

    /**
     * Initialise la map et les objets sur la map
     */
    public void init() {
        root = new Group();
        //On met en place le fond de la fenêtre avec l'image background
        scene = new Scene(root, Screen.getPrimary().getBounds().getMaxX(), 720);
        background = new ImageView(new Image("background2.png"));
        //On créer le lutin et on l'ajoute à la fenêtre.
        personnageElf = new Elf("lutin4.png", 50, 0);
        //On ajoute nos elements background et le lutin à la fenêtre
        root.getChildren().addAll(background, personnageElf);
    }

    /**
     * Gère le comportement dans le jeu
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            AnimationTimer boucle = new AnimationTimer() {
                @Override
                public void handle(long l) {
                   //Gravité de l'elf.
                        if(personnageElf.getY()<background.getLayoutBounds().getMaxY()*0.7d) {
                            if(!personnageElf.isSaut()) {
                                personnageElf.setY(personnageElf.getY() + personnageElf.getVitesseY());
                                personnageElf.setVitesseY(personnageElf.getVitesseY() + personnageElf.getG());
                            }
                        }

                        if(personnageElf.getY()>background.getLayoutBounds().getMaxY()*0.7d) {
                            personnageElf.setY(background.getLayoutBounds().getMaxY()*0.7d);
                        }

                    //Deplacement de l'elf
                    if(personnageElf.isDeplacementDroite()) {
                        personnageElf.seDeplace(background);
                    }
                    if(personnageElf.isDeplacementGauche()) {
                        personnageElf.seDeplace(background);
                    }

                    if(personnageElf.isSaut()) {
                        for(int i=personnageElf.getTimerSaut();i>0;i--) {
                            personnageElf.setY(personnageElf.getY() - personnageElf.getVitesseY());
                        }
                        personnageElf.setSaut(false);
                    }
                }
            };
            scene.setOnKeyPressed(e-> {
                switch(e.getCode()) {
                    case LEFT:
                        personnageElf.setDeplacementGauche(true);
                        break;
                    case RIGHT:
                        personnageElf.setDeplacementDroite(true);
                        break;
                }
            });
            scene.setOnKeyReleased(e-> {
                switch(e.getCode()) {
                    case LEFT:
                        personnageElf.setDeplacementGauche(false);
                        break;
                    case RIGHT:
                        personnageElf.setDeplacementDroite(false);
                        break;
                    case SPACE:
                            if(!personnageElf.isSaut()) {
                                personnageElf.setSaut(true);
                                personnageElf.setTimerSaut(25);
                                personnageElf.setVitesseY(personnageElf.getVitesseY()-personnageElf.getVITESSESAUT());
                            } else {
                                System.out.println("Il est déjà entrain de sauter la!!!!!!!");
                            }
                }
            });
            boucle.start();

            //On configure les détails de la fenêtre (taille, redimensionnable...)
            primaryStage.setTitle("ElfsRevenge");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}