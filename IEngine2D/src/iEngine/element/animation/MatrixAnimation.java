package iEngine.element.animation;

import iEngine.element.interfaces.Tickable;
import iEngine.math.Matrix;
import iEngine.math.Matrix2D;

public class MatrixAnimation extends AbstractAnimation implements Animation, Tickable{
	protected Matrix[] target, apply, step;
	protected Matrix currentApply;
	protected int repeat = 1;
	protected int alreadyRepeated = 0;
	public Animation setTarget(Matrix... target) {
		this.target = target;
		return this;
	}
	public Animation setApplyMatrix(Matrix... apply) {
		this.apply = apply;
		lastStep = apply.length - 1;
		step = new Matrix2D[apply.length];
		stepCount = new int[apply.length];
		return this;
	}
	@Override
	public void onTickChange(int tickrate) {
		super.onTickChange(tickrate);
		for(int i = 0; i < apply.length; i++) {
			step[i] = apply[i].clone().mul(1.0 / stepCount[i]);
		}
	}
	@Override
	public void onTick() {}
	@Override
	public boolean step() {
		currentStepCount--;
		if(currentStepCount <= 0) {
			if(currentStep == lastStep) {
				alreadyRepeated++;
				if(alreadyRepeated < repeat || repeat == 0)
					reset();
				else return false;
			}
			else currentStep++;
			currentStepCount = stepCount[currentStep];
		}
		for(Matrix curr: target) {
			curr.add(step[currentStep]);
		}
		return true;
	}
	@Override
	public Animation reset() {
		currentStep = 0;
		return this;
	}
	@Override
	public Animation repeat(int repeatCount) {
		repeat = repeatCount;
		return this;
	}
}
