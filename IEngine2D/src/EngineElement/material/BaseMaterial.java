package EngineElement.material;

public enum BaseMaterial implements Material{
	iron(7600), aluminium(2700);
	
	float density;
	BaseMaterial(float d){
		density = d;
	}
	public float density() {
		return density;
	}
	                     
}
