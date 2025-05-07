package iEngine.element.animation;

import java.util.function.Function;

import iEngine.math.Point;

public class PointAnimation extends Animation<Point, Function<Float, Point>,Function<Float, Point>> {

	@Override
	protected Function<Float, Point> prepareFunction(Function<Float, Point> function, int stepCount) {
		return function;
	}
	@Override
	protected Point applyFunction(Point target, Function<Float, Point> function, float at, float bt) {
		return target.add(function.apply(bt).sub(function.apply(at)));
	}

}
