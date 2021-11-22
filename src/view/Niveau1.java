package view;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Niveau1 {

	private int generation[][] = 
		{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0}};

	private ImageView background = new ImageView(new Image("background2.png"));
	private ImageView elf = new ImageView(new Image("lutin4.png"));
	private Group root;
	private ArrayList<ImageView> colisionBlocks;
	private double coordX;
	private double coordY;

	public Niveau1(Group root) {
		this.colisionBlocks = new ArrayList<ImageView>();
		this.root = root;
		root.getChildren().clear();
		//root.getChildren().addAll(this.background, this.elf);
		this.coordX=0.0;
		this.coordY=0.0;
		this.generateLevel();
		this.getColisionBlocks();
	}

	public void generateLevel() {
		for(int i=0; i<this.generation.length; i++) {
			for(int j=0;j<this.generation[i].length; j++) {
				String fond = getImageType(this.generation[i][j]);
				ImageView image = new ImageView(new Image(fond));
				root.getChildren().add(image);
				addBlockToList(fond, image);
				image.setX(this.coordX);
				image.setY(this.coordY);
				this.coordX+=64;
				System.out.println("On ajoute "+fond);
			}
			this.coordX=0;
			this.coordY+=64;
		}
		root.getChildren().add(this.elf);
		System.out.println(root.getChildren().get(0));
	}

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
	
	public void addBlockToList(String fond, ImageView image) {
		switch(fond) {
		case "sol.png":
			this.colisionBlocks.add(image);
			break;
		}
	}
	
	public ArrayList getColisionBlocks() {
		for(int i=0;i<this.colisionBlocks.size();i++) {
			ImageView image = this.colisionBlocks.get(i);
			System.out.println("Cordonnées de " + image + " : " + image.getLayoutBounds().getMinX() + ";" + image.getLayoutBounds().getMaxX());
		}
		return this.colisionBlocks;
	}

	public ImageView getBackground() {
		return this.background;
	}

	public ImageView getElf() {
		return this.elf;
	}
}
