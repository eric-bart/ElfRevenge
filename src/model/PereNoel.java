package model;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import view.Niveau;
import view.Niveau3;

public class PereNoel extends Personnage {

	private boolean deplacementGauche;
	private boolean deplacementDroite;
	private boolean colisionDroite;
	private boolean colisionGauche;
	private boolean saut;
	private int timerSaut;
	private static double VITESSESAUT = 0.01d;
	private static double VITESSE_DEPLACEMENT =4;
	private double vitesseY = 0;
	private static double G = 0.02d;
	private ImageView perenoel;
	private double coordMapMaxX;
	private double coordMapCentreX;
	private double coordMapMinX;
	private Niveau niveau;

	private boolean colisionADroite=false;
	private boolean colisionAGauche=false;
	private boolean doitReculer=false;


	public PereNoel(ImageView perenoel, double coordScreenX, double coordScreenY, Niveau niveau) {
		super(perenoel, coordScreenX, coordScreenY, niveau);
		this.niveau = niveau;
		this.perenoel= perenoel;
		this.perenoel.setX(coordScreenX);
		this.perenoel.setY(coordScreenY);
		this.coordMapMaxX = coordScreenX+this.perenoel.getLayoutBounds().getWidth();
		this.coordMapCentreX = coordScreenX+(this.perenoel.getLayoutBounds().getWidth()/2);
		this.coordMapMinX = coordScreenX;
		this.deplacementDroite=false;
		this.deplacementGauche=false;
		this.colisionDroite=false;
		this.colisionGauche=false;
		this.saut=false;
	}

	private ImageView getPereNoel() {
		return this.perenoel;
	}

	/**
	 * Déplace le personnage sur le niveau
	 * @param niveau Le niveau concerné
	 */
	@Override
	public void seDeplace() {
		if(!isDansLeCiel()) {
			if(this.blockDurDirectDroiteLutin()==null && !this.colisionADroite) {
				this.getPereNoel().setX(this.getPereNoel().getX()+getVITESSE_DEPLACEMENT());
				this.setCoordMapMaxX(this.getCoordMapMaxX() + getVITESSE_DEPLACEMENT());
				this.setCoordMapCentreX(this.getCoordMapCentreX() + getVITESSE_DEPLACEMENT());
				this.setCoordMapMinX(this.getCoordMapMinX() + getVITESSE_DEPLACEMENT());
			} else if(this.blockDurDirectGaucheLutin()==null){
				this.colisionADroite = true;
				this.getPereNoel().setX(this.getPereNoel().getX()-getVITESSE_DEPLACEMENT());
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
		double maxXLutin = lutin.getImage().getLayoutBounds().getMaxX();
		double minXLutin = lutin.getImage().getLayoutBounds().getMinX();
		double maxYLutin = lutin.getImage().getLayoutBounds().getMaxY();
		double minYLutin = lutin.getImage().getLayoutBounds().getMinY();
		if(maxYLutin>=this.getImage().getLayoutBounds().getMinY() && maxYLutin<=this.getImage().getLayoutBounds().getMinY()+10
				&& (minXLutin>this.getImage().getLayoutBounds().getMinX() && minXLutin<this.getImage().getLayoutBounds().getMaxX() ||
				maxXLutin<this.getImage().getLayoutBounds().getMaxX() && maxXLutin>this.getImage().getLayoutBounds().getMinX())) {
			return true;
		}
		return false;
	}

	public boolean isDansFenetre() {
		if(this.getPereNoel().getLayoutBounds().getMinX()<0-this.getPereNoel().getLayoutBounds().getWidth() || this.getPereNoel().getLayoutBounds().getMinX()>1280+this.getPereNoel().getLayoutBounds().getWidth()) {
			if(!niveau.getBossAffiche().contains(this.getPereNoel())) {
				niveau.addBossAffiche(this);
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
		this.perenoel.setY(this.perenoel.getY() + this.vitesseY);
		this.vitesseY=this.vitesseY + G;
	}

	@Override
	public void sauter() {
		if (!this.isDansLeCiel()) {
			this.perenoel.setY(this.perenoel.getY()-150d);
			this.setVitesseY(1.6d);
		}
	}

	public void setVitesseY(double vitesse) {
		this.vitesseY = vitesse;
	}
	@Override
	public boolean isMort() {
		return this.perenoel.getY()>=760;
	}

	public static double getVITESSE_DEPLACEMENT() {
		return VITESSE_DEPLACEMENT;
	}

	public static void setVITESSE_DEPLACEMENT(double vITESSE_DEPLACEMENT) {
		VITESSE_DEPLACEMENT = vITESSE_DEPLACEMENT;
	}
}