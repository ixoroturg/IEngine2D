package iEngine.element;
import java.util.*;
import iEngine.element.animation.AnimationMoveable;
import iEngine.element.interfaces.Moveable;
import iEngine.element.interfaces.Tickable;
import iEngine.math.*;
import iEngine.math.Vector;

public class VectorMover implements Tickable{
	public static SpeedFunction defaultSpeedFunction = SpeedFunction::linear;
	protected SpeedFunction sf = defaultSpeedFunction;
	protected Moveable target;
	protected AnimationMoveable ani;
	protected List<MoveProperties> props = new ArrayList<>();
	protected List<Float> time = new ArrayList<>();
	protected List<SpeedFunction> speedFunction = new ArrayList<>();
	public boolean active = false;
	protected int tickrate = 0;
	public VectorMover(Moveable target) {
		this.target = target;
	}
	public VectorMover(Moveable target, int tickrate) {
		this.target = target;
		this.tickrate = tickrate;
	}
	public VectorMover setTickrate(int tickrate) {
		if(tickrate != 0)
			this.tickrate = tickrate;
		return this;
	}
	public VectorMover setSpeedFunction(SpeedFunction speedFunction) {
		sf = speedFunction;
		return this;
	}
	public SpeedFunction getSpeedFunction() {
		return sf;
	}
	public VectorMover addAction(Vector moveTo, float rotateAngle, float time) {
		addAction(moveTo,rotateAngle,time,sf);
		return this;
	}
	public VectorMover addAction(Vector moveTo, float rotateAngle, float time, SpeedFunction speedFunction) {
		props.add(new MoveProperties(moveTo,rotateAngle));
		this.time.add(time);
		this.speedFunction.add(speedFunction);
		return this;
	}
	public void move() {
		ani.setTickrate(tickrate);
		ani.setFunction(props.toArray(MoveProperties[]::new));
		float[] f = new float[time.size()];
		int i = 0;
		for(float f1: time) {
			f[i++] = f1;
		}
		ani.setDuration(f);
		ani.setSpeedFunction(speedFunction.toArray(SpeedFunction[]::new));
		active = true;
	}
	@Override
	public void onTick() {
		if(active && !ani.step())
			ani.clear();
	}
}
