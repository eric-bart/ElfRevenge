package application;



import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import personnages.Elf;
import affichage.Menu;
import state.GameState;
import javafx.scene.paint.Paint;

public class Main extends Application {

    public Group root;
    public Elf personnageElf;
    public Scene scene;
    public ImageView background;
    public GameState etat;
    public Stage fenetre;


    /**
     * Initialise le lancement du jeu.
     */
    public void init() throws Exception {
        etat=GameState.MENU;
        root = new Group();
        //On met en place le fond de la fenêtre avec l'image background
        scene = new Scene(root, Screen.getPrimary().getBounds().getMaxX(), 720);
    }

    /**
     * Cette fonction est appelée lorsque l'utilisateur a selectionné un niveau
     * C'est ici que l'on lance la map qui a été sélectionnée
     */
    public void niveau() {
        //Gérer l'ajout de la map et du lutin ici

        System.out.println("On lance le niveau");
       background = new ImageView(new Image("background2.png"));
       Elf personnageElf = new Elf("lutin4.png", 50, 0);
        root.getChildren().addAll(background, personnageElf);
        /**background = new ImageView(new Image("background2.png"));
        //On créer le lutin et on l'ajoute à la fenêtre.
        personnageElf = new Elf("lutin4.png", 50, 0);
        //On ajoute nos elements background et le lutin à la fenêtre
        root.getChildren().addAll(background, personnageElf);**/


        try {
            AnimationTimer boucle = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    //Gravité de l'elf.
                    if (personnageElf.getY() < background.getLayoutBounds().getMaxY() * 0.7d) {
                        if (!personnageElf.isSaut()) {
                            personnageElf.setY(personnageElf.getY() + personnageElf.getVitesseY());
                            personnageElf.setVitesseY(personnageElf.getVitesseY() + personnageElf.getG());
                        }
                    }
                    if (personnageElf.getY() > background.getLayoutBounds().getMaxY() * 0.7d) {
                        personnageElf.setY(background.getLayoutBounds().getMaxY() * 0.7d);
                    }

                    //Deplacement de l'elf
                    if (personnageElf.isDeplacementDroite()) {
                        personnageElf.seDeplace(background);
                    }
                    if (personnageElf.isDeplacementGauche()) {
                        personnageElf.seDeplace(background);
                    }

                    /**if(personnageElf.isSaut()) {
                     for(int i=personnageElf.getTimerSaut();i>0;i--) {
                     personnageElf.setY(personnageElf.getY() - personnageElf.getVitesseY());
                     }
                     personnageElf.setSaut(false);
                     }**/
                }
            };
            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case LEFT:
                        personnageElf.setDeplacementGauche(true);
                        break;
                    case RIGHT:
                        personnageElf.setDeplacementDroite(true);
                        break;
                }
            });
            scene.setOnKeyReleased(e -> {
                switch (e.getCode()) {
                    case LEFT:
                        personnageElf.setDeplacementGauche(false);
                        break;
                    case RIGHT:
                        personnageElf.setDeplacementDroite(false);
                        break;
                    /**case SPACE:
                     if(!personnageElf.isSaut()) {
                     personnageElf.setSaut(true);
                     personnageElf.setTimerSaut(25);
                     personnageElf.setVitesseY(personnageElf.getVitesseY()-personnageElf.getVITESSESAUT());
                     } else {
                     System.out.println("Il est déjà entrain de sauter la!!!!!!!");
                     }**/
                }
            });
            boucle.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cette fonction se lance après la fonction init. Elle met en place les paramètres de notre fenêtre
     * et lance l'affichage de la fenêtre.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        changeGameState();
        scene.setFill(Color.web("9bbeff"));
        primaryStage.setTitle("ElfsRevenge");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Cette fonction permet de gérer le changement d'état du jeu
     */
    public void changeGameState() {
        switch(etat) {
            case MENU:
                //Si le jeu est à l'état MENU, on lance le menu
                menu();
                break;
            case PAUSE:
                System.out.println("Jeu en pause");
                //pause();
                break;
            case PARAMETRES:
                System.out.println("Sur les paramètres");
                //parametres();
                break;
            case SELECT_NIVEAU:
                System.out.println("Sur la selection de niveaux");
                //selectionNiveau();
                break;
            case NIVEAU:
                System.out.println("Nous sommes sur un niveau de jeu");
                //niveau();
                break;
        }
    }

    /**
     * Cette fonction permet d'afficher le menu de lancement du jeu
     */
    public void menu() {
        root.getChildren().clear();
        Menu menu = new Menu("start-screen.png", "surcre_orge.png");
        menu.createMenu(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER) {
                    keyEvent.consume();
                    switch(menu.getOptions()[menu.getSelectedOpt()]) {
                        case "JOUER":
                            selectionNiveau();
                            break;
                        case "CUSTOMISER":
                            customiserPersonnage();
                            break;
                        case "OPTIONS":
                            optionsJeu();
                            break;
                    }
                    return;
                }
                menu.menuController(keyEvent);

            }
        });
    }

    /**
     * Cette fonction permet d'afficher la fenêtre de selection d'un niveau
     */
    public void selectionNiveau() {
        System.out.println("Ouvrir la fenêtre de selection d'un niveau");
        //ImageView backgroundSelectNiveau = new ImageView("backgroundSelectNiveau.jpg");
        root.getChildren().clear();
        niveau();
    }

    /**
     * Cette fonction permet d'afficher la fenêtre de customisation de notre personnage
     */
    public void customiserPersonnage() {
        System.out.println("Ouvrir la fenêtre de customisation de notre personnage");
        ImageView backgroundSelectNiveau = new ImageView("backgroundSelectNiveau.jpg");
        root.getChildren().clear();
        root.getChildren().add(backgroundSelectNiveau);
        root.setOnKeyPressed(e-> {
            switch(e.getCode()) {
                case SPACE:
                    menu();
                    break;
            }
        });
    }

    /**
     * Cette fonction permet d'afficher la fenêtre d'options du jeu
     */
    public void optionsJeu() {
        System.out.println("Ouvrir la fenêtre d'options du jeu'");
        root.getChildren().clear();
        root.setOnKeyPressed(e-> {
            switch(e.getCode()) {
                case ESCAPE:
                    menu();
                    break;
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}