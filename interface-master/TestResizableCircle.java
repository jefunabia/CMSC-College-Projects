public class TestResizableCircle{
  public static void main(String[] args){
    GeometricObject c1 = new ResizableCircle(5.0);
    System.out.println(c1.getArea());
    System.out.println(c1.getPerimeter());
    
    Resizable c2 = (Resizable) c1;
    //System.out.println(c2);
    c2.resize(2);
    System.out.println(c2);
  }
}