import java.io.File;


public class PSprite { 
	private File img;
	private int width, height, x, y;
	
	/**
	 * Constructs a PSprite object
	 * 
	 * @param img   - Image of the Sprite
	 * @param w     - width
	 * @param h     - height
	 * @param x     - x-coordinate
	 * @param y     - y-coordinate
	 * @param speed - move speed
	 */
	public PSprite(File img, int width, int height, int x, int y) {
		this.img = img;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}
	
	public String getFileName() {
		return img.getName();
	}
	
	public void printFileName() {
		System.out.println(img.getName());
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int w) {
		this.width = w;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int h) {
		this.height = h;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}


