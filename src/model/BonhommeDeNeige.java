package model;

import javafx.scene.image.ImageView;
import view.Niveau;

public class BonhommeDeNeige extends Personnage {

	private static double VITESSE_DEPLACEMENT =2;
	private double vitesseY = 0;
	private static double G = 0.02d;
	private ImageView imageBonhommeNeige;
	private boolean colisionADroite;
	private boolean colisionAGauche;

	public BonhommeDeNeige(ImageView imagePersonnage, double coordScreenX, double coordScreenY, Niveau niveau) {
		super(imagePersonnage, coordScreenX, coordScreenY, niveau);
		this.imageBonhommeNeige= imagePersonnage;
		this.imageBonhommeNeige.setX(coordScreenX);
		this.imageBonhommeNeige.setY(coordScreenY);
		this.colisionADroite=false;
		this.colisionAGauche=false;
	}

	/**
	 * Fonction faisant tomber le bonhomme de neige.
	 */
	@Override
	public void tombe() {
		this.imageBonhommeNeige.setY(this.imageBonhommeNeige.getY() + this.vitesseY);
		this.vitesseY=this.vitesseY + G;
	}

	/**
	 * Fonction faisant sauter le bonhomme de neige.
	 */
	@Override
	public void sauter() {
		double saut = 150d;
		if (!this.isDansLeCiel()) {
			this.imageBonhommeNeige.setY(this.imageBonhommeNeige.getY()-saut);
			this.setVitesseY(1.6d);
		}
	}
	
	/**
	 * Retourne un booleen selon si le le personnage est mort ou pas.
	 * @return boolean true=Le bonhomme de neige est mort | false=Le bonhomme de neige n'est pas mort
	 */
	@Override
	public boolean isMort() {
		return this.imageBonhommeNeige.getY()>=760;
	}
	
	/**
	 * Déplace le personnage sur le niveau
	 * @param niveau Le niveau concerné
	 */
	@Override
	public void seDeplace() {
		if(!isDansLeCiel()) {
			if(this.blockDurDroite()==null && !this.colisionADroite) {
				this.getImage().setX(this.getImage().getX()+getVitesseDeplacement());
			} else if(this.blockDurGauche()==null){
				this.colisionADroite = true;
				this.getImage().setX(this.getImage().getX()-getVitesseDeplacement());
			} else {
				this.colisionAGauche=true;
				this.colisionADroite=false;
			}
		}

	}
	
	/**
	 * Retourne un boolean informant d'un contact par le dessus du bonhomme de neige. 
	 * Cet impact signifie que le bonhomme de neige a été tué.
	 * @param lutin Le lutin sur le niveau
	 * @return boolean true=Le bonhomme de neige est mort | false=Le bonhomme de neige n'est pas mort
	 */
	public boolean isMortColision(Lutin lutin) {
		double maxXLutin = lutin.getImage().getLayoutBounds().getMaxX();
		double minXLutin = lutin.getImage().getLayoutBounds().getMinX();
		double maxYLutin = lutin.getImage().getLayoutBounds().getMaxY();
		if(maxYLutin>=this.getImage().getLayoutBounds().getMinY() && maxYLutin<=this.getImage().getLayoutBounds().getMinY()+10
				&& (minXLutin>this.getImage().getLayoutBounds().getMinX() && minXLutin<this.getImage().getLayoutBounds().getMaxX() ||
				maxXLutin<this.getImage().getLayoutBounds().getMaxX() && maxXLutin>this.getImage().getLayoutBounds().getMinX())) {
			return true;
		}
		return false;
	}

	/**
	 * Fonction retournant la vitesse de déplacement du bonhomme de neige
	 * @return double représentant la vitesse de déplacement du bonhomme de neige
	 */
	public double getVitesseDeplacement() {
		return VITESSE_DEPLACEMENT;
	}
	
	/**
	 * Fonction retournant la vitesse sur l'axe Y du bonhomme de neige
	 * @param vitesse double étant la valeur de la vitesse
	 */
	public void setVitesseY(double vitesse) {
		this.vitesseY = vitesse;
	}
	
	/**
	 * Fonction permettant d'actualiser la valeur de vitesse de déplacement du bonhomme de neige
	 * @param vitesseDeplacement le facteur de vitesse du bonhomme de neige
	 */
	public static void setVitesseDeplacement(double vitesseDeplacement) {
		VITESSE_DEPLACEMENT = vitesseDeplacement;
	}
}
