package tp13;

public class AttaquePistoleEau extends AttaqueSpeciale{

	public AttaquePistoleEau( ) {
		super("Water Pistol", new String[] {"eau"}, 40, 100, 25);
	}
	
	@Override
	public AttaquePistoleEau genererMemeAttaque(boolean generer) {
		if (generer) {
			return this;
		} else {
			return null;
		}
	}
}
