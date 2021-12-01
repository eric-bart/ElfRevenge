package view;
import controller.FileManager;

import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import application.Main;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Block;
import model.DonneesNiveau;
import model.SolidBlock;
import model.TransparentBlock;
import controller.FileManager;


public class Niveau2 extends Niveau {

	/**
	 * Tableau représentant la map qui va être générée
	 * 0 = bloc de CIEL
	 * 1 = bloc de SOL
	 */
	private static final int[][] generationTab = 
		{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,0,2},
			{0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{0,0,0,0,0,0,0,0,4,0,0,1,0,0,0,0,1,0,3,0,0,0,0,0,0,0,0,2},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{0,0,0,0,0,0,6,0,0,0,0,0,0,0,1,1,3,0,0,0,0,6,0,0,0,0,0,2},
			{1,0,0,0,0,0,5,1,0,0,0,1,0,0,0,0,0,0,0,0,0,5,0,0,0,1,0,2},
			{1,1,1,1,1,1,1,2,1,1,1,2,1,0,0,0,0,0,0,0,0,5,0,1,0,0,0,2},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},};

	private Group root;

	public Niveau2(Group root) {
		super(root, generationTab);
		this.root=root;
		this.generateLevel(this.generationTab);
		this.addEntities();
	}
	
	/**
	 * Retourne le tableau de génération de notre map
	 */
	public int[][] getGenerationMap() {
		return this.generationTab;
	}

	@Override
	public void addEntities() {
		root.getChildren().add(this.getLutin());
	}
	
	public static void fini() {
		FileManager fileManager = new FileManager();
		HashMap<String, DonneesNiveau> recup = (HashMap<String, DonneesNiveau>)fileManager.readFile("donnees");
		DonneesNiveau d1 = recup.get("niveau2");
		d1.setFini(true);
		recup.put("niveau2", d1);
		fileManager.writeToFile("donnees", recup);
	}
}
