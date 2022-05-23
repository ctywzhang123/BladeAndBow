import processing.core.PApplet;
import processing.core.PImage;

public class DrawableWeapon implements PDrawable {

	private Weapon w;
	public DrawableWeapon(Weapon w) {
		this.w = w;
	}
	
	@Override
	public void draw(PApplet p) {
		PImage img = new PImage();
		img = p.loadImage(w.getSprite().getFileName());
		img.resize(w.getWidth(), w.getHeight());
		p.image(img, w.getX(), w.getY());
	}
	
	public Weapon getWeapon() {
		return w;
	}

}


