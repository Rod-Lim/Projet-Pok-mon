package tp13;

public class Jouet extends Item implements Utilisable, Modifiable {
	private int apportAppetit;
	private int apportLoyaute;
	
	//Constructeur :
	public Jouet(String nom, int frequence, int nombreUtilisations, int apportAppetit, int apportLoyaute) {
		super(nom, frequence, nombreUtilisations);
		this.apportAppetit=apportAppetit;
		this.apportLoyaute=apportLoyaute;
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
	public void modifier() {
		this.nom += " magique";
		this.apportAppetit += 10;
		this.apportLoyaute += 5;
		this.utilisationsRestantes = this.nombreUtilisations;
		System.out.println("Jouet bien modifié !");
	}

	@Override
	public void utiliser(Joueur joueur, int indexPokemon) {
		if (joueur != null && indexPokemon < joueur.getPokemons().length && joueur.getPokemons()[indexPokemon] != null) {
			if (this.nombreUtilisations > 0) {
				joueur.getPokemons()[indexPokemon].monterAppetit(this.apportAppetit);
				joueur.getPokemons()[indexPokemon].monterLoyaute(this.apportLoyaute);
				this.utilisationsRestantes-=1;
			}
		}
	}
	
	//Méthode String toString() :
	public String toString() {
		return this.nom + " : " + this.frequence + ", " + this.utilisationsRestantes + "/" + this.nombreUtilisations + ", "
				+ this.apportAppetit + ", " + this.apportLoyaute;
	}

	
}
