package iEngine.element;

import java.util.List;

import iEngine.element.interfaces.*;

public class BaseTickManager implements Tickable{
	protected World world;
	public BaseTickManager setWorld(World world) {
		this.world = world;
		return this;
	}
	@Override
	public void onTick() {
		List<Collider> l = world.getStorage().getColliderList();
		world.getStorage().getColliderList().forEach(coll -> {
			for(Collider C: l) {
				if(coll != C)
					coll.Collise(C);
			}
		});
		world.getStorage().getColliderList().forEach(coll -> {
			coll.moveDone();
		});
		world.getStorage().getTickableList().forEach(tick -> {
			tick.onTick();
		});
	}
}
