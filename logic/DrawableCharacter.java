import processing.core.PApplet;
import processing.core.PImage;

public class DrawableCharacter implements PDrawable {

	private Character c;
	
	public DrawableCharacter(Character c) {
		this.c = c;
	}
	
	@Override
	public void draw(PApplet p) {
		PImage img = new PImage();
		img = p.loadImage(c.getSprite().getFileName());
		img.resize(c.getWidth(), c.getHeight());
		p.image(img, c.getX(), c.getY());
	}
	
	public Character getCharacter() {
		return c;
	}
	
}


