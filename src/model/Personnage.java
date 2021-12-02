package model;

import javafx.scene.image.ImageView;
import view.Niveau;
import view.Niveau1;

public abstract class Personnage {
	
	private boolean deplacementGauche;
	private boolean deplacementDroite;
	private boolean saut;
	private int timerSaut;
	private ImageView imagePersonnage;
	private Niveau niveau;
	private double coordMapMaxX;
	private double coordMapCentreX;
	private double coordMapMinX;
	private boolean raterri;
	
	public Personnage(ImageView imagePersonnage, double coordScreenX, double coordScreenY, Niveau niveau) {
		this.niveau = niveau;
		this.imagePersonnage = imagePersonnage;
		this.imagePersonnage.setX(coordScreenX);
		this.imagePersonnage.setY(coordScreenY);
		this.coordMapMaxX = coordScreenX+this.imagePersonnage.getLayoutBounds().getWidth();
		this.coordMapCentreX = coordScreenX+(this.imagePersonnage.getLayoutBounds().getWidth()/2);
		this.coordMapMinX = coordScreenX;
		this.deplacementDroite=false;
		this.deplacementGauche=false;
		this.saut=false;
	}
	
	public abstract void seDeplace();
	
	public abstract void tombe();
	
	public abstract void sauter();
	
	public abstract boolean isMort();
	

	/**
	 * Vérifie si le personnage est dans le vide ou pas.
	 * Vide = Ne pas être sur un block
	 * @param block Le block sur lequel le lutin se trouve en X
	 * @return boolean true=Le lutin est dans le vide ; false=Le lutin n'est pas dans le vide
	 */
	public boolean isDansLeCiel() {
		if(this.blocDurDirectDessousLutin()==null) {
			return true;
		} else {
			return this.imagePersonnage.getLayoutBounds().getMaxY()<=this.blocDurDirectDessousLutin().getBlock().getLayoutBounds().getMinY();
		}
	}
	
