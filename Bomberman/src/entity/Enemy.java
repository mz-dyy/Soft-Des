package entity;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemy extends Entity{

	public Enemy(GamePanel gp, int x, int y) {
		super(gp);
		
		name = "Bot";
		speed = 1;
		worldX = gp.tileSize*x;
		worldY = gp.tileSize*y;
		collisionBox = new Rectangle(10, 10, 28, 38);
	}
	public void getPlayerImage() {
		try {
			down2 = ImageIO.read(getClass().getResourceAsStream("/enemy/edown2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/enemy/edown1.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/enemy/eright1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/enemy/eright2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/enemy/eleft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/enemy/eleft2.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/enemy/eup1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/enemy/eup2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void setMovement() {
		
	}

}
