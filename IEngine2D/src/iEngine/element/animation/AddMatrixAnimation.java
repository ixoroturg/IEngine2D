package iEngine.element.animation;

import iEngine.math.Matrix2D;

public class AddMatrixAnimation extends Animation<Matrix2D, Matrix2D,Matrix2D> {

	@Override
	protected Matrix2D prepareFunction(Matrix2D matrix, int count) {
//		return matrix.clone().mul(1.0 / count);
		return matrix;
	}
	@Override
	protected Matrix2D applyFunction(Matrix2D matrix, Matrix2D applyMatrix, float t, float dt) {
		return matrix.add(applyMatrix.copy().mul(1.0f / dt).sub(applyMatrix.copy().mul(1.0f / t)));
	}
}
