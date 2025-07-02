
import java.awt.Image;

import iEngine.element.interfaces.World;
import iEngine.element.interfaces.baseInstance.BaseWorld;
import iEngine.graphic.camera.Camera;
import iEngine.graphic.camera.instances.StandartJavaCamera;
import iEngine.input.instances.StandartJavaKeyboard;
import iEngine.input.instances.StandartJavaMouse;
import iEngine.input.interfaces.Keyboard;
import iEngine.input.interfaces.Mouse;
import iEngine.math.Point;
import iEngine.output.Window;
import iEngine.test.TestFailedException;

public class IEngine2D {

	public static StandartJavaMouse mouse = new StandartJavaMouse();

	public static void main(String[] args) {

		try {
			iEngine.test.Test.startTest();
		}catch(TestFailedException e) {
			System.err.println(e);
			System.exit(-1);
		}
		
		// World world = new MyWorldBuilder().build();

//		Device.toNativeResolution();
//		PathMover.defaultAcceleration = 0.5f;
//		PathMover.defaultFriction = 0.95f;
//		PathMover.defaultMaxRotationSpeed = (float) (Math.PI / 4.0f);
//		PathMover.defaultMaxVelocity = 200;
//		PathMover.defaultRotationAcceleration = (float) (Math.PI / 1800.0f);
		
		MouseFollower m = new MouseFollower();
//		m.getController()
//			.bind(KeyEvent.VK_W, MouseFollower.FOLLOW)
//			.bind(KeyEvent.VK_D, 0);

//		MouseFollower f = new MouseFollower2();
//		f.getController().bind(KeyEvent.VK_SPACE, MouseFollower.FOLLOW);

		MyCamera camera = (MyCamera) new MyCamera()
//				.setResolution(1920, 1080)
//				.setSize(1920, 1080)
				.setWidth(1920)
//				.setSize(1200,900)
				.setPosition(new Point(960, 540));
		
		
		World world = new BaseWorld(100);//WorldBuilder.newBuilder(100).create(m).create((GameObject) camera).build();
//		PathMover.defaultStorage = world.getStorage();
		
		Mouse mouse = new StandartJavaMouse();
		Keyboard keyboard = new StandartJavaKeyboard();

//		Camera camera = new StandartJavaCamera().setWorld(world).setDPI(1,1);

		
		mouse.setController(world.getController()).setCamera(camera);
		keyboard.setController(world.getController());

		Window window = new Window().setCamera(camera);

		window.frame.addMouseListener((StandartJavaMouse) mouse);
		window.frame.addMouseMotionListener((StandartJavaMouse) mouse);
		window.frame.addMouseWheelListener((StandartJavaMouse) mouse);
		window.addKeyListener((StandartJavaKeyboard) keyboard);
		
		
		world.initialize(m);
		world.initialize(camera);
		camera.setWorld(world);
//		world.initialize(f);
		// System.out.println("Установка тикрейта миру");
		// world.setTickrate(100);
//		System.out.println("До старта (Main)");
		world.startTickrate();
		window.setFPS(1000);

	}

}

