import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import iEngine.element.*;
import iEngine.element.MoveableObject;
import iEngine.element.interfaces.BindTickrate;
import iEngine.element.interfaces.Controlable;
import iEngine.element.interfaces.Tickable;
import iEngine.graphic.Model2D;
import iEngine.graphic.RenderContext;
import iEngine.graphic.Renderable2D;
import iEngine.graphic.camera.Camera;
import iEngine.input.BaseController;
import iEngine.input.interfaces.Controller;
import iEngine.input.interfaces.Mouse;
import iEngine.math.MatrixOld;
import iEngine.math.Matrix2D;
import iEngine.math.Matrix2DOLD;
import iEngine.math.Point;
import iEngine.math.Vector;

public class MouseFollower extends Collider implements Tickable, Controlable, Renderable2D {

	@BindTickrate(Float = 960)
	protected float speed = 4.8f;
	protected Matrix2D matrix = Matrix2D.getE();
	
	public static final byte FOLLOW = 0, animate = 1, aniRestart = 2, aniCycle = 3,aniDrop = 5, rotateToMouse = 4;
//	protected Image sprite;
	protected Controller con = new BaseController();
//	public final Consumer<Boolean> rotateToMouse = 
			
	protected Model2D model;
	public MouseFollower() {
		super(new Point[] { new Point(100, 100), new Point(100, -100), new Point(-100, -100), new Point(-100, 100)
		}, new Point(960, 540), 0, null);
	}
	public MoveableObject obj = new MoveableObject(position,angle);
	public PathMover mover = new MoveToPointMover(obj);
	
	@Override
	public void onCreate() {
//		mover.storage = world.getStorage();
		
		con.bind(Mouse.MOUSE1, FOLLOW);
		con.bind(Mouse.MOUSE2, rotateToMouse,(press) -> {
			if(press) {
				angle = position.getAngle(con.getMouse().getPosition());
			}
		});
		con.bind(KeyEvent.VK_1, animate,(press)->{
			if(press) {
				System.out.println("Анимированно");
				model.animate(0);
			}
				
		});
		con.bind(KeyEvent.VK_2, aniRestart, (press)->{
			if(press)
				model.animateAndReset(0);
		});
		con.bind(KeyEvent.VK_3, aniCycle, (press)->{
			if(press)
				model.animateCycle(0);
		});
		con.bind(KeyEvent.VK_4, aniDrop, (press)->{
			if(press)
				model.stopAnimateCycle(0,false);
		});
//		con.bind(key, action)
//		con.bind(Mouse.MOUSE2, RESTART);
//		con.bind(KeyEvent.VK_1, STOP);
//		con.bind(KeyEvent.VK_2, START);
		try {
			Image sprite = ImageIO.read(new File("/home/ixoroturg/java/IEngine2D/IEngine2D/data/ArrowImage.png"));
			Image sprite2 = ImageIO.read(new File("/home/ixoroturg/java/IEngine2D/IEngine2D/data/ArrowImage2.png"));
			Image sprite3 = ImageIO.read(new File("/home/ixoroturg/java/IEngine2D/IEngine2D/data/ArrowImage3.png"));
			model = new Model2D(200,200,position,angle,sprite);
			model.addAnimation(0, 2, sprite, sprite2, sprite3);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		Animation.defaultSpeedFunction = SpeedFunction::linear;
//		
//		Matrix m = new Matrix2D(5,3,3,4);
//		ani = world.initialize(new TransformMatrixAnimation())
//				.setTarget(matrix)
//				.setFunction(m.clone())
//				.setDuration(1)
//				.repeat(0)
//				.start()
//					;

//		System.out.println("Деление: "+m.clone().div(m.clone()));
//		
//		System.out.println("Текущая: " + m.clone());
//		System.out.println("Обратная: " + m.clone().reverse());

//		ani = new AddMatrixAnimation();
//		world.initialize(ani);
//		
//		ani
//			.setTarget(matrix)
//			.setFunction(m1,m2,m3,m4)
//			.setFullDuration(0.25f,20,60,140)
////			.setDuration(1)
//			
//			.repeat(0)
//			
//			.start()
//			.setTickrate(1)
		;

//		Animation.defaultSpeedFunction = SpeedFunction::linear;

//		var aniP = 
//		world.initialize(new PointAnimation())
//			.setTarget(position)
//			.setFunction(
//				(t)->{
//					float x = (float) Math.sin(Math.PI * 2 * t * 4)* 600f;
//					float y = (float) Math.sin(Math.PI * 2 * t * 16)* 400f;
//					return new Point(x,y);
//				},
//				(t)->{
//					float x = 600*t;
//					float y = 0;
//					return new Point(x,y);
//				},
//				(t)->{
//					float x = (float)Math.cos(Math.PI * 2 * t)*300f;
//					float y = (float)Math.sin(Math.PI * 2 * t)*300f;;
//					return new Point(x,y);
//				},
//				(t)->{
//					float x = -300*t;
//					float y = 0;
//					return new Point(x,y);
//				}
//			)
//			.repeat(2,1,1,3,2)
//			.setSpeedFunction(SpeedFunction::easeOutIn, SpeedFunction::easeOut, SpeedFunction::easeInOut, null)
//			.setFullDuration(10)
//			.start()
//				;

	}
	@Override
	public void onTick() {
		if (con.isActive(FOLLOW)) {
//			System.out.println("двигаться");
			Vector v = position.getVector(con.getMouse().getPosition()).getUnitVector(0.5f);
			movement.add(v);
		}
	}
	@Override
	public Controller getController() {
		return con;
	}
	@Override
	public Model2D getModel(Camera camera) {
		model.setAngle(angle);
		return model;
//		System.out.println(position);
//		return new RenderContext(sprite, obj.getPosition(), obj.getAngle(), 200, 200, matrix);
	}

}
