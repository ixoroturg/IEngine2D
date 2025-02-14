
import iEngine.element.interfaces.*;
import iEngine.element.interfaces.baseInstance.*;
import iEngine.math.Point;

import java.awt.event.*;
public class MyWorldBuilder implements WorldBuilderOld{
	private World world = new BaseWorld();
	@Override
	public World build() {
				world.setTickrate(100);

				
		return world;
	}
}