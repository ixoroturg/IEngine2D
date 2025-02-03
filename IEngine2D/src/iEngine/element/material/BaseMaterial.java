package iEngine.element.material;

public enum BaseMaterial implements Material{
	iron(7600), aluminium(2700), water(1000);
	
	float density;
	BaseMaterial(float d){
		density = d;
	}
	public float density() {
		return density;
	}              
}
