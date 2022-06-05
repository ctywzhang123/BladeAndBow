import java.awt.Rectangle;

public class Weapon extends GameObject implements Attackable {
	
	PSprite temp = new PSprite(null, 0, 0, 0, 0);
	private boolean walk;
	private boolean jump;
	private int damage = 4;
	private int cooldown;
	private int attack = -1; //-1 = READY (No attack), 0 = IN-PROGRESS/COOLDOWN, 1 = ATTACK REGISTERS
	private Rectangle hitbox;
	private Rectangle originalHitbox;

	public Weapon(PSprite p, int speed, int damage, int cooldown) {
		super(p, p.getWidth(), p.getHeight(), p.getX(), p.getY(), speed);
		this.hitbox = new Rectangle(p.getX(), p.getY(), p.getHeight(), p.getWidth());
		this.originalHitbox = hitbox;
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
	
	public int getCooldown() {
		return cooldown;
	}
	
	//Interface Methods:
	@Override
	public void move(int xMove, int yMove) {
		setX(getX() + xMove);
		setY(getY() + yMove);
		setHitbox(new Rectangle(getX(), getY(), getWidth(), getHeight())); //FIX
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


