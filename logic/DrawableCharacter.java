import processing.core.PApplet;
import processing.core.PImage;

public class DrawableCharacter implements PDrawable {

	private Character c;
	PImage img = new PImage();

	public DrawableCharacter(Character c, PApplet p) {
		this.c = c;
		img = p.loadImage(c.getSprite().getFileName());
		img.resize(c.getWidth(), c.getHeight());
	}

	@Override
	public void draw(PApplet p) {
		p.image(img, c.getX(), c.getY());
	}

	public Character getCharacter() {
		return c;
	}
}
