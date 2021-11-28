package model;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;
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
	private Niveau1 niveau;
	
	private boolean colisionADroite=false;
	private boolean colisionAGauche=false;
	private boolean doitReculer=false;
	
	
	public BonhommeDeNeige(ImageView bonhommeNeige, double coordScreenX, double coordScreenY, Niveau1 niveau) {
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
		if(blocDroiteLutin()!=null) {
			if(!isDansLeCiel()) {
				if(this.getBonhommeNeige().getLayoutBounds().getMaxX()>500) {
					this.doitReculer=true;
					this.getBonhommeNeige().setX(this.getBonhommeNeige().getX()-5);
					this.setCoordMapMaxX(this.getCoordMapMaxX() -5);
					this.setCoordMapCentreX(this.getCoordMapCentreX() -5);
					this.setCoordMapMinX(this.getCoordMapMinX() -5);
				} else {
					if(!isColisionDroite(blocDroiteLutin()) && !this.colisionADroite) {
					this.getBonhommeNeige().setX(this.getBonhommeNeige().getX()+getVITESSE_DEPLACEMENT());
					this.setCoordMapMaxX(this.getCoordMapMaxX() + getVITESSE_DEPLACEMENT());
					this.setCoordMapCentreX(this.getCoordMapCentreX() + getVITESSE_DEPLACEMENT());
					this.setCoordMapMinX(this.getCoordMapMinX() + getVITESSE_DEPLACEMENT());
					System.out.println("Coord bonhomme neige " + this.getBonhommeNeige().getLayoutBounds().toString());
					System.out.println("Coord du bloc à droite " + blocDroiteLutin().getBlock().getLayoutBounds().toString());
				} else if(!isColisionGauche(blocGaucheLutin())){
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
		}
	}
	
	/**
	 * Retourne le bloc situé à droite du lutin
	 * @param niveau
	 * @return ImageView le bloc situé à droite du lutin
	 */
	public Block blocDurDurDroiteLutin() {
		for(int i=((int)this.getCoordMapCentreX()+Block.blockXSize)/Block.blockXSize; i<niveau.getGenerationMap()[(((int) this.bonhommeNeige.getLayoutBounds().getCenterY())/Block.blockXSize)
		                     					* niveau.getGenerationMap()[0].length].length;i++) {
			if(this.niveau.getGenerationMap()[(((int) this.bonhommeNeige.getLayoutBounds().getCenterY())/Block.blockXSize)
		                     					* niveau.getGenerationMap()[0].length][i]==1) {
			return niveau.getGeneration().get((((int) this.bonhommeNeige.getLayoutBounds().getCenterY())/Block.blockXSize)
 					* niveau.getGenerationMap()[0].length+i);
			}
				
		}
		return null;
	}

	
	public void recule() {
		this.VITESSE_DEPLACEMENT = 5;
		this.getBonhommeNeige().setX(this.getBonhommeNeige().getX()-VITESSE_DEPLACEMENT);
		this.setCoordMapMaxX(this.getCoordMapMaxX() -VITESSE_DEPLACEMENT);
		this.setCoordMapCentreX(this.getCoordMapCentreX() -VITESSE_DEPLACEMENT);
		this.setCoordMapMinX(this.getCoordMapMinX() -VITESSE_DEPLACEMENT);
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
