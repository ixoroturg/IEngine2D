package iEngine.element.animation;

import iEngine.math.Matrix;
import iEngine.math.Matrix2D;

public class TransformMatrixAnimation extends AbstractAnimation<Matrix,Matrix>{
	@Override
	protected Matrix prepareFunction(Matrix function, int stepCount) {
		return function.sub(Matrix.getE());
	}
	
	@Override
	protected void applyFunction(Matrix target, Matrix function, float at, float bt) {
			if(at != 0) {
				target.div(function.clone().mul(at).add(Matrix.getE()));
			}
			if(bt != 0)
				target.mul(function.clone().mul(bt).add(Matrix.getE()));
	}
}
