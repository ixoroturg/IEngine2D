import EngineMath.*;


import java.util.*;
import EngineElement.*;
import EngineElement.interfaces.*;
import EngineElement.interfaces.baseInstance.*;
import EngineInput.*;
import EngineInput.interfaces.*;
import EngineOutput.camera.*;
import EngineOutput.camera.instance.*;
import EngineOutput.Window;
public class IEngine2D {
	public static StandartJavaMouse mouse = new StandartJavaMouse();
	public static void main(String[] args) {	
		World world = new MyWorldBuilder().build();
		
		Mouse mouse = new StandartJavaMouse();
		Keyboard keyboard = new StandartJavaKeyboard();
		
		Camera camera = new StandartJavaCamera().setWorld(world);
		
		mouse.setController(world.getController()).setCamera(camera);
		keyboard.setController(world.getController());
		
		Window window = new Window().setCamera(camera);
		window.addMouseListener((StandartJavaMouse)mouse);
		window.addMouseMotionListener((StandartJavaMouse)mouse);
		window.addMouseWheelListener((StandartJavaMouse)mouse);
		window.addKeyListener((StandartJavaKeyboard)keyboard);
		
		world.setTickrate(100);
		
		window.setFPS(1000);
		
	}
}
