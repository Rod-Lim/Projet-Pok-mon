package tp13;

public class PotionMagique extends Nourriture {

	//Constructeur :
	public PotionMagique(String nom, int frequence) {
		super(nom, frequence, 0, TOUS_LES_TYPES_DE_POKEMONS);
	}
	
	//Méthodes :
	@Override
	protected Item genererMemeItem(boolean generer) {
		if (generer == true) {
			Item memeItem= this;
			return memeItem;
		} else {
			return null;
		}
	}
	@Override
	public void utiliser(Joueur joueur, int indexPokemon) {
		if (joueur != null && indexPokemon < joueur.getPokemons().length && joueur.getPokemons()[indexPokemon] != null) {
			if (this.isCompatible(joueur.getPokemons()[indexPokemon]) && this.nombreUtilisations > 0) {
				joueur.getPokemons()[indexPokemon].mettreANiveau();
			}
		}
	}
}

