package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Block;
import model.Personnage;
import model.SolidBlock;
import model.TransparentBlock;

public abstract class Niveau {
	
	private Label chronometre = new Label("00.00.00");
	private Group root;
	private ArrayList<Block> colisionBlocks;
	private ArrayList<Block> generatedMap;
	private ArrayList<Personnage> mobAffiche;
	private double coordX;
	private double coordY;

	public Niveau(Group root) {
		root.getChildren().clear();
		this.root=root;
		this.generatedMap = new ArrayList<Block>();
		this.colisionBlocks = new ArrayList<Block>();
		this.mobAffiche = new ArrayList<Personnage>();
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
				this.coordX+=64;
			}
			this.coordX=0;
			this.coordY+=64;
		}
		root.getChildren().add(this.chronometre);
		System.out.println(root.getChildren().get(0));
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
			return new TransparentBlock("barreFinale1.png");
		case 6:
			return new TransparentBlock("barreFinale2.png");
		case 7:
			return new SolidBlock("mob1.png");
		default:
			return new TransparentBlock("ciel.png");
		}
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
	
	public void addMobAffiche(Personnage mob) {
		this.mobAffiche.add(mob);
	}
	
	public ArrayList<Personnage> getMobAffiche() {
		return this.mobAffiche;
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
}
