package iEngine.element.animation;
import java.awt.Graphics;
import java.awt.image.*;
import java.awt.Image;

public class AnimationImage extends Animation<Image,Image,Image>{

	@Override
	protected Image prepareFunction(Image function, int stepCount) {
		return function;
	}
	@Override
	protected Image copy(Image tc) {
		Image im = new BufferedImage(tc.getWidth(null),tc.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
		Graphics gr = im.getGraphics();
		gr.drawImage(tc,0,0,null);
		return im;
	}

	@Override
	protected Image applyFunction(Image target, Image function, float at, float bt) {
		return function;
	}

}
