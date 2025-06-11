package iEngine.graphic.camera;
import iEngine.graphic.*;
import iEngine.math.Matrix2D;
import iEngine.math.Point;
import java.awt.Image;
import java.util.Iterator;
//import java.awt.
/**
 * @see AbstractCamera
 */
public abstract class BaseCamera extends AbstractCamera {
	protected Image frame;
	@Override
	public Image render() {
		if (world == null)
			return null;
		renderStart();
		Iterator<Renderable2D> iter = getRenderList().iterator();
		
		while(iter.hasNext()) {
			Renderable2D renderObject = iter.next();
			prepareRenderObject(renderObject, beforeRenderObjectAction(renderObject));
		}
		return renderComplete();
	}
	protected abstract void renderStart();

	protected abstract Model2D beforeRenderObjectAction(Renderable2D renderObject);

//	protected abstract void prepareRenderObject(Renderable2D renderObject, Model2D model2d);
//	protected abstract void renderObject(Renderable2D renderObject, Model2D model2d);
	protected abstract void draw(Image sprite, int width,int height,float[] matrix);

	protected abstract Image renderComplete();

	protected void prepareRenderObject(Renderable2D renderObject, Model2D model) {

//		float frameWidth = frame.getWidth(null);
//		float frameHeight = frame.getHeight(null);

		Point p = model.getPosition().copy();
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
		Matrix2D transform = Matrix2D.translate(x, y);
		transform = transform.mul(Matrix2D.rotate(model.getAngle()));
//		System.out.println(model.getAngle());
		
		if (model.getMatrix() != null)
			transform = transform.mul(model.getMatrix());
		
		// как и выше вычисляем размеры
		int w = (int) (((model.getWidth() / width) * frameWidth / 2));
		int h = (int) (((model.getHeight() / height) * frameHeight / 2)) ;
//		w *= frameWidth / frameHeight;
//		h *= frameHeight / frameWidth;
//		 * frameHeight / frameWidth
//		h *= frameH
//		System.out.println(w + " "+h);
		draw(model.getSprite(),w,h,transform.get());
		for(Model2D innerModel: model.getInnerModels()) {
			prepareRenderObject(renderObject, innerModel);
		}
	}
}
