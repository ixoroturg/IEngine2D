import EngineMath.*;

import java.util.Arrays;
import java.util.List;

import EngineElement.*;
import EngineElement.interfaces.World;
import EngineElement.interfaces.baseInstance.BaseWorld;
import EngineElement.interfaces.baseInstance.BaseWorldController;
import EngineInput.Mouse;
import EngineOutput.Window;
public class IEngine2D {
	public static Mouse mouse = new Mouse();
	public static void main(String[] args) {	
		World world = new MyWorldBuilder().build();
				//.setTickrate(100);		
		Window window = new Window(world);
		window.addMouseListener(mouse);
		window.addMouseMotionListener(mouse);
		window.addMouseWheelListener(mouse);
		mouse.setCamera(window.frame.camera);
		world.setController(new BaseWorldController(world).setMouse(mouse));
		world.setTickrate(100);
		//world.buildWorld(new MyWorldBuilder()).setTickrate(100);
	}
}
