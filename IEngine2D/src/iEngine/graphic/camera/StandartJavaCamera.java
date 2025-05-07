package iEngine.graphic.camera;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import iEngine.graphic.Model2D;
import iEngine.graphic.RenderContext;
import iEngine.graphic.Renderable2D;
import iEngine.graphic.camera.CameraProperty.Property;
import iEngine.math.Point;
import iEngine.physic.Polygon;

/**
 * Реализация камеры стандартными методами java.awt<br>
 * В качестве буффера используется java.awt.image.BufferedImage<br>
 * Рисуется с помощью (Graphics2D)
 * java.awt.image.BufferedImage.getGraphics()<br>
 * <br>
 * Доступно: Image image - изображение камеры Graphics2D frame - Graphics этого
 * изображения
 *
 * @see iEngine.graphic.camera.AbstractCamera
 */
public class StandartJavaCamera extends BaseCamera {

	protected Image image;
	protected Graphics2D frame;

	@Override
	protected void renderStart() {

//		int frameWidth , frameHeight;
		float k = (float) this.frameWidth / this.frameHeight > 1 ? (float) this.frameWidth / width
				: (float) this.frameHeight / height;

		int frameWidth = (int) (width * k);
		int frameHeight = (int) (height * k);

		image = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
		frame = (Graphics2D) image.getGraphics();

		frame.setColor(new Color(100, 100, 100));
		frame.fillRect(0, 0, frameWidth, frameHeight);

		frame.translate(frameWidth / 2, frameHeight / 2);
		frame.scale(1, -1);
		frame.rotate(angle);
	}
	@Override
	protected Model2D beforeRenderObjectAction(Renderable2D renderObject) {
		return renderObject.getModel(this);
	}
	@Override
	protected void renderObject(Renderable2D renderObject, Model2D info) {

		AffineTransform saveTransform = frame.getTransform();

		float frameWidth = image.getWidth(null);
		float frameHeight = image.getHeight(null);

		Point p = info.getPosition().copy();
		p.sub(position);

		// p.x / (width/2) = p.x / width * 2 => процент от ширины экрана. -1 -
		// левая граница, 1 - правая граница
		// затем нужно прибавить 1, чтобы координата была от 0 до 2
		// и умножить на половину ширины экрана
		// с высотой тоже самое
		// ширину дополнительно умножаем на соотношение сторон * width / height
		float x = (p.x / width * 2);// * frameWidth / 2;
		float y = (p.y / height * 2);// * frameHeight / 2;

		x *= frameWidth / 2;
		y *= frameHeight / 2;

		// теперь у нас есть настоящие x и y координаты спрайта на нашем кадре
		frame.translate(x, y);
		frame.rotate(info.getAngle());

		float[] m = { 1, 0, 0, 1
		};
		if (info.getMatrix() != null)
			m = info.getMatrix().get();
		frame.transform(new AffineTransform(m[0], m[2], m[1], m[3], 0, 0));

		// как и выше вычисляем размеры
		int w = (int) ((info.getWidth() / width) * frameWidth / 2);
		int h = (int) ((info.getHeight() / height) * frameHeight / 2);
		frame.drawImage(info.getSprite(), -w / 2, -h / 2, w / 2, h / 2, 0, 0,
				600,
				600, null);

		// Особые настройки
		if (properties.isHave(CameraProperty.Property.showHitbox) && renderObject instanceof Polygon hitbox) {
			frame.setColor(new Color(properties.get(Property.showHitbox)));
			Point[] ps = hitbox.getForm();
			for (int i = 0; i < ps.length; i++) {
//				ps[i].sub(position);
//				ps[i].x = (ps[i].x/width+1) * this.frameWidth / 2;
//				ps[i].y = (ps[i].y/height+1) * this.frameHeight / 2;
				ps[i].sub(info.getPosition());
				ps[i].rotate(-info.getAngle());
//				System.out.println(ps[i].x);
				int raz = 20;

//				frame.fillOval((int)ps[i].x - raz/2, (int)ps[i].y - raz/2, raz,raz);

			}

			for (int i = 0; i < ps.length; i++) {
				frame.drawLine((int) (ps[i].x), (int) (ps[i].y), (int) (ps[(i + 1) % ps.length].x),
						(int) (ps[(i + 1) % ps.length].y));
			}
//			frame.drawLine((int)(ps[ps.length - 1].x), (int)(ps[ps.length - 1].y), (int)(ps[0].x), (int)(ps[0].y));
		}
		frame.setTransform(saveTransform);

	}
	@Override
	protected Image renderComplete() {
		return image;
	}
	@Override
	public void onCreate() {
	}

}
