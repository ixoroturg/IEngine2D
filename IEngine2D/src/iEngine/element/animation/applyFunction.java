package iEngine.element.animation;

//@FunctionalInterface
public interface applyFunction<T,F> {
	public void apply(T target, F function, float count);
}
