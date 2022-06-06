import java.io.File;
import processing.core.PImage;
import processing.core.PApplet;

public class GraphicsMain extends PApplet {
	
	boolean notMenu;
	boolean isBattleDone;
	int optcounter = 1;
	PImage[] images;
	int imageCount = 8;
	int frame = 0;
	  
	//Testing:
	static int count = 0;
	
	//Platform Bar Y-coordinate:
	private int bottomBar = 160;
	
	//FrameCount:
	
	// Sample Sprites:
	PImage img;
	
	File f = new File("PlayerSprite1.png");
	File g = new File("PlayerSprite2.png");
	File sampleW = new File("SampleWeapon.PNG");

	// PSprites:
	PSprite p1 = new PSprite(f, 50, 40, 200, 350);
	PSprite p2 = new PSprite(g, 50, 40, 700, 350);
	PSprite sampleWeapon1 = new PSprite(sampleW, 10, 30, p1.getX() + 2*p1.getWidth()/3, p1.getY());
	PSprite sampleWeapon2 = new PSprite(sampleW, 10, 30, p2.getX() + 2*p2.getWidth()/3, p2.getY());

	// Player Characters:
	private Character player1 = new Character(p1, 5, 1000);
	private Character player2 = new Character(p2, 5, 1000);
	
	//Player Weapons:
	private Weapon weapon1 = new Weapon(sampleWeapon1, 5); //FIX
	private Weapon weapon2 = new Weapon(sampleWeapon2, 5); //FIX

	// Drawable Characters:
	private DrawableCharacter dPlayer1;
	private DrawableCharacter dPlayer2;
	
	//Gravity + Jump Fields:
	private static int gravity = -10;
	
	//Drawable Weapons:
	private DrawableWeapon dWeapon1;
	private DrawableWeapon dWeapon2;
	
	//Movement:
	private boolean left1 = false;
	private boolean up1 = false;
	private boolean right1 = false;
	private boolean down1 = false;

	private boolean left2 = false;
	private boolean up2 = false;
	private boolean right2 = false;
	private boolean down2 = false;

	// Game Status Fields:
	//*
	private boolean inCombat = false;
	//*
	private boolean inFrontPage = false;
	private boolean inSelection = false;

	public static void main(String[] args) {
		PApplet.main("GraphicsMain");
	}

	public void settings() {
		size(1300, 750);
	}

	public void setup() {
//		background(0, 255, 0);
		images = new PImage[imageCount];
	    for (int i = 1; i <= imageCount; i++) {
	    	String filename = "options" + i + ".png";
	    	images[i-1] = loadImage(filename);
	    	images[i-1].resize(1300, 750);
	    }
		drawMenu();
		dWeapon1 = new DrawableWeapon(weapon1, this);
		dWeapon2 = new DrawableWeapon(weapon2, this);
		dPlayer1 = new DrawableCharacter(player1, this);
		dPlayer2 = new DrawableCharacter(player2, this);
		
	}
	
	public void draw() {
		//Update Positions and Drawings:

		moveCheck();
		weaponFollow();
		jumpCheck();
		
		//Check Bounds
		if(player1TouchingGround()) {
			up1 = false;
			player1.setXVelocity(6);
			player1.setYVelocity(6);
		}
		if(player2TouchingGround()) {
			up2 = false;
			player2.setXVelocity(6);
			player2.setYVelocity(6);
		}
//		
		//Draw:'
		if (notMenu && inCombat == false)
			display();
		image(img, 0, 0);
		
		System.out.println(frame);
		if (inCombat) {
			dPlayer1.draw(this);
			dPlayer2.draw(this);
			dWeapon1.draw(this);
			dWeapon2.draw(this);
		}
//		drawHitboxes();
		if (notMenu)
			drawCombatBar();
		
	}
	//*
	public void mouseClicked() {
		if (notMenu == false)
			if (mouseX >= 480 && mouseX <= 830 && mouseY >= 280 && mouseY <= 390) {
				drawBackground();
				notMenu = true;
				inCombat = true;
			}
			if (mouseX >= 355 && mouseX <= 960 && mouseY >= 415 && mouseY <= 525) {
				notMenu = true;
				display();
			}
			
	}
	//*
	
