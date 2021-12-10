package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.FileManager;
import model.Lutin;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Block;
import model.BonhommeDeNeige;
import model.Chronometre;
import model.PereNoel;
import model.Vie;

/**
 * Classe abstraite mère de nos niveaux. C'est ici que sont générés les niveaux.
 */
public abstract class Niveau {
	
	private Chronometre chronometre;
	private Group root;
	private ArrayList<Block> colisionBlocks;
	private ArrayList<Block> generatedMap;
	private ArrayList<BonhommeDeNeige> mobAffiche;
	private ArrayList<PereNoel> bossAffiche;
	private double coordX;
	private double coordY;
	private int[][] matriceNiveau;
	protected Vie vie;
	

	/**
	 * Initialisation de notre Niveau
	 * @param root le contenu de notre fenêtre
	 * @param generationTab la matrice du niveau que l'on va générer
	 */
	public Niveau(Group root, int[][] matriceNiveau) {
		root.getChildren().clear();
		this.matriceNiveau = matriceNiveau;
		this.chronometre = new Chronometre(new Label("00.00.00"));
		this.root=root;
		this.generatedMap = new ArrayList<Block>();
		this.colisionBlocks = new ArrayList<Block>();
		this.mobAffiche = new ArrayList<BonhommeDeNeige>();
		this.bossAffiche = new ArrayList<PereNoel>();
		this.coordX=0;
		this.coordY=0;
	}
	
	/**
	 * Ajoute les entités sur notre niveau (tel que le lutin, les bonhommes de neige... etc)
	 */
	public abstract void addEntities();
	
	/**
	 * Génère le niveau en fonction des entiers présents sur la matrice.
	 * @param generationTab la matrice du niveau
	 */
	public void generateLevel(int[][] generationTab) {
		for(int i=0; i<generationTab.length; i++) {
			for(int j=0;j<generationTab[i].length; j++) {
				Block block = generateBlock(generationTab[i][j]);
				root.getChildren().add(block.getBlock());
				this.generatedMap.add(block);
				addBlockToList(block);
				if(block.isMobBlock()) {
					BonhommeDeNeige mob = new BonhommeDeNeige(this.coordX, this.coordY);
					root.getChildren().addAll(mob.getImage());
					this.mobAffiche.add(mob);
				}
				if(block.isBossBlock()) {
					PereNoel boss = new PereNoel(this.coordX, this.coordY);
					root.getChildren().addAll(boss.getImage());
					this.bossAffiche.add(boss);
				}
				this.coordX+=64;
			}
			this.coordX=0;
			this.coordY+=64;
		}
		root.getChildren().add(this.chronometre.getChronometre());
	}

	/**
	 * Fonction ajoutant un boss au niveau
	 * @param boss le boss
	 */
	public void addBoss(PereNoel boss) {
		this.bossAffiche.add(boss);
	}
	
	/**
	 * Retourne la liste de boss contenu sur le niveau.
	 * @return ArrayList<PereNoel> la liste de boss
	 */
	public ArrayList<PereNoel> getBoss() {
		return this.bossAffiche;
	}
	
	/**
	 * Fonction retournant le nom de l'image à placer sur notre vue, en fonction du chiffre qui a été défini sur la variable "matriceNiveau"
	 * @param chiffre un chiffre qui a été défini sur la variable "matriceNiveau"
	 * @return 
	 * @return String le nom de l'image à placer
	 */
	public Block generateBlock(int chiffre) {
		Block block;
		switch(chiffre) {
		case 0:
			block = new Block("ciel.png");
			break;
		case 1:
			block = new Block("bloc1.png");
			block.setHardBlock(true);
			break;
		case 2:
			block = new Block("bloc2.png");
			block.setHardBlock(true);
			break;
		case 3:
			block = new Block("cielNuage1png.png");
			break;
		case 4:
			block = new Block("cielNuage2.png");
			break;
		case 5:
			block = new Block("barreFinale1.png");
			block.setBlockDeFin(true);
			break;
		case 6:
			block = new Block("barreFinale2.png");
			block.setBlockDeFin(true);
			break;
		case 7:
			block = new Block("blocpiege.png");
			block.setHardBlock(false);
			break;
		case 8:
			block = new Block("ciel.png");
			block.setBossBlock(true);
			break;
		case 9:
			block = new Block("ciel.png");
			block.setMobBlock(true);
			break;
		case 10:
			block = new Block("blocSpecial.png");
			block.setHardBlock(true);
			break;
		default:
			block = new Block("ciel.png");
			break;
		}
		
		block.getBlock().setX(this.coordX);
		block.getBlock().setY(this.coordY);
		return block;
	}
	
	/**
	 * Retourne l'objet vie contenu sur notre Niveau
	 * @return Vie la vie du lutin
	 */
	public Vie getVie() {
		return this.vie;
	}
	
	/**
	 * Fonction permettant d'ajouter les blocks de type "dur" à notre liste de blocks dur
	 * @param Block le block à ajouter dans le cas où il est de type sol
	 */
	public void addBlockToList(Block block) {
		if(block.isHardBlock()) {
			this.colisionBlocks.add(block);
		}
	}
	
	/**
	 * Méthode ajoutant des bonhommes de neige à notre liste
	 * @param mob le bonhomme de neige
	 */
	public void addBonhommeNeige(BonhommeDeNeige mob) {
		this.mobAffiche.add(mob);
	}
	
	/**
	 * Retourne la liste des bonhommes de neiges contenus sur notre niveau
	 * @return ArrayList<BonhommeNeige> la liste des bonhommes de neige
	 */
	public ArrayList<BonhommeDeNeige> getBonhommesNeige() {
		return this.mobAffiche;
	}
	
	/**
	 * Retourne la matrice de notre niveau
	 * @return int[][] la matrice de notre niveau
	 */
	public int[][] getMatriceNiveau() {
		return this.matriceNiveau;
	}
	
	/**
	 * Retourne notre liste des blocks durs qui composent notre niveau.
	 * @return ArrayList<Block> la liste des blocks durs du niveau
	 */
	public ArrayList<Block> getBlocksDurs() {
		return this.colisionBlocks;
	}
	
	/**
	 * Retourne la liste des images contenues sur notre vue sous la forme d'une liste
	 * @return ArrayList<Block> la liste des blocks du niveau
	 */
	public ArrayList<Block> getBlocksNiveau() {
		return this.generatedMap;
	}
	
	/**
	 * Retourne le Label du chronomètre qui figure sur notre niveau.
	 * @return Label le chronomètre
	 */
	public Chronometre getChronometre() {
		return this.chronometre;
	}
}
