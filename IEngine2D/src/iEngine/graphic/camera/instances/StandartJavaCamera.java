package iEngine.graphic.camera.instances;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import iEngine.graphic.Model2D;
import iEngine.graphic.Renderable2D;
import iEngine.graphic.camera.BaseCamera;

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
	protected Graphics2D frameGr;

	@Override
	protected void renderStart() {

		float k = (float) this.frameWidth / this.frameHeight > 1 ? (float) this.frameWidth / width
				: (float) this.frameHeight / height;

		int frameWidth = (int) (width * k);
		int frameHeight = (int) (height * k);

		frame = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
		frameGr = (Graphics2D) frame.getGraphics();

		frameGr.setColor(new Color(100, 100, 100));
		frameGr.fillRect(0, 0, frameWidth, frameHeight);

		frameGr.translate(frameWidth / 2, frameHeight / 2);
		frameGr.scale(1, -1);
		frameGr.rotate(angle);
	}
	@Override
	protected Model2D beforeRenderObjectAction(Renderable2D renderObject) {
		return renderObject.getModel();
	}
	
	@Override
	protected Image renderComplete() {
		return frame;
	}
	@Override
	public void onCreate() {
	}
	@Override
	protected void draw(Image sprite, int width, int height, float[] matrix) {
		var save = frameGr.getTransform();
		frameGr.transform(new AffineTransform(matrix[0],matrix[1],matrix[2],matrix[3],matrix[4],matrix[5]));
		frameGr.drawImage(sprite,-width / 2, -height / 2 , width/2, height / 2, 0, 0, sprite.getWidth(null),sprite.getHeight(null),null);
//		System.out.println(frameGr.getTransform());
		frameGr.setTransform(save);
	}

}
