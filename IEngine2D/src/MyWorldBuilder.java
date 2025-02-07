
import iEngine.element.interfaces.*;
import iEngine.element.interfaces.baseInstance.*;
import iEngine.math.Point;

import java.awt.event.*;
public class MyWorldBuilder implements WorldBuilder{
	private World world = new BaseWorld();
	@Override
	public World build() {
				world.setTickrate(100);
				MouseFollower m = (MouseFollower)new MouseFollower().initialize(world);
				m.getController()
					.bind(KeyEvent.VK_W, MouseFollower.FOLLOW)
					.bind(KeyEvent.VK_D, 0);
				
				MouseFollower f = (MouseFollower2) new MouseFollower2().initialize(world);
				//f.setPosition(new Point(300,300));
				f.getController().bind(KeyEvent.VK_SPACE, MouseFollower.FOLLOW);
				
		return world;
	}
}