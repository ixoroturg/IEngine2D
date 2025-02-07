import iEngine.element.animation.MatrixAnimation;
import iEngine.element.interfaces.BindTickrate;
import iEngine.math.Matrix;
import iEngine.math.Matrix2D;
import iEngine.output.camera.Camera;
import iEngine.output.camera.RenderInfo;
import iEngine.output.camera.Renderable;

public class MouseFollower2 extends MouseFollower implements Renderable{
	@BindTickrate(Float = 480)
	protected float speed=1;
	public MouseFollower2() {
		super();
		position.set(200,200);
	}
	@Override
	public void onCreate(){
		super.onCreate();
		ani = (MatrixAnimation) new MatrixAnimation().initialize(world);
		ani.setTarget(matrix);
		Matrix m1 = new Matrix2D(0,0,4,4);
		Matrix m2 = new Matrix2D(0,0,-4,-4);
		Matrix m3 = new Matrix2D(0,0,-4,4);
		Matrix m4 = new Matrix2D(0,0,4,-4);
		
		ani.setApplyMatrix(m1,m2,m3,m4);
		ani.setDuration(1,3,1,3);
		ani.repeat(2);
	}
	@Override
	public RenderInfo getRenderInfo(Camera camera) {
		//System.out.println(speed);
		return new RenderInfo(sprite, position, angle, 0.1f, matrix);
	}
}
