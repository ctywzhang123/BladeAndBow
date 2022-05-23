import java.io.File;

import processing.core.PApplet;

public class GraphicsMain extends PApplet {
	
	//Sample Sprites:
	File f = new File("PlayerSprite1.png");
	File g = new File("PlayerSprite2.png");

	
	//Game Status Fields:
	private boolean inCombat = false;
	private boolean inFrontPage = true;
	private boolean inSelection = false;
	
	
	public static void main(String[] args) {
		PApplet.main("GraphicsMain");
	}

	public void settings() {
		size(1400, 700);
	}

	public void setup() {
//		background(0,255,0);
		PSprite p1 = new PSprite(f, 200, 200, 700, 350);
		p1.printFileName();
		p1.draw(this);
		PSprite p2 = new PSprite(g, 200, 200, 200, 350);
		p2.printFileName();
		p2.draw(this);
		
	}

	public void draw() {
		
	}
	
	/**
	 * Checks for User Inputs
	 * Player 1 Controls: WASD
	 * Player 2 Controls: Arrow Keys
	 */
	public void keyPressed() {
		if(inCombat) { //Checks if in combat
			if(key == 'w' || key == 'a' || key == 's' || key == 'd') {
				//Move Player 1 based on direction
			}
			else if(key == CODED) {
				if(keyCode == UP || keyCode == LEFT || keyCode == RIGHT || keyCode == DOWN) {
					//Move Player 2 based on direction
				}
			}
		}
	}
	
	public void drawFrontPage() {
		//Add Buttons and Animation later
	}
	
	public void drawSelectionScreen() {
		//Add Buttons and Animation later
	}

}
