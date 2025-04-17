package main;

import object.OBJ_Bomb;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Bomb();
		gp.obj[0].worldX = gp.player.worldX;
		gp.obj[0].worldY = gp.player.worldY;
	}
}
