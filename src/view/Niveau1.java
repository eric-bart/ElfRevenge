package view;

import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import application.Main;
import controller.FileManager;
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

public class Niveau1 extends Niveau {

	/**
	 * Tableau représentant la map qui va être générée
	 * 0 = bloc de CIEL
	 * 1 = bloc de SOL
	 */
	private static final int[][] generationTab = 
			{{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,1,0,0,0,20,2,0,0,0,0,0,0,0,0,0,2},
			{2,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,6,0,0,0,0,1,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,1,5,0,0,0,0,0,20,1,0,0,0,0,0,0,2},
			{2,0,0,0,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2}};

	private Group root;

	public Niveau1(Group root) {
		super(root, generationTab);
		this.root=root;
		this.generateLevel(generationTab);
		this.addEntities();
	}
	

	@Override
	public void addEntities() {
		root.getChildren().add(this.getLutin());
	}
	
	public static void fini() {
		FileManager fileManager = new FileManager();
		HashMap<String, DonneesNiveau> recup = (HashMap<String, DonneesNiveau>)fileManager.readFile("donnees");
		DonneesNiveau d1 = recup.get("niveau3");
		d1.setFini(true);
		recup.put("niveau3", d1);
		fileManager.writeToFile("donnees", recup);
	}
}
