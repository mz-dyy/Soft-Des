package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import object.Bomb;
import entity.Explosion;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	private final int ogTileSize = 16;
	private final int scale = 3;
	
	public final int tileSize = ogTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	int FPS = 60;
	
	public TileManager tileMgr = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	public CollisionChecker colCheck = new CollisionChecker(this);
	public AssetSetter asset = new AssetSetter(this);
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	public ArrayList<Bomb> bombs = new ArrayList<>();
	public ArrayList<Explosion> explosions = new ArrayList<>();
	public UI ui = new UI(this);
	
	// GAME STATE
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.blue);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		asset.setObject();
		gameState = playState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		/*
		 * MAKE FPS CLASS
		 */
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
		
			//System.out.println("running");
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if (delta>=1) {
				update();
				repaint();
				delta--;
			}
			
		}
		
	}
	
	public void update() {
		if(gameState == playState) {
			player.update();
			System.out.println(bombs.size());
			for(int i = bombs.size() - 1; i >= 0; i--){
				Bomb b = bombs.get(i);
				if(b!=null) {
					b.update();
					if(!b.getBombStatus()) {
						explode(b);
						bombs.remove(i);
						player.removeBombsPlaced();
					}
				}
				System.out.println("[" + (b.getX() + player.collisionBox.x)/tileSize + "] [" + (b.getY()+player.collisionBox.y+10)/tileSize + "]");
			}
		}
		
		if(gameState == pauseState) {
			
		}
	}
	
	private void explode(Bomb b) {
		
		for(Explosion e: explosions) {
			if(e.getExpStatus()) {
				
			}
		}
			
		
		
		int eX = (b.getX() + player.collisionBox.x)/tileSize;
		int eY = (b.getY()+player.collisionBox.y+10)/tileSize;
		
		boolean upEmpty = checkTileExp(eX, eY-1, true);
		boolean downEmpty = checkTileExp(eX, eY+1, true);
		boolean leftEmpty = checkTileExp(eX-1, eY, true);
		boolean rightEmpty = checkTileExp(eX+1, eY, true);
		b.addExplosions(eX*tileSize, eY*tileSize);
		for(int i = 0; i < player.getBombRadius(); i++) {
			if(upEmpty) {
				b.addExplosions(eX*tileSize, (eY-i)*tileSize);
				upEmpty = checkTileExp(eX, eY-i, upEmpty);
			}
			if(downEmpty) {
				b.addExplosions(eX*tileSize, (eY+i)*tileSize);
				downEmpty = checkTileExp(eX, eY+i, downEmpty);
			}
			if(leftEmpty) {
				b.addExplosions((eX-i)*tileSize, eY*tileSize);
				leftEmpty = checkTileExp(eX-i, eY, leftEmpty);
			}
			if(rightEmpty) {
				b.addExplosions((eX+i)*tileSize, eY*tileSize);
				rightEmpty = checkTileExp(eX+i, eY, rightEmpty);
			}
		}
	}
	
	public boolean checkTileExp(int x, int y, boolean empty) {
		int tile = tileMgr.mapTileNum[x][y];
		if(tileMgr.tile[tile].collision == true) {
			//entity.collisionOn = true;
			if(tile == 2) {
				tileMgr.mapTileNum[x][y] = 0;
			}
			empty = false;
		}
		return empty;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		//TILE
		tileMgr.draw(g2);
		
		//OBJECT
		/*for(int i=0; i<obj.length; i++) {
			if(obj[i]!= null) {
				obj[i].draw(g2, this);
			}
		}*/
		for(Bomb b: bombs) {
			if(b!=null) {
				b.draw(g2, this);
			}
		}
		
		
		//player
		player.draw(g2);
		
		//UI
		ui.draw(g2);
		g2.dispose();
	}
	
}