package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

//ABSTRACT store variables used in player npc classes
public class Entity {
	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	public String name;
	public BufferedImage dog, dog2, wolf, down2, down1, right1, right2, left1, left2, up1, up2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle collisionBox;
	public boolean collisionOn = false;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
}
