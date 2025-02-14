package iEngine.element.animation;

public interface Animation extends Runnable{	
	
	public boolean step();
	public Animation reset();
	public Animation reset(boolean stayInCurrentAnimationState);
	
	public Animation start();
	public Animation stop();
	public Animation stop(boolean stayInCurrentAnimationState);
	public Animation restart();
	public Animation restart(boolean stayInCurrentAnimationState);
	
	public Animation resetToInitialState();
	
	public Animation repeat(int repeatCount);
	public Animation setFullDuration(float duration, float... partCoefficiens);
	public Animation setDuration(float... duration);
}
