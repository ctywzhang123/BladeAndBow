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
	private PSprite ps;

	// Constructor:
	public GameObject(PSprite ps, int width, int height, int x, int y, int speed) {
		this.ps = ps;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.hitbox = new Rectangle(x - width/2, y - height/2, width, height);
	}

	// Getters and Setters:
	public PSprite getSprite() {
		return ps;
	}
	
	public void setSprite(PSprite p) {
		ps = p;
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
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// Methods:

}


