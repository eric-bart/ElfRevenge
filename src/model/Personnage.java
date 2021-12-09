package model;

import javafx.scene.image.ImageView;
import view.Niveau;

/**
 * Classe abstraite mère de nos personnage.
 */
public abstract class Personnage {

	private boolean deplacementGauche;
	private boolean deplacementDroite;
	private boolean saut;
	private int timerSaut;
	private boolean raterri;
	protected ImageView imagePersonnage;
	
	
	public Personnage(ImageView imagePersonnage, double coordScreenX, double coordScreenY) {
		this.imagePersonnage = imagePersonnage;
		this.imagePersonnage.setX(coordScreenX);
		this.imagePersonnage.setY(coordScreenY);
		this.deplacementDroite=false;
		this.deplacementGauche=false;
		this.saut=false;
	}

	/**
	 * Fait se déplacer le personnage
	 */
	public abstract void seDeplace(Niveau niveau);

	/**
	 * Fait sauter le personnage
	 */
	public abstract void sauter(Niveau niveau);

	/**
	 * Retourne un booleen selon si le le personnage est mort ou pas.
	 * @return boolean true=Le bonhomme de neige est mort | false=Le bonhomme de neige n'est pas mort
	 */
	public boolean isMort() {
		return this.imagePersonnage.getY()>=760;
	}

	/**
	 * Vérifie si le personnage est dans le vide ou pas.
	 * Vide = Ne pas être sur un block
	 * @param block Le block sur lequel le lutin se trouve en X
	 * @return boolean true=Le lutin est dans le vide ; false=Le lutin n'est pas dans le vide
	 */
	public boolean isDansLeCiel(Niveau niveau) {
		if(this.blocDurDessousDirect(2, niveau)==null) {
			return true;
		} else {
			return this.imagePersonnage.getLayoutBounds().getMaxY()<=this.blocDurDessous(niveau).getBlock().getLayoutBounds().getMinY();
		}
	}


	/**
	 * Retourne le bloc au dessus du personnage � la distance sp�cifi�e dans les param�tres. 
	 * S'il est dur, le block est renvoy�. Sinon, null est renvoy�.
	 * @param distanceHaut distance
	 * @return Block si dur | Null sinon
	 */
	public Block blockDurDessus(int distanceHaut, Niveau niveau) {
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
	public Block blocDurDessous(Niveau niveau) {
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
	 * Retourne le bloc à la droite du personnage. S'il est dur, le block est renvoyé. Sinon, null est renvoyé.
	 * @return Block si dur | Null sinon
	 */
	public Block blockDurDroite(Niveau niveau) {
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
	 * Retourne le bloc au dessous du personnage. S'il est dur, le block est renvoyé. Sinon, null est renvoyé.
	 * @return Block si dur | Null sinon
	 */
	public Block blocDurDessousDirect(int precision, Niveau niveau) {
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
	 * Retourne le bloc à la gauche du personnage. S'il est dur, le block est renvoyé. Sinon, null est renvoyé.
	 * @return Block si dur | Null sinon
	 */
	public Block blockDurGauche(Niveau niveau) {
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

	/**
	 * Permet de changer la variable deplacementGauche
	 * @param deplacementGauche booleen true si il se déplace sur la gauche | false si il ne se déplace pas sur la droite
	 */
	public void setDeplacementGauche(boolean deplacementGauche) {
		this.deplacementGauche = deplacementGauche;
	}

	/**
	 * Permet de changer la variable deplacementGauche
	 * @param deplacementGauche booleen true si il se déplace sur la gauche | false si il ne se déplace pas sur la droite
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
	 * Retourne l'image du personnage.
	 * @return ImageView l'image du lutin
	 */
	public ImageView getImage() {
		return this.imagePersonnage;
	}

	/**
	 * Mets à jour l'attribut ratteri en fonction de si le lutin est entrain de raterrir ou non
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

}
