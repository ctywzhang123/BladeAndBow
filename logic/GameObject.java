public abstract class GameObject {

       //Sprite Fields:
       private int width; //Sprite Hitbox
       private int height; //Sprite Hitbox
       private int x; //Sprite Coords
       private int y; //Sprite Coords
 
       //Characteristic Fields:
 
       private int speed;
 
       //Constructor:
       public GameObject(int width,int height, int x, int y, int speed) {
              this.width = width;
              this.height = height;
              this.x = x;
              this.y = y;
              this.speed = speed;
       }

       //Methods:
      
