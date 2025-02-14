package iEngine.output.camera.instance;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;

import iEngine.element.*;
import iEngine.element.interfaces.Hitbox;
import iEngine.math.Point;
import iEngine.output.camera.*;
import iEngine.output.camera.CameraProperty.Property;
/**
 * Реализация камеры стандартными методами java.awt<br>
 * В качестве буффера используется java.awt.image.BufferedImage<br>
 * Рисуется с помощью (Graphics2D) java.awt.image.BufferedImage.getGraphics()
 */
public class StandartJavaCamera extends BaseCamera{
	private Image image;
	private Graphics2D frame;
	@Override
	protected void renderStart() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
		frame = (Graphics2D)image.getGraphics();
		frame.setColor(Color.GRAY);
		frame.fillRect(0, 0, width, height);
		frame.translate(position.x, -position.y);
		frame.scale(xDPI, yDPI);
	}
	@Override
	protected RenderInfo beforeRenderObjectAction(Renderable renderObject) {
		return renderObject.getRenderInfo(this);
	}
	@Override
	protected void renderObject(Renderable renderObject, RenderInfo info){
		
		//System.out.println("Окно: "+width+" "+height);
		//System.out.println(xDPI+" "+yDPI);
		AffineTransform saveTransform = frame.getTransform();
		
		frame.translate(info.position().x * xDPI, height - info.position().y * yDPI);
		//frame.scale(info.scale(), info.scale());
		frame.rotate(-info.angle());
		float[] m = {1,0,0,1};
		if(info.matrix() != null)
			m = info.matrix().get();
		frame.transform(new AffineTransform(m[0],-m[1],-m[2],m[3],0,0));
		// * (1.0 * height / width) 
		int w = (int) (width*info.xSize()* xDPI) ;
		int h = (int) (height*info.ySize() * yDPI) ;
		//System.out.println("Frame: "+w+" "+h);
		frame.drawImage(info.sprite(), -w/2, -h/2, w/2, h/2, 0, 0, info.sprite().getWidth(null), info.sprite().getHeight(null), null);
		
		if(properties.isHave(CameraProperty.Property.showHitbox) && renderObject instanceof Hitbox hitbox) {
			frame.setColor(new Color(properties.get(Property.showHitbox)));	
			Point[] ps = hitbox.getVertex();
			for(int i = 0; i < ps.length - 1; i++) {
				frame.drawLine((int)(ps[i].x), (int)(height - ps[i].y), (int)(ps[i+1].x), (int)(height - ps[i+1].y));
			}
			frame.drawLine((int)(ps[ps.length - 1].x), (int)(height - ps[ps.length - 1].y), (int)(ps[0].x), (int)(height - ps[0].y));
		}
		
		//frame.setS
		frame.setTransform(saveTransform);
	}
	@Override
	protected Image renderComplete() {
		return image;
	}

}
