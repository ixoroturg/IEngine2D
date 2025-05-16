package iEngine.test;
import iEngine.math.*;

public class Test {
	static Matrix2D m1 = new Matrix2D(2,3,4,7,5,1),
	 m2 = new Matrix2D(3,5,6,7,3,9),
	 waiting,
	 test = m1.copy();
	public static void startTest() throws TestFailedException{
		Matrix();
	}
	
	private static void Matrix() throws TestFailedException {
		
		waiting = new Matrix2D(5,8,10,14,8,10);
		testMatrix("add",test.add(m2));
		
		waiting = new Matrix2D(-1,-2,-2,0,2,-8);
		testMatrix("sub",test.sub(m2));
		
		waiting = new Matrix2D(10,15,20,35,25,5);
		testMatrix("mul",test.mul(5));
		
		waiting = new Matrix2D(26,44,40,67,47,73);
		testMatrix("mul matrix",test.mul(m2));
		
		float det = test.getDeterminant();
		if(det != 2)
			throw new TestFailedException("Детерминант "+test+", ожидалось 2, получено "+det);
		
		waiting = new Matrix2D(3.5f,-1.5f,-2,1,-15.5f,6.5f);
		testMatrix("reverse",test.reverse());
		
		
	}
	private static void testMatrix(String method, Matrix2D result) throws TestFailedException {
		if(!result.equals(waiting)) {
			throw new TestFailedException(method+": \n"+m1 + "\n"+m2 +"\nОжидалось "+waiting+", получено "+result);
		}
		test = m1.copy();
	}
}
