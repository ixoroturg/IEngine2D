package iEngine.element.animation;
import java.util.*;

import iEngine.element.BaseGameObject;
import iEngine.element.interfaces.Cloneable;
public abstract class AbstractAnimation<T extends Cloneable<T>, F> extends BaseGameObject implements Animation<T, F>{
	
	/**
	 * int[] с количеством шагов для каждой анимации
	 */
	private int[] stepCount;
	/**
	 * float[] с длительностью каждой анимации
	 */
	private float[] duration;
	/**
	 * int количество шагов в текущей анимации
	 */
	private float currentStepCount;
	private Timer timer = new Timer();
	/**
	 * int количество повторений до конца анимации
	 */
	private int repeat = 1;
	/**
	 * int указывает, сколько повторений уже было
	 */
	private int alreadyRepeated = 0;
	private TimerTask task = null;
	/**
	 * int номер текущего шага
	 */
	private int currentStep = 0;
	/**
	 * int хз
	 */
	private int lastStep = 0;
	private Thread vt;
	
	private T[] target, saveTarget;
	private F[] function, calcFunction;
	private boolean running = false;
	protected prepareFunction<F> prepare = null;
	
	protected applyFunction<T,F> apply = null;
	
	
	
	@Override
	public Animation<T,F> setTarget(T... target){
		this.target = target;
		saveTarget = Arrays.asList(target).toArray(this.target);
		saveTarget = Arrays.copyOf(target, target.length);
		for(int i = 0; i < target.length; i++) {
			saveTarget[i] =  target[i].clone();
		}
		return this;
	}
//	@Override
	public Animation<T,F> resetToInitialState(){
		for(int i = 0 ; i < target.length; i++) {
			target[i] = saveTarget[i].clone();
		}
		return this;
	}
	
	@Override
	public Animation<T,F> setFunction(F... function){
		this.function = function;
		this.calcFunction = Arrays.copyOf(function, function.length);
		stepCount = new int[function.length];
		return this;
	}
	
	@Override
	public Animation<T,F> setDuration(float... duration){
		this.duration = duration;
		//onTickChange(world.getTickrate());
		onTickChange();
		currentStepCount = stepCount[0];
		return this;
	}
	@Override
	public Animation<T,F> setFullDuration(float duration, float... partCoefficiens) {
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
//		tickChanged();
		for(int i = 0; i < calcFunction.length; i++) {
			calcFunction[i] = prepare.prepare(function[i],stepCount[i]);
		}
		reset(false);
		start();
	}
	@Override
	public void onCreate() {}
	@Override
	public boolean step() {
		currentStepCount--;
		if(currentStepCount <= 0) {
			if(currentStep == function.length-1) {
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
		
		for(T t: target) {
			apply.apply(t,calcFunction[currentStep], stepCount[currentStep] - currentStepCount);
		}
		
		return true;
	}
	@Override
	public Animation<T,F> reset() {
		return reset(false);
	}

	@Override
	public Animation<T,F> repeat(int repeatCount) {
		repeat = repeatCount;
		return this;
	}
	@Override
	public Animation<T,F> stop() {
		return stop(false);
	}
	@Override
	public Animation<T,F> start() {
		if(running)
			return this;
//		if(vt != null)
		
		timer.cancel();
		vt = Thread.startVirtualThread(this);
		running = true;
		return this;
		
	}
	@Override
	public Animation<T,F> restart() {
		return restart(false);
	}
	@Override
	public Animation<T,F> reset(boolean stayInCurrentAnimationState) {
		if(!stayInCurrentAnimationState)
			resetToInitialState();
		currentStep = 0;
		currentStepCount = stepCount[currentStep];
		return this;
	}
	@Override
	public Animation<T,F> stop(boolean stayInCurrentAnimationState) {
		if(!stayInCurrentAnimationState)
			resetToInitialState();	
		timer.cancel();
		vt = null;
		currentStep = 0;
		currentStepCount = stepCount[currentStep];
		
		running = false;
		return this;
	}
	@Override
	public Animation<T,F> restart(boolean stayInCurrentAnimationState) {
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
//	protected abstract void tickChanged();
}
