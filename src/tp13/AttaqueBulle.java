package tp13;

public class AttaqueBulle extends AttaqueSpeciale{

	public AttaqueBulle() {
		super("Bubble", new String[] {"eau"}, 40, 100, 30);
	}
	
	@Override
	public AttaqueBulle genererMemeAttaque(boolean generer) {
		if (generer) {
			return this;
		} else {
			return null;
		}
	}
}
