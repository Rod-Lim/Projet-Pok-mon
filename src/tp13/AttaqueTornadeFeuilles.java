package tp13;

public class AttaqueTornadeFeuilles extends AttaqueSpeciale {

	public AttaqueTornadeFeuilles() {
		super("Leaf Tornado", new String[] {"plante"}, 65, 90, 10);
	}
	
	@Override
	public AttaqueTornadeFeuilles genererMemeAttaque(boolean generer) {
		if (generer) {
			return this;
		} else {
			return null;
		}
	}
}
