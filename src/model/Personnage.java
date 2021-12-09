package model;

import javafx.scene.image.ImageView;
import view.Niveau;

public abstract class Personnage {

	private boolean deplacementGauche;
	private boolean deplacementDroite;
	private boolean saut;
	private int timerSaut;
	private ImageView imagePersonnage;
	private Niveau niveau;
	private boolean raterri;

	public Personnage(ImageView imagePersonnage, double coordScreenX, double coordScreenY, Niveau niveau) {
		this.niveau = niveau;
		this.imagePersonnage = imagePersonnage;
		this.imagePersonnage.setX(coordScreenX);
		this.imagePersonnage.setY(coordScreenY);
		this.deplacementDroite=false;
		this.deplacementGauche=false;
		this.saut=false;
	}

	public abstract void seDeplace();

	public abstract void tombe();

	public abstract void sauter();

	public abstract boolean isMort();


	/**
	 * V�rifie si le personnage est dans le vide ou pas.
	 * Vide = Ne pas �tre sur un block
	 * @param block Le block sur lequel le lutin se trouve en X
	 * @return boolean true=Le lutin est dans le vide ; false=Le lutin n'est pas dans le vide
	 */
	public boolean isDansLeCiel() {
		if(this.blocDurDessousDirect(2)==null) {
			return true;
		} else {
			return this.imagePersonnage.getLayoutBounds().getMaxY()<=this.blocDurDessous().getBlock().getLayoutBounds().getMinY();
		}
	}


