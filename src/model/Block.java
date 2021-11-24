package model;

import javafx.scene.image.ImageView;

public class Block {

	private ImageView block;
	private String blockName;
	static int blockXSize = 0;
	private boolean hardBlock;
	
	public Block(String blockName, boolean hardBlock) {
		this.block = new ImageView(blockName);
		blockXSize=(int)block.getLayoutBounds().getMaxX();
		this.hardBlock=hardBlock;
	}
	
	public ImageView getBlock() {
		return this.block;
	}
	
	public boolean isHardBlock() {
		return this.hardBlock;
	}
}
