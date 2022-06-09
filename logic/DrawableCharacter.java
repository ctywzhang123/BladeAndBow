import processing.core.PApplet;
import processing.core.PImage;

public class DrawableCharacter implements PDrawable {

	private Character c;
	PImage leftImg = new PImage();
	PImage rightImg = new PImage();
	
	public DrawableCharacter(Character c, PApplet p) {
		this.c = c;
		leftImg = p.loadImage(c.getLeftSprite().getFileName());
		leftImg.resize(c.getWidth(), c.getHeight());
		rightImg = p.loadImage(c.getRightSprite().getFileName());
		rightImg.resize(c.getWidth(), c.getHeight());
	}
	
	@Override
	public void draw(PApplet p) {
		if(c.isFacingLeft())
			p.image(leftImg, c.getX(), c.getY());
		else
			p.image(rightImg, c.getX(), c.getY());
	}
	
	public Character getCharacter() {
		return c;
	}
	
}



