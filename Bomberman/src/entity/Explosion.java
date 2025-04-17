package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import object.Bomb;

public class Explosion{
	public int worldX, worldY, timeCount, timer;
	BufferedImage exp;
	private boolean expActive;
	public Explosion(int x, int y) {
		worldX = x;
		worldY = y;
		timer = 60;
		expActive = true; 
		try {
			exp = ImageIO.read(getClass().getResourceAsStream("/objects/explosion.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void getExpArea() {
		//for(Bomb b: gp.bombs) {
			//int expX = 
		//}
	}
	
	public void update() {
		if (expActive) {
			timeCount++;
			if(timeCount > timer) {
				timeCount=0;
				expActive = false;
			}
		}
	}
	
	public boolean getExpStatus() {
		return expActive;
	}
	
	public void draw(Graphics2D g2, GamePanel gp){
		if(expActive) {
			g2.drawImage(exp, worldX, worldY, gp.tileSize, gp.tileSize, null);
		}
	}
}
