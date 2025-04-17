package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeft = entity.worldX + entity.collisionBox.x;
		int entityRight  = entity.worldX + entity.collisionBox.x + entity.collisionBox.width;
		int entityTop = entity.worldY + entity.collisionBox.y;
		int entityBottom = entity.worldY + entity.collisionBox.y + entity.collisionBox.height;
		
		int entityLeftCol = entityLeft/gp.tileSize;
		int entityRightCol = entityRight/gp.tileSize;
		int entityTopRow = entityTop/gp.tileSize;
		int entityBottomRow = entityBottom/gp.tileSize;
		
		int tile1, tile2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTop - entity.speed)/gp.tileSize;
			tile1 = gp.tileMgr.mapTileNum[entityLeftCol][entityTopRow];//TOP LEFT
			tile2 = gp.tileMgr.mapTileNum[entityRightCol][entityTopRow];//TOP RIGHT
			if(gp.tileMgr.tile[tile1].collision == true || gp.tileMgr.tile[tile2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottom + entity.speed)/gp.tileSize;
			tile1 = gp.tileMgr.mapTileNum[entityLeftCol][entityBottomRow];//BOT LEFT
			tile2 = gp.tileMgr.mapTileNum[entityRightCol][entityBottomRow];//BOT RIGHT
			if(gp.tileMgr.tile[tile1].collision == true || gp.tileMgr.tile[tile2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeft - entity.speed)/gp.tileSize;
			tile1 = gp.tileMgr.mapTileNum[entityLeftCol][entityTopRow];//TOP LEFT
			tile2 = gp.tileMgr.mapTileNum[entityLeftCol][entityBottomRow];//BOT LEFT
			if(gp.tileMgr.tile[tile1].collision == true || gp.tileMgr.tile[tile2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRight + entity.speed)/gp.tileSize;
			tile1 = gp.tileMgr.mapTileNum[entityRightCol][entityTopRow];//TOP RIGHT
			tile2 = gp.tileMgr.mapTileNum[entityRightCol][entityBottomRow];//BOT RIGHT
			if(gp.tileMgr.tile[tile1].collision == true || gp.tileMgr.tile[tile2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
}
