package iEngine.element.animation;

import iEngine.math.Matrix;
public class AddMatrixAnimation extends Animation<Matrix, Matrix>{
	@Override
	protected Matrix prepareFunction(Matrix matrix, int count) {
		return matrix.clone().mul(1.0 / count);
	}
	@Override
	protected void applyFunction(Matrix matrix, Matrix applyMatrix, float t, float dt) {
		matrix.add(applyMatrix);
	}
}
