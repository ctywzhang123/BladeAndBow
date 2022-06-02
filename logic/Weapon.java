import java.awt.Rectangle;

 

public class Weapon extends GameObject implements Attackable {

      

       PSprite temp = new PSprite(null, 0, 0, 0, 0);

       private boolean walk;

       private boolean jump;

       private boolean attack = false;

       private Rectangle hitbox;

       private Rectangle originalHitbox;

 

       public Weapon(PSprite p, int speed) {

              super(p, p.getWidth(), p.getHeight(), p.getX(), p.getY(), speed);

              this.hitbox = new Rectangle(p.getX(), p.getY(), p.getHeight(), p.getWidth());

              this.originalHitbox = hitbox;

       }

 

       public void setAttack(boolean attack) {

              this.attack = attack;

       }

      

       //Interface Methods:

       @Override

       public void move(int xMove, int yMove) {

              setX(getX() + xMove);

              setY(getY() + yMove);

              setHitbox(new Rectangle(getX(), getY(), getWidth(), getHeight())); //FIX

       }

 

       @Override

       public boolean collide(GameObject g) {

              if(!attack) {

                     return false;

              }

              Character temp = (Character)g;

              if(this.getHitbox().intersects(temp.getHitbox())) {

                     return true;

              }

              else {

                     return false;

              }

       }

 

       @Override

       public void attack() {

              attack = true;

       }

}
