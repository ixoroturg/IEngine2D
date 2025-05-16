package iEngine.element.animation;

import iEngine.util.Pointer;

import java.awt.Image;

public class AnimationImage extends Animation<Image,Image,Image>{

	@Override
	protected Image prepareFunction(Image function, int stepCount) {
		return function;
	}
	@Override
	protected Pointer<Image> copy(Pointer<Image> target) {
		return target.clone();
	}
	@Override
	protected Pointer<Image> applyFunction(Pointer<Image> target, Image function, float at, float bt) {
		target.value = function;
		return target;
	}
	@Override
	protected void paste(Pointer<Image> targetToPaste, Pointer<Image> targetCopied) {
		targetToPaste.value = targetCopied.value;
	}

}
