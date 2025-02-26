package iEngine.element.animation;
import iEngine.element.interfaces.Cloneable;

public interface Animation<T extends Cloneable<T>, F> extends Runnable{	
	
	public boolean step();
	public Animation<T,F> reset();
	public Animation<T,F> reset(boolean stayInCurrentAnimationState);
	
	public Animation<T,F> start();
	public Animation<T,F> stop();
	public Animation<T,F> stop(boolean stayInCurrentAnimationState);
	public Animation<T,F> restart();
	public Animation<T,F> restart(boolean stayInCurrentAnimationState);
	
//	public Animation<T,F> resetToInitialState();
	
	public Animation<T,F> repeat(int repeatCount);
	public Animation<T,F> setFullDuration(float duration, float... partCoefficiens);
	public Animation<T,F> setDuration(float... duration);
	
	public Animation<T,F> setFunction(F... function);
	public Animation<T,F> setTarget(T... target);
}
