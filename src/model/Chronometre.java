package model;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

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
	 * Incr�mente le chronom�tre en suivant les r�gles de temps et change le texte de notre chronom�tre.
	 * 
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
	 * Met les valeurs minutes, secondes et heures sous forme de String dans le but de le mettre sur notre Label chronom�tre
	 * 
	 */
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

