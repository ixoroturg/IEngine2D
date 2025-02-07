package iEngine.element.animation;

public interface Animation {
	public boolean step();
	public Animation reset();
	public Animation repeat(int repeatCount);
}
