package iEngine.input.interfaces;

import iEngine.math.Point;
import iEngine.math.Vector;
import iEngine.render.*;

public interface Mouse {
	public final static byte 
			MOUSE1 = 1,
			MOUSE2 = 2,
			MOUSE3 = 3,
			MOUSE4 = 4,
			MOUSE5 = 5,
			WHEEL_UP = 6,
			WHEEL_DOWN = 7,
			MOVE = 8,
			DRAG = 9
			;
	
	public Point getPosition();
	public Point getDragStart();
	public Point getDragStop();
	
	public boolean isDrag();

	public Vector getMovement();	
	public Mouse setCamera(Camera camera);
	public Camera getCamera();
	
	public Mouse setController(Controller controller);
	public Controller getController();
}
