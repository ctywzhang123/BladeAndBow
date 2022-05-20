import processing.core.PApplet;
import processing.core.PImage;

public abstract class PSprite extends GameObject{
	private PImage img;
	private int x, y, w, h;
	
	public PSprite(PImage img, int w, int h, int x, int y, int speed) {
		super(w, h, x, y, speed);
		this.img = img;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public PImage getImg() {
		return img;
	}

	public void setImg(PImage img) {
		this.img = img;
	}
	
	public void draw(PApplet p) {
		p.image(img,  x,  y);
	}
	

}
