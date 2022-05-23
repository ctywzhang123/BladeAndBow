import processing.core.PApplet;

public class Weapon extends GameObject {
	
	private PSprite ps;

	public Weapon(PSprite p, int width, int height, int x, int y, int speed) {
		super(width, height, x, y, speed);
		ps = p;
	}

	//Interface Methods:
	@Override
	public void move(int xMove, int yMove) {
		setX(getX() + xMove);
		setY(getY() + yMove);
	}

	@Override
	public boolean collide(GameObject g) {
		Character w = new Character(ps, 0, 0, 0, 0, 0);
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

	@Override
	public void draw(PApplet p) {
		ps.draw(p);		
	}

}
