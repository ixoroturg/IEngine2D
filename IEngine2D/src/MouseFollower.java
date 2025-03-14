import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.imageio.*;

import iEngine.element.Collider;
import iEngine.element.GlobalSettings;
import iEngine.element.animation.*;
import iEngine.element.interfaces.*;
import iEngine.element.interfaces.baseInstance.*;
import iEngine.input.*;
import iEngine.input.interfaces.*;
import iEngine.math.*;
import iEngine.math.Point;
import iEngine.output.camera.*;
public class MouseFollower extends Collider implements Tickable, Controlable, Renderable{
	
	@BindTickrate(Float = 960)
	protected float speed = 4.8f;
	protected Matrix matrix = new Matrix2D(1,0,0,1);
	public static final byte FOLLOW = 0, RESTART = 1, STOP = 2, START = 3;
	protected Image sprite;
	protected Controller con = new BaseController();
	public MouseFollower() {
		super(new Point[] {new Point(100,100), new Point(100,-100),new Point(-100,-100),new Point(-100,100)}, new Point(960,540),0,
				null
				);
	}
	

	@Override
	public void onCreate() {
		
		
//		System.out.println(position);
//		System.exit(0);
		con.bind(Mouse.MOUSE1, FOLLOW);
		con.bind(KeyEvent.VK_W, FOLLOW);
//		con.bind(Mouse.MOUSE2, RESTART);
//		con.bind(KeyEvent.VK_1, STOP);
//		con.bind(KeyEvent.VK_2, START);
		try {
			sprite = ImageIO.read(new File("/home/ixoroturg/java/IEngine2D/IEngine2D/data/ArrowImage.png"));
		}catch(IOException e) {e.printStackTrace();}
		
		
		
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
		
//		con.addControllerListener((action,act ) -> {
//			switch(action) {
//				case RESTART -> {
//					ani.reset(); 
////					aniP.reset();
//				}
//				case STOP -> {
//					ani.stop(); 
////					aniP.stop();
//				}
//				case START -> {
//					ani.start(); 
////					aniP.start();
//				}
//			}
//		});
	}
	@Override
	public void onTick() {
		
//		System.out.println(Arrays.toString(matrix.get()));
		
//		if(true)return;
		
		
//		angle = (float) ((position.getAngle(con.getMouse().getPosition()))
////				-Math.PI/2
//				);
		if(con.isActive(FOLLOW)) {	
//			System.out.println("движемся");
			Vector v = position.getVector(con.getMouse().getPosition()).getUnitVector().mul(0.1f);
//			System.out.println(v);
//			move(position.getVector(con.getMouse().getPosition()).getUnitVector().mul(speed));
			movement.add(v);
		}
		//System.out.println("Мышка: "+con.getMouse().getPosition());
		//System.out.println("MouseFollower: "+position);
//		moveDone();
	}
	@Override
	public Controller getController() {
		return con;
	}
	@Override
	public RenderInfo getRenderInfo(Camera camera) {
//		System.out.println(position);
		return new RenderInfo(sprite, position, angle, 200, 200 , matrix);
	}
}
