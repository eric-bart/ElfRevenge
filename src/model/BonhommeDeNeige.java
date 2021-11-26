package model;

import javafx.scene.image.ImageView;

public class BonhommeDeNeige extends Personnage {

	private boolean deplacementGauche;
	private boolean deplacementDroite;
	private boolean colisionDroite;
	private boolean colisionGauche;
	private boolean saut;
	private int timerSaut;
	private static double VITESSESAUT = 0.01d;
	private static double VITESSE_DEPLACEMENT = 5;
	private double vitesseY = 0;
	private static double G = 0.02d;
	private ImageView bonhommeNeige;
	private double coordMapMaxX;
	private double coordMapCentreX;
	private double coordMapMinX;
	private double coordMapCentreY;
	private double coordMapMaxY;
	private double coordMapMinY;
	
	
	public BonhommeDeNeige(ImageView bonhommeNeige, double coordScreenX, double coordScreenY) {
		this.bonhommeNeige= bonhommeNeige;
		bonhommeNeige.setX(coordScreenX);
		bonhommeNeige.setY(coordScreenY);
		this.coordMapMaxX = coordScreenX+this.bonhommeNeige.getLayoutBounds().getMaxX();
		this.coordMapMinX = coordScreenX;
		this.coordMapCentreX = coordScreenX+(this.bonhommeNeige.getLayoutBounds().getMaxX()/2);
		this.coordMapMaxY = coordScreenY+this.bonhommeNeige.getLayoutBounds().getMinY();
		this.coordMapMinY = coordScreenY;
		this.coordMapCentreY = coordMapMaxY+(this.bonhommeNeige.getLayoutBounds().getMaxY()/2);
		this.deplacementDroite=false;
		this.deplacementGauche=false;
		this.colisionDroite=false;
		this.colisionGauche=false;
		this.saut=false;
	}

	private ImageView getBonhommeNeige() {
		return this.bonhommeNeige;
	}
}
