package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.Bomb;

public class Player extends Entity{
	KeyHandler keyH;
	private int bombCnt, bombRadius, bombsPlaced, bombCooldown;
	
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		name = "Player";
		collisionBox = new Rectangle(10, 16, 28, 32);
		
		
		setDefaultVal();
		getPlayerImage();
	}
	
	public void setDefaultVal() {
		worldX = gp.tileSize;
		worldY = gp.tileSize;
		speed = 3;
		direction = "down";
		bombCnt = 1;
		bombsPlaced = 0;
		bombRadius = 1;
		bombCooldown = 0;
	}
	
	public void setBombCount(int bombCnt) {
		this.bombCnt = bombCnt;
	}
	
	public void setBombRadius(int bombRadius) {
		this.bombRadius = bombRadius;
	}
	
	public int getBombCount() {
		return bombCnt;
	}
	
	public int getBombRadius() {
		return bombRadius;
	}
	
	public void getPlayerImage() {
		try {
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void dropBomb() {
		Bomb b = new Bomb(worldX, worldY);
		gp.bombs.add(b);
		bombsPlaced++;
	}
	
	public void removeBombsPlaced() {
		bombsPlaced--;
	}
	
	public void update() {
		bombCooldown++;
		if(bombCooldown > 12) {
			if(keyH.spacePressed==true) {
				//check if bombs available
				int tile1 = gp.tileMgr.mapTileNum[(worldX + collisionBox.x)/gp.tileSize][(worldY+collisionBox.y)/gp.tileSize];
				System.out.println("=====" + worldX/gp.tileSize + "   " + worldY/gp.tileSize);
				if(bombsPlaced < bombCnt && !gp.tileMgr.getTileCollision(tile1)) {
					dropBomb();
				}
			}
			bombCooldown=0;
		}
		else if(keyH.upPressed == true ||keyH.downPressed == true ||keyH.leftPressed == true ||keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//CHECK COLLISION
			collisionOn = false;
			gp.colCheck.checkTile(this);
			
			//COLLISION FALSE, THEN PLAYER MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			spriteCounter++;//ANIMATION
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		System.out.println(worldX + " " + worldY);///////////////////////////////
	}
	
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum==1) {
				image = up1;
			}
			if(spriteNum==2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum==1) {
				image = down1;
			}
			if(spriteNum==2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum==1) {
				image = left1;
			}
			if(spriteNum==2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum==1) {
				image = right1;
			}
			if(spriteNum==2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
	}
}
