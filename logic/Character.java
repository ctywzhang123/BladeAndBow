import java.awt.Rectangle;

public class Character extends GameObject {
	
	PSprite temp = new PSprite(null, 0, 0, 0, 0);
	private boolean walk;
	private boolean jump;
	
	public Character(PSprite p, int speed) {
		super(p, p.getWidth(), p.getHeight(), p.getX(), p.getY(), speed);
	}
	
	//Interface Methods:
	@Override
	public void move(int xMove, int yMove) {
		//UPDATE HITBOX
		setX(getX() + xMove);
		setY(getY() + yMove);
		setHitbox(new Rectangle(getX(), getY(), getWidth(), getHeight()));
	}

	@Override //CHANGE BACK
	public boolean collide(GameObject g) {
		Weapon w = new Weapon(temp, 0); //TEMPORARY CHECK
		if(g.getClass() != w.getClass()) {
			return false;
		}
		else {
			Weapon temp = (Weapon)g;
			if(this.getHitbox().intersects(temp.getHitbox())) {
				return true;
			}
			else {
				return false;
			}
		}
	}

}


