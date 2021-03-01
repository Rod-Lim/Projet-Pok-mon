package tp13;

import java.util.Arrays;


public class Nourriture extends Item implements Utilisable {
	protected int apport;
	protected String[] compatibilites;
	public final static String[] TOUS_LES_TYPES_DE_POKEMONS = new String[] { "normal", "combat", "vol", "poison", "sol", "roche",
			"insecte", "spectre", "psy", "plante", "feu", "eau", "electrique", "glace", "dragon", "acier", "tenebres",
			"fee" };

	//Constructeur :
	public Nourriture(String nom, int frequence, int apport, String[] compabilites) {
		super(nom, frequence, 1);
		this.apport=apport;
		this.compatibilites=compatibilites;
	}
	//Getters :
	public int getApport() {
		return this.apport;
	}
	public String[] getCompatibilites() {
		return this.compatibilites;
	}
	
	//Méthodes :
	public boolean isCompatible(Pokemon pokemon) {
		boolean trouve = false;
		int i = 0;
		while(null != pokemon && i<this.compatibilites.length && !trouve) {
			if (this.compatibilites[i].equals(pokemon.getType())) {
				trouve = true;
			}
			i++;
		}
		if (null != pokemon && !trouve) {
			System.out.println("Un pokemon de type " + pokemon.getType() + " n'est pas compatible avec une nourriture de type " + this.getNom() + ".");
		}
		return trouve;
	}
	
	@Override
	public void utiliser(Joueur joueur, int indexPokemon) {
		if (null != joueur && indexPokemon >= 0 && indexPokemon < joueur.getPokemons().length) {
			if (null != joueur.getPokemons()[indexPokemon] && this.utilisationsRestantes > 0) {
				joueur.getPokemons()[indexPokemon].baisserAppetit(this.apport);
				this.baisserUtilisationsRestantes(1);
			}
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
	
	
	
	//Méthode String toString() :
	public String toString() {
		return this.nom + " : " + this.frequence + ", "+ this.utilisationsRestantes
				+ "/" + this.nombreUtilisations + ", "+ this.apport + ", " + Arrays.toString(this.compatibilites) ;
	}

}
