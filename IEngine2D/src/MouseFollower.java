import java.awt.*;
import java.io.*;

import javax.imageio.*;

import EngineElement.GameObject;
import EngineElement.interfaces.*;
import EngineElement.interfaces.baseInstance.*;
import EngineInput.*;
import EngineMath.Point;
import EngineOutput.camera.*;
public class MouseFollower extends GameObject implements Tickable, Controlable, Renderable{
	
	private float speed = 10;
	private Hitbox h;
	private final byte FOLLOW = 0;
	private Image sprite;
	private Controller con = new BaseController(FOLLOW);
	
	@Override
	protected void onCreate() {
		try {
			sprite = ImageIO.read(new File("/home/ixoroturg/java/IEngine2D/IEngine2D/data/ArrowImage.png"));
		}catch(IOException e) {e.printStackTrace();}
		
		con.setMouse(IEngine2D.mouse);
		Point[] p = new Point[4];
		p[0] = new Point(200,200);
		p[1] = new Point(200,-200);
		p[2] = new Point(-200,-200);
		p[3] = new Point(-200,200);
		h = new BaseHitbox(p, new Point(960,540), 0);
	}
	@Override
	public void onTick() {
		if(con.getMouse().button.get(Mouse.MOUSE1)) {
			h.setAngle(h.getPosition().getAngle(con.getMouse().position));
			h.rotate(-Math.PI/2);
			h.move(h.getPosition().getVector(con.getMouse().position).getUnitVector().mul(speed));
		}
	}
	@Override
	public Controller getController() {
		return con;
	}
	@Override
	public RenderInfo getRenderInfo() {
		return new RenderInfo(sprite, h.getPosition(), h.getAngle(), 0.25, null);
	}
}
