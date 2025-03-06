package iEngine.math;
import iEngine.element.interfaces.Cloneable;
public interface Matrix extends Cloneable<Matrix>{
	
	public Matrix add(Matrix matrix);
	public Matrix sub(Matrix matrix);
	public Matrix mul(double a);
	public Matrix mul(Matrix matrix);
	public Matrix div(Matrix matrix);	
	public Matrix reverse();

	public float det();
	public static Matrix getE() {
		return new Matrix2D(1,0,0,1);
	}
	public float[] get();
//	public Matrix set(Matrix matrix);
	public Matrix set(float... matrix);
//	public Matrix clone();
}
