import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import javax.imageio.*;
import iEngine.element.animation.*;
import iEngine.element.interfaces.*;
import iEngine.element.interfaces.baseInstance.*;
import iEngine.input.*;
import iEngine.input.interfaces.*;
import iEngine.math.*;
import iEngine.math.Point;
import iEngine.output.camera.*;
public class MouseFollower extends BaseHitbox implements Tickable, Controlable, Renderable{
	
	@BindTickrate(Float = 960)
	protected float speed = 4.8f;
	protected Matrix matrix = new Matrix2D(1,0,0,1);
	public static final byte FOLLOW = 0, RESTART = 1, STOP = 2, START = 3;
	protected Image sprite;
	protected Controller con = new BaseController();
	MatrixAnimation ani;
	Matrix m1 = new Matrix2D(0,0,1,1);
	Matrix m2 = new Matrix2D(0,0,-1,-1);
	Matrix m3 = new Matrix2D(0,0,-1,1);
	Matrix m4 = new Matrix2D(0,0,1,-1);
	public MouseFollower() {
		super(new Point[] {new Point(200,200), new Point(200,-200),new Point(-200,-200),new Point(-200,200)}, new Point(960,540),0);
	}
	@Override
	public void onCreate() {
		
		
//		System.out.println(position);
//		System.exit(0);
		con.bind(Mouse.MOUSE1, FOLLOW);
		con.bind(Mouse.MOUSE2, RESTART);
		con.bind(KeyEvent.VK_1, STOP);
		con.bind(KeyEvent.VK_2, START);
		try {
			sprite = ImageIO.read(new File("/home/ixoroturg/java/IEngine2D/IEngine2D/data/ArrowImage.png"));
		}catch(IOException e) {e.printStackTrace();}
		
		ani = new MatrixAnimation();
		world.initialize(ani);
		
		ani
			.setTarget(matrix)
			.setFunction(m1,m2,m3,m4)
			.setFullDuration(2,20,60,140)
			.repeat(0)
			.start()
				;
		
		con.addControllerListener((action,act ) -> {
			switch(action) {
			case RESTART -> {ani.reset();}
			case STOP -> {ani.stop();}
			case START -> {ani.start();}
			}
		});
	}
	@Override
	public void onTick() {
//		if(true)return;
		angle = (float) ((position.getAngle(con.getMouse().getPosition()))
//				-Math.PI/2
				);
		if(con.isActive(FOLLOW)) {	
//			System.out.println("движемся");
			Vector v = position.getVector(con.getMouse().getPosition()).getUnitVector().mul(speed);
//			System.out.println(v);
			move(position.getVector(con.getMouse().getPosition()).getUnitVector().mul(speed));
		}
		//System.out.println("Мышка: "+con.getMouse().getPosition());
		//System.out.println("MouseFollower: "+position);
	}
	@Override
	public Controller getController() {
		return con;
	}
	@Override
	public RenderInfo getRenderInfo(Camera camera) {
//		System.out.println(position);
		return new RenderInfo(sprite, position, angle, 200, 200, matrix);
	}
}
