package model;

import javafx.scene.image.ImageView;
import view.Niveau1;

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
	 * Construit un lutin d�tenant une certaine coordonn�e et un ImageView qui correspond � sa repr�sentation sur la vue.
	 * @param lutin
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

	/**
	 * Retourne la vitesse de saut du lutin
	 * @return double la vitesse de saut du lutin
	 */
	public double getVITESSESAUT() {
		return VITESSESAUT;
	}
	
	
	/**
	 * Retourne la valeur du timer qui permet de g�rer le saut du lutin
	 * @return int la valeur du timer qui permet de g�rer le saut du lutin
	 */
	public int getTimerSaut() {
		return this.timerSaut;
	}
	
	/**
	 * Permet de mettre � jour la valeur du timer de saut du lutin
	 * @param timerSaut
	 */
	public void setTimerSaut(int timerSaut) {
		this.timerSaut = timerSaut;
	}

	
	/**
	 * Permet de mettre � jour si le lutin est entrain de sauter ou non
	 * @param saut boolean true=Le lutin est entrain de sauter ; false=Le lutin n'est pas entrain de sauter
	 */
	public void setSaut(boolean saut) {
		this.saut = saut;
	}

	/**
	 * Retourne si le lutin est entrain de sauter ou non
	 * @return boolean true=Le lutin est entrain de sauter ; false=Le lutin n'est pas entrain de sauter
	 */
	public boolean isSaut() {
		return this.saut;
	}

	/**
	 * Permet de r�cup�rer la valeur de gravit� du lutin
	 * @return double la valeur de gravit� du lutin
	 */
	public double getG() {
		return G;
	}

	/**
	 * Permet de mettre � jour la vitesse en Y du lutin
	 * @param vitesseY
	 */
	public void setVitesseY(double vitesseY) {
		this.vitesseY = vitesseY;
	}
	
	/**
	 * Permet de r�cup�rer la vitesse du lutin en Y (haut/bas)
	 * @return double la vitesse en Y du lutin
	 */
	public double getVitesseY() {
		return this.vitesseY;
	}

	/**
	 * D�place le personnage sur le niveau
	 * @param niveau Le niveau concern�
	 */
	public void seDeplace(Niveau1 niveau) {
		//Si le personnage se d�place � droite alors on recup�re la position maximum de la map � droite et on regarde s'il peut avancer.
		if(this.deplacementDroite) {
			if(this.lutin.getX()+VITESSE_DEPLACEMENT>=niveau.getGeneration().get(niveau.getGeneration().size()-1).getLayoutBounds().getMaxX()) {
				this.lutin.setX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getLayoutBounds().getMinX());
			} else {
				this.lutin.setX(this.lutin.getX() + VITESSE_DEPLACEMENT);
			}
		}
		//Si le personnage se d�place � gauche alors on recup�re la position maximum de la map � gauche et on regarde s'il peut avancer.
		if(this.deplacementGauche) {
			if(this.lutin.getX()+VITESSE_DEPLACEMENT<=niveau.getGeneration().get(0).getLayoutBounds().getMinX()) {
				this.lutin.setX(niveau.getGeneration().get(0).getLayoutBounds().getMinX());
			} else {
				this.lutin.setX(this.lutin.getX() - VITESSE_DEPLACEMENT);
			}
		}
	}
	
	
	/**
	 * V�rifie si le personnage est dans le vide ou pas.
	 * Vide = Ne pas �tre sur un block
	 * @param block Le block sur lequel le lutin se trouve en X
	 * @return boolean true=Le lutin est dans le vide ; false=Le lutin n'est pas dans le vide
	 */
	public boolean isDansLeCiel(ImageView block) {
		return this.lutin.getLayoutBounds().getMaxY()<block.getLayoutBounds().getMinY() 
				||  this.lutin.getLayoutBounds().getMaxY()>block.getLayoutBounds().getMaxY()
				&& (!(this.lutin.getLayoutBounds().getMinY()>block.getLayoutBounds().getMaxY())
				|| this.lutin.getLayoutBounds().getMinY()<block.getLayoutBounds().getMinX());
	}

	/**
	 * Retourne la vitesse de d�placement du lutin
	 * @return double la vitesse de d�placement du lutin
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
	 * Retourne un booleen qui informe si le lutin se d�place vers la gauche ou pas
	 * @return boolean true=Le lutin se d�place vers la gauche ; false=Le lutin ne se d�place pas vers la gauche
	 */
	public boolean isDeplacementGauche() {
		return this.deplacementGauche;
	}

	/**
	 * Retourne un booleen qui informe si le lutin se d�place vers la gauche ou pas
	 * @return boolean true=Le lutin se d�place vers la droite ; false=Le lutin ne se d�place pas vers la droite
	 */
	public boolean isDeplacementDroite() {
		return this.deplacementDroite;
	}

	/**
	 * Retourne l'image du lutin sur la vue
	 * @return ImageView l'image du lutin
	 */
	public ImageView getLutin() {
		return this.lutin;
	}
}
