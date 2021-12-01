package model;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import view.Niveau;
import view.Niveau1;

public class BonhommeDeNeige extends Personnage {

	private boolean deplacementGauche;
	private boolean deplacementDroite;
	private boolean colisionDroite;
	private boolean colisionGauche;
	private boolean saut;
	private int timerSaut;
	private static double VITESSESAUT = 0.01d;
	private static double VITESSE_DEPLACEMENT =2;
	private double vitesseY = 0;
	private static double G = 0.02d;
	private ImageView bonhommeNeige;
	private double coordMapMaxX;
	private double coordMapCentreX;
	private double coordMapMinX;
	private Niveau niveau;

	private boolean colisionADroite=false;
	private boolean colisionAGauche=false;
	private boolean doitReculer=false;


	public BonhommeDeNeige(ImageView bonhommeNeige, double coordScreenX, double coordScreenY, Niveau niveau) {
		super(bonhommeNeige, coordScreenX, coordScreenY, niveau);
		this.niveau = niveau;
		this.bonhommeNeige= bonhommeNeige;
		this.bonhommeNeige.setX(coordScreenX);
		this.bonhommeNeige.setY(coordScreenY);
		this.coordMapMaxX = coordScreenX+this.bonhommeNeige.getLayoutBounds().getWidth();
		this.coordMapCentreX = coordScreenX+(this.bonhommeNeige.getLayoutBounds().getWidth()/2);
		this.coordMapMinX = coordScreenX;
		this.deplacementDroite=false;
		this.deplacementGauche=false;
		this.colisionDroite=false;
		this.colisionGauche=false;
		this.saut=false;
	}

	private ImageView getBonhommeNeige() {
		return this.bonhommeNeige;
	}

	/**
	 * Déplace le personnage sur le niveau
	 * @param niveau Le niveau concerné
	 */
	@Override
	public void seDeplace() {
		if(!isDansLeCiel()) {
			if(this.blockDurDirectDroiteLutin()==null && !this.colisionADroite) {
				this.getBonhommeNeige().setX(this.getBonhommeNeige().getX()+getVITESSE_DEPLACEMENT());
				this.setCoordMapMaxX(this.getCoordMapMaxX() + getVITESSE_DEPLACEMENT());
				this.setCoordMapCentreX(this.getCoordMapCentreX() + getVITESSE_DEPLACEMENT());
				this.setCoordMapMinX(this.getCoordMapMinX() + getVITESSE_DEPLACEMENT());
			} else if(this.blockDurDirectGaucheLutin()==null){
				this.colisionADroite = true;
				this.getBonhommeNeige().setX(this.getBonhommeNeige().getX()-getVITESSE_DEPLACEMENT());
				this.setCoordMapMaxX(this.getCoordMapMaxX() - getVITESSE_DEPLACEMENT());
				this.setCoordMapCentreX(this.getCoordMapCentreX() - getVITESSE_DEPLACEMENT());
				this.setCoordMapMinX(this.getCoordMapMinX() - getVITESSE_DEPLACEMENT());
			} else {
				this.colisionAGauche=true;
				this.colisionADroite=false;
			}
		}

	}
	
	public boolean isMort(Lutin lutin) {
		double maxXLutin = lutin.getPersonnage().getLayoutBounds().getMaxX();
		double minXLutin = lutin.getPersonnage().getLayoutBounds().getMinX();
		double maxYLutin = lutin.getPersonnage().getLayoutBounds().getMaxY();
		double minYLutin = lutin.getPersonnage().getLayoutBounds().getMinY();
		if(maxYLutin>=this.getPersonnage().getLayoutBounds().getMinY() && maxYLutin<=this.getPersonnage().getLayoutBounds().getMinY()+10
				&& (minXLutin>this.getPersonnage().getLayoutBounds().getMinX() && minXLutin<this.getPersonnage().getLayoutBounds().getMaxX() ||
				maxXLutin<this.getPersonnage().getLayoutBounds().getMaxX() && maxXLutin>this.getPersonnage().getLayoutBounds().getMinX())) {
			return true;
		}
		return false;
	}

	public boolean isDansFenetre() {
		if(this.getBonhommeNeige().getLayoutBounds().getMinX()<0-this.getBonhommeNeige().getLayoutBounds().getWidth() || this.getBonhommeNeige().getLayoutBounds().getMinX()>1280+this.getBonhommeNeige().getLayoutBounds().getWidth()) {
			if(!niveau.getMobAffiche().contains(this.getBonhommeNeige())) {
				niveau.addMobAffiche(this);
			}
			return false;
		}
		return true;
	}

	public double getVitesse() {
		return getVITESSE_DEPLACEMENT();
	}

	@Override
	public void tombe() {
		this.bonhommeNeige.setY(this.bonhommeNeige.getY() + this.vitesseY);
		this.vitesseY=this.vitesseY + G;
	}

	@Override
	public void sauter() {
		if (!this.isDansLeCiel()) {
			this.bonhommeNeige.setY(this.bonhommeNeige.getY()-150d);
			this.setVitesseY(1.6d);
		}
	}

	public void setVitesseY(double vitesse) {
		this.vitesseY = vitesse;
	}
	@Override
	public boolean isMort() {
		return this.bonhommeNeige.getY()>=760;
	}

	public static double getVITESSE_DEPLACEMENT() {
		return VITESSE_DEPLACEMENT;
	}

	public static void setVITESSE_DEPLACEMENT(double vITESSE_DEPLACEMENT) {
		VITESSE_DEPLACEMENT = vITESSE_DEPLACEMENT;
	}
}
