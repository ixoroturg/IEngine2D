package iEngine.math;

public class SpeedFunction {
	
	public static float linear(float x){
		return x;
	};
	public static float easeIn(float x){
		return (float)Math.sin(Math.PI / 2 * x);
	}
	public static float easeOut(float x){
		return (float)Math.cos(Math.PI / 2 * x);
	}
	public static float easeInOut(float x){
		return (float) (1 - Math.cos(Math.PI * x)) * 0.5f;
	}
	private static float test = 0;
	public static float easeOutIn(float x){
		float result = 0;
		
		result = x > 0.5f ? 
			(float) ((Math.cos(Math.PI / 2 * x) * 0.5)) : 
			(float) (Math.sin(Math.PI / 2 * (x-0.5)/0.5 ) * 0.5 + 0.5f);
		
		result = 1 - (float)(Math.sin(Math.PI  + 3/2*Math.PI * x) );
		
		result = x < 0.5f ? 
				easeIn(x*2f)*0.5f :
				Math.abs(easeIn((x)*2f) - 1)*0.5f+0.5f;
//					x;
//		System.out.println("speed: "+result+", x: "+x+", ds: "+(result - test));
		test = result;
		return result;
	}
}
