import EngineMath.*;
public class IEngine2D {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Hello from engine");
		
		Point p1 = new Point(0,0);
		
		Point p2 = new Point(1,1);
		Point p3 = new Point(1,2);
		
		Point p4 = new Point(-2,1);
		Point p5 = new Point(-1,2);
		
		Point p6 = new Point(-2,-1);
		Point p7 = new Point(-1,-2);
		
		Point p8 = new Point(2,-1);
		Point p9 = new Point(1,-2);
		Point p10 = p2.clone();
		System.out.println(p10);
		//System.out.println(Math.toDegrees(p1.getAngl(p2)));
		//System.out.println(Math.toDegrees(Math.atan(Math.tan(Math.toRadians(150)))));
	}

}
