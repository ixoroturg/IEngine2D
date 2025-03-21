package iEngine.element.animation;

import iEngine.math.AffineMatrix;
import iEngine.math.Matrix;
import iEngine.math.Matrix2D;

public class TransformMatrixAnimation extends Animation<Matrix,Matrix>{
	private Matrix E = Matrix.getE();//new AffineMatrix(1,1,1,1,1,1);
	@Override
	protected Matrix prepareFunction(Matrix function, int stepCount) {
		return function.sub(E);
	}
	@Override
	protected void applyFunction(Matrix target, Matrix function, float at, float bt) {
			if(at != 0) {
				target.div(function.clone().mul(at).add(E));
			}
			if(bt != 0)
				target.mul(function.clone().mul(bt).add(E));
	}
}
