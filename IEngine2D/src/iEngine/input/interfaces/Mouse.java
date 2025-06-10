package iEngine.input.interfaces;

import iEngine.graphic.camera.Camera;

public interface Mouse {

	public final static byte LMB = -1, RMB = -2, MOUSE_WHEEL = -3,
			MOUSE4 = -4, MOUSE5 = -5, WHEEL_UP = -6, WHEEL_DOWN = -7,
			MOVE = -8, DRAG = -9;

	public Mouse setCamera(Camera camera);

	public Camera getCamera();

	public Mouse setController(Controller controller);

	public Controller getController();

}
