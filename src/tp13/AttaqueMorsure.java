package tp13;

public class AttaqueMorsure extends AttaquePhysique{

	public AttaqueMorsure() {
		super("Bite", 60, 100, 25);
	}	
	
	@Override
	public AttaqueMorsure genererMemeAttaque(boolean generer) {
		if (generer) {
			return this;
		} else {
			return null;
		}
	}
}
