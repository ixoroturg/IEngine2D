package iEngine.math;

import java.util.Arrays;

public class Matrix2D extends Matrix{
	
	public Matrix2D(float... matrix) {
		m1 = matrix;
	}
	
	@Override
	public Matrix reverse() {
		float det = det();
		if(det == 0)
			return  this;
//		for(int i = 0; i < m1.length; i++) {
//			m1[i] /= det;
//		}		
//		m1[1] *= -1;
//		m1[2] *= -1;
		
		float[] result = new float[m1.length]; 
		
		result[0] = m1[3] / det;
		result[1] = -m1[1] / det;
		result[2] = -m1[2] / det;
		result[3] = m1[0] / det;
		
		m1 = result;
				
		return this;
	}
	
	@Override
	public Matrix mul(Matrix matrix) {
		float[] m2 = matrix.get();
//		if(m2.length < m1.length)
//			return this;
		float[] result = new float[m1.length];		
		result[0] = m1[0]*m2[0] + m1[1]*m2[2];
		result[1] = m1[0]*m2[1] + m1[1]*m2[3];
		result[2] = m1[2]*m2[0] + m1[3]*m2[2];
		result[3] = m1[2]*m2[1] + m1[3]*m2[3];	
		m1 = result;	
		return this;
	}
	@Override
	public float det() {
//		System.out.println("det: "+Arrays.toString(m1));
//		System.out.println("det answer: "+(m1[0]*m1[3] - m1[1]*m1[2]));
		return m1[0]*m1[3] - m1[1]*m1[2];
	}
	@Override
	public Matrix clone() {
		return new Matrix2D(Arrays.copyOf(m1, m1.length));
	}
	@Override
	public float[] mul(float... vector) {
		return new float[] {m1[0]*vector[0]+m1[1]*vector[1], m1[2] * vector[0] + m1[3]*vector[1]};
	}
}
