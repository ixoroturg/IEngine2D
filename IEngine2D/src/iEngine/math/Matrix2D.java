package iEngine.math;

import java.util.Arrays;

import iEngine.element.interfaces.Copyable;

public class Matrix2D implements Copyable<Matrix2D>{
	private float[] m1 = {1,0,0,1,0,0};
	
	public Matrix2D(float ix,float iy,float jx,float jy,float kx,float ky) {
		m1[0] = ix;
		m1[1] = iy;
		m1[2] = jx;
		m1[3] = jy;
		m1[4] = kx;
		m1[5] = ky;
	}
	public Matrix2D(double... m2) {
		if(m2.length < 6)
			return;
		for(int i = 0; i < 6; i++) {
			m1[i] = (float)m2[i];
		}
	}
	public Matrix2D(float... m2) {
		if(m2.length < 6)
			return;
		for(int i = 0; i < 6; i++) {
			m1[i] = m2[i];
		}
	}
	
	public Matrix2D add(Matrix2D matrix) {
		float[] m2 = matrix.get();
		for(int i = 0; i < 6; i++) {
			m1[i] += m2[i];
		}
		return this;
	}
	public Matrix2D sub(Matrix2D matrix) {
		float[] m2 = matrix.get();
		for(int i = 0; i < 6; i++) {
			m1[i] -= m2[i];
		}
		return this;
	}
	public Matrix2D mul(float a) {
		for(int i = 0; i < 6; i++) {
			m1[i] *= a;
		}
		return this;
	}
	public Matrix2D mul(Matrix2D matrix) {
		float[] m2 = matrix.get();
		float[] result = new float[6];

		result[0] = m1[0]*m2[0]+m1[2]*m2[1];
		result[1] = m1[1]*m2[0]+m1[3]*m2[1];
		result[2] = m1[0]*m2[2]+m1[2]*m2[3];
		result[3] = m1[1]*m2[2]+m1[3]*m2[3];
		result[4] = m1[0]*m2[4]+m1[2]*m2[5]+m1[4];
		result[5] = m1[1]*m2[4]+m1[3]*m2[5]+m1[5];
		m1 = result;
		return this;
	}
	public Matrix2D div(Matrix2D matrix) {
		return mul(matrix.copy().reverse());
	}
	public float getDeterminant() {
		return m1[0]*m1[3] - m1[2]*m1[1];
	}
	public Matrix2D reverse() {
		float det = getDeterminant();
		if(det == 0)
			return this;
		float[] result = new float[6];
		result[0] = m1[3];
		result[1] = -m1[1];
		result[2] = -m1[2];
		result[3] = m1[0];
		result[4] = m1[2]*m1[5]-m1[4]*m1[3];
		result[5] = m1[4]*m1[1]-m1[0]*m1[5];
		m1 = result;
		mul(1/det);
		return this;
	}
	
	
	public float[] get() {
		return m1;
	}
	public static Matrix2D getE() {
		return new Matrix2D(1,0,0,1,0,0);
	}
	public boolean equals(Matrix2D matrix) {
		float[] m2 = matrix.get();
		for(int i = 0; i < 6; i++) {
			if(m1[i] != m2[i])
				return false;
		}
		return true;
	}
	
	public String toString() {
		return "Матрица: "+Arrays.toString(m1);
	}
	public Matrix2D transform(Matrix2D matrix) {
		mul(matrix);
		return this;
	}
	@Override
	public Matrix2D copy() {
		return new Matrix2D(m1);
	}
	@Override
	public Matrix2D paste(Matrix2D matrix) {
		float[] m2 = matrix.get();
		for(int i = 0; i < 6; i++) {
			m1[i] = m2[i];
		}
		return this;
	}
	public float[] resolveSLAE() {
		float[] result = new float[2];
		float det = m1[0]*m1[3] - m1[2]*m1[1];
		if(det == 0){
			return null;
		}
		result[0] = m1[4]*m1[3] - m1[2]*m1[5] / det;
		result[1] = m1[0]*m1[5] - m1[4]*m1[1] / det;
		return result;
	}
	
	public static Matrix2D translate(float x, float y) {
		return new Matrix2D(1,0,0,1,x,y);
	}
	public static Matrix2D scale(float x, float y) {
		return new Matrix2D(x,0,0,y,0,0);
	}
	public static Matrix2D rotate(float angle) {
		return new Matrix2D((float)Math.cos(angle),(float)Math.sin(angle),(float)-Math.sin(angle),(float)Math.cos(angle),0,0);
	}
	public static Matrix2D shape(float x, float y) {
		return new Matrix2D(1,x,y,1,0,0);
	}
}
