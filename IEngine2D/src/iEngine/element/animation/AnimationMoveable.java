package iEngine.element.animation;

import iEngine.element.MoveProperties;
import iEngine.element.interfaces.Moveable;
import iEngine.util.Pointer;

public class AnimationMoveable extends Animation<Moveable, MoveProperties, MoveProperties>{

	@Override
	protected MoveProperties prepareFunction(MoveProperties function, int stepCount) {
		return function;
	}

	@Override
	protected Pointer<Moveable> applyFunction(Pointer<Moveable> target, MoveProperties function, float at, float bt) {
		float l = function.v().getLength();
		float a = function.a();
		l = l * bt - l * at;
		a = a * bt - a * at;
		target.value.move(function.v().getUnitVector().mul(l));
		target.value.rotate(a);
		return target;
	}

	@Override
	protected Pointer<Moveable> copy(Pointer<Moveable> target) {
		return target;
	}

	@Override
	protected void paste(Pointer<Moveable> targetToPaste, Pointer<Moveable> targetCopied) {
		
	}
}
