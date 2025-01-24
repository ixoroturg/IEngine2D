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
	private Graphics frame;
	@Override
	protected void renderStart() {
		//System.out.println(width+" "+height);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
		frame = image.getGraphics();
		frame.translate((int)position.x, (int)(-position.y));
	}
	@Override
	protected RenderInfo beforeRenderObjectAction(Renderable renderObject) {
		return renderObject.getRenderInfo();
	}
	@Override
	protected void renderObject(Renderable renderObject, RenderInfo info) {
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
