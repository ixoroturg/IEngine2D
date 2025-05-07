package iEngine.element.animation;

import iEngine.math.MatrixOld;

public class AddMatrixAnimation extends Animation<MatrixOld, MatrixOld,MatrixOld> {

	@Override
	protected MatrixOld prepareFunction(MatrixOld matrix, int count) {
//		return matrix.clone().mul(1.0 / count);
		return matrix;
	}
	@Override
	protected void applyFunction(MatrixOld matrix, MatrixOld applyMatrix, float t, float dt) {
		matrix.add(applyMatrix.clone().mul(1.0f / dt).sub(applyMatrix.clone().mul(1.0f / t)));
	}
}
