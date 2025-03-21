package iEngine.render;

import java.awt.Image;
/**
 * @see AbstractCamera
 */
public abstract class BaseCamera extends AbstractCamera{
//	protected float currentSideRatio;
	@Override
	public Image render() {
//		currentSideRatio = (float)frameWidth / frameHeight;
		if(world == null)
			return null;
		renderStart();
			getRenderList().forEach(renderObject -> {
				renderObject(renderObject ,beforeRenderObjectAction(renderObject));
			});

		return renderComplete();
	}
	protected abstract void renderStart();
	protected abstract RenderInfo beforeRenderObjectAction(Renderable renderObject);
	protected abstract void renderObject(Renderable renderObject, RenderInfo info);
	protected abstract Image renderComplete();
}
