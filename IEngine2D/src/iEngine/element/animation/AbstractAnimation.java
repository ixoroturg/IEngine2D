package iEngine.element.animation;
import java.util.*;
import iEngine.element.GameObject;
import iEngine.element.interfaces.Tickable;

public abstract class AbstractAnimation extends GameObject implements Animation, Tickable{
	
	protected int[] stepCount;
	public float[] duration;
	protected float currentStepCount;
	protected Timer timer;
	protected int repeat = 1;
	protected int alreadyRepeated = 0;
	protected TimerTask task = null;
	protected int currentStep = 0;
	protected int lastStep = 0;
	protected Thread vt;
	
	@Override
	public Animation setDuration(float... duration){
		this.duration = duration;
		//onTickChange(world.getTickrate());
		onTickChange();
		currentStepCount = stepCount[0];
		return this;
	}
	@Override
	public Animation setFullDuration(float duration, float... partCoefficiens) {
		if(partCoefficiens.length == 0)
			return setDuration(duration / stepCount.length);
		float sum = 0;
		for(float x: partCoefficiens) {
			sum += x;
		}
		for(int i = 0; i < partCoefficiens.length; i++) {
			partCoefficiens[i] = partCoefficiens[i] / sum * duration;
		}
		return setDuration(partCoefficiens);
	}
	
	public void onTickChange() {
		int tickrate = world.getTickrate();
		if(tickrate == 0)
			return;
		for(int i = 0; i < stepCount.length; i++) {
			stepCount[i] = (int)(duration[i % duration.length] * tickrate);
		}
		tickChanged();
		reset(false);
		start();
	}
	@Override
	public void onTick() {}
	@Override
	protected void onCreate() {}
	@Override
	public boolean step() {
		currentStepCount--;
		if(currentStepCount <= 0) {
			if(currentStep == lastStep) {
				alreadyRepeated++;
				if(alreadyRepeated < repeat || repeat == 0) {
					currentStep = 0;
					currentStepCount = stepCount[currentStep];
				}
				else 
					return false;
			}
			else currentStep++;
			currentStepCount = stepCount[currentStep];
		}
		return true;
	}
	@Override
	public Animation reset() {
		return reset(false);
	}

	@Override
	public Animation repeat(int repeatCount) {
		repeat = repeatCount;
		return this;
	}
	@Override
	public Animation stop() {
		return stop(false);
	}
	@Override
	public Animation start() {
		if(vt != null) {
			timer.cancel();
		}
		vt = Thread.startVirtualThread(this);
		return this;
		
	}
	@Override
	public Animation restart() {
		return restart(false);
	}
	@Override
	public Animation reset(boolean stayInCurrentAnimationState) {
		if(!stayInCurrentAnimationState)
			resetToInitialState();
		currentStep = 0;
		currentStepCount = stepCount[currentStep];
		return this;
	}
	@Override
	public Animation stop(boolean stayInCurrentAnimationState) {
		if(!stayInCurrentAnimationState)
			resetToInitialState();	
		timer.cancel();
		vt = null;
		currentStep = 0;
		currentStepCount = stepCount[currentStep];
		return this;
	}
	@Override
	public Animation restart(boolean stayInCurrentAnimationState) {
		stop(stayInCurrentAnimationState);
		start();
		return this;
	}
	@Override
	public void run() {
		timer = new Timer(true);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if(!step()) {
					timer.cancel();
					vt = null;
				}
					
			}
		};
		if(world.getTickrate() != 0)
			timer.scheduleAtFixedRate(task, 0, 1000 / world.getTickrate());
	}
	protected abstract void tickChanged();
}
