package affichage;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;

public class Menu {
    private String[] options = {"JOUER", "CUSTOMISER", "OPTIONS"};
    private ImageView backgroundMenu;
    private ImageView orgeSelection;
    private int selectedOpt;

    public Menu(String backgroundName, String orgeName) {
        this.backgroundMenu = new ImageView(new Image(backgroundName));
        this.orgeSelection = new ImageView(new Image(orgeName));
        this.selectedOpt = 0;
        orgeAnimation();
    }

    /**
     * Met en place le background et l'orge de sélection
     * @param root Le group sur lequel il faut faire les ajouts
     */
    public void createMenu(Group root) {
        StackPane sp = new StackPane();
        root.getChildren().add(sp);
        sp.getChildren().add(this.backgroundMenu);
        root.getChildren().addAll(this.orgeSelection);
    }

    /**
     * Cette fonction permet de déplacer l'orge en fonction
     * de l'option du menu qui est en cours de sélection
     */
    public void orgeAnimation() {
        int selectedOpt = this.selectedOpt;
        switch(selectedOpt) {
            case 0:
                this.orgeSelection.setY(295);
                this.orgeSelection.setX(495);
                System.out.println("Le sucre doit être en 0");
                break;
            case 1:
                this.orgeSelection.setY(370);
                this.orgeSelection.setX(410);
                System.out.println("Le sucre doit être en 1");
                break;
            case 2:
                this.orgeSelection.setY(450);
                this.orgeSelection.setX(470);
                System.out.println("Le sucre doit être en 2");
                break;
        }
    }

    /**
     * Gère la sélection des options sur le menu
     * @param keyEvent La touche qui a été pressée
     */
    public void menuController(KeyEvent keyEvent) {
        switch(keyEvent.getCode()) {
            case UP:
                if(this.selectedOpt > 0) {
                    this.selectedOpt--;
                    orgeAnimation();
                }
                break;
            case DOWN:
                if(this.selectedOpt < 2) {
                    this.selectedOpt++;
                    orgeAnimation();
                }
                break;
        }
    }

    /**
     * Retourne le tableau contenant les différentes options disponibles sur le menu
     * @return options, le tableau d'options
     */
    public String[] getOptions() {
        return this.options;
    }

    /**
     * Retourne l'itération de l'option correspondant au tableau d'options
     * @return l'itération (0 = JOUER, 1 = CUSTOMISATION, 2 = OPTIONS)
     */
    public int getSelectedOpt() {
        return this.selectedOpt;
    }
}
