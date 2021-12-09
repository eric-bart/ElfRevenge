package view;
import java.util.HashMap;

import model.FileManager;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.DonneesNiveau;
import model.Lutin;
import model.Vie;


public class Niveau2 extends Niveau {

	
	private Lutin lutin;
	/**
	 * Tableau représentant la map qui va être générée
	 * 0 = bloc de CIEL
	 * 1 = bloc de SOL
	 */
	private static final int[][] matriceNiveau = 
		{{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,1,0,0,0,9,2,0,0,0,0,0,0,0,0,0,2},
				{2,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,6,0,0,0,0,1,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,1,5,0,0,0,0,0,9,1,0,0,0,0,0,0,2},
				{2,0,0,0,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2}};

	private Group root;

	public Niveau2(Group root) {
		super(root, matriceNiveau);
		this.lutin = new Lutin(new ImageView(this.getSkin()), 0, 0, this);
		this.vie = new Vie(lutin.getVie(), new Text(""+lutin.getVie()));
		this.vie.setVie(2);
		this.vie.getLabelVie();
		this.root=root;
		this.generateLevel(matriceNiveau);
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
		@SuppressWarnings("unchecked")
		HashMap<String, DonneesNiveau> recup = (HashMap<String, DonneesNiveau>)fileManager.readFile("donnees");
		DonneesNiveau d1 = recup.get("niveau2");
		d1.setFini(true);
		recup.put("niveau2", d1);
		fileManager.writeToFile("donnees", recup);
	}
}
