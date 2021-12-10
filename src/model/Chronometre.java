package model;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Classe du modèle "Chronomètre"
 */
public class Chronometre {

	private Label chronometre;
	private Integer minutes;
	private Integer heures;
	private Integer secondes;
	
	public Chronometre(Label chronometre) {
		this.chronometre = chronometre;
		Font font = Font.loadFont(this.getClass().getClassLoader().getResource("MARIO-KART-DS.TTF").toString(), 50);
		this.chronometre.setFont(font);
		this.minutes =0;
		this.heures=0;
		this.secondes=0;
	}
	
	/**
	 * Incrémente le chronomètre en suivant les règles de temps et change le texte de notre chronomètre.
	 */
	public void ajouter() {
		if(this.secondes<59) {
			this.secondes++;
		} else {
			this.secondes=0;
			if(this.minutes<59) {
				this.minutes++;
			} else {
				this.minutes=0;
				this.heures++;
			}
		}
		this.chronometre.setText(this.toString());
	}
	
	/**
	 * Calcule les points en fonction du résultat effectué sur le chrono
	 * @return le nombre de points de base (30000) - 100 * le nombre total d'heures / 2
	 */
	public int calculerPoints() {
		int pts = 30000;
		int nbTotalSecondes = this.heures*60*60+this.minutes*60+this.secondes;
		if(this.minutes<10) {
			return pts - 100* (nbTotalSecondes / 2);
		}
		return 0;
	}
	
	/**
	 * Retourne le label du chronometre
	 * @return Label le label du chronometre
	 */
	public Label getChronometre() {
		return this.chronometre;
	}
	
	public String toString() {
		String strMinutes="";
		String strSecondes="";
		String strHeures="";
		if(this.minutes<10) {
			strMinutes+="0"+this.minutes;
		} else {
			strMinutes+=this.minutes;
		}
		if(this.heures<10) {
			strHeures+="0"+this.heures;
		} else {
			strHeures+=this.heures;
		}
		if(this.secondes<10) {
			strSecondes+="0"+this.secondes;
		} else {
			strSecondes+=this.secondes;
		}
		return strHeures+"."+strMinutes+"."+strSecondes;
	}
}

