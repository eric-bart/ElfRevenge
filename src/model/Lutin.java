package model;

import javafx.scene.image.ImageView;
import view.Niveau;

public class Lutin extends Personnage {

	private static int vie = 2;
	private static double VITESSE_DEPLACEMENT = 5;
	private double vitesseY = 0;
	private static double G = 0.1d;
	private ImageView imageLutin;
	private Niveau niveau;
	private boolean rebondi;
	private boolean touche;

	/**
	 * Construit un lutin détenant une certaine coordonnée et un ImageView qui correspond à sa représentation sur la vue.
	 * @param lutin
	 * @param coordX
	 * @param coordY
	 */
	public Lutin(ImageView imagePersonnage, double coordScreenX, double coordScreenY, Niveau niveau) {
		super(imagePersonnage, coordScreenX, coordScreenY, niveau);
		this.touche=false;
		this.imageLutin = imagePersonnage;
		this.niveau = niveau;
		this.rebondi=false;
		this.setDeplacementDroite(false);
		this.setDeplacementGauche(false);
	}

	/**
	 * Déplace le personnage sur le niveau
	 * @param niveau Le niveau concerné
	 */
	@Override
	public void seDeplace() {
		//Si le personnage se déplace à droite alors on recupère la position maximum de la map à droite et on regarde s'il peut avancer.
		if(this.isDeplacementDroite()) {
			if(this.blockDurDroite()==null) {
				if(!this.isDansLeCiel()) {
					if(this.imageLutin.getX()+VITESSE_DEPLACEMENT>=niveau.getBlocksNiveau().get(niveau.getBlocksNiveau().size()-1).getBlock().getLayoutBounds().getMaxX()) {
						this.imageLutin.setX(niveau.getBlocksNiveau().get(niveau.getBlocksNiveau().size()-1).getBlock().getLayoutBounds().getMinX());
					} else {
						this.imageLutin.setX(this.imageLutin.getX() + VITESSE_DEPLACEMENT);
					}
				} else {
					if(this.blockDurDroite()==null || this.imageLutin.getLayoutBounds().getMaxX()+VITESSE_DEPLACEMENT<this.blockDurDroite().getBlock().getLayoutBounds().getMinX()) {
						if(this.imageLutin.getX()+VITESSE_DEPLACEMENT>=niveau.getBlocksNiveau().get(niveau.getBlocksNiveau().size()-1).getBlock().getLayoutBounds().getMaxX()) {
							this.imageLutin.setX(niveau.getBlocksNiveau().get(niveau.getBlocksNiveau().size()-1).getBlock().getLayoutBounds().getMinX());
						} else {
							this.imageLutin.setX(this.imageLutin.getX() + VITESSE_DEPLACEMENT);
						}
					}
				}
			}
		}
		if(this.blockDurGauche()==null) {
			//Si le personnage se déplace à gauche alors on recupère la position maximum de la map à gauche et on regarde s'il peut avancer.
			if(this.isDeplacementGauche()) {
				if(!isDansLeCiel()) {
					if(this.imageLutin.getX()-VITESSE_DEPLACEMENT<=niveau.getBlocksNiveau().get(0).getBlock().getLayoutBounds().getMinX()) {
						this.imageLutin.setX(niveau.getBlocksNiveau().get(0).getBlock().getLayoutBounds().getMinX());
					} else {
						this.imageLutin.setX(this.imageLutin.getX() - VITESSE_DEPLACEMENT);
					}
				} else {
					if(this.blockDurGauche()==null || this.imageLutin.getLayoutBounds().getMinX()-VITESSE_DEPLACEMENT<this.blockDurGauche().getBlock().getLayoutBounds().getMaxX()) {
						if(this.imageLutin.getX()+VITESSE_DEPLACEMENT>=niveau.getBlocksNiveau().get(niveau.getBlocksNiveau().size()-1).getBlock().getLayoutBounds().getMaxX()) {
							this.imageLutin.setX(niveau.getBlocksNiveau().get(niveau.getBlocksNiveau().size()-1).getBlock().getLayoutBounds().getMinX());
						} else {
							this.imageLutin.setX(this.imageLutin.getX() - VITESSE_DEPLACEMENT);
						}
					}
				}
			}
		}
	}

	/**
	 * Fait en sorte que le lutin tombe progressivement.
	 * Cette fonction réduit les coordonnées en Y du lutin progressivement en fonction du facteur de gravité "G".
	 */
	@Override
	public void tombe() {
		this.imageLutin.setY(this.imageLutin.getY() + this.vitesseY);
		this.vitesseY=this.vitesseY + G;
	}

