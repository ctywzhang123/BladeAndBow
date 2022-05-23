import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;

public class PSprite extends GameObject{ //FIX!!!
private File img;
private int x, y, w, h;

/**
* Constructs a PSprite object
* @param img - Image of the Sprite
* @param w - width
* @param h - height
* @param x - x-coordinate
* @param y - y-coordinate
* @param speed - move speed
*/
public PSprite(File img, int w, int h, int x, int y) { //CHANGE PImage PARAMETER TO FILE PARAMETER!!!
super(w, h, x, y, 1);
this.img = img;
this.x = x;
this.y = y;
this.w = w;
this.h = h;
}

public File getImg() {
return img;
}

public void setImg(File img) {
this.img = img;
}

@Override
public void draw(PApplet p) {
PImage img = new PImage();
p.loadImage(this.img.getName());
img.resize(this.w, this.h);
p.image(img,  x,  y);
}

@Override
public void move(int xMove, int yMove) {
// TODO Auto-generated method stub

}

@Override
public boolean collide(GameObject g) {
// TODO Auto-generated method stub
return false;
}


}
