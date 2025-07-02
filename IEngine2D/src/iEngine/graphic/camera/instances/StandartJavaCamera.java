package iEngine.graphic.camera.instances;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import iEngine.graphic.Model2D;
import iEngine.graphic.Renderable2D;
import iEngine.graphic.camera.BaseCamera;
import iEngine.graphic.camera.Camera;

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

public class StandartJavaCamera extends BaseCamera<Image> {
	protected Graphics2D frameGr;
	protected Image frame = null;
	public StandartJavaCamera() {
		
	}
	
	@Override
	protected void renderStart() {
//		System.out.println("Разрешение окна: "+frameWidth+" "+frameHeight);
//		float k = (float) this.frameWidth / this.frameHeight > 1 ? (float) this.frameWidth / width
//				: (float) this.frameHeight / height;
//
//		int frameWidth = (int) (width * k);
//		int frameHeight = (int) (height * k);
		
//		frame = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
//		if(frame == null)
//			return;
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
	public Camera<Image> setResolution(int w, int h){
		super.setResolution(w, h);
		float currentRatio = (float)frameWidth / frameHeight;
//		System.out.println(currentRatio + " "+ratio);
		if(currentRatio > ratio) {
			frameHeight = (int)(frameWidth / ratio);
		} else {
			frameWidth = (int)(frameHeight * ratio);
		}
//		System.out.println(frameWidth+" "+frameHeight);
		frame = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
		return this;
	}
	@Override
	public void onCreate() {
//		System.err.println("Создание");
		frame = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
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
