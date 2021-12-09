package model;

import javafx.scene.image.ImageView;
import view.Niveau;

public class PereNoel extends Personnage {

	private static double VITESSE_DEPLACEMENT =4;
	private double vitesseY = 0;
	private static double G = 0.02d;
	private ImageView imagePereNoel;
	private boolean colisionADroite;
	private boolean colisionAGauche;


	public PereNoel(ImageView imagePersonnage, double coordScreenX, double coordScreenY, Niveau niveau) {
		super(imagePersonnage, coordScreenX, coordScreenY, niveau);
		this.imagePereNoel= imagePersonnage;
		this.imagePereNoel.setX(coordScreenX);
		this.imagePereNoel.setY(coordScreenY);
		this.colisionADroite=false;
		this.colisionAGauche=false;
	}

	@Override
	public void tombe() {
		this.imagePereNoel.setY(this.imagePereNoel.getY() + this.vitesseY);
		this.vitesseY=this.vitesseY + G;
	}

	@Override
	public void sauter() {
		if (!this.isDansLeCiel()) {
			this.imagePereNoel.setY(this.imagePereNoel.getY()-150d);
			this.setVitesseY(1.6d);
		}
	}

	@Override
	public boolean isMort() {
		return this.imagePereNoel.getY()>=760;
	}
	
	/**
	 * Déplace le personnage sur le niveau
	 * @param niveau Le niveau concerné
	 */
	@Override
	public void seDeplace() {
		if(!isDansLeCiel()) {
			if(this.blockDurDroite()==null && !this.colisionADroite) {
				this.imagePereNoel.setX(this.imagePereNoel.getX()+getVitesseDeplacement());
			} else if(this.blockDurGauche()==null){
				this.colisionADroite = true;
				this.imagePereNoel.setX(this.imagePereNoel.getX()-getVitesseDeplacement());
			} else {
				this.colisionAGauche=true;
				this.colisionADroite=false;
			}
		}
	}
	
	public boolean isMort(Lutin lutin) {
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

	public double getVitesse() {
		return VITESSE_DEPLACEMENT;
	}
	
	public void setVitesseY(double vitesse) {
		this.vitesseY = vitesse;
	}
	

	public static double getVitesseDeplacement() {
		return VITESSE_DEPLACEMENT;
	}

	public static void setVitesseDeplacement(double vitesseDeplacement) {
		VITESSE_DEPLACEMENT = vitesseDeplacement;
	}
}