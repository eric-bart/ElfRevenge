package model;

import javafx.scene.image.ImageView;
import view.Niveau;

/**
 * Classe de notre Personnage PereNoel
 */
public class PereNoel extends Personnage {

	private static double VITESSE_DEPLACEMENT =4;
	private double vitesseY = 0;
	private static double G = 0.02d;
	private ImageView imagePereNoel;
	private boolean colisionADroite;
	private boolean colisionAGauche;


	public PereNoel(double coordScreenX, double coordScreenY) {
		super(new ImageView("perenoel.png"), coordScreenX, coordScreenY);
		this.imagePereNoel = this.getImage();
		this.imagePereNoel.setX(coordScreenX);
		this.imagePereNoel.setY(coordScreenY);
		this.colisionADroite=false;
		this.colisionAGauche=false;
	}

	@Override
	public void sauter(Niveau niveau) {
		if (!this.isDansLeCiel(niveau)) {
			this.imagePereNoel.setY(this.imagePereNoel.getY()-150d);
			this.setVitesseY(1.6d);
		}
	}
	
	@Override
	public void seDeplace(Niveau niveau) {
		if(!isDansLeCiel(niveau)) {
			if(this.blockDurDroite(niveau)==null && !this.colisionADroite) {
				this.imagePereNoel.setX(this.imagePereNoel.getX()+getVitesseDeplacement());
			} else if(this.blockDurGauche(niveau)==null){
				this.colisionADroite = true;
				this.imagePereNoel.setX(this.imagePereNoel.getX()-getVitesseDeplacement());
			} else {
				this.colisionAGauche=true;
				this.colisionADroite=false;
			}
		}
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
	 * Détermine si le pere noel a été tué par le Lutin
	 * @param lutin le Lutin
	 * @return booleen true=Le pereNoel est mort | false=Le pereNoel est pas mort
	 */
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
	
	/**
	 * Setter pour la vitesse en Y du pere noel
	 * @param vitesse la vitesse Y du pereNoel
	 */
	public void setVitesseY(double vitesse) {
		this.vitesseY = vitesse;
	}
	
	/**
	 * Getter pour la vitesse de déplacement du pere noel
	 * @return
	 */
	public static double getVitesseDeplacement() {
		return VITESSE_DEPLACEMENT;
	}
}