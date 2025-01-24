package EngineOutput.camera.instance;
import java.awt.*;
import java.awt.image.BufferedImage;
import EngineElement.*;
import EngineElement.interfaces.Hitbox;
import EngineMath.Point;
import EngineOutput.camera.*;
import EngineOutput.camera.CameraProperty.Property;
/**
 * Реализация камеры стандартными методами java.awt<br>
 * В качестве буффера используется java.awt.image.BufferedImage<br>
 * Рисуется с помощью java.awt.image.BufferedImage.getGraphics()
 */
public class StandartJavaCamera extends BaseCamera{
	private Image image;
	private Graphics2D frame;
	@Override
	protected void renderStart() {
		//System.out.println(width+" "+height);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
		frame = (Graphics2D)image.getGraphics();
		frame.translate((int)position.x, (int)(-position.y));
	}
	@Override
	protected RenderInfo beforeRenderObjectAction(Renderable renderObject) {
		return renderObject.getRenderInfo();
	}
	@Override
	protected void renderObject(Renderable renderObject, RenderInfo info) {
		//frame.rotate(scale, height, angle);
		//frame.scale(info.scale(), info.scale());
		//frame.rotate(-info.angle(), info.position().x - info.sprite().getWidth(null)/2, info.position().y + info.sprite().getHeight(null)/2);
		frame.translate(info.position().x, height - info.position().y);
		frame.scale(info.scale(), info.scale());
		
		frame.rotate(-info.angle());
		frame.drawImage(info.sprite(), -info.sprite().getWidth(null)/2, -info.sprite().getHeight(null)/2, null);
		frame.rotate(info.angle());
		
		frame.scale(1, 1);
		frame.translate(-info.position().x, -height + info.position().y);
		//frame.rotate(info.angle(), info.position().x - info.sprite().getWidth(null)/2, info.position().y + info.sprite().getHeight(null)/2);
		//frame.scale(1.0/info.scale(), 1.0/info.scale());
		
		if(properties.isHave(CameraProperty.Property.showHitbox) && renderObject instanceof Hitbox hitbox) {
			frame.setColor(new Color(properties.get(Property.showHitbox)));	
			Point[] ps = hitbox.getVertex();
			for(int i = 0; i < ps.length - 1; i++) {
				frame.drawLine((int)(ps[i].x), (int)(height - ps[i].y), (int)(ps[i+1].x), (int)(height - ps[i+1].y));
			}
			frame.drawLine((int)(ps[ps.length - 1].x), (int)(height - ps[ps.length - 1].y), (int)(ps[0].x), (int)(height - ps[0].y));
		}
	}
	@Override
	protected Image renderComplete() {
		return image;
	}

}
