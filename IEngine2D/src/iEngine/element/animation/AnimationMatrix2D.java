package iEngine.element.animation;

import iEngine.math.Matrix2D;

public class AnimationMatrix2D extends Animation<Matrix2D, Matrix2D,Matrix2D> {

	private Matrix2D E = Matrix2D.getE();// new AffineMatrix(1,1,1,1,1,1);

	@Override
	protected Matrix2D prepareFunction(Matrix2D function, int stepCount) {
		return function.sub(E);
	}
	@Override
	protected void applyFunction(Matrix2D target, Matrix2D function, float at, float bt) {
		if (at != 0) {
			target.div(function.copy().mul(at).add(E));
		}
		if (bt != 0)
			target.mul(function.copy().mul(bt).add(E));
	}

}
