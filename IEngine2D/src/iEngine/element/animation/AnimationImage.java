package iEngine.element.animation;
import java.awt.*;
import java.awt.image.*;
import java.awt.Image;

public class AnimationImage extends Animation<Image,Image,Image>{

	@Override
	protected Image prepareFunction(Image function, int stepCount) {
		return function;
	}
	@Override
	protected Image[] copy(Image[] tc) {
//		Image im = new BufferedImage(tc[0].getWidth(null),tc[0].getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
//		Graphics gr = im.getGraphics();
//		gr.drawImage(tc[0],0,0,null);
//		gr.dispose();
		Image[] arr = new Image[1];
		arr[0] = tc[0];
//		return arr;
//		return im;
		return arr;
	}
//	@Override
//	protected Image copy(Image tc) {
//		Image im = new BufferedImage(tc.getWidth(null),tc.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
//		Graphics gr = im.getGraphics();
//		gr.drawImage(tc,0,0,null);
//		gr.dispose();
//		return im;
//	}
	@Override
	protected void paste(Image[] tc, Image[] tp) {
		tc[0] = tp[0];
	}
//	@Override
//	protected void paste(Image tc, Image tp) {
//		Graphics2D gr = (Graphics2D)tc.getGraphics();
//		gr.setBackground(new Color(0,0,0,0));
//		gr.clearRect(0, 0, tc.getWidth(null), tc.getHeight(null));
//		gr.drawImage(tp,0,0,null);
//		gr.dispose();
//	}
	@Override
	protected Image applyFunction(Image target, Image function, float at, float bt) {
		return function;
	}

}
