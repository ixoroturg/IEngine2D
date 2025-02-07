package iEngine.math;

public interface Matrix {
	public Matrix add(Matrix matrix);
	public Matrix sub(Matrix matrix);
	public Matrix mul(double a);
	public Matrix mul(Matrix matrix);
	public Matrix div(Matrix matrix);	
	public Matrix reverse();
		
	public Matrix getE();
	public Matrix clone();
	
	public float[] get();
	
	public float det();
	
}
