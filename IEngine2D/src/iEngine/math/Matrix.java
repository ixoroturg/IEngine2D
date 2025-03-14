package iEngine.math;

import java.util.Arrays;
import iEngine.element.interfaces.Cloneable;
public abstract class Matrix implements Cloneable<Matrix>{
	protected float[] m1;

	public static Matrix get(float... matrix) {
		if(matrix.length == 4)
			return  new Matrix2D(matrix);
		if(matrix.length == 6)
			return  new AffineMatrix(matrix);
		
		return null;
	}
	public Matrix add(Matrix matrix) {
//		if(matrix == null)
//			return this;
		float[] m2 = matrix.get();
			if(m2.length < m1.length)
				return this;
			for(int i = 0; i < m1.length; i++) {
				m1[i] += m2[i];
			}		
		return this;
	}
	
	public Matrix sub(Matrix matrix) {
		float[] m2 = matrix.get();
		if(m2.length < m1.length) 
			return this;
		for(int i = 0; i < m1.length; i++) {
			m1[i] -= m2[i];
		}	
		return this;
	}
	
	public Matrix mul(double a) {
//		float[] result = new float[m1.length];
		for(int i = 0; i < m1.length; i++) {
			m1[i] *= a;
		}
		return this;
	}
	
	public Matrix set(Matrix matrix) {
		float[] m2 = matrix.get();
		if(m2.length < m1.length)
			return this;
		for(int i = 0; i < m1.length; i++) {
			m1[i] = m2[i];
		}
		return this;
	}
	public Matrix set(float... matrix) {
		if(matrix.length < m1.length)
			return this;
		for(int i = 0; i < m1.length; i++) {
			m1[i] = matrix[i];
		}
		return this;
	}
	public Matrix div(Matrix matrix) {	
		return mul(matrix.reverse());
	}
	public float[] get() {
		return m1;
	}
	@Override
	public Matrix clone() {
		return null;
	}
	@Override
	public String toString() {
		return "Матрица "+hashCode()+":\n\t"+
				Arrays.toString(m1);
	}

	public static Matrix getE() {
		return new AffineMatrix(1,0,0,1,0,0);
	}
	public abstract Matrix reverse();
	public abstract Matrix mul(Matrix matrix);
	public abstract float det();
	public abstract float[] mul(float... vector);
}
