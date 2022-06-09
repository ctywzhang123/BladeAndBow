import java.awt.Rectangle;

import processing.core.PApplet;

public class Character extends GameObject implements Jumpable{
	
	private static int gravity = 1;
	private int xVelocity;
	private int yVelocity;
	
	//Player Health:
	private int health;
	private int originalHealth;
	
	public Character(PSprite pLeft, PSprite pRight, int speed, int playerHealth) {
		super(pLeft, pRight, pLeft.getWidth(), pLeft.getHeight(), pLeft.getX(), pLeft.getY(), speed);
		xVelocity = 6;
		yVelocity = 8;
		health = playerHealth;
		originalHealth = playerHealth;
	}
	
	//Methods:
	
	public int getXVelocity() {
		return xVelocity;
	}
	
	public int getYVelocity() {
		return yVelocity;
	}
	
	public void setXVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
	
	public void setYVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
	
	public int getOriginalHealth() {
		return originalHealth;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}	
	
	//Interface Methods:
	@Override
	public void move(int xMove, int yMove) {
		//UPDATE HITBOX
		setX(getX() + xMove);
		setY(getY() + yMove);
		setHitbox(new Rectangle(this.getHitbox().x + xMove, this.getHitbox().y + yMove, getWidth(), getHeight()));
	}

	@Override
	public boolean collide(GameObject g) {
		Weapon temp = (Weapon)g;
		if(this.getHitbox().intersects(temp.getHitbox())) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void jump(PApplet p) {
		setYVelocity(getYVelocity() - gravity);
	}
}


