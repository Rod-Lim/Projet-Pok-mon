package tp13;

public class AttaqueEnfer extends AttaqueSpeciale{

	public AttaqueEnfer() {
		super("Inferno", new String[] {"fire"}, 100, 50, 5);
	}
	
	@Override
	public AttaqueEnfer genererMemeAttaque(boolean generer) {
		if (generer) {
			return this;
		} else {
			return null;
		}
	}
}
