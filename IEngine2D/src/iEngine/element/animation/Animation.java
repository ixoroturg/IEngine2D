package iEngine.element.animation;
import java.util.*;

import java.util.function.Function;

import iEngine.element.GameObject;
import iEngine.element.GlobalSettings;
import iEngine.element.interfaces.Cloneable;
//import iEngine.element.interfaces.GameObject;
import iEngine.math.SpeedFunction;
public abstract class Animation<T extends Cloneable<T>, F> extends GameObject implements Runnable{
	
	/**
	 * Эта функция используется по умолчанию, если не указана другая (SpeedFunction::linear)
	 */
	public static SpeedFunction defaultSpeedFunction = SpeedFunction::easeInOut;
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
	private int stepRepeat[];
	/**
	 * int указывает, сколько повторений уже было
	 */
	private int alreadyRepeated = 0;
	private int currentStepAlreadyRepeated = 0;
	private SpeedFunction[] speedFunction = null;
//	private TimerTask task = null;
	/**
	 * int номер текущего шага
	 */
	private int currentStep = 0;
	/**
	 * int хз
	 */
//	private int lastStep = 0;
//	private Thread vt;
	private int tickrate = 0;
	private T[] target, saveTarget;
	private F[] function, calcFunction;
	private boolean running = false;
	private float lastT = 0;
	
	@SafeVarargs
	public final Animation<T,F> setTarget(T... target){
		this.target = target;
		saveTarget = Arrays.copyOf(target, target.length);
		for(int i = 0; i < target.length; i++) {
			saveTarget[i] =  target[i].clone();
		}
		return this;
	}
	private Animation<T,F> resetToInitialState(){
		for(int i = 0 ; i < target.length; i++) {
			target[i].set(saveTarget[i].clone());
		}
		return this;
	}
	

	@SafeVarargs
	public final Animation<T,F> setFunction(F... function){
		this.function = function;
		this.calcFunction = Arrays.copyOf(function, function.length);
		stepCount = new int[function.length];
		stepRepeat = new int[function.length];
		speedFunction = new SpeedFunction[function.length];
//		speedFunction = List.of(defaultSpeedFunction).toArray(speedFunction);
//		speedFunction = Arrays.copyOf(speedFunction, function.length);
		
//		speedFunction = new Function<Float,Float>[function.length];
		Arrays.fill(stepRepeat, 1);
		Arrays.fill(speedFunction, defaultSpeedFunction);
		return this;
	}
	
	public Animation<T,F> setDuration(float... duration){
		this.duration = duration;
		prepareFunctionOnTickChange();
		currentStepCount = stepCount[0];
		return this;
	}
	
	public Animation<T,F> setFullDuration(float duration, float... partCoefficiens) {
		if(partCoefficiens.length == 0) {
			partCoefficiens = new float[stepRepeat.length];
			Arrays.fill(partCoefficiens, duration / partCoefficiens.length);
			for(int i = 0; i < partCoefficiens.length; i++) {
				partCoefficiens[i] /= stepRepeat[i];
			}
			return setDuration(partCoefficiens);
		}
			
		float sum = 0;
		for(float x: partCoefficiens) {
			sum += x;
		}
		for(int i = 0; i < partCoefficiens.length; i++) {
			partCoefficiens[i] = partCoefficiens[i] / sum * duration / stepRepeat[i];
//			partCoefficiens[i] /= stepRepeat[i];
		}
		return setDuration(partCoefficiens);
	}
	
	public void onTickChange(int tickrate) {
		this.tickrate = tickrate;
	}
	public Animation<T, F> setTickrate(int tickrate){
		this.tickrate = tickrate;
		prepareFunctionOnTickChange();
		if(running)
			restart(false);
		return this;
	}
	private void prepareFunctionOnTickChange() {
		if(tickrate == 0)
			return;
		for(int i = 0; i < stepCount.length; i++) {
			stepCount[i] = (int)(duration[i % duration.length] * tickrate);
		}
		for(int i = 0; i < calcFunction.length; i++) {
			calcFunction[i] = prepareFunction(function[i],stepCount[i]+1);
		}
	}
	@Override
	public void onCreate() {
		
	}
	
