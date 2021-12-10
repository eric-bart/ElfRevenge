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
	
	/**
	 * Retourne un boolean si le niveau a été fini ou non.
	 * @return boolean
	 */
	public boolean isFini() {
		return fini;
	}
	
	/**
	 * Permet de set le niveau à true
	 */
	public void setFini(boolean fini) {
		this.fini = fini;
	}
	
	/**
	 * Permet de retrouver le scoreMax d'un niveau.
	 * @return Int ScoreMax 
	 */
	public int getScoreMax() {
		return scoreMax;
	}
	
	/**
	 * Permet de d�finir le scoreMax d'un niveau.
	 * @return Int ScoreMax 
	 */
	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
	}
	public String scoreString() {
		return ""+this.getScoreMax();
	}
	public String toString() {
		return "Est fini : "+ this.isFini() + " score max : " + this.getScoreMax();
	}
}