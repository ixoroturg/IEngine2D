
import iEngine.element.interfaces.*;
import iEngine.input.*;
import iEngine.input.interfaces.*;
import iEngine.output.Window;
import iEngine.output.camera.*;
import iEngine.output.camera.instance.*;

import sun.misc.Unsafe;

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
		
		//System.out.println("Установка тикрейта миру");
		//world.setTickrate(100);
		world.startTickrate();
		window.setFPS(1000);
		
	}
}