	/**
	 * 
	 */
	@Override
	public void sauter() {
		int tailleSaut=150;
		boolean blocDurDessus=false;
		boolean pasBlocDurDessus=false;
		int i=0;
		if (!this.isDansLeCiel()) {
			do {
				if(i>=tailleSaut) {
					pasBlocDurDessus=true;
				}
				if(this.blockDurDessus(i)==null) {
				} else if (this.blockDurDessus(5)==null){
					this.imageLutin.setY(this.blockDurDessus(i).getBlock().getLayoutBounds().getMaxY());
					this.setVitesseY(1.6d);
					blocDurDessus = true;
				}
				i+=10;
			} while(!blocDurDessus && !pasBlocDurDessus);
			if(pasBlocDurDessus) {
				this.imageLutin.setY(this.imageLutin.getY()-tailleSaut);
				this.setVitesseY(1.6d);
			}
		}
	}

	/**
	 * Cette fonction fait rebondir le lutin.
	 * Elle est invoquée lorsque le lutin saute sur un mob.
	 */
	public void rebond() {
		if(!this.rebondi) {
			this.rebondi=true;
			int tailleSaut=50;
			boolean blocDurDessus=false;
			boolean pasBlocDurDessus=false;
			int i=0;
			do {
				if(i>=tailleSaut) {
					pasBlocDurDessus=true;
				}
				if(this.blockDurDessus(i)==null) {
				} else if (this.blockDurDessus(5)==null){
					this.imageLutin.setY(this.blockDurDessus(i).getBlock().getLayoutBounds().getMaxY());
					this.setVitesseY(1.6d);
					blocDurDessus = true;
				}
				i+=10;
			} while(!blocDurDessus && !pasBlocDurDessus);
			if(pasBlocDurDessus) {
				this.imageLutin.setY(this.imageLutin.getY()-tailleSaut);
				this.setVitesseY(1.6d);
			}
		}
	}

	/**
	 * Retourne un booleen informant de si le lutin est de chutte ou non.
	 * @return booleen true=Le mob est
	 */
	@Override
	public boolean isMort() {
		return this.imageLutin.getY()>=760;
	}
	
	/**
	 * Retourne si le lutin est en colision avec un mob
	 * @param mob le mob avec lequel on vérifie tout ça
	 * @return booleen true=Il est en colision avec un mob | false=Il n'est pas en colision avec un mob
	 */
	public boolean isColisionMob(Personnage mob) {
		double maxXLutin = this.getImage().getLayoutBounds().getMaxX();
		double minXLutin = this.getImage().getLayoutBounds().getMinX();
		double centerYLutin = this.getImage().getLayoutBounds().getCenterY();
		if((maxXLutin>=mob.getImage().getLayoutBounds().getMinX() && maxXLutin<=mob.getImage().getLayoutBounds().getMinX()+10
				&& (centerYLutin>=mob.getImage().getLayoutBounds().getMinY() && centerYLutin<=mob.getImage().getLayoutBounds().getMaxY())
				) || (minXLutin<=mob.getImage().getLayoutBounds().getMaxX() && minXLutin>=mob.getImage().getLayoutBounds().getMaxX()-10
				&& (centerYLutin>=mob.getImage().getLayoutBounds().getMinY() && centerYLutin<=mob.getImage().getLayoutBounds().getMaxY()))) {
			
			return true;
		}
		return false;
	}

	/**
	 * Met à jour la vitesse en Y du lutin
	 * @param vitesse double
	 */
	public void setVitesseY(double vitesse) {
		this.vitesseY = vitesse;
	}

	/**
	 * Met à jour l'attribut "rebond" du lutin.
	 * @param rebond booleen
	 */
	public void setRebond(boolean rebond) {
		this.rebondi=rebond;
	}

	/**
	 * Met à jour le booleen "touche" informant si le lutin a été touché par un mob ennemi ou non.
	 * @param touche booleen
	 */
	public void setTouche(boolean touche) {
		this.touche=touche;
	}
	
	/**
	 * Retourne un booleen informant si le lutin a été touché ou non.
	 * @return booleen true=Le lutin a été touché | false=Le lutin n'a pas été touché
	 */
	public boolean isTouche() {
		return this.touche;
	}

	/**
	 * Retire un point de vie au lutin
	 */
	public void enleverVie() {
		vie--;
	}
	
	/**
	 * Retourne le nombre de points de vie que détient le lutin
	 * @return int vie
	 */
	public int getVie() {
		return vie;
	}
}
