package iEngine.element.animation;

import iEngine.element.GameObject;
import iEngine.element.interfaces.BindTickrate;
import iEngine.element.interfaces.Tickable;

public abstract class AbstractAnimation extends GameObject implements Animation, Tickable{
	protected int[] stepCount;
	public float[] duration;
	protected float currentStepCount;
	
	protected int currentStep = 0;
	protected int lastStep = 0;
	public Animation setDuration(float... duration){
		this.duration = duration;
		onTickChange(world.getTickrate());
		currentStepCount = stepCount[0];
		return this;
	}
	public Animation setFullDuration(float duration) {
		return setDuration(duration / stepCount.length);	
	}
	@Override
	public void onTickChange(int tickrate) {
		for(int i = 0; i < stepCount.length; i++) {
			stepCount[i] = (int)(duration[i % duration.length] * tickrate);
		}
	}
	@Override
	protected void onCreate() {
		world.getStorage().getTickableList().remove(this);
	}
}
