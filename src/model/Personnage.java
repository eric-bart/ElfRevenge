package model;

import javafx.scene.image.ImageView;

public class Personnage {
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
	private double coordMapMaxX;
	private double coordMapCentreX;
	private double coordMapMinX;
	private double coordMapCentreY;
	private double coordMapMaxY;
	private double coordMapMinY;
}
