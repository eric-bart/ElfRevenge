package model;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import view.Niveau1;

public class Lutin {

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
	private ImageView lutin;

	/**
	 * Construit un lutin détenant une certaine coordonnée et un ImageView qui correspond à sa représentation sur la vue.
	 * @param lutin
	 * @param coordX
	 * @param coordY
	 */
	public Lutin(ImageView lutin, double coordX, double coordY) {
		this.lutin = lutin;
		lutin.setX(coordX);
		lutin.setY(coordY);
		this.deplacementDroite=false;
		this.deplacementGauche=false;
		this.colisionDroite=false;
		this.colisionGauche=false;
		this.saut=false;
	}

	/**
	 * Retourne la vitesse de saut du lutin
	 * @return double la vitesse de saut du lutin
	 */
	public double getVITESSESAUT() {
		return VITESSESAUT;
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

	/**
	 * Permet de récupérer la valeur de gravité du lutin
	 * @return double la valeur de gravité du lutin
	 */
	public double getG() {
		return G;
	}

	/**
	 * Permet de mettre à jour la vitesse en Y du lutin
	 * @param vitesseY
	 */
	public void setVitesseY(double vitesseY) {
		this.vitesseY = vitesseY;
	}
	
	/**
	 * Permet de récupérer la vitesse du lutin en Y (haut/bas)
	 * @return double la vitesse en Y du lutin
	 */
	public double getVitesseY() {
		return this.vitesseY;
	}

	/**
	 * Déplace le personnage sur le niveau
	 * @param niveau Le niveau concerné
	 */
	public void seDeplace(Niveau1 niveau) {
		//Si le personnage se déplace à droite alors on recupère la position maximum de la map à droite et on regarde s'il peut avancer.
		if(this.deplacementDroite) {
			if(this.lutin.getX()+VITESSE_DEPLACEMENT>=niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMaxX()) {
				this.lutin.setX(niveau.getGeneration().get(niveau.getGeneration().size()-1).getBlock().getLayoutBounds().getMinX());
			} else {
				this.lutin.setX(this.lutin.getX() + VITESSE_DEPLACEMENT);
			}
		}
		//Si le personnage se déplace à gauche alors on recupère la position maximum de la map à gauche et on regarde s'il peut avancer.
		if(this.deplacementGauche) {
			if(this.lutin.getX()+VITESSE_DEPLACEMENT<=niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX()) {
				this.lutin.setX(niveau.getGeneration().get(0).getBlock().getLayoutBounds().getMinX());
			} else {
				this.lutin.setX(this.lutin.getX() - VITESSE_DEPLACEMENT);
			}
		}
	}
	
	public boolean isColisionGauche(Block image) {
		return this.lutin.getLayoutBounds().getMinX()>=image.getBlock().getLayoutBounds().getMinX() 
				&& this.lutin.getLayoutBounds().getMinX()<= image.getBlock().getLayoutBounds().getMaxX()
				&& this.lutin.getLayoutBounds().getMinY()<=image.getBlock().getLayoutBounds().getMaxY()
				&& this.lutin.getLayoutBounds().getMaxY()>=image.getBlock().getLayoutBounds().getMinY()
				&& image.isHardBlock();
	}
	
	public boolean isColisionDroite(Block image) {
		return this.lutin.getLayoutBounds().getMaxX()>=image.getBlock().getLayoutBounds().getMinX() 
				&& this.lutin.getLayoutBounds().getMaxX() <= image.getBlock().getLayoutBounds().getMaxX()
				&& this.lutin.getLayoutBounds().getMinY()<=image.getBlock().getLayoutBounds().getMaxY()
				&& this.lutin.getLayoutBounds().getMaxY()>=image.getBlock().getLayoutBounds().getMinY()
				&& image.isHardBlock();
	}
	
	public boolean isColisionDessus(Block image) {
		return this.lutin.getLayoutBounds().getMinX()>=image.getBlock().getLayoutBounds().getMinX() 
				&& this.lutin.getLayoutBounds().getMinX()<=image.getBlock().getLayoutBounds().getMaxX()
				|| this.lutin.getLayoutBounds().getMaxX()>=image.getBlock().getLayoutBounds().getMinX() 
				&& this.lutin.getLayoutBounds().getMaxX()<=image.getBlock().getLayoutBounds().getMaxX()
				&& image.isHardBlock();
	}
	
	/**
	 * Vérifie si le personnage est dans le vide ou pas.
	 * Vide = Ne pas être sur un block
	 * @param block Le block sur lequel le lutin se trouve en X
	 * @return boolean true=Le lutin est dans le vide ; false=Le lutin n'est pas dans le vide
	 */
	public boolean isDansLeCiel(Block block) {
		
		//Si le lutin a les pieds dans le vide
		//ou si le lutin a les pieds dans 
		/**return this.lutin.getLayoutBounds().getMaxY()<block.getLayoutBounds().getMinY() 
				||  this.lutin.getLayoutBounds().getMaxY()>block.getLayoutBounds().getMaxY()
				&& (!(this.lutin.getLayoutBounds().getMinY()>block.getLayoutBounds().getMaxY())
				|| this.lutin.getLayoutBounds().getMinY()<block.getLayoutBounds().getMinX());**/
		return this.lutin.getLayoutBounds().getMaxY()<block.getBlock().getLayoutBounds().getMinY()
				|| this.lutin.getLayoutBounds().getMinY()<block.getBlock().getLayoutBounds().getMaxY() 
				&& this.lutin.getLayoutBounds().getMaxY()<block.getBlock().getLayoutBounds().getMinY();
	}
	
	/**
	 * Retourne le bloc actuel dans lequel se trouve le lutin
	 * @param niveau
	 * @return ImageView le bloc sur lequel se trouve le lutin
	 */
	public Block blocActuelLutin(Niveau1 niveau) {
		if(((int) this.lutin.getLayoutBounds().getCenterX()/Block.blockXSize
				+ (((int) this.lutin.getLayoutBounds().getCenterY())/Block.blockXSize)
				* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size()) {
			return niveau.getGeneration().get(((int) this.lutin.getLayoutBounds().getCenterX()/Block.blockXSize
				+ (((int) this.lutin.getLayoutBounds().getCenterY())/Block.blockXSize)
				* niveau.getGenerationMap()[0].length));
		} else {
			return null;
		}
	}
	
	/**
	 * Retourne le bloc situé en dessous du lutin
	 * @param niveau
	 * @return ImageView le bloc situé en dessous du lutin
	 */
	public Block blocDessousLutin(Niveau1 niveau) {
		if(((int) this.lutin.getLayoutBounds().getCenterX()/Block.blockXSize 
				+ (((int) this.lutin.getLayoutBounds().getCenterY() + Block.blockXSize)/Block.blockXSize)
				* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size()) {
			return niveau.getGeneration().get(((int) this.lutin.getLayoutBounds().getCenterX()/Block.blockXSize 
				+ (((int) this.lutin.getLayoutBounds().getCenterY() + Block.blockXSize)/Block.blockXSize)
				* niveau.getGenerationMap()[0].length));
		} else {
			return null;
		}
	}
	
	/**
	 * Retourne le bloc situé à droite du lutin
	 * @param niveau
	 * @return ImageView le bloc situé à droite du lutin
	 */
	public Block blocDroiteLutin(Niveau1 niveau) {
		if((((int) this.lutin.getLayoutBounds().getCenterX()+Block.blockXSize)/Block.blockXSize 
				+ (((int) this.lutin.getLayoutBounds().getCenterY())/Block.blockXSize)
				* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size()) {
			return niveau.getGeneration().get((((int) this.lutin.getLayoutBounds().getCenterX()+Block.blockXSize)/Block.blockXSize 
				+ (((int) this.lutin.getLayoutBounds().getCenterY())/Block.blockXSize)
				* niveau.getGenerationMap()[0].length));
		} else {
			return null;
		}
	}
	
	/**
	 * Retourne le bloc situé à gauche du lutin
	 * @param niveau
	 * @return ImageView le bloc situé à gauche du lutin
	 */
	public Block blocGaucheLutin(Niveau1 niveau) {
		if((((int) this.lutin.getLayoutBounds().getCenterX()-Block.blockXSize)/Block.blockXSize
				+ (((int) this.lutin.getLayoutBounds().getCenterY())/Block.blockXSize)
				* niveau.getGenerationMap()[0].length) <= niveau.getGeneration().size()) {
			return niveau.getGeneration().get((((int) this.lutin.getLayoutBounds().getCenterX()-Block.blockXSize)/Block.blockXSize
				+ (((int) this.lutin.getLayoutBounds().getCenterY())/Block.blockXSize)
				* niveau.getGenerationMap()[0].length));
		} else {
			return null;
		}
	}
	
	
	
	
	/**
	 * Retourne la ligne de blocs sur lequel le lutin se trouve
	 * @param niveau
	 * @return ArrayList<ImageView> La liste de blocks sur laquelle le lutin se trouve
	 */
	public ArrayList<Block> ligneBlockLutin(Niveau1 niveau) {
		ArrayList<Block> listeBlockLigne = new ArrayList<Block>();
		int res = 0;
		for(int i=0;i<(int) this.lutin.getLayoutBounds().getCenterY()/Block.blockXSize * niveau.getGenerationMap()[0].length;i+=niveau.getGenerationMap()[0].length) {
			res = (int) this.lutin.getLayoutBounds().getCenterX()/Block.blockXSize + i;
			//System.out.println("Ajout du bloc " + res);
			if(niveau.getColisionBlocks().contains(niveau.getGeneration().get((int) this.lutin.getLayoutBounds().getCenterX()/Block.blockXSize + i))) {
				listeBlockLigne.add(niveau.getGeneration().get((int) this.lutin.getLayoutBounds().getCenterX()/Block.blockXSize + i));
			}
			
		}
		return listeBlockLigne;
	}

	/**
	 * Retourne la vitesse de déplacement du lutin
	 * @return double la vitesse de déplacement du lutin
	 */
	public double getVitesseDeplacement() {
		return VITESSE_DEPLACEMENT;
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
	public ImageView getLutin() {
		return this.lutin;
	}
}
