import EngineElement.interfaces.baseInstance.BaseHitbox;
import EngineMath.Point;
import EngineOutput.camera.RenderInfo;
import EngineOutput.camera.Renderable;

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
