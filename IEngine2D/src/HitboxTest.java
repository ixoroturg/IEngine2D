import iEngine.element.interfaces.baseInstance.BaseHitbox;
import iEngine.math.Point;
import iEngine.output.camera.RenderInfo;
import iEngine.output.camera.Renderable;

public class HitboxTest extends BaseHitbox implements Renderable{

	public HitboxTest(Point[] vertex, Point position, double angle) {
		super(vertex, position, angle);
	}
	@Override
	public RenderInfo getRenderInfo() {
		return null;
	}
	@Override
	protected void onCreate() {
		// TODO Auto-generated method stub
		
	}
	
}
