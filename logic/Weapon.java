import java.awt.Rectangle;

public class Weapon extends GameObject implements Attackable {
	
	PSprite temp = new PSprite(null, 0, 0, 0, 0);
	private int damage = 4;
	private double cooldown;
	private int attack = -1; //-1 = READY (No attack), 0 = IN-PROGRESS/COOLDOWN, 1 = ATTACK REGISTERS
	private Rectangle hitbox;

	public Weapon(PSprite pLeft, PSprite pRight, int speed, int damage, double cooldown) {
		super(pLeft, pRight, pLeft.getWidth(), pLeft.getHeight(), pLeft.getX(), pLeft.getY(), speed);
		this.hitbox = new Rectangle(pLeft.getX(), pLeft.getY(), pLeft.getHeight(), pLeft.getWidth());
		this.damage = damage;
		this.cooldown = cooldown;
	}
	
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public double getCooldown() {
		return cooldown;
	}
	
	//Interface Methods:
	@Override
	public void move(int xMove, int yMove) {
		setX(getX() + xMove);
		setY(getY() + yMove);
		setHitbox(new Rectangle(getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight())); //FIX
	}

	@Override
	public boolean collide(GameObject g) {
		if(attack == -1 || attack == 0) {
			return false;
		}
		Character temp = (Character)g;
		if(this.getHitbox().intersects(temp.getHitbox())) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void attack() {
		attack = 1;
	}
}


