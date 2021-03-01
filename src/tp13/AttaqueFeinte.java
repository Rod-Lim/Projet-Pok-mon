package tp13;

public class AttaqueFeinte extends AttaquePhysique{

	public AttaqueFeinte() {
		super("Feint", 30, 100, 10);
	}
	
	@Override
	public AttaqueFeinte genererMemeAttaque(boolean generer) {
		if (generer) {
			return this;
		} else {
			return null;
		}
	}
}
