import java.awt.Rectangle;

import java.io.File;

import java.util.ArrayList;

import processing.core.PImage;

import processing.core.PApplet;

public class GraphicsMain extends PApplet {

	// GIF Stuff:

	boolean notMenu;

	boolean isBattleDone;

	int optcounter = 1;

	PImage[] images;

	int imageCount = 8;

	int frame = 0;

	// Testing:

	static int count = 0;

	// Controls:

	// DOWN or 'S' = Blade Attack

	// Z or M = Bow Attack

	// Platform Bar Y-coordinate:

	private int bottomBar = 160;

	// Attack Cooldowns:

	static double cooldownTimer1;

	static double cooldownTimer2;

	static double bowCooldownTimer1;

	static double bowCooldownTimer2;

	// Arrow Directions:

	int arrowDirection1 = 1;

	int arrowDirection2 = 1;

	// Sample Sprites:

	PImage img;

	File f = new File("PlayerSprite1.png");

	File g = new File("PlayerSprite2.png");

	File swordBlade = new File("SwordBlade.PNG");

	File sampleB = new File("SampleBow.PNG");

	File longBow = new File("LongBowLeft.png");

	PImage combatBackground = new PImage();

	PImage selectionBackground = new PImage();

	// PSprites:

	PSprite p1 = new PSprite(f, 50, 40, 100, 570);

	PSprite p2 = new PSprite(g, 50, 40, 1300 - 100, 570);

	PSprite sampleBlade1 = new PSprite(swordBlade, 20, 40, p1.getX() + 2 * p1.getWidth() / 3, p1.getY());

	PSprite sampleBlade2 = new PSprite(swordBlade, 20, 40, p2.getX() + 2 * p2.getWidth() / 3, p2.getY());

	PSprite sampleBow1 = new PSprite(longBow, 30, 10, p1.getX() + 2 * p1.getWidth() / 3, p1.getY());

	PSprite sampleBow2 = new PSprite(longBow, 30, 10, p2.getX() + 2 * p2.getWidth() / 3, p2.getY());

	// Player Characters:

	private Character player1 = new Character(p1, 5, 100);

	private Character player2 = new Character(p2, 5, 100);

	// Player Weapons:

	private Weapon weapon1 = new Blade(sampleBlade1, 5, 10, 1);

	private Weapon weapon2 = new Blade(sampleBlade2, 5, 10, 1);

	private Weapon bow1 = new Bow(sampleBow1, 24, 2, 3);

	private Weapon bow2 = new Bow(sampleBow2, 24, 2, 3);

	// Drawable Characters:

	private DrawableCharacter dPlayer1;

	private DrawableCharacter dPlayer2;

	// Gravity + Jump Fields:

	private static int gravity = -10;

	// Drawable Blades:

	private DrawableWeapon dWeapon1;

	private DrawableWeapon dWeapon2;

	// Drawable Bows:

	private DrawableWeapon dBow1;

	private DrawableWeapon dBow2;

	// Movement:

	private boolean left1 = false;

	private boolean up1 = false;

	private boolean right1 = false;

	private boolean left2 = false;

	private boolean up2 = false;

	private boolean right2 = false;

	// For Selection Screen:

	private int player1BladeChoice = 0;

	private int player2BladeChoice = 0;

	private int player1BowChoice = 0;

	private int player2BowChoice = 0;

	private int player1Stage = 0;

	private int player2Stage = 0;

	// Array of Possible Bows + Blades:

	Bow[] bowSelection = { new LongBow(p1), new CrossBow(p1) };

	Blade[] bladeSelection = { new DaggerBlade(p1), new SwordBlade(p1) };

	// ArrayList for Player Names:

	ArrayList<Character> player1Name = new ArrayList<Character>();

	ArrayList<Character> player2Name = new ArrayList<Character>();

	// Game Status Fields:

	private boolean inCombat = true;

	private boolean endScreen = false;

	private boolean inFrontPage = false;

	private boolean inSelection = false;

	public static void main(String[] args) {

		PApplet.main("GraphicsMain");

	}

	public void settings() {

		size(1300, 750);

	}

