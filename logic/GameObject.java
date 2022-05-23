import java.awt.Rectangle;

public abstract class GameObject implements Movable,Collidable,PDrawable {

	// Sprite Fields:
	private int width; // Sprite Width
	private int height; // Sprite Height
	private int x; // Sprite Coords
	private int y; // Sprite Coords
	
	//Sprite Hitbox using Rectangle Class:
	private Rectangle hitbox;

	// Characteristic Fields:
	private int speed;

	// Constructor:
	public GameObject(int width, int height, int x, int y, int speed) {
              this.width = width;
              this.height = height;
              this.x = x;
              this.y = y;
              this.speed = speed;
              this.hitbox = new Rectangle(this.height, this.width, this.x, this.y);
       }

	//Getters and Setters:
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

	//Methods:
}
