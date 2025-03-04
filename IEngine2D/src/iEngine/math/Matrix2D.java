package iEngine.math;

import java.util.Arrays;

public class Matrix2D extends AbstractMatrix{
	
	public Matrix2D(float... matrix) {
		if(matrix.length < 4)
			System.exit(-1);
		m1 = matrix;
	}
	
	@Override
	public Matrix mul(Matrix matrix) {
		float[] m2 = matrix.get();
		if(m2.length < m1.length)
			return this;
		float[] result = new float[m1.length];		
		result[0] = m1[0]*m2[0] + m1[1]*m2[2];
		result[1] = m1[0]*m2[1] + m1[1]*m2[3];
		result[2] = m1[2]*m2[0] + m1[3]*m2[2];
		result[3] = m1[2]*m2[1] + m1[3]*m2[3];	
		m1 = result;	
		return this;
	}
	@Override
	public Matrix getE() {
		return new Matrix2D(1,0,1,0);
	}
	@Override
	public float det() {
		return m1[0]*m1[3] - m1[1]*m1[2];
	}
	@Override
	public Matrix clone() {
		return new Matrix2D(Arrays.copyOf(m1, m1.length));
	}
}
