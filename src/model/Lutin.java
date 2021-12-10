package model;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Niveau;

/**
 * Classe du Personnage Lutin.
 */
public class Lutin extends Personnage {

	private static int vie = 2;
	private static double VITESSE_DEPLACEMENT = 5;
	private double vitesseY = 0;
	private static double G = 0.1d;
	private boolean rebondi;
	private boolean touche;
	private ImageView imageLutin;

	/**
	 * Construit un lutin détenant une certaine coordonnée et un ImageView qui correspond à sa représentation sur la vue.
	 * @param lutin
	 * @param coordX
	 * @param coordY
	 */
	public Lutin(double coordScreenX, double coordScreenY) {
		super(new ImageView(getSkin()), coordScreenX, coordScreenY);
		this.imageLutin = this.getImage();
		this.touche=false;
		this.rebondi=false;
		this.setDeplacementDroite(false);
		this.setDeplacementGauche(false);
	}

	/**
	 * Fait tomber le personnage progressivement.
	 * Cette fonction réduit les coordonnées en Y du lutin progressivement en fonction du facteur de gravité "G".
	 */
	public void tombe(Niveau niveau) {
		this.imagePersonnage.setY(this.imagePersonnage.getY() + this.vitesseY);
		this.vitesseY=this.vitesseY + G;
	}
	
	/** Javadoc sur l'abstract **/
	@Override
	public void seDeplace(Niveau niveau) {
		//Si le personnage se déplace à droite alors on recupère la position maximum de la map à droite et on regarde s'il peut avancer.
		if(this.isDeplacementDroite()) {
			if(this.blockDurDroite(niveau)==null) {
				if(!this.isDansLeCiel(niveau)) {
					if(this.imageLutin.getX()+VITESSE_DEPLACEMENT>=niveau.getBlocksNiveau().get(niveau.getBlocksNiveau().size()-1).getBlock().getLayoutBounds().getMaxX()) {
						this.imageLutin.setX(niveau.getBlocksNiveau().get(niveau.getBlocksNiveau().size()-1).getBlock().getLayoutBounds().getMinX());
					} else {
						this.imageLutin.setX(this.imageLutin.getX() + VITESSE_DEPLACEMENT);
					}
				} else {
					if(this.blockDurDroite(niveau)==null || this.imageLutin.getLayoutBounds().getMaxX()+VITESSE_DEPLACEMENT<this.blockDurDroite(niveau).getBlock().getLayoutBounds().getMinX()) {
						if(this.imageLutin.getX()+VITESSE_DEPLACEMENT>=niveau.getBlocksNiveau().get(niveau.getBlocksNiveau().size()-1).getBlock().getLayoutBounds().getMaxX()) {
							this.imageLutin.setX(niveau.getBlocksNiveau().get(niveau.getBlocksNiveau().size()-1).getBlock().getLayoutBounds().getMinX());
						} else {
							this.imageLutin.setX(this.imageLutin.getX() + VITESSE_DEPLACEMENT);
						}
					}
				}
			}
		}
		if(this.blockDurGauche(niveau)==null) {
			//Si le personnage se déplace à gauche alors on recupère la position maximum de la map à gauche et on regarde s'il peut avancer.
			if(this.isDeplacementGauche()) {
				if(!isDansLeCiel(niveau)) {
					if(this.imageLutin.getX()-VITESSE_DEPLACEMENT<=niveau.getBlocksNiveau().get(0).getBlock().getLayoutBounds().getMinX()) {
						this.imageLutin.setX(niveau.getBlocksNiveau().get(0).getBlock().getLayoutBounds().getMinX());
					} else {
						this.imageLutin.setX(this.imageLutin.getX() - VITESSE_DEPLACEMENT);
					}
				} else {
					if(this.blockDurGauche(niveau)==null || this.imageLutin.getLayoutBounds().getMinX()-VITESSE_DEPLACEMENT<this.blockDurGauche(niveau).getBlock().getLayoutBounds().getMaxX()) {
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

	/** Javadoc sur l'abstract **/
	@Override
	public void sauter(Niveau niveau) {
		int tailleSaut=150;
		boolean blocDurDessus=false;
		boolean pasBlocDurDessus=false;
		int i=0;
		if (!this.isDansLeCiel(niveau)) {
			do {
				if(i>=tailleSaut) {
					pasBlocDurDessus=true;
				}
				if(this.blockDurDessus(i, niveau)==null) {
				} else if (this.blockDurDessus(5, niveau)==null){
					this.imageLutin.setY(this.blockDurDessus(i, niveau).getBlock().getLayoutBounds().getMaxY());
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
	 * Retourne si le lutin se trouve sur le dernier bloc du niveau ou non.
	 * @return booleen true=Le lutin est sur le dernier bloc | false=Le lutin n'est pas sur le dernier bloc
	 */
	public Boolean isSurDernierBlock(Niveau niveau) {
		double centerY = this.getImage().getLayoutBounds().getCenterY();
		double centerX = this.getImage().getLayoutBounds().getCenterX();
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
	 * Cette fonction fait rebondir le lutin.
	 * Elle est invoquée lorsque le lutin saute sur un mob.
	 */
	public void rebond(Niveau niveau) {
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
				if(this.blockDurDessus(i, niveau)==null) {
				} else if (this.blockDurDessus(5, niveau)==null){
					this.imageLutin.setY(this.blockDurDessus(i, niveau).getBlock().getLayoutBounds().getMaxY());
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
	 * Retourne le skin que doit avoir le lutin sur le niveau.
	 * Le skin retourné dépend de celui qui a été sélectionné sur le menu de customisation
	 * @return Image le skin
	 */
	public static Image getSkin() {
		FileManager fileManager = new FileManager();
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> read = (HashMap<String, Integer>) fileManager.readFile("skin");
		for(Map.Entry<String, Integer> entry : read.entrySet()) {
		    Integer skin = entry.getValue();
		    switch(skin) {
		    case 0 : 
		    	return new Image("lutinBleu.png");
		    case 1 : 
		    	return new Image("lutinRouge.png");
		    case 2 : 
		    	return new Image("lutinVert.png");
		    default : 
		    	return new Image("lutinVert.png");
		    }
		
		}
		return new Image("lutinVert.png");
	}

	/**
	 * Setter pour la vitesse de déplacement
	 * @param i double la nouvelle vitesse
	 */
	public void setVitesse(double i) {
		VITESSE_DEPLACEMENT = i;
		
	}

	/**
	 * Retourne la vitesse de déplacement du lutin
	 * @return double la vitesse de déplacement du lutin
	 */
	public double getVitesse() {
		return VITESSE_DEPLACEMENT;
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
	
	/**
	 * Setter pour la gravité du lutin
	 * @param g la gravité
	 */
	public void setG(double g) {
		G = g;
	}
}
