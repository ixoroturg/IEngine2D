package iEngine.input;

import iEngine.input.interfaces.Controller;
import iEngine.input.interfaces.Mouse;
import iEngine.math.Point;
import iEngine.math.Vector;
import iEngine.render.*;

public abstract class AbstractMouse implements Mouse{
	protected Controller controller;
	protected Camera camera;
	protected Point position = new Point(0,0);
	@Override
	public Mouse setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}
	@Override
	public Camera getCamera() {
		return camera;
	}
	@Override
	public Mouse setController(Controller controller) {
		this.controller = controller;
		controller.setMouse(this);
		return this;
	}
	@Override
	public Controller getController() {
		return controller;
	}
}
