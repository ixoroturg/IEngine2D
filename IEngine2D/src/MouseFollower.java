import java.awt.*;
import java.io.*;

import javax.imageio.*;

import EngineElement.GameObject;
import EngineElement.interfaces.*;
import EngineElement.interfaces.baseInstance.*;
import EngineInput.*;
import EngineInput.interfaces.Controller;
import EngineInput.interfaces.Mouse;
import EngineMath.Point;
import EngineOutput.camera.*;
public class MouseFollower extends BaseHitbox implements Tickable, Controlable, Renderable{
	

	public float speed = 10;
	private final byte FOLLOW = 0;
	private Image sprite;
	private Controller con = new BaseController();
	
	public MouseFollower() {
		super(new Point[] {new Point(200,200), new Point(200,-200),new Point(-200,-200),new Point(-200,200)}, new Point(960,540),0);
	}
	@Override
	protected void onCreate() {
		con.bind(Mouse.MOUSE1, FOLLOW);
		try {
			sprite = ImageIO.read(new File("/home/ixoroturg/java/IEngine2D/IEngine2D/data/ArrowImage.png"));
		}catch(IOException e) {e.printStackTrace();}
		
		//con.setMouse(IEngine2D.mouse);
		
		//h = new BaseHitbox(p, new Point(960,540), 0) {public void onCreate() {}};
	}
	@Override
	public void onTick() {
		angle = (position.getAngle(con.getMouse().getPosition()));
		rotate(-Math.PI/2);
		if(con.isActive(FOLLOW)) {
			
			move(position.getVector(con.getMouse().getPosition()).getUnitVector().mul(speed));
		}
	}
	@Override
	public Controller getController() {
		return con;
	}
	@Override
	public RenderInfo getRenderInfo() {
		return new RenderInfo(sprite, position, angle, 0.25, null);
	}
}
