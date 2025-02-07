import java.awt.*;
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
	public float speed;
	protected Matrix matrix = new Matrix2D(1,0,0,1);
	public static final byte FOLLOW = 0;
	protected Image sprite;
	private Controller con = new BaseController();
	MatrixAnimation ani;
	Matrix m1 = new Matrix2D(0,0,1,1);
	Matrix m2 = new Matrix2D(0,0,-1,-1);
	Matrix m3 = new Matrix2D(0,0,-1,1);
	Matrix m4 = new Matrix2D(0,0,1,-1);
	public MouseFollower() {
		super(new Point[] {new Point(200,200), new Point(200,-200),new Point(-200,-200),new Point(-200,200)}, new Point(960,540),0);
	}
	@Override
	protected void onCreate() {
		
		con.bind(Mouse.MOUSE1, FOLLOW);
		
		try {
			sprite = ImageIO.read(new File("/home/ixoroturg/java/IEngine2D/IEngine2D/data/ArrowImage.png"));
		}catch(IOException e) {e.printStackTrace();}
		
		ani = (MatrixAnimation) new MatrixAnimation().initialize(world);
		ani.setTarget(matrix);
		ani.setApplyMatrix(m1,m2,m3,m4);
		ani.setFullDuration(2);
		ani.repeat(0);
	}
	@Override
	public void onTick() {
		ani.step();
		angle = (float) ((position.getAngle(con.getMouse().getPosition()))-Math.PI/2);
		if(con.isActive(FOLLOW)) {
			move(position.getVector(con.getMouse().getPosition()).getUnitVector().mul(speed));
		}
	}
	@Override
	public Controller getController() {
		return con;
	}
	@Override
	public RenderInfo getRenderInfo(Camera camera) {
		return new RenderInfo(sprite, position, angle, 0.2f, matrix);
	}
}
