package EngineMath;

public class Matrix {
	public static float[][] add(float[][] matrix1, float[][] matrix2) {
		for(int i = 0; i < matrix1.length; i++) {
			for(int j = 0; j < matrix1[0].length; j++) {
				matrix1[i][j] += matrix2[i][j];
			}
		}
		return matrix1;
	}
	public static float[][] sub(float[][] matrix1, float[][] matrix2) {
		for(int i = 0; i < matrix1.length; i++) {
			for(int j = 0; j < matrix1[0].length; j++) {
				matrix1[i][j] -= matrix2[i][j];
			}
		}
		return matrix1;
	}
	public static float[][] mul(float[][] matrix1, float[][] matrix2){
		
		
		return null;
	}
}
