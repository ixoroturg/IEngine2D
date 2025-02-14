package iEngine.math;

public interface Matrix {
	
	public Matrix add(Matrix matrix);
	public Matrix sub(Matrix matrix);
	public Matrix mul(double a);
	public Matrix mul(Matrix matrix);
	public Matrix div(Matrix matrix);	
	public Matrix reverse();

	public float det();
	
	public Matrix getE();
	
	public float[] get();
	public Matrix set(Matrix matrix);
	public Matrix set(float... matrix);
	public Matrix clone();
}
