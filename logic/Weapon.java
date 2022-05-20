import processing.core.PApplet;

 

public class Weapon extends GameObject{

 

       public Weapon(int width, int height, int x, int y, int speed) {

              super(width, height, x, y, speed);

       }

 

       @Override

       public void move(int xMove, int yMove) {

              setX(getX() + xMove);

              setY(getY() + yMove);           

       }

 

       @Override

       public boolean collide(GameObject g) {

              //Check if the Weapon hitbox collides with a player hitbox

              //X and Y coordinates are the center of the image/hitbox rectangle

              Character c = new Character(0, 0, 0, 0, 0);

              if(g.getClass() != c.getClass()) //Ensure Weapon hits a player

                     return false;

              else {

                     if((this.getX() + this.getWidth()/2 >= g.getX() - g.getWidth()/2) &&

                                  (this.getY() + this.getHeight()/2 >= g.getY() - g.getHeight()/2) &&

                                  (this.getY() - this.getHeight()/2 <= g.getY() + g.getHeight())) {

                           return true;

                     }

                     else {

                           return false;

                     }

              }

       }

 

       @Override

       public void draw(PApplet p) {

              // TODO Auto-generated method stub

             

       }

 

}
