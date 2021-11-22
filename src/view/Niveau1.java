package view;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Niveau1 {

	/**
	 * Tableau représentant la map qui va être générée
	 * 0 = bloc de CIEL
	 * 1 = bloc de SOL
	 */
	private int generationTab[][] = 
		{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0}};

	private ImageView lutin = new ImageView(new Image("lutin4.png"));
	private Group root;
	private ArrayList<ImageView> colisionBlocks;
	private ArrayList<ImageView> generatedMap;
	private double coordX;
	private double coordY;

	public Niveau1(Group root) {
		this.colisionBlocks = new ArrayList<ImageView>();
		this.generatedMap = new ArrayList<ImageView>();
		this.root = root;
		root.getChildren().clear();
		this.coordX=0.0;
		this.coordY=0.0;
		this.generateLevel();
		this.getColisionBlocks();
	}
	
	/**
	 * Fonction permettant de placer les blocs sur notre vue suivant la disposition définie sur la variable "generationTab"
	 */
	public void generateLevel() {
		for(int i=0; i<this.generationTab.length; i++) {
			for(int j=0;j<this.generationTab[i].length; j++) {
				String fond = getImageType(this.generationTab[i][j]);
				ImageView image = new ImageView(new Image(fond));
				root.getChildren().add(image);
				this.generatedMap.add(image);
				addBlockToList(fond, image);
				image.setX(this.coordX);
				image.setY(this.coordY);
				this.coordX+=64;
				System.out.println("On ajoute "+fond);
			}
			this.coordX=0;
			this.coordY+=64;
		}
		root.getChildren().add(this.lutin);
		System.out.println(root.getChildren().get(0));
	}

	/**
	 * Fonction retournant le nom de l'image à placer sur notre vue, en fonction du chiffre qui a été défini sur la variable "generationTab"
	 * @param chiffre un chiffre qui a été défini sur la variable "generationTab"
	 * @return String le nom de l'image à placer
	 */
	public String getImageType(int chiffre) {
		switch(chiffre) {
		case 0:
			return "ciel.png";
		case 1:
			return "sol.png";
		case 2:
			return "ciel.png";
		default:
			return "";
		}
	}
	
	/**
	 * Fonction permettant d'ajouter les blocks de type "sol" à notre liste de blocks de sol
	 * @param fond le nom de l'image à vérifier
	 * @param image l'image à ajouter dans le cas où son nom correspond à un block de type sol
	 */
	public void addBlockToList(String fond, ImageView image) {
		switch(fond) {
		case "sol.png":
			this.colisionBlocks.add(image);
			break;
		}
	}
	
	/**
	 * Retourne notre liste de blocks qui soumettent notre lutin à une colision
	 * @return la liste de blocks
	 */
	public ArrayList getColisionBlocks() {
		return this.colisionBlocks;
	}
	
	/**
	 * Retourne l'image du lutin contenu sur la vue
	 * @return l'image du ltuin
	 */
	public ImageView getLutin() {
		return this.lutin;
	}
	
	/**
	 * Retourne la liste des images contenues sur notre vue sous la forme d'une liste
	 * @return la liste d'images
	 */
	public ArrayList<ImageView> getGeneration() {
		return this.generatedMap;
	}
}
