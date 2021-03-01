package tp13;

public class AttaqueCroquer extends AttaquePhysique {

	public AttaqueCroquer() {
		super("Crunch", 80, 100, 15);
	}
	
	@Override
	public AttaqueCroquer genererMemeAttaque(boolean generer) {
		if (generer) {
			return this;
		} else {
			return null;
		}
	}
}
