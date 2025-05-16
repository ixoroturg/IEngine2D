package iEngine.element.animation;

import iEngine.math.Matrix2D;
import iEngine.util.Pointer;

public class AddMatrixAnimation extends Animation<Matrix2D, Matrix2D,Matrix2D> {

	@Override
	protected Matrix2D prepareFunction(Matrix2D matrix, int count) {
//		return matrix.clone().mul(1.0 / count);
		return matrix;
	}
	@Override
	protected Pointer<Matrix2D> applyFunction(Pointer<Matrix2D> matrix, Matrix2D applyMatrix, float t, float dt) {
		matrix.value = matrix.value.add(applyMatrix.copy().mul(1.0f / dt).sub(applyMatrix.copy().mul(1.0f / t)));
		return matrix;
	}
	@Override
	protected Pointer<Matrix2D> copy(Pointer<Matrix2D> target) {
		return new Pointer<Matrix2D>(target.value.copy());
	}
	@Override
	protected void paste(Pointer<Matrix2D> targetToPaste, Pointer<Matrix2D> targetCopied) {
		targetToPaste.value.paste(targetCopied.value);
	}
}
