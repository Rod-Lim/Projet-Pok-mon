package tp13;

import java.util.Arrays;

public class Gourmandise extends Nourriture{
	private int apportLoyaute;
	
	//Constructeur :
	public Gourmandise(String nom, int frequence, int apport, String[] compabilites, int apportLoyaute) {
		super(nom, frequence, apport, compabilites);
		this.apportLoyaute=apportLoyaute;
	}
	
	//Getters :
	public int getApportLoyaute() {
		return this.apportLoyaute;
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
				if (joueur.getPokemons()[indexPokemon].getLoyaute() + this.apportLoyaute < 101) {
					joueur.getPokemons()[indexPokemon].setLoyaute(joueur.getPokemons()[indexPokemon].getLoyaute() + this.apportLoyaute);
				} else {
					joueur.getPokemons()[indexPokemon].setLoyaute(100);
				}
			}
		}
	}

	
	//Méthode String toString() :
	public String toString() {
		return this.nom + " : " + this.frequence + ", "+ this.utilisationsRestantes
				+ "/" + this.nombreUtilisations + ", "+ this.apport + ", " + this.apportLoyaute + ", " + Arrays.toString(this.compatibilites);
	}
	
}
