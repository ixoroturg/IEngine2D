package iEngine.element.animation;

import java.util.function.Function;

import iEngine.math.Point;
import iEngine.util.Pointer;

public class PointAnimation extends Animation<Point, Function<Float, Point>,Function<Float, Point>> {

	@Override
	protected Function<Float, Point> prepareFunction(Function<Float, Point> function, int stepCount) {
		return function;
	}
	@Override 
	protected Pointer<Point> copy(Pointer<Point> target){
		return new Pointer<Point>(target.value.copy());
	}
	@Override
	protected Pointer<Point> applyFunction(Pointer<Point> target, Function<Float, Point> function, float at, float bt) {
		target.value.add(function.apply(bt).sub(function.apply(at)));
		return target;
	}
	@Override
	protected void paste(Pointer<Point> targetToPaste, Pointer<Point> targetCopied) {
		targetToPaste.value.paste(targetCopied.value);
	}

}
