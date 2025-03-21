import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import iEngine.element.animation.AddMatrixAnimation;
import iEngine.element.interfaces.BindTickrate;
import iEngine.input.interfaces.Mouse;
import iEngine.math.Matrix;
import iEngine.math.Matrix2D;
import iEngine.render.*;

public class MouseFollower2 extends MouseFollower implements Renderable{
	@BindTickrate(Float = 480)
	protected float speed=10;
	public MouseFollower2() {
		super();
		position.set(200,200);
	}
	
	@Override
	public void onCreate(){
		
		con.bind(Mouse.MOUSE1, FOLLOW);
		con.bind(KeyEvent.VK_S, FOLLOW);
//		con.bind(Mouse.MOUSE2, RESTART);
//		con.bind(KeyEvent.VK_1, STOP);
//		con.bind(KeyEvent.VK_2, START);
		try {
			sprite = ImageIO.read(new File("/home/ixoroturg/java/IEngine2D/IEngine2D/data/ArrowImage.png"));
		}catch(IOException e) {e.printStackTrace();}	
		
//		con.addControllerListener((action, act) -> {
//			switch(action) {
//			case RESTART -> {ani.reset();}
//			case STOP -> {ani.stop();}
//			case START -> {ani.start();}
//			}
//		});
//		ani = new AddMatrixAnimation();
//		world.initialize(ani);
//		ani.setTarget(matrix);
//		Matrix m1 = new Matrix2D(0,0,4,4);
//		Matrix m2 = new Matrix2D(0,0,-4,-4);
//		Matrix m3 = new Matrix2D(0,0,-4,4);
//		Matrix m4 = new Matrix2D(0,0,4,-4);
//		
//		ani.setFunction(m1,m2,m3,m4);
//		ani.setDuration(1,3,1,3);
//		ani.repeat(2);
//		ani.start();
	}
//	@Override
//	public void onTick() {
//		angle = (float) ((position.getAngle(con.getMouse().getPosition()))
//				-Math.PI/2
//				);
//		if(con.isActive(FOLLOW)) {	
//			move(position.getVector(con.getMouse().getPosition()).getUnitVector().mul(speed));
//		}
//	}
	@Override
	public RenderInfo getRenderInfo(Camera camera) {
		//System.out.println(speed);
		return new RenderInfo(sprite, position, angle, 200, 200, matrix);
	}
}
