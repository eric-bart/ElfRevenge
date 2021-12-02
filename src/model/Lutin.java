package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.FileManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import view.Niveau;
import view.Niveau1;

public class Lutin extends Personnage {

	private static int vie = 2;
	private static double VITESSESAUT = 0.01d;
	private static double VITESSE_DEPLACEMENT = 5;
	private double vitesseY = 0;
	private static double G = 0.1d;
	private ImageView lutin;
	private Niveau niveau;
	private boolean seDeplace;
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
		this.lutin = this.getImage();
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
		if(this.getDeplacementDroite()) {
			if(this.blockDurDirectDroiteLutin()==null) {

				if(!this.isDansLeCiel()) {
					if(this.lutin.getX()+VITESSE_DEPLACEMENT>=niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMaxX()) {
						this.lutin.setX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX());
						this.setCoordMapMinX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX());
						this.setCoordMapMaxX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX()+(this.lutin.getLayoutBounds().getWidth()));
						this.setCoordMapCentreX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX() +(this.lutin.getLayoutBounds().getWidth()/2));
					} else {
						this.lutin.setX(this.lutin.getX() + VITESSE_DEPLACEMENT);
						this.setCoordMapMaxX(this.getCoordMapMaxX() + VITESSE_DEPLACEMENT);
						this.setCoordMapCentreX(this.getCoordMapCentreX() + VITESSE_DEPLACEMENT);
						this.setCoordMapMinX(this.getCoordMapMinX() + VITESSE_DEPLACEMENT);
					}
				} else {
					if(this.blockDurDirectDroiteLutin()==null || lutin.getLayoutBounds().getMaxX()+VITESSE_DEPLACEMENT<this.blockDurDirectDroiteLutin().getBlock().getLayoutBounds().getMinX()) {
						if(this.lutin.getX()+VITESSE_DEPLACEMENT>=niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMaxX()) {
							this.lutin.setX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX());
						} else {
							this.lutin.setX(this.lutin.getX() + VITESSE_DEPLACEMENT);
							this.setCoordMapMaxX(this.getCoordMapMaxX() + VITESSE_DEPLACEMENT);
							this.setCoordMapCentreX(this.getCoordMapCentreX() + VITESSE_DEPLACEMENT);
							this.setCoordMapMinX(this.getCoordMapMinX() + VITESSE_DEPLACEMENT);
						}
					}
				}
			}
		}

		if(this.blockDurDirectGaucheLutin()==null) {
			//Si le personnage se déplace à gauche alors on recupère la position maximum de la map à gauche et on regarde s'il peut avancer.
			if(this.getDeplacementGauche()) {
				if(!isDansLeCiel()) {
					if(this.lutin.getX()-VITESSE_DEPLACEMENT<=niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX()) {
						this.lutin.setX(niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX());
						this.setCoordMapMinX(niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX());
						this.setCoordMapMaxX(niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX()+(this.lutin.getLayoutBounds().getWidth()));
						this.setCoordMapCentreX(niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX() +(this.lutin.getLayoutBounds().getWidth()/2));
					} else {
						this.lutin.setX(this.lutin.getX() - VITESSE_DEPLACEMENT);
						this.setCoordMapMaxX(this.getCoordMapMaxX() - VITESSE_DEPLACEMENT);
						this.setCoordMapCentreX(this.getCoordMapCentreX() - VITESSE_DEPLACEMENT);
						this.setCoordMapMinX(this.getCoordMapMinX() - VITESSE_DEPLACEMENT);
					}
				} else {
					if(this.blockDurDirectGaucheLutin()==null || lutin.getLayoutBounds().getMinX()-VITESSE_DEPLACEMENT<this.blockDurDirectGaucheLutin().getBlock().getLayoutBounds().getMaxX()) {
						if(this.lutin.getX()+VITESSE_DEPLACEMENT>=niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMaxX()) {
							this.lutin.setX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX());
						} else {
							this.lutin.setX(this.lutin.getX() - VITESSE_DEPLACEMENT);
							this.setCoordMapMaxX(this.getCoordMapMaxX() - VITESSE_DEPLACEMENT);
							this.setCoordMapCentreX(this.getCoordMapCentreX() - VITESSE_DEPLACEMENT);
							this.setCoordMapMinX(this.getCoordMapMinX() - VITESSE_DEPLACEMENT);
						}
					}
				}
			}
		}
	}

	public boolean isSeDeplace() {
		return this.seDeplace;
	}

	@Override
	public void tombe() {
		this.lutin.setY(this.lutin.getY() + this.vitesseY);
		this.vitesseY=this.vitesseY + G;
	}

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
				if(this.blockDurDirectDessusLutin(i)==null) {
				} else {
					this.lutin.setY(this.blockDurDirectDessusLutin(i).getBlock().getLayoutBounds().getMaxY());
					this.setVitesseY(1.6d);
					blocDurDessus = true;
				}
				i+=10;
			} while(!blocDurDessus && !pasBlocDurDessus);
			if(pasBlocDurDessus) {
				this.lutin.setY(this.lutin.getY()-tailleSaut);
				this.setVitesseY(1.6d);
			}
		}
	}

	public void rebond() {
		if(!this.rebondi) {
			this.rebondi=true;
			int tailleSaut=150;
			boolean blocDurDessus=false;
			boolean pasBlocDurDessus=false;
			int i=0;
			do {
				if(i>=tailleSaut) {
					pasBlocDurDessus=true;
				}
				if(this.blockDurDirectDessusLutin(i)==null) {
				} else {
					this.lutin.setY(this.blockDurDirectDessusLutin(i).getBlock().getLayoutBounds().getMaxY());
					this.setVitesseY(1.6d);
					blocDurDessus = true;
				}
				i+=10;
			} while(!blocDurDessus && !pasBlocDurDessus);
			if(pasBlocDurDessus) {
				this.lutin.setY(this.lutin.getY()-tailleSaut);
				this.setVitesseY(1.6d);
			}
		}
	}

	@Override
	public boolean isMort() {
		return this.lutin.getY()>=760;
	}
	
	public boolean isColisionMob(Personnage mob) {
		double maxXLutin = this.getImage().getLayoutBounds().getMaxX();
		double minXLutin = this.getImage().getLayoutBounds().getMinX();
		double centerYLutin = this.getImage().getLayoutBounds().getCenterY();
		double maxYLutin = this.getImage().getLayoutBounds().getMaxY();
		double minYLutin = this.getImage().getLayoutBounds().getMinY();
		if((maxXLutin>=mob.getImage().getLayoutBounds().getMinX() && maxXLutin<=mob.getImage().getLayoutBounds().getMinX()+10
				&& (centerYLutin>=mob.getImage().getLayoutBounds().getMinY() && centerYLutin<=mob.getImage().getLayoutBounds().getMaxY())
				) || (minXLutin<=mob.getImage().getLayoutBounds().getMaxX() && minXLutin>=mob.getImage().getLayoutBounds().getMaxX()-10
				&& (centerYLutin>=mob.getImage().getLayoutBounds().getMinY() && centerYLutin<=mob.getImage().getLayoutBounds().getMaxY()))) {
			
			return true;
		}
		return false;
	}

	public void setVitesseY(double vitesse) {
		this.vitesseY = vitesse;
	}

	public void setRebond(boolean rebond) {
		this.rebondi=rebond;
	}
	
	public boolean niveauFini() {
		if (this.blocDurDirectDessousLutin()!=null) {
			if (this.blocDurDirectDessousLutin().getBlockName().equals("barreFinale1.png") || this.blocDurDirectDessousLutin().getBlockName().equals("barreFinale2.png")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void setTouche(boolean touche) {
		this.touche=touche;
	}
	
	public boolean isTouche() {
		return this.touche;
	}

	public void enleverVie() {
		vie--;
	}
	
	public int getVie() {
		return vie;
	}
	
	public void setVie(int vie) {
		vie=vie;
	}
}
