package personnages;
import javafx.scene.image.Image;import javafx.scene.image.ImageView;

/**
 * Class du Lutin. Un lutin est une image qui va se trouver sur la map.
 */
public class Elf extends ImageView {
    private String skin;
    private boolean deplacementGauche;
    private boolean deplacementDroite;
    private boolean saut;
    private int timerSaut;
    private static double VITESSESAUT = 0.01d;
    private static double VITESSE_DEPLACEMENT = 5;
    private double vitesseY = 0;
    private static double G = 0.02d;

    /**
     * Constructeur du lutin, ce constructeur détient une méthode "super" qui correspond au paramètre de l'extends ImageView.
     * Ce paramètre est le chemin qui mène vers le skin du lutin.
     * @param skin
     * @param coordX
     * @param coordY
     */
    public Elf(String skin, double coordX, double coordY) {
        super(new Image(skin));
        this.skin=skin;
        this.setX(coordX);
        this.setY(coordY);
        this.deplacementDroite=false;
        this.deplacementGauche=false;
        this.saut=false;
    }

    public double getVITESSESAUT() {
        return VITESSESAUT;
    }

    public int getTimerSaut() {
        return this.timerSaut;
    }

    public void setTimerSaut(int timerSaut) {
        this.timerSaut = timerSaut;
    }

    public void setSaut(boolean saut) {
        this.saut = saut;
    }

    public boolean isSaut() {
        return this.saut;
    }

    public double getG() {
        return G;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public double getVitesseY() {
        return this.vitesseY;
    }

    /**public void saute() {
        this.setY
    }**/

    /**
     * Déplace le personnage sur la map.
     * @param background La map
     */
    public void seDeplace(ImageView background) {
        if(this.deplacementDroite) {
            if(this.getX()+VITESSE_DEPLACEMENT>=background.getLayoutBounds().getMaxX()) {
                this.setX(background.getLayoutBounds().getMaxX());
            } else {
                this.setX(this.getX() + VITESSE_DEPLACEMENT);
            }
        }
        if(this.deplacementGauche) {
            if(this.getX()-VITESSE_DEPLACEMENT<=background.getLayoutBounds().getMinX()) {
                this.setX(background.getLayoutBounds().getMinX());
            } else {
                this.setX(this.getX() - VITESSE_DEPLACEMENT);
            }
        }
    }

    /**
     * Retourne la vitesse de déplacement du lutin
     * @return
     */
    public double getVitesseDeplacement() {
        return VITESSE_DEPLACEMENT;
    }

    /**
     * Permet de changer la variable deplacementGauche
     * @param deplacementGauche
     */
    public void setDeplacementGauche(boolean deplacementGauche) {
        this.deplacementGauche = deplacementGauche;
    }

    /**
     * Permet de changer la variable deplacementDroite
     * @param deplacementDroite
     */
    public void setDeplacementDroite(boolean deplacementDroite) {
        this.deplacementDroite = deplacementDroite;
    }

    /**
     * Retourne un booleen qui informe si le lutin se déplace vers la gauche ou pas
     * @return true si il se déplace vers la gauche, sinon false
     */
    public boolean isDeplacementGauche() {
        return this.deplacementGauche;
    }

    /**
     * Retourne un booleen qui informe si le lutin se déplace vers la droite ou pas
     * @return true si il se déplace vers la droite, sinon false
     */
    public boolean isDeplacementDroite() {
        return this.deplacementDroite;
    }

    /**
     * Retourne le chemin qui mène vers l'image du skin du Lutin.
     * @return Chemin
     */
    public String getSkin() {
        return this.skin;
    }
}
