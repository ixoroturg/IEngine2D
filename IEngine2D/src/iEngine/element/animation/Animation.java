package iEngine.element.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import iEngine.element.interfaces.Copyable;
import iEngine.math.SpeedFunction;

public abstract class Animation<T, F, S> implements Runnable {

	/**
	 * Эта функция используется по умолчанию, если не указана другая
	 * (SpeedFunction::linear)
	 */
	public static SpeedFunction defaultSpeedFunction = SpeedFunction::linear;
	private SpeedFunction animationDefaultSpeedFunction = defaultSpeedFunction;
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
	/**
	 * int номер текущего шага
	 */
	private int currentStep = 0;
	/**
	 * int хз
	 */
	private int tickrate = 0;
	private T[] target, saveTarget;
	private F[] function;
	private S[] calcFunction;
	private boolean running = false;
	private float lastT = 0;

	@SafeVarargs
	public final Animation<T, F, S> setTarget(T... target) {
		this.target = target;
//		saveTarget = Arrays.copyOf(target, target.length);
//		for (int i = 0; i < target.length; i++) {
////			saveTarget[i] = target[i].copy();
//		}
		return this;
	}
//	private Animation<T, F, S> resetToInitialState() {
//		for (int i = 0; i < target.length; i++) {
////			target[i].paste(saveTarget[i].copy());
//		}
//		return this;
//	}
	@SafeVarargs
	public final Animation<T, F, S> setFunction(F... function) {
		this.function = function;
		stepCount = new int[function.length];
		stepRepeat = new int[function.length];
		speedFunction = new SpeedFunction[function.length];
		Arrays.fill(stepRepeat, 1);
		Arrays.fill(speedFunction, animationDefaultSpeedFunction);
		return this;
	}
	public Animation<T, F, S> setDuration(float... duration) {
		this.duration = duration;
		prepareFunctionOnTickChange();
		currentStepCount = stepCount[0];
		return this;
	}
	public Animation<T, F, S> setFullDuration(float duration, float... partCoefficiens) {
		if (partCoefficiens.length == 0) {
			partCoefficiens = new float[stepRepeat.length];
			Arrays.fill(partCoefficiens, duration / partCoefficiens.length);
			for (int i = 0; i < partCoefficiens.length; i++) {
				partCoefficiens[i] /= stepRepeat[i];
			}
			return setDuration(partCoefficiens);
		}

		float sum = 0;
		for (float x : partCoefficiens) {
			sum += x;
		}
		for (int i = 0; i < partCoefficiens.length; i++) {
			partCoefficiens[i] = partCoefficiens[i] / sum * duration / stepRepeat[i];
		}
		return setDuration(partCoefficiens);
	}
	public Animation<T, F, S> setTickrate(int tickrate) {
		if(tickrate == 0)
			return this;
		this.tickrate = tickrate;
		prepareFunctionOnTickChange();
		if (running)
			restart(false);
		return this;
	}
	private void prepareFunctionOnTickChange() {
		if(function == null || function.length == 0)
			return;
		List<S> pf = new ArrayList<S>();
		for (int i = 0; i < stepCount.length; i++) {
			stepCount[i] = (int) (duration[i % duration.length] * tickrate);
		}
		for (int i = 0; i < function.length; i++) {
			pf.add(prepareFunction(function[i], stepCount[i] + 1));
		}
		calcFunction = pf.toArray(calcFunction);
	}
	public boolean step() {

		currentStepCount--;
		if (currentStepCount < 0) {
			currentStepAlreadyRepeated--;
			if (currentStepAlreadyRepeated == 0) {

				if (currentStep == function.length - 1) {
					alreadyRepeated++;
					if (alreadyRepeated < repeat || repeat == 0) {
						currentStep = 0;
						currentStepAlreadyRepeated = stepRepeat[currentStep];
						currentStepCount = stepCount[currentStep];
						lastT = 0;
					} else
						return false;
				} else
					currentStep++;
				currentStepAlreadyRepeated = stepRepeat[currentStep];
			}
			currentStepCount = stepCount[currentStep];
			lastT = 0;
		}
		float currentTime = speedFunction[currentStep].apply(1.0f - currentStepCount / stepCount[currentStep]);
		for (T t : target) {
			applyFunction(t, calcFunction[currentStep], lastT, currentTime);
		}
		lastT = currentTime;
		return true;
	}
	public Animation<T, F, S> setDefaultSpeedFunction(SpeedFunction function){
		animationDefaultSpeedFunction = function;
		return this;
	}
	public SpeedFunction getDefaultSpeedFunction(){
		return animationDefaultSpeedFunction;
	}
	public Animation<T, F, S> setSpeedFunction(SpeedFunction... function) {
		for (int i = 0; i < speedFunction.length; i++) {
			if (function[i % function.length] != null)
				speedFunction[i] = function[i % function.length];
			else
				speedFunction[i] = animationDefaultSpeedFunction;
		}
		return this;
	}
	public Animation<T, F, S> setSpeedFunctionOtherDefault(SpeedFunction... function) {
		for (int i = 0; i < speedFunction.length; i++) {
			if (i >= function.length) {
				speedFunction[i] = animationDefaultSpeedFunction;
			} else
				if (function[i % function.length] != null)
					speedFunction[i] = function[i];
				else
					speedFunction[i] = animationDefaultSpeedFunction;

		}
		return this;
	}
	/**
	 * Верните F функцию или объект<br>
	 * При необходимости можно изменить объект, чтобы он работал относительно
	 * кадров анимации
	 * 
	 * @param F         function - функция или объект, который описывает шаг
	 *                  анимации
	 * @param stepCount - количество кадров в этом шаге анимации
	 * 
	 * @return возвращает F функцию или объект, который описывает кадр анимации
	 */
	protected abstract S prepareFunction(F function, int stepCount);

