
import java.awt.event.KeyEvent;

import iEngine.element.WorldBuilder;
import iEngine.element.interfaces.*;
import iEngine.input.*;
import iEngine.input.interfaces.*;
import iEngine.math.Point;
import iEngine.output.Device;
import iEngine.output.Window;
import iEngine.output.camera.*;
import iEngine.output.camera.instance.*;

import sun.misc.Unsafe;

public class IEngine2D {
	public static StandartJavaMouse mouse = new StandartJavaMouse();
	public static void main(String[] args) {

		//World world = new MyWorldBuilder().build();
		
		Device.toNativeResolution();
		
		MouseFollower m = (MouseFollower)new MouseFollower();
		m.getController()
			.bind(KeyEvent.VK_W, MouseFollower.FOLLOW)
			.bind(KeyEvent.VK_D, 0);
		
		MouseFollower f = new MouseFollower2();
		f.getController().bind(KeyEvent.VK_SPACE, MouseFollower.FOLLOW);
		
		Camera camera = new MyCamera().setSize(1920, 1080).setPosition(new Point(960,540));
		
		World world = WorldBuilder.newBuilder()
				.create(m)
				.create(f)
				.create((MyCamera)camera)
				.build(100);
		
		Mouse mouse = new StandartJavaMouse();
		Keyboard keyboard = new StandartJavaKeyboard();
		
//		Camera camera = new StandartJavaCamera().setWorld(world).setDPI(1,1);
		
		
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
