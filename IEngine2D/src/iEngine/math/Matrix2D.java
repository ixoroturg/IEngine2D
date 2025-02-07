package iEngine.math;

public class Matrix2D implements Matrix{
	private float[] m1 = new float[4];
	public Matrix2D(float... matrix) {
		if(matrix.length != 4)
			System.exit(-1);
		m1 = matrix;
	}
	@Override
	public Matrix add(Matrix matrix) {
		float[] m2 = matrix.get();
			if(m2.length < 4)
				return this;
			for(int i = 0; i < 4; i++) {
				m1[i] += m2[i];
			}		
		return this;
	}

	@Override
	public Matrix sub(Matrix matrix) {
		float[] m2 = matrix.get();
		if(m2.length < 4) 
			return this;
		for(int i = 0; i < 4; i++) {
			m1[i] -= m2[i];
		}	
		return this;
	}

	@Override
	public Matrix mul(double a) {
		for(int i = 0; i < 4; i++) {
			m1[i] *= a;
		}
		return this;
	}

	@Override
	public Matrix mul(Matrix matrix) {
		float[] m2 = matrix.get();
		if(m2.length < 4)
			return this;
		float[] result = new float[4];
		
		result[0] = m1[0]*m2[0] + m1[1]*m2[2];
		result[1] = m1[0]*m2[1] + m1[1]*m2[3];
		result[2] = m1[2]*m2[0] + m1[3]*m2[2];
		result[3] = m1[2]*m2[1] + m1[3]*m2[3];
		
		m1 = result;
		
		return this;
	}
	
	@Override
	public Matrix div(Matrix matrix) {	
		return mul(matrix.reverse());
	}
	@Override
	public Matrix reverse() {
		float det = det();
		if(det == 0)
			return  this;
		for(int i = 0; i < 4; i++) {
			m1[i] /= det;
		}
		m1[1] *= -1;
		m1[2] *= -1;
		
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
	public float[] get() {
		return m1;
	}
	@Override
	public Matrix2D clone() {
		return new Matrix2D(m1[0],m1[1],m1[2],m1[3]);
	}
}
