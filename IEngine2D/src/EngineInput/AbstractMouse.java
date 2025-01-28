package EngineInput;

import EngineInput.interfaces.Controller;
import EngineInput.interfaces.Mouse;
import EngineMath.Point;
import EngineMath.Vector;
import EngineOutput.camera.Camera;

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
