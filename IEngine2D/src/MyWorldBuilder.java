
import iEngine.element.interfaces.World;
import iEngine.element.interfaces.WorldBuilderOld;
import iEngine.element.interfaces.baseInstance.BaseWorld;

public class MyWorldBuilder implements WorldBuilderOld {

	private World world = new BaseWorld(100);

	@Override
	public World build() {
		world.setTickrate(100);
		return world;
	}

}