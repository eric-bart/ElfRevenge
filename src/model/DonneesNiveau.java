package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DonneesNiveau implements Serializable {
	
	private boolean fini;
	private int scoreMax;
	
	public DonneesNiveau(boolean fini, int scoreMax) {
		this.fini = fini;
		this.scoreMax = scoreMax;
	}
	public boolean isFini() {
		return fini;
	}
	public void setFini(boolean fini) {
		this.fini = fini;
	}
	public int getScoreMax() {
		return scoreMax;
	}
	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
	}
	public String toString() {
		return "Est fini : "+ this.isFini() + " score max : " + this.getScoreMax();
	}
}
