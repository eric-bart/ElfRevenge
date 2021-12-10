package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Niveau;

/**
 * Classe du Personnage BonhommeDeNeige. C'est dans cette classe que l'on gère les méthodes propres à notre
 * Personnage BonhommeDeNeige. 
 */
public class BonhommeDeNeige extends Personnage {

	private static double VITESSE_DEPLACEMENT =2;
	private double vitesseY = 0;
	private static double G = 0.02d;
	private ImageView imageBonhommeNeige;
	private boolean colisionADroite;
	private boolean colisionAGauche;

	public BonhommeDeNeige(double coordScreenX, double coordScreenY) {
		super(new ImageView("mob1.png"), coordScreenX, coordScreenY);
		this.imageBonhommeNeige = this.getImage();
		this.imageBonhommeNeige.setX(coordScreenX);
		this.imageBonhommeNeige.setY(coordScreenY);
		this.colisionADroite=false;
		this.colisionAGauche=false;
	}

	/**
	 * Fonction faisant sauter le bonhomme de neige.
	 */
	@Override
	public void sauter(Niveau niveau) {
		double saut = 150d;
		if (!this.isDansLeCiel(niveau)) {
			this.imageBonhommeNeige.setY(this.imageBonhommeNeige.getY()-saut);
			this.setVitesseY(1.6d);
		}
	}
	
	/**
	 * Déplace le personnage sur le niveau
	 * @param niveau Le niveau concerné
	 */
	@Override
	public void seDeplace(Niveau niveau) {
		if(!isDansLeCiel(niveau)) {
			if(this.blockDurDroite(niveau)==null && !this.colisionADroite) {
				this.getImage().setX(this.getImage().getX()+getVitesseDeplacement());
			} else if(this.blockDurGauche(niveau)==null){
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
	 * Fait tomber le personnage progressivement.
	 * Cette fonction réduit les coordonnées en Y du lutin progressivement en fonction du facteur de gravité "G".
	 */
	public void tombe(Niveau niveau) {
		this.imagePersonnage.setY(this.imagePersonnage.getY() + this.vitesseY);
		this.vitesseY=this.vitesseY + G;
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
