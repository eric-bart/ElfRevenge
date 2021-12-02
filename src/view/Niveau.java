package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.FileManager;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Block;
import model.BonhommeDeNeige;
import model.Lutin;
import model.PereNoel;
import model.Personnage;
import model.SolidBlock;
import model.TransparentBlock;
import model.Vie;

public abstract class Niveau {
	
	private Label chronometre;
	private Group root;
	private ArrayList<Block> colisionBlocks;
	private ArrayList<Block> generatedMap;
	private ArrayList<BonhommeDeNeige> mobAffiche;
	private ArrayList<PereNoel> bossAffiche;
	private double coordX;
	private double coordY;
	private int[][] generationTab;
	protected Vie vie;
	

	public Niveau(Group root, int[][] generationTab) {
		root.getChildren().clear();
		this.generationTab = generationTab;
		this.chronometre = new Label("00.00.00");
		this.root=root;
		this.generatedMap = new ArrayList<Block>();
		this.colisionBlocks = new ArrayList<Block>();
		this.mobAffiche = new ArrayList<BonhommeDeNeige>();
		this.bossAffiche = new ArrayList<PereNoel>();
		this.coordX=0;
		this.coordY=0;
		
	}
	
	public abstract void addEntities();
	
	/**
	 * Fonction permettant de placer les blocs sur notre vue suivant la disposition définie sur la variable "generationTab"
	 */
	public void generateLevel(int[][] generationTab) {
		for(int i=0; i<generationTab.length; i++) {
			for(int j=0;j<generationTab[i].length; j++) {
				Block newBlock = getImageType(generationTab[i][j]);
				root.getChildren().add(newBlock.getBlock());
				this.generatedMap.add(newBlock);
				addBlockToList(newBlock);
				newBlock.getBlock().setX(this.coordX);
				newBlock.getBlock().setY(this.coordY);
				if(isMobBlock(generationTab[i][j])) {
					BonhommeDeNeige mob = new BonhommeDeNeige(new ImageView(new Image("mob1.png")), this.coordX, this.coordY, this);
					root.getChildren().addAll(mob.getImage());
					this.mobAffiche.add(mob);
				}
				if(isBossBlock(generationTab[i][j])) {
					PereNoel boss = new PereNoel(new ImageView(new Image("perenoel.png")), this.coordX, this.coordY, this);
					root.getChildren().addAll(boss.getImage());
					this.bossAffiche.add(boss);
				}
				this.coordX+=64;
			}
			this.coordX=0;
			this.coordY+=64;
		}
		root.getChildren().add(this.chronometre);
		System.out.println(root.getChildren().get(0));
	}
	
	public boolean isMobBlock(int i) {
		if(i==20) {
			return true;
		}
		return false;
	}
	
	public boolean isBossBlock(int i) {
		if(i==19) {
			return true;
		}
		return false;
	}

	public void addBossAffiche(PereNoel boss) {
		this.bossAffiche.add(boss);
	}
	
	public ArrayList<PereNoel> getBossAffiche() {
		return this.bossAffiche;
	}
	
	/**
	 * Fonction retournant le nom de l'image à placer sur notre vue, en fonction du chiffre qui a été défini sur la variable "generationTab"
	 * @param chiffre un chiffre qui a été défini sur la variable "generationTab"
	 * @return String le nom de l'image à placer
	 */
	public Block getImageType(int chiffre) {
		switch(chiffre) {
		case 0:
			return new TransparentBlock("ciel.png");
		case 1:
			return new SolidBlock("bloc1.png");
		case 2:
			return new SolidBlock("bloc2.png");
		case 3:
			return new TransparentBlock("cielNuage1png.png");
		case 4:
			return new TransparentBlock("cielNuage2.png");
		case 5:
			return new SolidBlock("barreFinale1.png");
		case 6:
			return new SolidBlock("barreFinale2.png");
		case 7:
			return new SolidBlock("mob1.png");
		case 20:
			return new TransparentBlock("ciel.png");
		default:
			return new TransparentBlock("ciel.png");
		}
	}
	
	public Vie getVie() {
		return this.vie;
	}
	
	
	
	/**
	 * Fonction permettant d'ajouter les blocks de type "sol" à notre liste de blocks de sol
	 * @param fond le nom de l'image à vérifier
	 * @param image l'image à ajouter dans le cas où son nom correspond à un block de type sol
	 */
	public void addBlockToList(Block block) {
		if(block.isHardBlock()) {
			this.colisionBlocks.add(block);
		}
	}
	
	public void addMobAffiche(BonhommeDeNeige mob) {
		this.mobAffiche.add(mob);
	}
	
	public ArrayList<BonhommeDeNeige> getMobAffiche() {
		return this.mobAffiche;
	}
	
	/**
	 * Retourne le tableau de génération de notre map
	 */
	public int[][] getGenerationMap() {
		return this.generationTab;
	}
	
	/**
	 * Retourne notre liste de blocks qui soumettent notre lutin à une colision
	 * @return la liste de blocks
	 */
	public ArrayList getColisionBlocks() {
		return this.colisionBlocks;
	}
	
	/**
	 * Retourne la liste des images contenues sur notre vue sous la forme d'une liste
	 * @return la liste d'images
	 */
	public ArrayList<Block> getGeneration() {
		return this.generatedMap;
	}
	
	public Label getChronometre() {
		return this.chronometre;
	}
	
	public Image getSkin() {
		FileManager fileManager = new FileManager();
		ImageView skinLutin = new ImageView();
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
}
