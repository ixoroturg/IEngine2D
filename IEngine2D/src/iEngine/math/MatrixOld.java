package iEngine.math;

import java.util.Arrays;

import iEngine.element.interfaces.Copyable;

public abstract class MatrixOld implements Copyable<MatrixOld> {

	protected float[] m1;

	public static MatrixOld get(float... matrix) {
		if (matrix.length == 4)
			return new Matrix2DOLD(matrix);
		if (matrix.length == 6)
			return new AffineMatrix(matrix);

		return null;
	}
	public MatrixOld add(MatrixOld matrix) {
//		if(matrix == null)
//			return this;
		float[] m2 = matrix.get();
		if (m2.length < m1.length)
			return this;
		for (int i = 0; i < m1.length; i++) {
			m1[i] += m2[i];
		}
		return this;
	}
	public MatrixOld sub(MatrixOld matrix) {
		float[] m2 = matrix.get();
		if (m2.length < m1.length)
			return this;
		for (int i = 0; i < m1.length; i++) {
			m1[i] -= m2[i];
		}
		return this;
	}
	public MatrixOld mul(double a) {
//		float[] result = new float[m1.length];
		for (int i = 0; i < m1.length; i++) {
			m1[i] *= a;
		}
		return this;
	}
	public MatrixOld paste(MatrixOld matrix) {
		float[] m2 = matrix.get();
		if (m2.length < m1.length)
			return this;
		for (int i = 0; i < m1.length; i++) {
			m1[i] = m2[i];
		}
		return this;
	}
	public MatrixOld set(float... matrix) {
		if (matrix.length < m1.length)
			return this;
		for (int i = 0; i < m1.length; i++) {
			m1[i] = matrix[i];
		}
		return this;
	}
	public MatrixOld div(MatrixOld matrix) {
		return mul(matrix.reverse());
	}
	public float[] get() {
		return m1;
	}
	@Override
	public MatrixOld clone() {
		return null;
	}
	@Override
	public String toString() {
		return "Матрица " + hashCode() + ":\n\t" + Arrays.toString(m1);
	}
	public static MatrixOld getE() {
		return new AffineMatrix(1, 0, 0, 1, 0, 0);
	}
	public abstract MatrixOld reverse();

	public abstract MatrixOld mul(MatrixOld matrix);

	public abstract float det();

	public abstract float[] mul(float... vector);

}
