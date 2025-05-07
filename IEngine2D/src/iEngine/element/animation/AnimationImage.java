package iEngine.element.animation;

import java.awt.Image;

public class AnimationImage extends Animation<Image,Image,Image>{

	@Override
	protected Image prepareFunction(Image function, int stepCount) {
		return null;
	}

	@Override
	protected void applyFunction(Image target, Image function, float at, float bt) {
	}

}