	public void setup() {

		// For Options:

//                            images = new PImage[imageCount];

//                for (int i = 1; i <= imageCount; i++) {

//            String filename = "options" + i + ".png";

//                            images[i-1] = loadImage(filename);

//                            images[i-1].resize(1300, 750);

//                }

		frameRate(60);

		// Other:

		player2.setFacingLeft(true);

		dWeapon1 = new DrawableWeapon(weapon1, this);

		dWeapon2 = new DrawableWeapon(weapon2, this);

		dPlayer1 = new DrawableCharacter(player1, this);

		dPlayer2 = new DrawableCharacter(player2, this);

		dBow1 = new DrawableWeapon(bow1, this);

		dBow2 = new DrawableWeapon(bow2, this);

		combatBackground = loadImage("BladeAndBowBackground.PNG");

		combatBackground.resize(1300, 630);

		selectionBackground = loadImage("StoneBackground.jpg");

		selectionBackground.resize(1300, 750);

	}

	public void draw() {

		inCombat = true;

		inSelection = false;

		if (inCombat) {

			imageMode(CORNER);

			image(combatBackground, 0, 0);

			// Update Positions and Drawings:

			moveCheck();

			bowMoveCheck();

			weaponFollow();

			jumpCheck();

			// Check Bounds

			if (player1TouchingGround()) {

				up1 = false;

				player1.setXVelocity(5);

				player1.setYVelocity(6);

			}

			if (player2TouchingGround()) {

				up2 = false;

				player2.setXVelocity(5);

				player2.setYVelocity(6);

			}

			// Check Game/Weapon/Character States:

			checkCooldowns();

			// Draw:

			imageMode(CENTER);

			dPlayer1.draw(this);

			dPlayer2.draw(this);

			imageMode(CENTER);

			dWeapon1.draw(this);

			dWeapon2.draw(this);

			imageMode(CENTER);

			dBow1.draw(this);

			dBow2.draw(this);

//                                drawPlayerHitboxes();

//                                            drawWeaponHitboxes();

			drawCombatBar();

			gameOver();

		}

		if (inSelection) {

			imageMode(CORNER);

			image(selectionBackground, 0, 0);

			// Dividing Bar Down the Middle:

			rectMode(CORNER);

			fill(0, 0, 0);

			rect(width / 2 - 5, 0, 10, height);

			// Draw Rotating Bar Displaying Options:

			fill(255, 255, 255);

			strokeWeight(3);

			rectMode(CENTER);

			rect(width / 4, height / 4, 100, 100);

			rect(3 * width / 4, height / 4, 100, 100);

			fill(0, 0, 0);

			text(player1BladeChoice, width / 4, height / 4);

			text(player2BladeChoice, 3 * width / 4, height / 4);

		}

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
	 * 
	 * Checks for User Inputs Player 1 Controls: WASD Player 2 Controls: Arrow
	 * 
	 * Keys
	 * 
	 */

	public void keyPressed() {

		if (inSelection) {

			if (key == 'a' || key == 'd') {

				if (key == 'a') {

					if (player1Stage == 0 && player1BladeChoice > 0)

						player1BladeChoice--;

					else if (player1Stage == 1 && player1BowChoice > 0)

						player1BowChoice--;

				}

				if (key == 'd') {

					if (player1Stage == 0 && player1BladeChoice < bladeSelection.length - 1)

						player1BladeChoice++;

					else if (player1Stage == 1 && player1BowChoice < bowSelection.length - 1)

						player1BowChoice++;

				}

			}

			if (key == CODED) {

				if (keyCode == LEFT) {

					if (player2Stage == 0 && player2BladeChoice > 0)

						player2BladeChoice--;

					else if (player2Stage == 1 && player2BowChoice > 0)

						player2BowChoice--;

				}

				if (keyCode == RIGHT) {

					if (player2Stage == 0 && player2BladeChoice < bladeSelection.length - 1)

						player2BladeChoice++;

					else if (player2Stage == 1 && player2BowChoice < bowSelection.length - 1)

						player2BowChoice++;

				}

			}

		}

		if (inCombat) { // Checks if in combat

			if (key == 'w' || key == 'a' || key == 's' || key == 'd' || key == 'z' || key == 'm') {

				// Move Player 1 based on direction

				if (key == 'w' || key == 'W') {

					up1 = true;

				} else if (key == 'a' || key == 'A') {

					left1 = true;

					player1.setFacingLeft(true);

				} else if (key == 's') {

					if (weapon1.getAttack() == -1) {

						weapon1.attack();

					}

				} else if (key == 'd') {

					right1 = true;

					player1.setFacingLeft(false);

				}

				else if (key == 'z') {

					if (player1.isFacingLeft()) {

						arrowDirection1 = -1;

					}

					else {

						arrowDirection1 = 1;

					}

					if (bow1.getAttack() == -1) {

						bow1.setAttack(1);

					}

				}

				else if (key == 'm') {

					if (player2.isFacingLeft()) {

						arrowDirection2 = -1;

					}

					else {

						arrowDirection2 = 1;

					}

					if (bow2.getAttack() == -1)

						bow2.setAttack(1);

				}

			} else if (key == CODED) {

				if (keyCode == UP || keyCode == LEFT || keyCode == RIGHT || keyCode == DOWN) {

					// Move Player 2 based on direction

					if (keyCode == UP) {

						up2 = true;

					} else if (keyCode == LEFT) {

						left2 = true;

						player2.setFacingLeft(true);

					} else if (keyCode == RIGHT) {

						right2 = true;

						player2.setFacingLeft(false);

					} else if (keyCode == DOWN) {

						if (weapon2.getAttack() == -1) {

							weapon2.attack();

						}

					}

				}

			}

		}

	}

	/**
	 * 
	 * Checks for Jumps and Smooth Movement
	 * 
	 */

	public void keyReleased() {

		if (key != CODED) {

			// Player 1 Smooth Movement

			if (key == 'w') {

				// Nothing

			}

			else if (key == 'a')

				left1 = false;

			else if (key == 'd')

				right1 = false;

		} else {

			// Player 2 Smooth Movement

			if (keyCode == UP) {

				// Nothing

			}

			else if (keyCode == LEFT)

				left2 = false;

			else if (keyCode == RIGHT)

				right2 = false;

		}

	}

	/**
	 * 
	 * Checks the status of movement booleans and moves either player
	 * 
	 * accordingly, allowing for smooth movement
	 * 
	 */

	public void moveCheck() {

		if (left1 && player1.getX() > 0 + player1.getWidth() / 2) {

			player1.move(-player1.getXVelocity(), 0);

			weapon1.move(-player1.getXVelocity(), 0);

		}

		if (up1 && player1.getY() > 0 + player1.getHeight() / 2) {

			player1.move(0, -player1.getYVelocity()); // FIX

			player1.jump(this);

			weapon1.move(0, -player1.getYVelocity());

		}

		if (right1 && player1.getX() < width - player1.getWidth() / 2) {

			player1.move(player1.getXVelocity(), 0);

			weapon1.move(player1.getXVelocity(), 0);

		}

		if (left2 && player2.getX() > 0 + player2.getWidth() / 2) {

			player2.move(-player2.getXVelocity(), 0);

			weapon2.move(-player2.getXVelocity(), 0);

		}

		if (up2 && player2.getY() > 0 + player2.getHeight() / 2) {

			player2.move(0, -player2.getYVelocity()); // FIX

			player2.jump(this);

			weapon2.move(0, -player2.getYVelocity());

		}

		if (right2 && player2.getX() < width - player2.getWidth() / 2) {

			player2.move(player2.getXVelocity(), 0);

			weapon2.move(player2.getXVelocity(), 0);

		}

	}

	/**
	 * 
	 * Checks Bow Movement
	 * 
	 */

	public void bowMoveCheck() {

		if (!bow1.getHitbox().intersects(new Rectangle(0, 0, width, height))) {

			bow1.setX(weapon1.getX());

			bow1.setY(weapon1.getY());

			bow1.setAttack(-1);

			// BRING BACK THE HITBOX

		}

		if (!bow2.getHitbox().intersects(new Rectangle(0, 0, width, height))) {

			bow2.setAttack(-1);

			bow2.setX(weapon2.getX());

			bow2.setY(weapon2.getY());

		}

		if (bow1.getAttack() == 1) {

			bow1.move(arrowDirection1 * bow1.getSpeed(), 0);

		}

		if (bow2.getAttack() == 1) {

			bow2.move(arrowDirection2 * bow2.getSpeed(), 0);

		}

	}

	public void jumpCheck() {

		if (up1 && player1.getY() > 0 + player1.getHeight() / 2) {

			player1.move(0, -player1.getSpeed() + gravity);

			weapon1.move(0, -player1.getSpeed() + gravity);

		}

		if (up2 && player2.getY() > 0 + player2.getHeight() / 2) {

			player2.move(0, -player2.getSpeed() + gravity);

			weapon2.move(0, -player2.getSpeed() + gravity);

		}

	}

	/**
	 * 
	 * Draws the Bottom Black Bar and Health Bar during Combat Mode
	 * 
	 */

	public void drawCombatBar() {

		if (inCombat) {

			// Bottom Bar

			rectMode(CORNER);

			fill(0, 0, 0);

			rect(0, height - 160, width, 160);

			// //Health Bar Logic:

			// Player1:

			fill(255, 0, 0);

			rect(30, height - 140, 300, 40);

			fill(0, 255, 0);

			rect(30, height - 140, (float) (300 * ((double) player1.getHealth() / player1.getOriginalHealth())), 40);

			// Player2:

			fill(255, 0, 0);

			rect(width - 30 - 300, height - 140, 300, 40);

			fill(0, 255, 0);

			rect(width - 30 - 300, height - 140,
					(float) (300 * ((double) player2.getHealth() / player2.getOriginalHealth())), 40);

			// "if hit" logic (for left player)

			if (weapon2.collide(player1) == true && player1.getHealth() > 0 && weapon2.getAttack() == 1) {

				fill(0, 0, 255);

				rect(100, 100, 100, 100);

				player1.setHealth(player1.getHealth() - weapon2.getDamage());

				// Add Knockback Here:

//                                                            player1.move(player2.getXVelocity() *3, -player2.getYVelocity() *3); //FIX!!!

				weapon2.setAttack(0);

			}

			if (weapon1.collide(player2) == true && player2.getHealth() > 0 && weapon1.getAttack() == 1) {

				fill(0, 0, 255);

				rect(900, 100, 100, 100);

				player2.setHealth(player2.getHealth() - weapon1.getDamage());

				// Add Knockback Here:

//                                                            player2.move(player1.getXVelocity() *3, -player1.getYVelocity() *3); //FIX!!!

				weapon1.setAttack(0);

			}

			if (bow1.collide(player2) == true && player2.getHealth() > 0 && bow1.getAttack() == 1) {

				fill(255, 0, 0);

				rect(900, 100, 100, 100);

				player2.setHealth(player2.getHealth() - bow1.getDamage());

				// Return to bow position:

				bow1.setAttack(0);

				bow1.setX(weapon1.getX());

				bow1.setY(weapon1.getY());

			}

			if (bow2.collide(player1) == true && player1.getHealth() > 0 && bow2.getAttack() == 1) {

				fill(255, 0, 0);

				rect(900, 100, 100, 100);

				player1.setHealth(player1.getHealth() - bow2.getDamage());

				bow2.setAttack(0);

				// Return to bow position:

				bow2.setX(weapon2.getX());

				bow2.setY(weapon2.getY());

			}

		}

	}

	/**
	 * 
	 * Checks Cooldowns for Both Players
	 * 
	 */

	public void checkCooldowns() {

		if (cooldownTimer1 < 0) {

			cooldownTimer1 = 0;

		}

		if (cooldownTimer2 < 0) {

			cooldownTimer2 = 0;

		}

		if (cooldownTimer1 > 0) {

			cooldownTimer1 -= (double) 1 / (60 * weapon1.getCooldown());

		}

		if (cooldownTimer2 > 0) {

			cooldownTimer2 -= (double) 1 / (60 * weapon2.getCooldown());

		}

		if (bowCooldownTimer1 < 0) {

			bowCooldownTimer1 = 0;

		}

		if (bowCooldownTimer2 < 0) {

			bowCooldownTimer2 = 0;

		}

		if (bowCooldownTimer1 > 0) {

			bowCooldownTimer1 -= (double) 1 / (60 * bow1.getCooldown());

		}

		if (bowCooldownTimer2 > 0) {

			bowCooldownTimer2 -= (double) 1 / (60 * bow2.getCooldown());

		}

		if (weapon1.getAttack() == 0 && cooldownTimer1 == 0) {

			weapon1.setAttack(-1);

			cooldownTimer1 = weapon1.getCooldown();

		}

		if (weapon2.getAttack() == 0 && cooldownTimer2 == 0) {

			weapon2.setAttack(-1);

			cooldownTimer2 = weapon2.getCooldown();

		}

		if (bow1.getAttack() == 0 && bowCooldownTimer1 == 0) {

			bow1.setAttack(-1);

			bowCooldownTimer1 = bow1.getCooldown();

		}

		if (bow2.getAttack() == 0 && bowCooldownTimer2 == 0) {

			bow2.setAttack(-1);

			bowCooldownTimer2 = bow2.getCooldown();

		}

	}

	public boolean player1TouchingGround() {

		// Account for Falling Too Much:

		if (player1.getY() > height - bottomBar - player1.getHeight() / 2) {

			player1.setY(height - bottomBar - player1.getHeight() / 2);

		}

		if (player1.getY() == height - bottomBar - player1.getHeight() / 2)

			return true;

		else

			return false;

	}

	public boolean player2TouchingGround() {

		// Account for Falling Too Much:

		if (player2.getY() > height - bottomBar - player2.getHeight() / 2) {

			player2.setY(height - bottomBar - player2.getHeight() / 2);

		}

		if (player2.getY() == height - bottomBar - player2.getHeight() / 2)

			return true;

		else

			return false;

	}

	public void weaponFollow() {

		if (player1.isFacingLeft()) {

			weapon1.setX(player1.getX() - 2 * p1.getWidth() / 3);

			if (bow1.getAttack() == -1)

				bow1.setX(player1.getX() - 2 * p1.getWidth() / 3);

		}

		else {

			weapon1.setX(player1.getX() + 2 * p1.getWidth() / 3);

			if (bow1.getAttack() == -1)

				bow1.setX(player1.getX() + 2 * p1.getWidth() / 3);

		}

		weapon1.setY(player1.getY());

		if (bow1.getAttack() == -1)

			bow1.setY(player1.getY());

		if (bow1.getAttack() == -1)

			bow1.setY(player1.getY());

		if (player2.isFacingLeft()) {

			weapon2.setX(player2.getX() - 2 * p2.getWidth() / 3);

			if (bow2.getAttack() == -1 || bow2.getAttack() == 0)

				bow2.setX(player2.getX() - 2 * p2.getWidth() / 3);

		}

		else {

			weapon2.setX(player2.getX() + 2 * p2.getWidth() / 3);

			if (bow2.getAttack() == -1 || bow2.getAttack() == 0)

				bow2.setX(player2.getX() + 2 * p2.getWidth() / 3);

		}

		weapon2.setY(player2.getY());

		if (bow2.getAttack() == -1 || bow2.getAttack() == 0)

			bow2.setY(player2.getY());

	}

	public void drawPlayerHitboxes() {

		rectMode(CORNER);

		// Player1 hitbox:

		fill(0, 0, 255);

		rect(player1.getHitbox().x, player1.getHitbox().y, player1.getHitbox().width, player1.getHitbox().height);

		// Player2 hitbox:

		fill(0, 0, 255);

		rect(player2.getHitbox().x, player2.getHitbox().y, player2.getHitbox().width, player2.getHitbox().height);

	}

	public void drawWeaponHitboxes() {

		// Weapon1 hitbox:

		rectMode(CORNER);

		fill(0, 255, 255);

		rect((int) weapon1.getHitbox().x, (int) weapon1.getHitbox().y, weapon1.getHitbox().width,
				weapon1.getHitbox().height);

		// Weapon2 hitbox:

		fill(0, 255, 255);

		rect((int) weapon2.getHitbox().x, (int) weapon2.getHitbox().y, weapon2.getHitbox().width,
				weapon2.getHitbox().height);

	}

	public void gameOver() {

		if (player1.getHealth() <= 0 || player2.getHealth() <= 0) {

			inCombat = false;

			if (player1.getHealth() <= 0) {

				background(100, 0, 0);

				fill(255, 255, 255);

				textSize(100);

				text("Player 2 Wins", 300, 200);

			}

			else if (player2.getHealth() <= 0) {

				background(0, 100, 0);

				fill(255, 255, 255);

				textSize(100);

				text("Player 1 Wins", 300, 200);

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
