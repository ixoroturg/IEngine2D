import java.awt.Image;

import iEngine.element.interfaces.*;
import iEngine.input.BaseController;
import iEngine.input.interfaces.*;
import iEngine.math.Point;
import iEngine.math.Vector;
import iEngine.output.camera.instance.StandartJavaCamera;

public class MyCamera extends StandartJavaCamera implements Controlable{
	private final int MOVE = 0, START = 1;
	private Vector movement = new Vector(0,0);
	private Point previousPosition = new Point(0,0);
	private boolean startMove = false;
	private Controller con = new BaseController();
	private boolean wasDrag = false;
	@Override
	public void onCreate() {
		con.bind(Mouse.MOUSE3, MOVE);
		con.bind(Mouse.DRAG, START);
		con.addControllerListener((action, isStart)->{
			switch(action) {
			case MOVE -> {
				startMove = isStart;
				if(isStart) 
					previousPosition.set(position);
				else {
					 position.set(previousPosition).add(movement);
					 movement.set(0, 0);
					 startMove = false;
				}
			}
					
			case START -> {
					if(startMove) {
//						wasDrag = true;
//						Point tmp = position.clone();
//						position.set(previousPosition);
//						movement = con.getMouse().getMovement();
//						movement.sub(position.x, position.y);
//						position.set(tmp).add(movement);
						movement = (con.getMouse().getMovement());
//						System.out.println("1: "+v +"2: "+ movement);
						move(movement);
//						v.sub(movement);
//						position.set(previousPosition);
//						move(v);
//						movement = v;
						
						
						
					}
				}
			}
			
		});
	}
	@Override
	public Image render() {
//		if(startMove)
//			move(movement);
		Image img = super.render();	
//		if(startMove)
//			position.set(previousPosition);
		return img;
	}
	@Override
	public Controller getController() {
		return con;
	}
	
}
