package tp13;

public class Outil extends Item implements ChangerItems {

	public Outil(String nom, int frequence, int nombreUtilisations) {
		super(nom, frequence, nombreUtilisations);
	}

	@Override
	public void changer(Modifiable item) {
		if (item != null && this.utilisationsRestantes > 0 ) {
			item.modifier();
			this.utilisationsRestantes -= 1;
		}
	}

	@Override
	protected Item genererMemeItem(boolean generer) {
		if (generer == true) {
			Item memeItem= this;
			return memeItem;
		} else {
			return null;
		}
	}

}
