package iEngine.math;
import java.util.function.Function;

@FunctionalInterface
public interface SpeedFunction extends Function<Float,Float>{
	
	public Float apply(Float t);
	
	public static float linear(float t){
		return t;
	};
	public static float easeIn(float t){
		return (float)Math.sin(Math.PI / 2 * t);
	}
	public static float easeOut(float t){
		return 1f - (float)Math.cos(Math.PI / 2 * t);
	}
	public static float easeInOut(float t){
		return (float) (1 - Math.cos(Math.PI * t)) * 0.5f;
	}
	public static float easeOutIn(float t){
		float result = 0;
		result = t < 0.5f ? 
				easeIn(t*2f)*0.5f :
				Math.abs(easeIn((t)*2f) - 1)*0.5f+0.5f;
		return result;
	}
}
