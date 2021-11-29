package model;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import view.Niveau1;

public class Lutin extends Personnage {

	private static double VITESSESAUT = 0.01d;
	private static double VITESSE_DEPLACEMENT = 5;
	private double vitesseY = 0;
	private static double G = 0.1d;
	private ImageView lutin;
	private Niveau1 niveau;
	private boolean seDeplace;
	
	/**
	 * Construit un lutin détenant une certaine coordonnée et un ImageView qui correspond à sa représentation sur la vue.
	 * @param lutin
	 * @param coordX
	 * @param coordY
	 */
	public Lutin(ImageView imagePersonnage, double coordScreenX, double coordScreenY, Niveau1 niveau) {
		super(imagePersonnage, coordScreenX, coordScreenY, niveau);
		this.lutin = this.getPersonnage();
		this.niveau = niveau;
	}
	
	/**
	 * Déplace le personnage sur le niveau
	 * @param niveau Le niveau concerné
	 */
	@Override
	public void seDeplace() {
		//Si le personnage se déplace à droite alors on recupère la position maximum de la map à droite et on regarde s'il peut avancer.
		if(this.getDeplacementDroite()) {
			this.seDeplace=true;
			if(!this.isDansLeCiel()) {
				if(this.lutin.getX()+VITESSE_DEPLACEMENT>=niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMaxX()) {
					this.lutin.setX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX());
					this.setCoordMapMinX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX());
					this.setCoordMapMaxX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX()+(this.lutin.getLayoutBounds().getWidth()));
					this.setCoordMapCentreX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX() +(this.lutin.getLayoutBounds().getWidth()/2));
				} else {
					if(lutin.getX()<(Screen.getPrimary().getBounds().getMaxX()/2)){
						this.lutin.setX(this.lutin.getX() + VITESSE_DEPLACEMENT);
						this.setCoordMapMaxX(this.getCoordMapMaxX() + VITESSE_DEPLACEMENT);
						this.setCoordMapCentreX(this.getCoordMapCentreX() + VITESSE_DEPLACEMENT);
						this.setCoordMapMinX(this.getCoordMapMinX() + VITESSE_DEPLACEMENT);
					} else {
						if(niveau.getMobAffiche().size()>0) {
							niveau.getMobAffiche().forEach(e -> {
								e.getPersonnage().setX(e.getPersonnage().getX()-BonhommeDeNeige.getVITESSE_DEPLACEMENT());
							});
						}
						niveau.getGeneration().forEach(e -> {
							e.getBlock().setX(e.getBlock().getX()-VITESSE_DEPLACEMENT);
						});
						this.lutin.setX((Screen.getPrimary().getBounds().getMaxX()/2));
						this.setCoordMapMaxX(this.getCoordMapMaxX() + VITESSE_DEPLACEMENT);
						this.setCoordMapCentreX(this.getCoordMapCentreX() + VITESSE_DEPLACEMENT);
						this.setCoordMapMinX(this.getCoordMapMinX() + VITESSE_DEPLACEMENT);
					}
				}
			} else {
				if(this.blocDurDirectDroiteLutin()==null || lutin.getLayoutBounds().getMaxX()+VITESSE_DEPLACEMENT<this.blocDurDirectDroiteLutin().getBlock().getLayoutBounds().getMinX()) {
					if(this.lutin.getX()+VITESSE_DEPLACEMENT>=niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMaxX()) {
						this.lutin.setX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX());
					} else if(lutin.getX()<(Screen.getPrimary().getBounds().getMaxX()/2)){
						this.lutin.setX(this.lutin.getX() + VITESSE_DEPLACEMENT);
						this.setCoordMapMaxX(this.getCoordMapMaxX() + VITESSE_DEPLACEMENT);
						this.setCoordMapCentreX(this.getCoordMapCentreX() + VITESSE_DEPLACEMENT);
						this.setCoordMapMinX(this.getCoordMapMinX() + VITESSE_DEPLACEMENT);
					} else {
						if(niveau.getMobAffiche().size()>0) {
							niveau.getMobAffiche().forEach(e -> {
								e.getPersonnage().setX(e.getPersonnage().getX()-BonhommeDeNeige.getVITESSE_DEPLACEMENT());
							});
						}
						niveau.getGeneration().forEach(e -> {
							e.getBlock().setX(e.getBlock().getX()-VITESSE_DEPLACEMENT);
						});
						this.lutin.setX((Screen.getPrimary().getBounds().getMaxX()/2));
						this.setCoordMapMaxX(this.getCoordMapMaxX() + VITESSE_DEPLACEMENT);
						this.setCoordMapCentreX(this.getCoordMapCentreX() + VITESSE_DEPLACEMENT);
						this.setCoordMapMinX(this.getCoordMapMinX() + VITESSE_DEPLACEMENT);
					}
				}
			}

		}
		//Si le personnage se déplace à gauche alors on recupère la position maximum de la map à gauche et on regarde s'il peut avancer.
		if(this.getDeplacementGauche()) {
			this.seDeplace=true;
			if(!isDansLeCiel()) {
				if(this.lutin.getX()-VITESSE_DEPLACEMENT<=niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX()) {
					this.lutin.setX(niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX());
					this.setCoordMapMinX(niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX());
					this.setCoordMapMaxX(niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX()+(this.lutin.getLayoutBounds().getWidth()));
					this.setCoordMapCentreX(niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX() +(this.lutin.getLayoutBounds().getWidth()/2));
				} else {
					if(this.getCoordMapMaxX()>0+(Screen.getPrimary().getBounds().getMaxX()/2)) {
						if(niveau.getMobAffiche().size()>0) {
							niveau.getMobAffiche().forEach(e -> {
								e.getPersonnage().setX(e.getPersonnage().getX()+BonhommeDeNeige.getVITESSE_DEPLACEMENT());
							});
						}
						niveau.getGeneration().forEach(e -> {
							e.getBlock().setX(e.getBlock().getX()+VITESSE_DEPLACEMENT);
						});
						this.lutin.setX((Screen.getPrimary().getBounds().getMaxX()/2));
						this.setCoordMapMaxX(this.getCoordMapMaxX() - VITESSE_DEPLACEMENT);
						this.setCoordMapCentreX(this.getCoordMapCentreX() - VITESSE_DEPLACEMENT);
						this.setCoordMapMinX(this.getCoordMapMinX() - VITESSE_DEPLACEMENT);
					} else {
						this.lutin.setX(this.lutin.getX() - VITESSE_DEPLACEMENT);
						this.setCoordMapMaxX(this.getCoordMapMaxX() - VITESSE_DEPLACEMENT);
						this.setCoordMapCentreX(this.getCoordMapCentreX() - VITESSE_DEPLACEMENT);
						this.setCoordMapMinX(this.getCoordMapMinX() - VITESSE_DEPLACEMENT);
					}
				}
			} else {
				if(this.blocDurDirectGaucheLutin()==null || lutin.getLayoutBounds().getMinX()-VITESSE_DEPLACEMENT<this.blocDurDirectGaucheLutin().getBlock().getLayoutBounds().getMaxX()) {
					if(this.lutin.getX()+VITESSE_DEPLACEMENT>=niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMaxX()) {
						this.lutin.setX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX());
					} else if(lutin.getX()<(Screen.getPrimary().getBounds().getMaxX()/2)){
						this.lutin.setX(this.lutin.getX() - VITESSE_DEPLACEMENT);
						this.setCoordMapMaxX(this.getCoordMapMaxX() - VITESSE_DEPLACEMENT);
						this.setCoordMapCentreX(this.getCoordMapCentreX() - VITESSE_DEPLACEMENT);
						this.setCoordMapMinX(this.getCoordMapMinX() - VITESSE_DEPLACEMENT);
					} else {
						if(niveau.getMobAffiche().size()>0) {
							niveau.getMobAffiche().forEach(e -> {
								e.getPersonnage().setX(e.getPersonnage().getX()+BonhommeDeNeige.getVITESSE_DEPLACEMENT());
							});
						}
						niveau.getGeneration().forEach(e -> {
							e.getBlock().setX(e.getBlock().getX()+VITESSE_DEPLACEMENT);
						});
						this.lutin.setX((Screen.getPrimary().getBounds().getMaxX()/2));
						this.setCoordMapMaxX(this.getCoordMapMaxX() - VITESSE_DEPLACEMENT);
						this.setCoordMapCentreX(this.getCoordMapCentreX() - VITESSE_DEPLACEMENT);
						this.setCoordMapMinX(this.getCoordMapMinX() - VITESSE_DEPLACEMENT);
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
		if (!this.isDansLeCiel()) {
			this.lutin.setY(this.lutin.getY()-170d);
			this.setVitesseY(1.6d);
		}
	}
	
	@Override
	public boolean isMort() {
		return this.lutin.getY()>=760;
	}
	
	public void setVitesseY(double vitesse) {
		this.vitesseY = vitesse;
	}

	public boolean niveauFini() {
		if (this.blocDessousLutin()!=null) {
			if (this.blocDessousLutin().getBlockName() == "barreFinale1.png" || this.blocDessousLutin().getBlockName() == "barreFinale2.png") {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
}
