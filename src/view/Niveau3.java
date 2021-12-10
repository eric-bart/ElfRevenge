package view;
import java.util.HashMap;

import model.FileManager;
import javafx.scene.Group;
import javafx.scene.text.Text;
import model.DonneesNiveau;
import model.Lutin;
import model.Vie;

/**
 * Classe correspondant à notre Niveau3.
 */
public class Niveau3 extends Niveau {

	
	private Lutin lutin;
	
	/**
	 * Tableau représentant la map qui va être générée
	 * 0 = bloc de CIEL
	 * 1 = bloc de SOL
	 */
	private static final int[][] matriceNiveau = 
		{{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
                {2,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,2},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,2},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,2},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,1,0,0,2},
                {2,0,1,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,1,2},
                {2,1,2,1,1,1,1,1,7,1,1,1,1,2,0,0,0,0,2,2},
                {2,2,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,1,2,2},
                {2,2,9,0,0,0,9,0,0,2,9,0,0,0,0,0,1,0,0,2},
                {2,2,0,0,0,0,0,1,1,2,0,0,0,0,0,1,0,9,0,2},
                {2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
                {2,2,0,0,0,1,0,0,0,0,0,1,1,1,1,0,0,0,0,2},
                {2,2,1,1,1,2,1,1,1,1,1,2,2,2,2,1,1,1,1,2},
                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2}};

	private Group root;

	public Niveau3(Group root, Lutin lutin) {
		super(root, matriceNiveau);
		this.lutin = lutin;
		this.vie = new Vie(lutin.getVie(), new Text(""+lutin.getVie()));
		this.vie.setVie(2);
		this.vie.getLabelVie();
		this.root=root;
		this.generateLevel(matriceNiveau);
		this.addEntities();
	}
	
	/**
	 * Retourne le lutin
	 * @return Lutin le lutin
	 */
	public Lutin getLutin() {
		return this.lutin;
	}
	
	@Override
	public void addEntities() {
		root.getChildren().add(this.lutin.getImage());
		root.getChildren().add(this.vie.getLabelVie());
	}

	/**
	 * Mets le jeu à l'état fini au sein des fichiers de sauvegarde de notre jeu et fixe le score maximum qui a été réalisé.
	 * @param points le score qui a été réalisé
	 */
	public void fini(int points) {
		FileManager fileManager = new FileManager();
		@SuppressWarnings("unchecked")
		HashMap<String, DonneesNiveau> recup = (HashMap<String, DonneesNiveau>)fileManager.readFile("donnees");
		DonneesNiveau d3 = recup.get("niveau3");
		d3.setFini(true);
		if(d3.getScoreMax()<points) {
			d3.setScoreMax(points);
		}
		recup.put("niveau3", d3);
		fileManager.writeToFile("donnees", recup);
	}
}