	public boolean step() {
		
		currentStepCount--;
		if(currentStepCount < 0) {
			currentStepAlreadyRepeated--;
			if(currentStepAlreadyRepeated == 0) {
				
				if(currentStep == function.length-1) {
					alreadyRepeated++;
					if(alreadyRepeated < repeat || repeat == 0) {
						currentStep = 0;
						currentStepAlreadyRepeated = stepRepeat[currentStep];
						currentStepCount = stepCount[currentStep];
						lastT = 0;
					}
					else 
						return false;				
				}
				else 
					currentStep++;
				currentStepAlreadyRepeated = stepRepeat[currentStep];
			}
				currentStepCount = stepCount[currentStep];
				lastT = 0;
		}
		float currentTime = speedFunction[currentStep].apply(1.0f - currentStepCount / stepCount[currentStep]);
		for(T t: target) {
			applyFunction(t,calcFunction[currentStep],  lastT, currentTime);
		}
		lastT = currentTime;
		return true;
	}
	
	public Animation<T,F> setSpeedFunction(SpeedFunction... function){
		for(int i = 0; i < speedFunction.length; i++) {
			if(function[i % function.length] != null)
				speedFunction[i] = function[i % function.length];
			else
				speedFunction[i] = defaultSpeedFunction;
		}
		return this;
	}
	
	public Animation<T,F> setSpeedFunctionOtherDefault(SpeedFunction... function){
		for(int i = 0; i < speedFunction.length; i++) {
			if(i >= function.length) {
				speedFunction[i] = defaultSpeedFunction;
			} else if(function[i % function.length] != null)
				speedFunction[i] = function[i];
			else
				speedFunction[i] = defaultSpeedFunction;
			
		}
		return this;
	}
	/**
	 * Верните F функцию или объект<br>
	 * При необходимости можно изменить объект, чтобы он работал относительно кадров анимации
	 * @param F function - функция или объект, который описывает шаг анимации
	 * @param stepCount - количество кадров в этом шаге анимации
	 * @return возвращает F функцию или объект, который описывает кадр анимации
	 */
	protected abstract F prepareFunction(F function, int stepCount);
	/**
	 * Примените функцию так, как считаете нужным<br>
	 * <p>
	 * Можете использовать определённый интеграл на промежутке от at до bt
	 * </p>
	 * @param target - цель применения функции. Необходимо напрамую изменить цель
	 * @param function - функция, которая должна быть применена
	 * @param at - предыдущее время шага анимации от 0.0 до 1.0. 
	 * @param bt - текущее время шага анимации от 0.0 до 1.0
	 */
	protected abstract void applyFunction(T target, F function, float at, float bt);
	
	public Animation<T,F> reset() {
		return reset(false);
	}

	
	public Animation<T,F> repeat(int repeatCount, int... stepRepeatCount) {
		repeat = repeatCount;
		if(stepRepeatCount.length != 0)
			for(int i = 0; i < stepRepeat.length; i++) {
				stepRepeat[i] = stepRepeatCount[i % stepRepeat.length];
			}
		currentStepAlreadyRepeated = stepRepeat[0];
		return this;
	}
	
	public Animation<T,F> stop() {
		return stop(false);
	}
	
	public Animation<T,F> start() {
		if(running)
			return this;
		timer.cancel();
		Thread.startVirtualThread(this);
		running = true;
		return this;
		
	}
	
	public Animation<T,F> restart() {
		return restart(false);
	}
	
	public Animation<T,F> reset(boolean stayInCurrentAnimationState) {
		if(!stayInCurrentAnimationState)
			resetToInitialState();
		currentStep = 0;
		currentStepCount = stepCount[currentStep];
		return this;
	}
	
	public Animation<T,F> stop(boolean stayInCurrentAnimationState) {
		if(!stayInCurrentAnimationState)
			resetToInitialState();	
		timer.cancel();
		currentStep = 0;
		currentStepCount = stepCount[0];
		
		running = false;
		return this;
	}
	
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
				}
					
			}
		};
		if(world.getTickrate() != 0)
			timer.scheduleAtFixedRate(task, 0, 1000 / tickrate);
	}
}
