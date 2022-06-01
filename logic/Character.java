import java.awt.Rectangle;

import processing.core.PApplet;

public class Character extends GameObject implements Jumpable{
	
	private static int gravity = 1;
	private int xVelocity;
	private int yVelocity;
	private boolean walk;
	private boolean facingLeft; //TRUE = LEFT, FALSE = RIGHT
	private boolean jump;
	private boolean fall;
	
	//Player Health:
	private static int health;
	private int originalHealth;
	
	public Character(PSprite p, int speed, int playerHealth) {
		super(p, p.getWidth(), p.getHeight(), p.getX(), p.getY(), speed);
		xVelocity = 8;
		yVelocity = 8;
		health = playerHealth;
		originalHealth = playerHealth;
	}
	
	//Methods:
	public boolean isFacingLeft() {
		return facingLeft;
	}
	
	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}
	
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
	
	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
	public boolean isFall() {
		return fall;
	}
	
	public void setFall(boolean fall) {
		this.fall = fall;
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
		setXVelocity(getXVelocity() - gravity);
		setYVelocity(getYVelocity() - gravity);
	}
}
