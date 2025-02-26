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
		
		frame.translate(frameWidth/2, frameHeight/2);
		frame.scale(1, -1);
		frame.rotate(angle);
		
//		frame.setColor(Color.RED);
//		frame.fillRect(0, 0, 100, 100);
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
		//TODO: камера не зависящая от разрешения
	}
	@Override
	protected void renderObject(Renderable renderObject, RenderInfo info){

		AffineTransform saveTransform = frame.getTransform();
		
		
		Point p = info.position().clone();

		p.sub(position);
//		System.out.println("начальные координаты: "+p.x+" "+p.y);
		// p.x / (width/2) = p.x / width * 2 => процент от ширины экрана. -1 - левая граница, 1 - правая граница
		// затем нужно прибавить 1, чтобы координата была от 0 до 2
		// и умножить на половину ширины экрана
		// с высотой тоже самое
		// ширину дополнительно умножаем на соотношение сторон * width / height
		
//		System.out.println(p);
		
		float x = (p.x / width * 2 ) ;//* frameWidth / 2;
		float y = (p.y / height * 2 );// * frameHeight / 2;
		
		
		x *= frameWidth / 2;
		y *= frameHeight / 2;
//		float x = (p.x / width * 2);
//		float y = (p.y / height * 2);
		
//		System.out.println(x+" "+y);
		// теперь у нас есть настоящие x и y координаты спрайта на нашем кадре
		
		
		
		frame.translate(x, y);
		
		// Math.PI нужно, т.к. из-за scale (1, -1) поворот отображается в зеркально
		
		
		float[] m = {1,0,0,1};
		if(info.matrix() != null)
			m = info.matrix().get();
		frame.transform(new AffineTransform(m[0],-m[1],-m[2],m[3],0,0));
		
		// как и выше вычисляем координаты
		
//		int w = (int) (width*info.xSize());
//		int h = (int) (height*info.ySize());
//		System.out.println(frame.getTransform());
//		frame.scale(0.1,0.1);
//		frame.setColor(Color.RED);
//		frame.fillRect(0,0,200,200);
		
//		int w = 100, h = 100;
		
		float w1 = (info.xSize() / width);
		float h1 =  (info.ySize() / height);
//		float angle = (float) (info.angle());
//		frame.rotate(info.angle());
//		frame.scale(Math.cos(info.angle())*w, Math.sin(info.angle())*h );
//		frame.scale(w1, h1);
		
//		System.out.println(currentSideRatio+" "+sideRatio);
		float angle = info.angle();
		float k = currentSideRatio / sideRatio;
//		double[] M = {Math.cos(angle)*k, Math.sin(angle)/k, -Math.sin(angle)*k, Math.cos(angle)/k};
		double[] M = {Math.cos(angle)*k, Math.sin(angle)/k, -Math.sin(angle)*k, Math.cos(angle)/k};
		AffineTransform tr = new AffineTransform(M[0],M[1],M[2],M[3],0,0);
		frame.transform(tr);
		w1 *= frameWidth;
		h1 *= frameHeight;
//		frame.drawIm
//		w1 *= 1+(Math.abs(currentSideRatio / sideRatio)-1) * Math.abs(Math.sin(info.angle()));
//		frame.rotate(0);
//		w1 = (float) ( (Math.abs(currentSideRatio / sideRatio) - 1 ) * Math.abs(Math.sin(info.angle())));
//		h1 *= 1+(Math.abs(sideRatio / currentSideRatio)-1) * Math.abs(Math.cos(info.angle()));
		
//		int w = (int)(w1 * frameWidth * Math.abs(currentSideRatio / sideRatio));
//		int h = (int)(h1 * frameHeight * Math.abs(sideRatio / currentSideRatio));
		
		int w = (int)(w1);
		int h = (int)(h1);
		
//		frame.scale(Math.abs(1.0 / Math.cos(info.angle())), 1);
		
//		frame.scale(w1 * Math.cos(info.angle()), h1 * Math.sin(info.angle()));
//		System.out.println(Math.toDegrees(info.angle()));
//		w *= 
//		frame.scale(w1, h1);
		
//		frame.scale(Math.abs(sideRatio / currentSideRatio),1);
		
		frame.drawImage(info.sprite(), -w/2, -h/2, w/2, h/2, 0, 0, info.sprite().getWidth(null), info.sprite().getHeight(null), null);
//		frame.drawImage(info.sprite(),tr,null);
		
//		frame.setColor(Color.RED);
//		frame.fillRect(-frameWidth/2, -frameHeight/2, frameWidth/2, frameHeight/2);
//		frame.drawImage(info.sprite(), -frameWidth/2, -frameHeight/2, frameWidth/2, frameHeight/2, 0, 0, info.sprite().getWidth(null), info.sprite().getHeight(null), null);
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
	public void onCreate() {}
}
