package iEngine.element.animation;

@FunctionalInterface
public interface prepareFunction<F> {
	public F prepare(F function, int stepCount);
}
