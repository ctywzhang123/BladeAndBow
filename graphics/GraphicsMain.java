import java.io.File;
import processing.core.PImage;
import processing.core.PApplet;

public class GraphicsMain extends PApplet {

	// Sample Sprites:
	PImage img;
	File f = new File("PlayerSprite1.png");
	File g = new File("PlayerSprite2.png");

	// Player Sprites:
	PSprite p1 = new PSprite(f, 50, 40, 700, 350);
	PSprite p2 = new PSprite(g, 50, 40, 200, 350);

	// Player Characters:
	private Character player1 = new Character(p1, 10);
	private Character player2 = new Character(p2, 10);

	// Drawable Characters:
	private DrawableCharacter dPlayer1 = new DrawableCharacter(player1);
	private DrawableCharacter dPlayer2 = new DrawableCharacter(player2);

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
		background(0, 255, 0);
		System.out.println("loop");
		dPlayer1.draw(this);
		dPlayer2.draw(this);
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
			System.out.println(key);
			if (key == 'd') {
				System.out.println("chad");
			}
			if (key == 'w' || key == 'a' || key == 's' || key == 'd') {
				// Move Player 1 based on direction
				if (key == 'w' || key == 'W') {
					player1.move(0, -player1.getSpeed());
				} 
				else if (key == 'a' || key == 'A') {
					player1.move(-player1.getSpeed(), 0);
				} 
				else if (key == 's') {
					player1.move(0, player1.getSpeed());
				} 
				else if (key == 'd') {
					player1.move(player1.getSpeed(), 0);
				}

			} else if (key == CODED) {
				if (keyCode == UP || keyCode == LEFT || keyCode == RIGHT || keyCode == DOWN) {
					// Move Player 2 based on direction
					if (keyCode == UP) {
						player2.move(0, -player2.getSpeed());
					} 
					else if (keyCode == LEFT) {
						player2.move(-player2.getSpeed(), 0);
					} 
					else if (keyCode == RIGHT) {
						player2.move(player2.getSpeed(), 0);
					} 
					else if (keyCode == DOWN) {
						player2.move(0, player2.getSpeed());
					}
				}
			}
		}
	}

	public void drawFrontPage() {
		// Add Buttons and Animation later
	}

	public void drawSelectionScreen() {
		// Add Buttons and Animation later
	}

}