	public void drawMenu() {
		img = loadImage("menu.png");
		img.resize(width,  height);
	}

	public void drawBackground() {
		img = loadImage("background.png");
		img.resize(width, height);
	}

	public void drawResults() {
		img = loadImage("results.jpg");
		img.resize(width,  height);
	}

	public void display() {
		if (frame > 7)
			frame = 0;
	    img = images[frame];
	    frame++;
	  }
	  
	public int getWidth() {

	    return images[0].width;
	  }
	/**
	 * Checks for User Inputs Player 1 Controls: WASD Player 2 Controls: Arrow
	 * Keys
	 */
	public void keyPressed() {
		if (inCombat) { // Checks if in combat
			if (key == 'w' || key == 'a' || key == 's' || key == 'd') {
				// Move Player 1 based on direction
				if (key == 'w' || key == 'W') {
					up1 = true;
				} 
				else if (key == 'a' || key == 'A') {
					left1 = true;
					player1.setFacingLeft(true);
				} 
				else if (key == 's') {
					down1 = true;
				} 
				else if (key == 'd') {
					right1 = true;
					player1.setFacingLeft(false);
				}
			} else if (key == CODED) {
				if (keyCode == UP || keyCode == LEFT || keyCode == RIGHT || keyCode == DOWN) {
					// Move Player 2 based on direction
					if (keyCode == UP) {
						up2 = true;
					} 
					else if (keyCode == LEFT) {
						left2 = true;
						player2.setFacingLeft(true);
					} 
					else if (keyCode == RIGHT) {
						right2 = true;
						player2.setFacingLeft(false);
					} 
					else if (keyCode == DOWN) {
						down2 = true;
					}
				}
			}
		}
	}

	/**
	 * Checks for Jumps and Smooth Movement
	 */
	public void keyReleased() {
		if(key != CODED) {
			//Player 1 Smooth Movement
			if (key == 'w') {
				//Nothing
			} 
			else if (key == 'a')
				left1 = false;
			else if (key == 's') 
				down1 = false;
			 
			else if (key == 'd') 
				right1 = false;
			
		}
		else {
			//Player 2 Smooth Movement
			if (keyCode == UP) {
				//Nothing
			}
			else if (keyCode == LEFT) 
				left2 = false;
			else if (keyCode == DOWN) 
				down2 = false;
			else if (keyCode == RIGHT) 
				right2 = false;
		}
	}
	
	/**
	 * Checks the status of movement booleans and moves either player accordingly, allowing for smooth movement
	 */
	public void moveCheck() {
		if(left1 && player1.getX() > 0 + player1.getWidth()/2) {
			player1.move(-player1.getSpeed(), 0);
			weapon1.move(-player1.getSpeed(), 0);
		}
		if(up1 && player1.getY() > 0 + player1.getHeight()/2) {
			player1.move(0, -player1.getYVelocity()); //FIX
			player1.jump(this);
			weapon1.move(0, -player1.getYVelocity());
		}
//		if(down1 && player1.getY() < height - bottomBar - player1.getHeight()/2) {
//			player1.move(0, player1.getSpeed());
//			weapon1.move(0, player1.getSpeed());
//		}
		if(right1 && player1.getX() < width - player1.getWidth()/2) {
			player1.move(player1.getSpeed(), 0);
			weapon1.move(player1.getSpeed(), 0);
		}
		if(left2 && player2.getX() > 0 + player2.getWidth()/2) {
			player2.move(-player2.getSpeed(), 0);
			weapon2.move(-player2.getSpeed(), 0);
		}
		if(up2 && player2.getY() > 0 + player2.getHeight()/2) {
			player2.move(0, -player2.getYVelocity()); //FIX
			player2.jump(this);
			weapon2.move(0, -player2.getYVelocity());
		}
//		if(down2 && player2.getY() < height - bottomBar - player2.getHeight()/2) {
//			player2.move(0, player2.getSpeed());
//			weapon2.move(0, player2.getSpeed());
//		}
		if(right2 && player2.getX() < width - player2.getWidth()/2) {
			player2.move(player2.getSpeed(), 0);
			weapon2.move(player2.getSpeed(), 0);
		}
	}
	
