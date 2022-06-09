import java.awt.Rectangle;

public abstract class GameObject implements Movable, Collidable {

	// Sprite Fields:
	private int width; // Sprite Width
	private int height; // Sprite Height
	private int x; // Sprite Coords
	private int y; // Sprite Coords
	private int speed; // Sprite Speed

	// Sprite Hitbox using Rectangle Class:
	private Rectangle hitbox;

	// PSprite:
	private PSprite leftS;
	private PSprite rightS;
	
	//Direction:
	private boolean facingLeft; //TRUE = LEFT, FALSE = RIGHT

	// Constructor:
	public GameObject(PSprite leftS, PSprite rightS, int width, int height, int x, int y, int speed) {
		this.leftS = leftS;
		this.rightS = rightS;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.hitbox = new Rectangle(x - width/2, y - height/2, width, height);
	}

	public boolean isFacingLeft() {
		return facingLeft;
	}
	
	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}
	
	// Getters and Setters:
	public PSprite getLeftSprite() {
		return leftS;
	}
	
	public PSprite getRightSprite() {
		return rightS;
	}
	
	public void setLeftSprite(PSprite left) {
		leftS = left;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle r) {
		hitbox = r;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		hitbox = new Rectangle(this.x - width/2, y - height/2, width, height);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		hitbox = new Rectangle(x - width/2, this.y - height/2, width, height);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}


}


