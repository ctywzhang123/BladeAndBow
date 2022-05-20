public abstract class GameObject implements Movable, Collidable {

	// Sprite Fields:

	private int width; // Sprite Hitbox

	private int height; // Sprite Hitbox

	private int x; // Sprite Coords

	private int y; // Sprite Coords

	// Characteristic Fields:

	private int speed;

	// Constructor:

	public GameObject(int width, int height, int x, int y, int speed) {

		this.width = width;

		this.height = height;

		this.x = x;

		this.y = y;

		this.speed = speed;

	}

	// Methods:

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

}
