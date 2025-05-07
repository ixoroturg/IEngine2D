package iEngine.element.animation;

import iEngine.element.MoveProperties;
import iEngine.element.interfaces.Moveable;

public class AnimationMoveable extends Animation<Moveable, MoveProperties, MoveProperties>{

	@Override
	protected MoveProperties prepareFunction(MoveProperties function, int stepCount) {
		return function;
	}

	@Override
	protected Moveable applyFunction(Moveable target, MoveProperties function, float at, float bt) {
		float l = function.v().getLength();
		float a = function.a();
		l = l * bt - l * at;
		a = a * bt - a * at;
		target.move(function.v().getUnitVector().mul(l));
		target.rotate(a);
		return target;
	}
}
