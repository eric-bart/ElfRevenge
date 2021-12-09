package model;

import application.Jeu;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Classe du modèle "Vie". C'est dans cette classe que l'on gère l'ajout, l'affichage et le retrait de vie à un personnage.
 */
public class Vie {

	private Text vie;
	private Integer nbVie;
	
	public Vie(int vie, Text labelVie) {
		this.nbVie=vie;
		this.vie = labelVie;
		//On charge la police MARIO-KART que l'on applique ensuite à l'Objet Text qui diffuse
		//la vie du personnage sur le jeu
		Font font = Font.loadFont(this.getClass().getClassLoader().getResource("MARIO-KART-DS.TTF").toString(), 50);
		this.vie.setFont(font);
		this.vie.setX(Jeu.monJeu.getGameStage().getWidth()-150);
		this.vie.setY(this.vie.getY()+this.vie.getLayoutBounds().getHeight());
		this.vie.setText(this.toString());
	}
	
	/**
	 * Incrémente le chronomètre en suivant les règles de temps et change le texte de notre chronomètre.
	 */
	public void enleverVie() {
		if(this.nbVie<=0) {
			this.nbVie=0;
		} else {
			this.nbVie--;
		}
		this.vie.setText(this.toString());
	}
	
	/**
	 * Mets à jour le "Text" vie en lui mettant la valeur actuelle de la vie du lutin passée en paramètre
	 * @param vie La vie du lutin
	 */
	public void setVie(int vie) {
		this.nbVie = vie;
		this.vie.setText(this.toString());
	}
	
	/**
	 * Retourne l'objet Text de la vie du lutin
	 * @return Text la vie du lutin
	 */
	public Text getLabelVie() {
		return this.vie;
	}
	
	public String toString() {
		return "Vie "+this.nbVie;
	}
}
