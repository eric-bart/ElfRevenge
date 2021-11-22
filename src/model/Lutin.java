package model;
import java.util.ArrayList;

import javafx.scene.image.Image;import javafx.scene.image.ImageView;

/**
 * Class du Lutin. Un lutin est une image qui va se trouver sur la map.
 */
public class Lutin {

	private boolean deplacementGauche;
	private boolean deplacementDroite;
	private boolean saut;
	private int timerSaut;
	private static double VITESSESAUT = 0.01d;
	private static double VITESSE_DEPLACEMENT = 5;
	private double vitesseY = 0;
	private static double G = 0.02d;
	private ImageView lutin;

	/**
	 * Constructeur du lutin, ce constructeur détient une méthode "super" qui correspond au paramètre de l'extends ImageView.
	 * Ce paramètre est le chemin qui mène vers le skin du lutin.
	 * @param skin
	 * @param coordX
	 * @param coordY
	 */
	public Lutin(ImageView lutin, double coordX, double coordY) {
		this.lutin = lutin;
		lutin.setX(coordX);
		lutin.setY(coordY);
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

	/**
	 * Déplace le personnage sur la map.
	 * @param background La map
	 */
	public void seDeplace() {
		/**if(this.deplacementDroite) {
			if(this.lutin.getX()+VITESSE_DEPLACEMENT>=background.getLayoutBounds().getMaxX()) {
				this.lutin.setX(background.getLayoutBounds().getMaxX());
			} else {
				this.lutin.setX(this.lutin.getX() + VITESSE_DEPLACEMENT);
			}
		}
		if(this.deplacementGauche) {
			if(this.lutin.getX()-VITESSE_DEPLACEMENT<=background.getLayoutBounds().getMinX()) {
				this.lutin.setX(background.getLayoutBounds().getMinX());
			} else {
				this.lutin.setX(this.lutin.getX() - VITESSE_DEPLACEMENT);
			}
		}**/
		if(this.deplacementDroite) {
				this.lutin.setX(this.lutin.getX() + VITESSE_DEPLACEMENT);
		}
		if(this.deplacementGauche) {
				this.lutin.setX(this.lutin.getX() - VITESSE_DEPLACEMENT);
		}
	}
	
	
	public boolean isDansLeCiel(ImageView block) {
		return this.lutin.getLayoutBounds().getMaxY()<block.getLayoutBounds().getMinY() 
				||  this.lutin.getLayoutBounds().getMaxY()>block.getLayoutBounds().getMaxY();
	}
	
	public boolean isSurSol(ImageView block) {
		return this.lutin.getLayoutBounds().getMaxY()<=block.getLayoutBounds().getMaxY() 
				&& this.lutin.getLayoutBounds().getMaxY()>=block.getLayoutBounds().getMinY()
				&& this.lutin.getLayoutBounds().getMinX()>=block.getLayoutBounds().getMinX()
				&& this.lutin.getLayoutBounds().getMinX()<=block.getLayoutBounds().getMaxX();
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

	public ImageView getLutin() {
		return this.lutin;
	}
}
