package iEngine.element.animation;

import iEngine.math.Matrix;
public class MatrixAnimation extends AbstractAnimation<Matrix, Matrix>{
//	protected Matrix[] target, saveTarget, apply, step;
	protected Matrix currentApply;
	
	public MatrixAnimation(){
		prepare = (matrix, count) -> {
			return matrix.clone().mul(1.0 / count);
		};
		apply = (matrix, applyMatrix, count) -> {
			matrix.add(applyMatrix);
		};
	}
	
//	@Override
//	public MatrixAnimation setTarget(Matrix... target) {
//		this.target = target;
//		saveTarget = new Matrix[target.length];
//		for(int i = 0; i < saveTarget.length; i++) {
//			saveTarget[i] = target[i].clone();
//		}
//		return this;
//	}
//	@Override
//	public MatrixAnimation setFunction(Matrix... matrix) {
//		apply = matrix;
//		lastStep = apply.length - 1;
//		step = new Matrix[apply.length];
//		stepCount = new int[apply.length];
//		return this;
//	}
//	@Override
//	public void tickChanged() {
//		for(int i = 0; i < apply.length; i++) {
//			step[i] = apply[i].clone().mul(1.0 / stepCount[i]);
//		}
//	}
	
//	@Override
//	public boolean step() {
//		if(super.step())
//			for(Matrix curr: target) {
//				curr.add(step[currentStep]);
//			}
//		return true;
//	}
//	@Override
//	public MatrixAnimation resetToInitialState() {
//		for(int i = 0; i < target.length; i++) {
//			target[i].set(saveTarget[i]);
//		}
//		return this;
//	}
}
