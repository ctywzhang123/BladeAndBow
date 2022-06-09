import processing.core.PApplet;
import processing.core.PImage;

public class DrawableWeapon implements PDrawable {

	private Weapon w;
	PImage leftImg = new PImage();
	PImage rightImg = new PImage();
	
	public DrawableWeapon(Weapon w, PApplet p) {
		this.w = w;
		leftImg = p.loadImage(w.getLeftSprite().getFileName());
		leftImg.resize(w.getWidth(), w.getHeight());
		rightImg = p.loadImage(w.getRightSprite().getFileName());
		rightImg.resize(w.getWidth(), w.getHeight());
	}
	
	@Override
	public void draw(PApplet p) {
		if(w.isFacingLeft())
			p.image(leftImg, w.getX(), w.getY());
		else
			p.image(rightImg, w.getX(), w.getY());
	}
	
	public Weapon getWeapon() {
		return w;
	}

}



