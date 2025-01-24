package EngineOutput.camera;

import java.awt.Image;

public abstract class BaseCamera extends AbstractCamera{
	@Override
	public Image render() {
		renderStart();
			world.getStorage().getRenderList().forEach(renderObject -> {
				renderObject(renderObject ,beforeRenderObjectAction(renderObject));
			});

		return renderComplete();
	}
	protected abstract void renderStart();
	protected abstract RenderInfo beforeRenderObjectAction(Renderable renderObject);
	protected abstract void renderObject(Renderable renderObject, RenderInfo info);
	protected abstract Image renderComplete();
}
