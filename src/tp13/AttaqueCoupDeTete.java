package tp13;

public class AttaqueCoupDeTete extends AttaquePhysique{

	public AttaqueCoupDeTete() {
		super("Headbutt", 70, 100, 15);
	}
	
	@Override
	public AttaqueCoupDeTete genererMemeAttaque(boolean generer) {
		if (generer) {
			return this;
		} else {
			return null;
		}
	}
}
