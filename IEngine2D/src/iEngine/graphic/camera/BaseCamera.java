package iEngine.graphic.camera;
import iEngine.graphic.*;
import java.awt.Image;
import java.util.Iterator;

/**
 * @see AbstractCamera
 */
public abstract class BaseCamera extends AbstractCamera {

//	protected float currentSideRatio;
	@Override
	public Image render() {
//		currentSideRatio = (float)frameWidth / frameHeight;
		if (world == null)
			return null;
		renderStart();
		Iterator<Renderable2D> iter = getRenderList().iterator();
		
		while(iter.hasNext()) {
			Renderable2D renderObject = iter.next();
			renderObject(renderObject, beforeRenderObjectAction(renderObject));
		}
//		for(getRenderList().iterator()) {
//			renderObject(renderObject, beforeRenderObjectAction(renderObject));
//		}
//		getRenderList().forEach(renderObject -> {
//			renderObject(renderObject, beforeRenderObjectAction(renderObject));
//		});

		return renderComplete();
	}
	protected abstract void renderStart();

	protected abstract Model2D beforeRenderObjectAction(Renderable2D renderObject);

	protected abstract void renderObject(Renderable2D renderObject, Model2D model2d);

	protected abstract Image renderComplete();

}
