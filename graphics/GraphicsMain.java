import java.io.File;
import processing.core.PImage;
import processing.core.PApplet;

public class GraphicsMain extends PApplet {

	// Sample Sprites:
	PImage img;
	File f = new File("PlayerSprite1.png");
	File g = new File("PlayerSprite2.png");

	// Player Sprites:
	PSprite p1 = new PSprite(f, 50, 40, 200, 350);
	PSprite p2 = new PSprite(g, 50, 40, 700, 350);

	// Player Characters:
	private Character player1 = new Character(p1, 5);
	private Character player2 = new Character(p2, 5);
	
	//Player Weapons:
	private Weapon weapon1; //FIX
	private Weapon weapon2; //FIX

	// Drawable Characters:
	private DrawableCharacter dPlayer1 = new DrawableCharacter(player1);
	private DrawableCharacter dPlayer2 = new DrawableCharacter(player2);
	
	//Drawable Weapons:
	private DrawableWeapon dWeapon1 = new DrawableWeapon(weapon1);
	private DrawableWeapon dWeapon2 = new DrawableWeapon(weapon2);
	
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
	private boolean inCombat = true;
	private boolean inFrontPage = false;
	private boolean inSelection = false;

	public static void main(String[] args) {
		PApplet.main("GraphicsMain");
	}

	public void settings() {
		size(1300, 750);
	}

	public void setup() {
		background(0, 255, 0);
	}

	public void draw() {
		//In Draw, check and move accordingly
		moveCheck();
		background(0, 255, 0);
		dPlayer1.draw(this);
		dPlayer2.draw(this);
		drawCombatBar();
	}

	public void drawMenu() {
		img = loadImage("menu.jpg");
	}

	public void drawBackground() {
		img = loadImage("background.png");
	}

	public void drawResults() {
		img = loadImage("results.jpg");
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
				} 
				else if (key == 's') {
					down1 = true;
				} 
				else if (key == 'd') {
					right1 = true;
				}
			} else if (key == CODED) {
				if (keyCode == UP || keyCode == LEFT || keyCode == RIGHT || keyCode == DOWN) {
					// Move Player 2 based on direction
					if (keyCode == UP) {
						up2 = true;
					} 
					else if (keyCode == LEFT) {
						left2 = true;
					} 
					else if (keyCode == RIGHT) {
						right2 = true;
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
				up1 = false;
			} 
			else if (key == 'a') {
				left1 = false;
			} 
			else if (key == 's') {
				down1 = false;
			} 
			else if (key == 'd') {
				right1 = false;
			}
		}
		else {
			//Player 2 Smooth Movement
			if (keyCode == UP) {
				up2 = false;
			} 
			else if (keyCode == LEFT) {
				left2 = false;
			} 
			else if (keyCode == DOWN) {
				down2 = false;
			} 
			else if (keyCode == RIGHT) {
				right2 = false;
			}
		}
	}
	
	/**
	 * Checks the status of movement booleans and moves either player accordingly, allowing for smooth movement
	 */
	public void moveCheck() {
		if(left1) {
			player1.move(-player1.getSpeed(), 0);
		}
		if(up1) {
			player1.move(0, -player1.getSpeed());
		}
		if(down1) {
			player1.move(0, player1.getSpeed());
		}
		if(right1) {
			player1.move(player1.getSpeed(), 0);
		}
		if(left2) {
			player2.move(-player2.getSpeed(), 0);
		}
		if(up2) {
			player2.move(0, -player2.getSpeed());
		}
		if(down2) {
			player2.move(0, player2.getSpeed());
		}
		if(right2) {
			player2.move(player2.getSpeed(), 0);
		}
	}
	
	/**
	 * Draws the Bottom Black Bar during Combat Mode
	 */
	public void drawCombatBar() {
		if(inCombat) {
			fill(0,0,0);
			rect(0, height - 160, width, 160);
			//Add Other Stuff
		}
	}
	
	public void drawFrontPage() {
		// Add Buttons and Animation later
	}

	public void drawSelectionScreen() {
		// Add Buttons and Animation later
	}

}


