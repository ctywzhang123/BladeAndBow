import processing.core.PApplet;
import processing.core.PImage;

public class DrawableWeapon implements PDrawable {

	private Weapon w;
	PImage img = new PImage();
	
	public DrawableWeapon(Weapon w, PApplet p) {
		this.w = w;
		img = p.loadImage(w.getSprite().getFileName());
		img.resize(w.getWidth(), w.getHeight());
	}
	
	@Override
	public void draw(PApplet p) {
		p.image(img, w.getX(), w.getY());
	}
	
	public Weapon getWeapon() {
		return w;
	}

}
