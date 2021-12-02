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
import javafx.scene.text.Text;
import model.Block;
import model.DonneesNiveau;
import model.Lutin;
import model.SolidBlock;
import model.TransparentBlock;
import model.Vie;

public class Niveau1 extends Niveau {

	private Lutin lutin;
	
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
		this.lutin = new Lutin(new ImageView(this.getSkin()), 0, 0, this);
		this.vie = new Vie(lutin.getVie(), new Text(""+lutin.getVie()));
		this.vie.setVie(2);
		this.vie.getLabelVie();
		this.root=root;
		this.generateLevel(generationTab);
		this.addEntities();
	}
	
	public Lutin getLutin() {
		return this.lutin;
	}
	
	
	@Override
	public void addEntities() {
		root.getChildren().add(this.lutin.getImage());
		root.getChildren().add(this.vie.getLabelVie());
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
