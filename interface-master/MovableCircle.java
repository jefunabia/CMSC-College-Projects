public class MovableCircle implements Movable{
  private int radius;
  private MovablePoint center;
  
  public MovableCircle(int x, int y, int xSpeed, int ySpeed, int radius){
    center = new MovablePoint(x, y, xSpeed, ySpeed);
    this.radius = radius;
  }
  
 //methods
  public String toString(){
    return "Radius: " + radius + ", Center: " + center;
  }
  
  public void moveUp(){
    center.y -= center.ySpeed;
  }
  
  public void moveDown(){
    center.y += center.ySpeed;
  }
  
  public void moveLeft(){
    center.x -= center.xSpeed;
  }
  
  public void moveRight(){
    center.x += center.xSpeed;
  }
}