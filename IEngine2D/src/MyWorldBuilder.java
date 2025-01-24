
import EngineElement.interfaces.*;
import EngineElement.interfaces.baseInstance.*;

public class MyWorldBuilder implements WorldBuilder{
	private World world = new BaseWorld();
	@Override
	public World build() {
		//MouseFollower f = (MouseFollower) 
				new MouseFollower().setWorld(world);
				MouseFollower f = (MouseFollower) new MouseFollower().setWorld(world);
		//f.setWorld(world);
		return world;
	}
}