	public void jumpCheck() {
		if(up1 && player1.getY() > 0 + player1.getHeight()/2) 
			player1.move(0, -player1.getSpeed() + gravity);
		if(up2 && player2.getY() > 0 + player2.getHeight()/2) 
			player2.move(0, -player2.getSpeed() + gravity);
	}
	
	/**
	 * Draws the Bottom Black Bar and Health Bar during Combat Mode
	 */
	public void drawCombatBar() {
		if(inCombat) {
			//Bottom Bar
			rectMode(CORNER);
			fill(0,0,0);
			rect(0, height - 160, width, 160);
//			//Health Bar Logic:
			//Player1:
			fill(255, 0, 0);
			rect(30, height - 140, 300, 40);
			fill(0, 255, 0);
			rect(30, height - 140, 300 * (player1.getHealth()/player1.getOriginalHealth()), 40);
			//Player2:
			fill(255, 0, 0);
			rect(width - 30 - 300, height - 140, 300, 40);
			fill(0, 255, 0);
			rect(width - 30 - 300, height - 140, 300 * (player2.getHealth()/player2.getOriginalHealth()), 40);
			//"if hit" logic (for left player)
			if(weapon2.collide(player1) == true && player1.getHealth() > 0) 
			{
				fill(0,0,255);
				rect(100,100,100,100);
				if(frameCount % 10 == 0)
					player1.setHealth(player1.getHealth() - 1);
				count++;
//				fill(255, 0, 0);
//				rect(30 + 300 * ((player1.getHealth()) / player1.getOriginalHealth()),
//					height - 140, 300 * (player1.getOriginalHealth() - player1.getHealth() / player1.getOriginalHealth()), 40);
			}
			if(weapon1.collide(player2) == true && player2.getHealth() > 0)
			{
				fill(0,0,255);
				rect(100,100,100,100);
				if(frameCount % 10 == 0)
					player2.setHealth(player2.getHealth() - 1);
				count++;
//				rect(width - 30 - 300, height - 140, 
//				300 * (player2.getOriginalHealth() - player2.getHealth() / player2.getOriginalHealth()), 40);
			}
			
		}
	}
	
	public void statusChecker() {
		if (notMenu)
			drawBackground();
		else if (isBattleDone)
			drawResults();
		else
			drawMenu();
	}
	public boolean player1TouchingGround() {
		if(player1.getY() == height - bottomBar - player1.getHeight()/2) 
			return true;
		else
			return false;		
	}
	
	public boolean player2TouchingGround() {
		if(player2.getY() == height - bottomBar - player2.getHeight()/2)
			return true;
		else
			return false;		
	}
	
	public void weaponFollow() {
		if(player1.isFacingLeft()) 
			weapon1.setX(player1.getX() - 2*p1.getWidth()/3);
		else
			weapon1.setX(player1.getX() + 2*p1.getWidth()/3);
		weapon1.setY(player1.getY());
		if(player2.isFacingLeft())
			weapon2.setX(player2.getX() - 2*p2.getWidth()/3);
		else
			weapon2.setX(player2.getX() + 2*p2.getWidth()/3);
		weapon2.setY(player2.getY());
	}
	
	public void drawHitboxes() {
		//Player1 hitbox:
		fill(0,0,255);
		rect(player1.getHitbox().x, player1.getHitbox().y, player1.getHitbox().width, player1.getHitbox().height);
		
		//Player2 hitbox:
		fill(0,0,255);
		rect(player2.getHitbox().x, player2.getHitbox().y, player2.getHitbox().width, player2.getHitbox().height);
	}
	
	public void drawFrontPage() {
		// Add Buttons and Animation later
	}

	public void drawSelectionScreen() {
		// Add Buttons and Animation later
	}

}