	public Block blockDurDirectDessusLutin(int distanceHaut) {
		if(this.getImage().getLayoutBounds().getMinY()>0) {
			if((((int) this.getCoordMapMaxX()/Block.blockXSize 
				+ (((int) this.imagePersonnage.getLayoutBounds().getMinY() - distanceHaut)/Block.blockXSize)
				* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size() && ((int) this.getCoordMapMaxX()/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMinY() - distanceHaut)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length) >0)
				|| (((int) this.getCoordMapMinX()/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMinY() - distanceHaut)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size() && (((int) this.getCoordMapMinX()/Block.blockXSize 
								+ (((int) this.imagePersonnage.getLayoutBounds().getMinY() - distanceHaut)/Block.blockXSize)
								* niveau.getGenerationMap()[0].length)> 0))) {
			if(niveau.getGeneration().get(((int) this.getCoordMapMaxX()/Block.blockXSize 
					+ (((int) this.imagePersonnage.getLayoutBounds().getMinY() - distanceHaut)/Block.blockXSize)
					* niveau.getGenerationMap()[0].length)).isHardBlock()) {
				return niveau.getGeneration().get(((int) this.getCoordMapMaxX()/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMinY() - distanceHaut)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length));
			} else if (niveau.getGeneration().get(((int) this.getCoordMapMinX()/Block.blockXSize 
					+ (((int) this.imagePersonnage.getLayoutBounds().getMinY() - distanceHaut)/Block.blockXSize)
					* niveau.getGenerationMap()[0].length)).isHardBlock()) {
				return niveau.getGeneration().get(((int) this.getCoordMapMinX()/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMinY() - distanceHaut)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length));
			} else {
				return null;
			}
		} else {
			return null;
		}
		} else {
			return null;
		}
		
	}
	

	/**
	 * Retourne le bloc situé en dessous du lutin
	 * @param niveau
	 * @return ImageView le bloc situé en dessous du lutin
	 */
	public Block blocDurDirectDessousLutin() {
		//SI LE MAXX A EN DESSOUS DE LUI UN BLOC NON DUR
		//REGARDER LE MINX, SI LE MINY A EN DESSOUS DE LUI UN BLOC NON DUR
		//RENVOYER NULL
		if(this.getImage().getLayoutBounds().getMinY()>0) {
			if(((int) this.getCoordMapMaxX()/Block.blockXSize 
				+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY() + 10)/Block.blockXSize)
				* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size() 
				|| ((int) this.getCoordMapMinX()/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY() + 10)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size()) {
			if(niveau.getGeneration().get(((int) this.getCoordMapMaxX()/Block.blockXSize 
					+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY() + 10)/Block.blockXSize)
					* niveau.getGenerationMap()[0].length)).isHardBlock()) {
				return niveau.getGeneration().get(((int) this.getCoordMapMaxX()/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY() + 10)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length));
			} else if (niveau.getGeneration().get(((int) this.getCoordMapMinX()/Block.blockXSize 
					+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY() + 10)/Block.blockXSize)
					* niveau.getGenerationMap()[0].length)).isHardBlock()) {
				return niveau.getGeneration().get(((int) this.getCoordMapMinX()/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY() + 10)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length));
			} else {
				return null;
			}
		} else {
			return null;
		}
		} else {
			return null;
		}
		
	}
	
	public Block blockDurDirectDroiteLutin() {
		if(this.getImage().getLayoutBounds().getMinY()>0) {
			if((((int) this.getCoordMapMaxX()+2)/Block.blockXSize 
				+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY()-10)/Block.blockXSize)
				* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size() 
				|| (((int) this.getCoordMapMaxX()+2)/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMinY()-10)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size()) {
			if(niveau.getGeneration().get((((int) this.getCoordMapMaxX()+2)/Block.blockXSize 
					+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY()-10)/Block.blockXSize)
					* niveau.getGenerationMap()[0].length)).isHardBlock()) {
				System.out.println("Y a bien un bloc");
				return niveau.getGeneration().get((((int) this.getCoordMapMaxX()+2)/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY()-10)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length));
			} else if (niveau.getGeneration().get((((int) this.getCoordMapMaxX()+2)/Block.blockXSize 
					+ (((int) this.imagePersonnage.getLayoutBounds().getMinY())/Block.blockXSize)
					* niveau.getGenerationMap()[0].length)).isHardBlock()) {
				System.out.println("y a bien ce bloc");
				return niveau.getGeneration().get((((int) this.getCoordMapMaxX()+2)/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMinY())/Block.blockXSize)
						* niveau.getGenerationMap()[0].length));
			} else {
				return null;
			}
		} else {
			return null;
		}
		} else {
			return null;
		}
		
	}
	
	public Block blockDurDirectGaucheLutin() {
		if(this.getImage().getLayoutBounds().getMinY()>0) {
			if((((int) this.getCoordMapMinX()-2)/Block.blockXSize 
				+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY()-10)/Block.blockXSize)
				* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size() 
				|| (((int) this.getCoordMapMinX()-2)/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMinY()-10)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size()) {
			if(niveau.getGeneration().get((((int) this.getCoordMapMinX()-2)/Block.blockXSize 
					+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY()-10)/Block.blockXSize)
					* niveau.getGenerationMap()[0].length)).isHardBlock()) {
				System.out.println("Y a bien un bloc");
				return niveau.getGeneration().get((((int) this.getCoordMapMinX()-2)/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMaxY()-10)/Block.blockXSize)
						* niveau.getGenerationMap()[0].length));
			} else if (niveau.getGeneration().get((((int) this.getCoordMapMinX()-2)/Block.blockXSize 
					+ (((int) this.imagePersonnage.getLayoutBounds().getMinY())/Block.blockXSize)
					* niveau.getGenerationMap()[0].length)).isHardBlock()) {
				System.out.println("y a bien ce bloc");
				return niveau.getGeneration().get((((int) this.getCoordMapMinX()-2)/Block.blockXSize 
						+ (((int) this.imagePersonnage.getLayoutBounds().getMinY())/Block.blockXSize)
						* niveau.getGenerationMap()[0].length));
			} else {
				return null;
			}
		} else {
			return null;
		}
		} else {
			return null;
		}
		
	}
	
	public boolean getDeplacementGauche() {
		return this.deplacementGauche;
	}
	
	public boolean getDeplacementDroite() {
		return this.deplacementDroite;
	}
	

	/**
	 * Permet de changer la variable deplacementGauche
	 * @param deplacementGauche
	 */
	public void setDeplacementGauche(boolean deplacementGauche) {
		this.deplacementGauche = deplacementGauche;
	}

	/**
	 * Permet de changer la variable deplacementDroite
	 * @param deplacementDroite
	 */
	public void setDeplacementDroite(boolean deplacementDroite) {
		this.deplacementDroite = deplacementDroite;
	}

	/**
	 * Retourne un booleen qui informe si le lutin se déplace vers la gauche ou pas
	 * @return boolean true=Le lutin se déplace vers la gauche ; false=Le lutin ne se déplace pas vers la gauche
	 */
	public boolean isDeplacementGauche() {
		return this.deplacementGauche;
	}

	/**
	 * Retourne un booleen qui informe si le lutin se déplace vers la gauche ou pas
	 * @return boolean true=Le lutin se déplace vers la droite ; false=Le lutin ne se déplace pas vers la droite
	 */
	public boolean isDeplacementDroite() {
		return this.deplacementDroite;
	}

	/**
	 * Retourne l'image du lutin sur la vue
	 * @return ImageView l'image du lutin
	 */
	public ImageView getImage() {
		return this.imagePersonnage;
	}

	public void setRaterri(boolean b) {
		this.raterri=b;
		
	}
	
	public boolean getRaterri() {
		return this.raterri;
	}
	
	/**
	 * Retourne la valeur du timer qui permet de gérer le saut du lutin
	 * @return int la valeur du timer qui permet de gérer le saut du lutin
	 */
	public int getTimerSaut() {
		return this.timerSaut;
	}

	/**
	 * Permet de mettre à jour la valeur du timer de saut du lutin
	 * @param timerSaut
	 */
	public void setTimerSaut(int timerSaut) {
		this.timerSaut = timerSaut;
	}


	/**
	 * Permet de mettre à jour si le lutin est entrain de sauter ou non
	 * @param saut boolean true=Le lutin est entrain de sauter ; false=Le lutin n'est pas entrain de sauter
	 */
	public void setSaut(boolean saut) {
		this.saut = saut;
	}

	/**
	 * Retourne si le lutin est entrain de sauter ou non
	 * @return boolean true=Le lutin est entrain de sauter ; false=Le lutin n'est pas entrain de sauter
	 */
	public boolean isSaut() {
		return this.saut;
	}
	
	public double getCoordMapMinX() {
		return this.coordMapMinX;
	}

	public void setCoordMapMinX(double coord) {
		this.coordMapMinX = coord;
	}


	public double getCoordMapCentreX() {
		return this.coordMapCentreX;
	}

	public double getCoordMapMaxX() {
		return this.coordMapMaxX;
	}

	public void setCoordMapCentreX(double coordMapX) {
		this.coordMapCentreX = coordMapX;
	}

	public void setCoordMapMaxX(double coordMapX) {
		this.coordMapMaxX = coordMapX;
	}

}
