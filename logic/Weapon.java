
public class Weapon extends GameObject {
	
	PSprite temp = new PSprite(null, 0, 0, 0, 0);
	private boolean walk;
	private boolean jump;

	public Weapon(PSprite p, int speed) {
		super(p, p.getWidth(), p.getHeight(), p.getX(), p.getY(), speed);
	}

	//Interface Methods:
	@Override
	public void move(int xMove, int yMove) {
		setX(getX() + xMove);
		setY(getY() + yMove);
	}

	@Override
	public boolean collide(GameObject g) {
		Character w = new Character(temp, 0);
		if(g.getClass() != w.getClass()) {
			return false;
		}
		else {
			Character temp = (Character)g;
			if(this.getHitbox().intersects(temp.getHitbox())) {
				return true;
			}
			else {
				return false;
			}
		}
	}

}


