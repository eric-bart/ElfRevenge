package view;
import java.util.HashMap;

import model.FileManager;
import javafx.scene.Group;
import javafx.scene.text.Text;
import model.DonneesNiveau;
import model.Lutin;
import model.Vie;

/**
 * Classe correspondant à notre Niveau2.
 */
public class Niveau2 extends Niveau {

	
	private Lutin lutin;
	/**
	 * Tableau représentant la map qui va être générée
	 * 0 = bloc de CIEL
	 * 1 = bloc de SOL
	 */
	private static final int[][] matriceNiveau = 
		{{2,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,2},
                {2,0,9,0,0,9,4,0,9,0,0,9,0,3,0,0,4,0,0,2},
                {2,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,1,0,2},
                {2,0,1,2,2,1,0,0,1,2,2,1,0,0,1,2,2,2,0,2},
                {2,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,0,2},
                {2,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,2},
                {2,0,0,4,0,0,0,4,0,0,0,0,0,0,3,0,0,0,0,2},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
                {2,3,0,0,0,0,7,7,1,0,0,0,0,7,1,0,0,0,0,2},
                {2,0,0,0,0,0,0,0,0,0,7,1,0,0,0,0,0,0,0,2},
                {2,0,0,0,0,0,4,0,0,0,0,0,0,0,7,7,1,0,0,2},
                {2,6,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,2},
                {2,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2},
                {2,1,7,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
                {2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2}};

	private Group root;

	public Niveau2(Group root, Lutin lutin) {
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
		DonneesNiveau d2 = recup.get("niveau2");
		d2.setFini(true);
		if(d2.getScoreMax()<points) {
			d2.setScoreMax(points);
		}
		recup.put("niveau2", d2);
		fileManager.writeToFile("donnees", recup);
	}
}
