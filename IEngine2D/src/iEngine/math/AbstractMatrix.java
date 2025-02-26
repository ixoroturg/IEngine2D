package iEngine.math;

import java.util.Arrays;

public abstract class AbstractMatrix implements Matrix{
	protected float[] m1;

	@Override
	public Matrix add(Matrix matrix) {
		if(matrix == null)
			return this;
		float[] m2 = matrix.get();
			if(m2.length < m1.length)
				return this;
			for(int i = 0; i < m1.length; i++) {
				m1[i] += m2[i];
			}		
		return this;
	}

	@Override
	public Matrix sub(Matrix matrix) {
		float[] m2 = matrix.get();
		if(m2.length < m1.length) 
			return this;
		for(int i = 0; i < m1.length; i++) {
			m1[i] -= m2[i];
		}	
		return this;
	}
	@Override
	public Matrix mul(double a) {
		for(int i = 0; i < m1.length; i++) {
			m1[i] *= a;
		}
		return this;
	}
	@Override
	public Matrix set(Matrix matrix) {
		float[] m2 = matrix.get();
		if(m2.length < m1.length)
			return this;
		for(int i = 0; i < m1.length; i++) {
			m1[i] = m2[i];
		}
		return this;
	}
	@Override
	public Matrix set(float... matrix) {
		if(matrix.length < m1.length)
			return this;
		for(int i = 0; i < m1.length; i++) {
			m1[i] = matrix[i];
		}
		return this;
	}
	@Override
	public Matrix reverse() {
		float det = det();
		if(det == 0)
			return  this;
		for(int i = 0; i < m1.length; i++) {
			m1[i] /= det;
			if((i & 1) != 0) 
				m1[i] *= -1;
		}		
		return this;
	}
	
	@Override
	public Matrix div(Matrix matrix) {	
		return mul(matrix.reverse());
	}

	@Override
	public float[] get() {
		return m1;
	}
	@Override
	public Matrix clone() {
		return clone();
	}
	@Override
	public String toString() {
		return "Матрица "+hashCode()+":\n\t"+
	Arrays.toString(m1);
	}
}
