package iEngine.element.animation;
import iEngine.math.Matrix2D;
import iEngine.util.Pointer;

public class AnimationMatrix2D extends Animation<Matrix2D, Matrix2D,Matrix2D> {

	private Matrix2D E = Matrix2D.getE();// new AffineMatrix(1,1,1,1,1,1);

	@Override
	protected Matrix2D prepareFunction(Matrix2D function, int stepCount) {
		return function.sub(E);
	}
	@Override
	protected Pointer<Matrix2D> copy(Pointer<Matrix2D> target) {
		return new Pointer<Matrix2D>(target.value.copy());
	}
	@Override
	protected Pointer<Matrix2D> applyFunction(Pointer<Matrix2D> target, Matrix2D function, float at, float bt) {
		if (at != 0) {
			target.value = target.value.div(function.copy().mul(at).add(E));
		}
		if (bt != 0)
			target.value = target.value.mul(function.copy().mul(bt).add(E));
		return target;
	}
//	@Override
//	protected Pointer<Matrix2D> applyFunction(Pointer<Matrix2D> target2, Matrix2D function, float at, float bt) {
//		return null;
//	}
	@Override
	protected void paste(Pointer<Matrix2D> targetToPaste, Pointer<Matrix2D> targetCopied) {
		targetToPaste.value.paste(targetCopied.value);
	}

}