	/**
	 * Примените функцию так, как считаете нужным<br>
	 * <p>
	 * Можете использовать определённый интеграл на промежутке от at до bt
	 * </p>
	 * 
	 * @param target   - цель применения функции. Необходимо напрамую изменить
	 *                 цель
	 * @param function - функция, которая должна быть применена
	 * @param at       - предыдущее время шага анимации от 0.0 до 1.0.
	 * @param bt       - текущее время шага анимации от 0.0 до 1.0
	 */
	protected abstract void applyFunction(T target, S function, float at, float bt);
	public Animation<T, F, S> reset() {
		return reset(false);
	}
	public Animation<T, F, S> clear(){
		stop(false);
		target = null;
		function = null;
		calcFunction = null;
		stepCount = null;
		duration = null;
		stepRepeat = null;
		return this;
	}
	public Animation<T, F, S> repeat(int repeatCount, int... stepRepeatCount) {
		repeat = repeatCount;
		if (stepRepeatCount.length != 0)
			for (int i = 0; i < stepRepeat.length; i++) {
				stepRepeat[i] = stepRepeatCount[i % stepRepeat.length];
			}
		currentStepAlreadyRepeated = stepRepeat[0];
		return this;
	}
	public Animation<T, F, S> stop() {
		return stop(false);
	}
	public Animation<T, F, S> start() {
		if (running)
			return this;
		timer.cancel();
		Thread.startVirtualThread(this);
		running = true;
		return this;

	}
	public Animation<T, F, S> restart() {
		return restart(false);
	}
	public Animation<T, F, S> reset(boolean stayInCurrentAnimationState) {
//		if (!stayInCurrentAnimationState)
//			resetToInitialState();
		currentStep = 0;
		currentStepCount = stepCount[currentStep];
		return this;
	}
	public Animation<T, F, S> stop(boolean stayInCurrentAnimationState) {
//		if (!stayInCurrentAnimationState)
//			resetToInitialState();
		timer.cancel();
		currentStep = 0;
		currentStepCount = stepCount[0];

		running = false;
		return this;
	}
	public Animation<T, F, S> restart(boolean stayInCurrentAnimationState) {
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
				if (!step()) {
					timer.cancel();
				}

			}

		};
		if (tickrate != 0)
			timer.scheduleAtFixedRate(task, 0, 1000 / tickrate);
	}

}
