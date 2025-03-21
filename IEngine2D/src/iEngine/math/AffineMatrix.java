package iEngine.math;

import java.util.Arrays;

public class AffineMatrix extends Matrix2D{
	public AffineMatrix(float... matrix){
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
		
		result[4] = m1[0]*m2[4] + m1[1]*m2[5] + m1[4];
		result[5] = m1[2]*m2[4] + m1[3]*m2[5] + m1[5];
		
		m1 = result;	
		return this;
	}
	@Override
	public float[] mul(float... vector) {
		return new float[] {m1[0]*vector[0]+m1[1]*vector[1] + m1[4], m1[2] * vector[0] + m1[3]*vector[1] + m1[5]};
	}
	
	@Override
	public AffineMatrix clone() {
		return new AffineMatrix(Arrays.copyOf(m1, m1.length));
	}
}
