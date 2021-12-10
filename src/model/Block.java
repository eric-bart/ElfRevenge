package model;

import javafx.scene.image.ImageView;

/**
 * Classe du modèle "BLOCK"
 */
public class Block {

	private ImageView block;
	private String blockName;
	static int blockXSize = 0;
	private boolean hardBlock;
	private boolean isMobBlock;
	private boolean bossBlock;
	private boolean blockDeFin;
	private boolean specialBlock;
	
	public Block(String blockName) {
		this.blockName=blockName;
		this.isMobBlock=false;
		this.bossBlock =false;
		this.hardBlock=false;
		this.blockDeFin=false;
		this.block = new ImageView(blockName);
		blockXSize=(int)block.getLayoutBounds().getMaxX();
	}
	
	/**
	 * Retourne si le bloc fait apparaître un boss
	 * @return booleen true=Il fait apparaître un boss | false=Il ne fait pas apparaître de bosss
	 */
	public boolean isBossBlock() {
		return bossBlock;
	}


	/**
	 * Retourne si le block actuel est un block spécial ou non
	 * @return booleen true=Le block est un block spécial | false=Le block n'est pas un block spécial
	 */
	public boolean isSpecialBlock() {
		return specialBlock;
	}
	
	
	/**
	 * Setter pour l'attribut specialBlock
	 * @param specialBlock specialBlock
	 */
	public void setSpecialBlock(boolean specialBlock) {
		this.specialBlock = specialBlock;
	}

	/**
	 * Change l'attribut bossBlock
	 * @param bossBlock
	 */
	public void setBossBlock(boolean bossBlock) {
		this.bossBlock = bossBlock;
	}

	/**
	 * Permet de récupérer l'image du block
	 * @return ImageView l'image du block
	 */
	public ImageView getBlock() {
		return this.block;
	}

	/**
	 * Retourne si le block est un block faisant apparaître un mob
	 * @return booleen true=Il fait apparaître un mob | false=Il ne fait pas apparaître de mob
	 */
	public boolean isMobBlock() {
		return isMobBlock;
	}

	/**
	 * Permet de retourner si le Block courant est un bloc "dur" ou non
	 * @return boolean true=Le bloc courant est dur | false=Le bloc courant n'est pas dur
	 */
	public boolean isHardBlock() {
		return this.hardBlock;
	}

	/**
	 * Setter pour l'attribut hardBlock
	 * @param hardBlock
	 */
	public void setHardBlock(boolean hardBlock) {
		this.hardBlock = hardBlock;
	}

	/**
	 * Setter pour l'attribut mobBlock
	 * @param isMobBlock
	 */
	public void setMobBlock(boolean isMobBlock) {
		this.isMobBlock = isMobBlock;
	}

	/**
	 * Retourne le nom du block courant
	 * @return String le nom du bloc
	 */
	public String getBlockName() {
		return this.blockName;
	}

	/**
	 * Retourne si le block est un bloc de fin ou pas.
	 * Un block de fin est le block sur lequel si le lutin est dessus, le niveau est validé
	 * @return booleen
	 */
	public boolean isBlockDeFin() {
		return blockDeFin;
	}

	/**
	 * Setter pour si le block est un bloc de fin ou pas.
	 * @param blocDeFin boolen
	 */
	public void setBlockDeFin(boolean blocDeFin) {
		this.blockDeFin = blocDeFin;
	}


}
