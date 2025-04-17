package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Explosion;
import main.GamePanel;

public class Bomb {
	public BufferedImage dynamite1, dynamite2;
	public String name;
	//public boolean collision;
	public int worldX, worldY, timer, timeCount, spriteCounter, spriteNum;
	private boolean bombPlaced, expActive;
	public ArrayList<Explosion> explosions = new ArrayList<>();
	
	public Bomb(int worldX, int worldY) {
		name = "Bomb";
		//collision = false;
		this.worldX = worldX;
		this.worldY = worldY;
		timer = 180;
		timeCount = 0;
		bombPlaced = true;
		//expActive = false;
		spriteCounter = 0;
		spriteNum = 1;
		try {
			dynamite1 = ImageIO.read(getClass().getResourceAsStream("/objects/dynamite1.png"));
			dynamite2 = ImageIO.read(getClass().getResourceAsStream("/objects/dynamite2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/*public int[][] getCoords(){
		int[][] coords = {{worldX},{worldY}};
		return coords;
	}*/
	
	public int getX() {
		return this.worldX;
	}
	
	public int getY() {
		return this.worldY;
	}
	
	public boolean getBombStatus() {
		return this.bombPlaced;
	}
	
	/*public boolean getExpStatus() {
		return this.expActive;
	}*/
	
	public void addExplosions(int x, int y) {
		explosions.add(new Explosion(x, y));
	}
	
	public void update() {
		if (bombPlaced) {
			timeCount++;
			if(timeCount > timer) {
				timeCount=0;
				bombPlaced = false;
				//expActive = true;
			}
		
		//else {
			
		//}
		//CLASSIFY
		spriteCounter++;//ANIMATION
		if(spriteCounter > 30) {
			System.out.println("spriteupdate");
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		}
		/*else {
			if(expActive) {
				System.out.println("EXPLOSION");
				timeCount++;
				if(timeCount > timer) {
					timeCount=0;
					expActive = false;
				}
			}
		}*/
	}
	
	public void draw(Graphics2D g2, GamePanel gp) {
		//int x=0;
		//int y=0;
		if(bombPlaced == true) {
			BufferedImage image = null;
			if(spriteNum==1) {
				image = dynamite1;
			}
			if(spriteNum==2) {
				image = dynamite2;
			}
			g2.drawImage(image, (worldX + gp.player.collisionBox.x)/gp.tileSize*gp.tileSize, (worldY+gp.player.collisionBox.y+10)/gp.tileSize*gp.tileSize, gp.tileSize, gp.tileSize, null);
			//get center
			//make update
		}
	}
}
