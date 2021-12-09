package model;

import javafx.scene.image.ImageView;

public class Block {

	private ImageView block;
	private String blockName;
	static int blockXSize = 0;
	private boolean hardBlock;
	private boolean isMobBlock;
	private boolean bossBlock;
	private boolean blockDeFin;
	
	public Block(String blockName) {
		this.blockName=blockName;
		this.isMobBlock=false;
		this.bossBlock =false;
		this.hardBlock=false;
		this.blockDeFin=false;
		this.block = new ImageView(blockName);
		blockXSize=(int)block.getLayoutBounds().getMaxX();
	}
	
	
	
	
	public boolean isBossBlock() {
		return bossBlock;
	}

	
	
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}




	public static void setBlockXSize(int blockXSize) {
		Block.blockXSize = blockXSize;
	}




	public void setBossBlock(boolean bossBlock) {
		this.bossBlock = bossBlock;
	}




	/**
	 * Permet de récupérer l'image du bloc
	 * @return ImageView l'image du block
	 */
	public ImageView getBlock() {
		return this.block;
	}
	
	public static int getBlockXSize() {
		return blockXSize;
	}

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
	
	public void setBlock(ImageView block) {
		this.block = block;
	}

	public void setHardBlock(boolean hardBlock) {
		this.hardBlock = hardBlock;
	}

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

	public boolean isBlockDeFin() {
		return blockDeFin;
	}

	public void setBlockDeFin(boolean blocDeFin) {
		this.blockDeFin = blocDeFin;
	}


}
