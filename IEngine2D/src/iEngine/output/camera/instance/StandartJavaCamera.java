package iEngine.output.camera.instance;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import iEngine.element.*;
import iEngine.element.interfaces.Hitbox;
import iEngine.math.Point;
import iEngine.output.Device;
import iEngine.output.camera.*;
import iEngine.output.camera.CameraProperty.Property;
/**
 * Реализация камеры стандартными методами java.awt<br>
 * В качестве буффера используется java.awt.image.BufferedImage<br>
 * Рисуется с помощью (Graphics2D) java.awt.image.BufferedImage.getGraphics()<br>
 * <br>
 * Доступно:
 * Image image - изображение камеры
 * Graphics2D frame - Graphics этого изображения
 *
 * @see iEngine.output.camera.AbstractCamera
 */
public class StandartJavaCamera extends BaseCamera{
	protected Image image;
	protected Graphics2D frame;
	@Override
	protected void renderStart() {
		
		image = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
		frame = (Graphics2D)image.getGraphics();
		
		frame.setColor(new Color(100,100,100));	
		frame.fillRect(0, 0, frameWidth, frameHeight);
		
		frame.translate(0, frameHeight);
		frame.scale(0, -1);
//		System.out.println("start");
//		System.out.println("кадр: "+frameWidth+" "+frameHeight);
//		System.out.println("размер камеры: "+width+" "+height);
//		System.out.println("позиция камеры: "+position.x +" "+position.y);
//		frame.translate(position.x, position.y + frameHeight);
//		int[] res = Device.getDisplayResolution();
//		frame.scale((float)frameWidth / res[0], -(float)frameHeight / res[1]);
	}
	@Override
	protected RenderInfo beforeRenderObjectAction(Renderable renderObject) {
		return renderObject.getRenderInfo(this);
	}
	@Override
	protected void renderObject(Renderable renderObject, RenderInfo info){

		AffineTransform saveTransform = frame.getTransform();
		
		
		Point p = info.position().clone();
//		System.out.println("начальные координаты: "+p.x+" "+p.y);
		p.sub(position);
		
		// p.x / (width/2) = p.x / width * 2 => процент от ширины экрана. -1 - левая граница, 1 - правая граница
		// затем нужно прибавить 1, чтобы координата была от 0 до 2
		// и умножить на половину ширины экрана
		// с высотой тоже самое
		// ширину дополнительно умножаем на соотношение сторон * width / height
		
//		System.out.println(p);
		
		float x = (p.x / width * 2 + 1) * frameWidth / 2 * width / height;
		float y = (p.y / height * 2 + 1) * frameHeight / 2;
		
//		float x = (p.x / width * 2);
//		float y = (p.y / height * 2);
		
		
		// теперь у нас есть настоящие x и y координаты спрайта на нашем кадре
		
		System.out.println(x+" "+y);
		
		frame.translate(x, y);
		
		// Math.PI нужно, т.к. из-за scale (0, -1) поворот отображается в зеркально
		frame.rotate(info.angle() + Math.PI);
		
		float[] m = {1,0,0,1};
		if(info.matrix() != null)
			m = info.matrix().get();
		frame.transform(new AffineTransform(m[0],-m[1],-m[2],m[3],0,0));
		
		// как и выше вычисляем координаты
		
//		int w = (int) (width*info.xSize());
//		int h = (int) (height*info.ySize());
		int w = 100, h = 100;
		frame.drawImage(info.sprite(), -w/2, -h/2, w/2, h/2, 0, 0, info.sprite().getWidth(null), info.sprite().getHeight(null), null);
		
		if(properties.isHave(CameraProperty.Property.showHitbox) && renderObject instanceof Hitbox hitbox) {
			frame.setColor(new Color(properties.get(Property.showHitbox)));	
			Point[] ps = hitbox.getVertex();
			for(int i = 0; i < ps.length - 1; i++) {
				frame.drawLine((int)(ps[i].x), (int)(frameHeight - ps[i].y), (int)(ps[i+1].x), (int)(frameHeight - ps[i+1].y));
			}
			frame.drawLine((int)(ps[ps.length - 1].x), (int)(frameHeight - ps[ps.length - 1].y), (int)(ps[0].x), (int)(frameHeight - ps[0].y));
		}
		
		//frame.setS
		frame.setTransform(saveTransform);
	}
	@Override
	protected Image renderComplete() {
		return image;
	}
	@Override
	protected void onCreate() {}

}