	/**
	 * Retourne le bloc au dessus du personnage � la distance sp�cifi�e dans les param�tres. 
	 * S'il est dur, le block est renvoy�. Sinon, null est renvoy�.
	 * @param distanceHaut distance
	 * @return Block si dur | Null sinon
	 */
	public Block blockDurDessus(int distanceHaut) {
		double minY = this.imagePersonnage.getLayoutBounds().getMinY();
		double minX = this.imagePersonnage.getLayoutBounds().getMinX();
		double maxX = this.imagePersonnage.getLayoutBounds().getMaxX();
		if(minY>0) {
			if((((int) maxX/Block.blockXSize 
					+ (((int) minY - distanceHaut)/Block.blockXSize)
					* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size() && ((int) maxX/Block.blockXSize 
							+ (((int) minY - distanceHaut)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length) >0)
					|| (((int) minX/Block.blockXSize 
							+ (((int) minY - distanceHaut)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size() && (((int) minX/Block.blockXSize 
									+ (((int) minY - distanceHaut)/Block.blockXSize)
									* niveau.getMatriceNiveau()[0].length)> 0))) {
				if(niveau.getBlocksNiveau().get(((int) maxX/Block.blockXSize 
						+ (((int) minY - distanceHaut)/Block.blockXSize)
						* niveau.getMatriceNiveau()[0].length)).isHardBlock()) {
					return niveau.getBlocksNiveau().get(((int) maxX/Block.blockXSize 
							+ (((int) minY - distanceHaut)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length));
				} else if (niveau.getBlocksNiveau().get(((int) minX/Block.blockXSize 
						+ (((int) minY - distanceHaut)/Block.blockXSize)
						* niveau.getMatriceNiveau()[0].length)).isHardBlock()) {
					return niveau.getBlocksNiveau().get(((int) minX/Block.blockXSize 
							+ (((int) minY - distanceHaut)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length));
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
	 * Retourne le bloc au dessous du personnage. S'il est dur, le block est renvoy�. Sinon, null est renvoy�.
	 * @return Block si dur | Null sinon
	 */
	public Block blocDurDessous() {
		double minY = this.imagePersonnage.getLayoutBounds().getMinY();
		double maxY = this.imagePersonnage.getLayoutBounds().getMaxY();
		double minX = this.imagePersonnage.getLayoutBounds().getMinX();
		double maxX = this.imagePersonnage.getLayoutBounds().getMaxX();
		//SI LE MAXX A EN DESSOUS DE LUI UN BLOC NON DUR
		//REGARDER LE MINX, SI LE MINY A EN DESSOUS DE LUI UN BLOC NON DUR
		//RENVOYER NULL
		if(minY>0) {
			if(((int) maxX/Block.blockXSize 
					+ (((int) maxY + 10)/Block.blockXSize)
					* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size() 
					|| ((int) minX/Block.blockXSize 
							+ (((int) maxY + 10)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size()) {
				if(niveau.getBlocksNiveau().get(((int) maxX/Block.blockXSize 
						+ (((int) maxY + 10)/Block.blockXSize)
						* niveau.getMatriceNiveau()[0].length)).isHardBlock()) {
					return niveau.getBlocksNiveau().get(((int) maxX/Block.blockXSize 
							+ (((int) maxY + 10)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length));
				} else if (niveau.getBlocksNiveau().get(((int) minX/Block.blockXSize 
						+ (((int) maxY + 10)/Block.blockXSize)
						* niveau.getMatriceNiveau()[0].length)).isHardBlock()) {
					return niveau.getBlocksNiveau().get(((int) minX/Block.blockXSize 
							+ (((int) maxY + 10)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length));
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
	 * Retourne le bloc � la droite du personnage. S'il est dur, le block est renvoy�. Sinon, null est renvoy�.
	 * @return Block si dur | Null sinon
	 */
	public Block blockDurDroite() {
		double minY = this.imagePersonnage.getLayoutBounds().getMinY();
		double maxY = this.imagePersonnage.getLayoutBounds().getMaxY();
		double maxX = this.imagePersonnage.getLayoutBounds().getMaxX();
		if(minY>0) {
			if((((int) maxX +2)/Block.blockXSize 
					+ (((int) maxY-10)/Block.blockXSize)
					* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size() 
					|| (((int) maxX+2)/Block.blockXSize 
							+ (((int) minY -10)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size()) {
				if(niveau.getBlocksNiveau().get((((int) maxX+2)/Block.blockXSize 
						+ (((int) maxY -10)/Block.blockXSize)
						* niveau.getMatriceNiveau()[0].length)).isHardBlock()) {
					System.out.println("Y a bien un bloc");
					return niveau.getBlocksNiveau().get((((int) maxX +2)/Block.blockXSize 
							+ (((int) maxY-10)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length));
				} else if (niveau.getBlocksNiveau().get((((int) maxX +2)/Block.blockXSize 
						+ (((int) minY)/Block.blockXSize)
						* niveau.getMatriceNiveau()[0].length)).isHardBlock()) {
					System.out.println("y a bien ce bloc");
					return niveau.getBlocksNiveau().get((((int) maxX +2)/Block.blockXSize 
							+ (((int) minY)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length));
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
	 * Retourne le bloc au dessous du personnage. S'il est dur, le block est renvoy�. Sinon, null est renvoy�.
	 * @return Block si dur | Null sinon
	 */
	public Block blocDurDessousDirect(int precision) {
		double minY = this.imagePersonnage.getLayoutBounds().getMinY();
		double maxY = this.imagePersonnage.getLayoutBounds().getMaxY();
		double minX = this.imagePersonnage.getLayoutBounds().getMinX();
		double maxX = this.imagePersonnage.getLayoutBounds().getMaxX();
		//SI LE MAXX A EN DESSOUS DE LUI UN BLOC NON DUR
		//REGARDER LE MINX, SI LE MINY A EN DESSOUS DE LUI UN BLOC NON DUR
		//RENVOYER NULL
		if(minY>0) {
			if(((int) maxX/Block.blockXSize 
					+ (((int) maxY + 10)/Block.blockXSize)
					* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size() 
					|| ((int) minX/Block.blockXSize 
							+ (((int) maxY + 10)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size()) {
				if(niveau.getBlocksNiveau().get((((int) maxX-precision)/Block.blockXSize 
						+ (((int) maxY + 10)/Block.blockXSize)
						* niveau.getMatriceNiveau()[0].length)).isHardBlock()) {
					return niveau.getBlocksNiveau().get((((int) maxX-precision)/Block.blockXSize 
							+ (((int) maxY + 10)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length));
				} else if (niveau.getBlocksNiveau().get((((int) minX+precision)/Block.blockXSize 
						+ (((int) maxY + 10)/Block.blockXSize)
						* niveau.getMatriceNiveau()[0].length)).isHardBlock()) {
					return niveau.getBlocksNiveau().get((((int) minX-precision)/Block.blockXSize 
							+ (((int) maxY + 10)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length));
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
	 * Retourne le bloc � la gauche du personnage. S'il est dur, le block est renvoy�. Sinon, null est renvoy�.
	 * @return Block si dur | Null sinon
	 */
	public Block blockDurGauche() {
		double minY = this.imagePersonnage.getLayoutBounds().getMinY();
		double maxY = this.imagePersonnage.getLayoutBounds().getMaxY();
		double minX = this.imagePersonnage.getLayoutBounds().getMinX();
		if(minY>0) {
			if((((int) minX-2)/Block.blockXSize 
					+ (((int) maxY -10)/Block.blockXSize)
					* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size() 
					|| (((int) minX -2)/Block.blockXSize 
							+ (((int) minY -10)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size()) {
				if(niveau.getBlocksNiveau().get((((int) minX-2)/Block.blockXSize 
						+ (((int) maxY -10)/Block.blockXSize)
						* niveau.getMatriceNiveau()[0].length)).isHardBlock()) {
					System.out.println("Y a bien un bloc");
					return niveau.getBlocksNiveau().get((((int) minX -2)/Block.blockXSize 
							+ (((int) maxY -10)/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length));
				} else if (niveau.getBlocksNiveau().get((((int) minX -2)/Block.blockXSize 
						+ (((int) minY )/Block.blockXSize)
						* niveau.getMatriceNiveau()[0].length)).isHardBlock()) {
					System.out.println("y a bien ce bloc");
					return niveau.getBlocksNiveau().get((((int) minX -2)/Block.blockXSize 
							+ (((int) minY )/Block.blockXSize)
							* niveau.getMatriceNiveau()[0].length));
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


	public Boolean isSurDernierBlock() {
		double centerY = this.imagePersonnage.getLayoutBounds().getCenterY();
		double centerX = this.imagePersonnage.getLayoutBounds().getCenterX();
		if((((int) centerX)/Block.blockXSize 
				+ (((int) centerY)/Block.blockXSize)
				* niveau.getMatriceNiveau()[0].length) <= niveau.getBlocksNiveau().size()) {
			if(niveau.getBlocksNiveau().get((((int)centerX)/Block.blockXSize 
					+ (((int) centerY)/Block.blockXSize)
					* niveau.getMatriceNiveau()[0].length)).isBlockDeFin()) {
				return true;
			}
		}
		return false;
	}

/**
 * Permet de changer la variable deplacementGauche
 * @param deplacementGauche booleen true si il se d�place sur la gauche | false si il ne se d�place pas sur la droite
 */
public void setDeplacementGauche(boolean deplacementGauche) {
	this.deplacementGauche = deplacementGauche;
}

/**
 * Permet de changer la variable deplacementGauche
 * @param deplacementGauche booleen true si il se d�place sur la gauche | false si il ne se d�place pas sur la droite
 */
public void setDeplacementDroite(boolean deplacementDroite) {
	this.deplacementDroite = deplacementDroite;
}

/**
 * Retourne un booleen qui informe si le lutin se d�place vers la gauche ou pas
 * @return boolean true=Le lutin se d�place vers la gauche ; false=Le lutin ne se d�place pas vers la gauche
 */
public boolean isDeplacementGauche() {
	return this.deplacementGauche;
}

/**
 * Retourne un booleen qui informe si le lutin se d�place vers la gauche ou pas
 * @return boolean true=Le lutin se d�place vers la droite ; false=Le lutin ne se d�place pas vers la droite
 */
public boolean isDeplacementDroite() {
	return this.deplacementDroite;
}

/**
 * Retourne l'image du personnage.
 * @return ImageView l'image du lutin
 */
public ImageView getImage() {
	return this.imagePersonnage;
}

/**
 * Mets � jour l'attribut ratteri en fonction de si le lutin est entrain de raterrir ou non
 * @param ratteri true=il ratteri | false=il ne ratteri pas
 */
public void setRaterri(boolean ratteri) {
	this.raterri=ratteri;

}

/**
 * Retourne si le personnage est entrain de raterrir ou pas
 * @return
 */
public boolean getRaterri() {
	return this.raterri;
}

/**
 * Retourne la valeur du timer qui permet de g�rer le saut du lutin
 * @return int la valeur du timer qui permet de g�rer le saut du lutin
 */
public int getTimerSaut() {
	return this.timerSaut;
}

/**
 * Permet de mettre � jour la valeur du timer de saut du lutin
 * @param timerSaut
 */
public void setTimerSaut(int timerSaut) {
	this.timerSaut = timerSaut;
}


/**
 * Permet de mettre � jour si le lutin est entrain de sauter ou non
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

}
