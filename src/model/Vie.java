package model;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Vie {

	private Text vie;
	private Integer nbVie;
	
	public Vie(int vie, Text labelVie) {
		this.nbVie=vie;
		this.vie = labelVie;
		Font font = Font.loadFont(this.getClass().getClassLoader().getResource("MARIO-KART-DS.TTF").toString(), 50);
		this.vie.setFont(font);
		this.vie.setX(application.Main.fenetre.getWidth()-150);
		this.vie.setY(this.vie.getY()+this.vie.getLayoutBounds().getHeight());
		this.vie.setText(this.toString());
	}
	
	/**
	 * Incrémente le chronomètre en suivant les règles de temps et change le texte de notre chronomètre.
	 * 
	 */
	public void enleverVie() {
		if(this.nbVie<=0) {
			this.nbVie=0;
		} else {
			this.nbVie--;
		}
		this.vie.setText(this.toString());
	}
	
	public void setVie(int vie) {
		this.nbVie = vie;
		this.vie.setText(this.toString());
	}
	
	public Text getLabelVie() {
		return this.vie;
	}
	
	public String toString() {
		return "Vie "+this.nbVie;
	}
}
