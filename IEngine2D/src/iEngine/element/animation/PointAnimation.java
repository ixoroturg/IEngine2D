package iEngine.element.animation;

import java.util.function.*;

import iEngine.math.Point;

public class PointAnimation extends AbstractAnimation<Point, BiFunction<Point, Float, Point> >{
	@Override
	protected BiFunction<Point, Float, Point> prepareFunction(BiFunction<Point, Float, Point> function, int stepCount) {
		return function;
	}

	@Override
	protected void applyFunction(Point target, BiFunction<Point, Float, Point> function, float t, float lastT) {
		target.add(function.apply(target, lastT).sub(function.apply(target, t)) );
	}
}
