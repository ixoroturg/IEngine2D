
import EngineElement.interfaces.*;
import EngineElement.interfaces.baseInstance.*;
import EngineMath.Point;
import java.awt.event.*;
public class MyWorldBuilder implements WorldBuilder{
	private World world = new BaseWorld();
	@Override
	public World build() {
		//MouseFollower f = (MouseFollower) 
		
				MouseFollower m = (MouseFollower)new MouseFollower().setWorld(world);
				m.getController().bind(KeyEvent.VK_W, 0)
				.bind(KeyEvent.VK_D, 0);
				
				MouseFollower f = (MouseFollower) new MouseFollower().setWorld(world);
				f.setPosition(new Point(300,300));
				f.speed = 20;
				f.getController().bind(KeyEvent.VK_SPACE, 0);
				
				
		return world;
	}
}