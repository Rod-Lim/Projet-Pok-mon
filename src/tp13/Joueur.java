package tp13;

public class Joueur {
	private String nom;
	private String prenom;
	private int age;
	private Pokemon[] pokemons;
	private Nourriture[] provisions = new Nourriture[10];
	private Item[] sac;
	private Pokedex pokedex;

	// Constructeurs de la classe Joueur
	public Joueur(String nom, String prenom, int age, Pokemon[] pokemons,Nourriture[] provisions) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.pokemons = pokemons;
		this.provisions = provisions;
		for (int i = 0; i < this.pokemons.length; i++) {
			if (null != this.pokemons[i]) {
				this.pokemons[i].setMonJoueur(this);
			}
		}
		for (int i = 0; i < this.provisions.length; i++) {
			if (null != this.provisions[i]) {
				this.provisions[i]=null;
			}
		}
		this.sac = new Item[15];
		this.pokedex = new Pokedex();
		for (int i=0;i<pokemons.length;i++) {
			this.rencontrer(pokemons[i]);
		}
	}

	public Joueur(String nom, String prenom, int age) {
		this(nom, prenom, age, new Pokemon[5],new Nourriture[10]);
		for (int i = 0; i < this.pokemons.length; i++) {
			this.pokemons[i]=null;
		}
		for (int i = 0;i < this.provisions.length; i++) {
			this.provisions[i]=null;
		}
		this.pokedex = new Pokedex();
		for (int i=0;i<pokemons.length;i++) {
			this.rencontrer(pokemons[i]);
		}
	}

	// Getters
	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public int getAge() {
		return this.age;
	}

	public Pokemon[] getPokemons() {
		return this.pokemons;
	}
	public Nourriture[] getProvisions() {
		return this.provisions;
	}
	public Item[] getSac() {
		return this.sac;
	}
	public Pokedex getPokedex() {
		return this.pokedex;
	}
	
	// Méthode pour trouver un Pokemon :
	public int trouverPokemon(Pokemon pokemon) {
		for (int i=0; i < this.pokemons.length;i++) {
			if (this.pokemons[i] == pokemon) {
				return i;
			}
		}
		return -1;
	}
	
	// Méthode pour trouver une Provision :
	private int trouverProvision(Nourriture provision) {
		for (int i=0; i < this.provisions.length;i++) {
			if (this.provisions[i] == provision) {
				return i;
			}
		}
		return -1;
	}
	
	// Méthode pour ajouter des provisions dans le sac de provisions.
	public void ajouterProvision(Nourriture nourriture) {
		if (this.trouverProvision(null) != -1) {
			this.provisions[this.trouverProvision(null)] = nourriture;
		} else {
			System.out.println("Le sac de provisions est plein, on ne peut donc pas y ajouter d'autres provisions !");
		}
	}
	
	// Méthode pour nourrir un pokémon :
	public void nourrirPokemon(Pokemon pokemon, Nourriture nourriture) {
		if (null == pokemon || null == nourriture || !this.equals(pokemon.getMonJoueur()) || this.trouverProvision(nourriture) == -1) {
			System.out.println("Erreur, impossible de nourrir le Pokémon.");
		} else {
			pokemon.utiliser(nourriture);
			this.provisions[this.trouverProvision(nourriture)] = null;
		}
	}

	// Méthode pour capturer un Pokemon :
	public void capturerPokemon(Pokemon pokemon) throws PlusDePlaceException,DejaUnMaitreException {
		this.rencontrer(pokemon);
		if (pokemon.getMonJoueur() != null) {
			throw new DejaUnMaitreException();
		} else {
			int premierePlaceDisponible = this.trouverPokemon(null);
			if (premierePlaceDisponible != -1) {
				this.pokemons[premierePlaceDisponible] = pokemon;
				System.out.println(pokemon.getNom() + " capturé par "+ this.getNom() + " " + this.getPrenom() +" !");
				pokemon.setMonJoueur(this);
			} else {
				throw new PlusDePlaceException();
			}
		}
	}

	// Méthode pour libérer un Pokemon :
	public void libererPokemon(Pokemon pokemon) throws LibererDUnAutreMaitreException,LibererLibreException{
		int positionPokemon = this.trouverPokemon(pokemon);
		if (positionPokemon != -1) {
			this.pokemons[positionPokemon] = null;
			System.out.println("Ce pokémon n'est plus la propriete du joueur " + this.prenom + " " + this.nom + ".");
			pokemon.setMonJoueur(null);
			pokemon.setNomDonne(null);
			pokemon.setAppetit(10);
			pokemon.setLoyaute(0);
		} else if (pokemon.getMonJoueur()!= null){
			this.rencontrer(pokemon);
			throw new LibererDUnAutreMaitreException();
		} else {
			this.rencontrer(pokemon);
			throw new LibererLibreException();
		}
	}

	// Méthode pour nommer un Pokemon :
	public void donnerNom(Pokemon pokemon, String nomDonne) {
		if (pokemon != null) {
			int positionPokemon = this.trouverPokemon(pokemon);
			if (positionPokemon != -1) {
				pokemon.setNomDonne(nomDonne);
				if (pokemon.getNomDejaDonne()) {
					if (pokemon.getLoyaute() < 10) {
						pokemon.setLoyaute(0);
					} else {
						pokemon.baisserLoyaute(10);
					}
				} else {
					if (pokemon.getLoyaute() > 90) {
						pokemon.setLoyaute(100);
					} else {
						pokemon.monterLoyaute(10);
					}
					pokemon.setNomDejaDonne(true);
				}
			} else {
				this.rencontrer(pokemon);
				System.out.println("Vous ne pouvez pas nommer ce pokémon car ce n'est pas le votre !");
			}
		}
	}
	
	//Méthode caresserPokemon :
	public void caresserPokemon(Pokemon pokemon) {
		if (pokemon.getMonJoueur().equals(this)) {
			pokemon.monterLoyaute(1);
			if (pokemon.getLoyaute() < 100) {
				System.out.println("Mmmm, j'aime bien ça... Et sous mon oreille gauche ?");
			} else {
				System.out.println("Oui, moi aussi je t'aime!");
			} 
		} else {
			this.rencontrer(pokemon);
			System.out.println("Ce pokémon ne vous appartient pas ! Vous ne pouvez donc pas le carésser !");
		}
	}
	
	//Méthode donnerItem.
	public void donnerItem(int indexPokemon,int indexItem) {
		if (indexPokemon <= this.pokemons.length && this.sac.length >= indexItem) {
			if (this.pokemons[indexPokemon] != null && this.sac[indexItem] != null) {
				if (this.sac[indexItem] instanceof Utilisable) {
					Utilisable monItem = (Utilisable) this.sac[indexItem];
					this.pokemons[indexPokemon].utiliser(monItem);
					if (sac[indexItem].getUtilisationsRestantes() == 0) {
						sac[indexItem] = null;
					}
				}
			}
		}
	}
	
	//Méthode modifierItem :
	public void modifierItem(int indexChangeur,int indexAModifier) {
		if (this.sac.length >= indexChangeur && this.sac.length >= indexAModifier) {
			if (this.sac[indexChangeur] != null && this.sac[indexAModifier] != null) {
				if (this.sac[indexChangeur] instanceof ChangerItems && this.sac[indexAModifier] instanceof Modifiable) {
					Modifiable monItemModifiable = (Modifiable) this.sac[indexAModifier];
					monItemModifiable.modifier();
					if (this.sac[indexChangeur].getUtilisationsRestantes() == 0) {
						this.sac[indexChangeur] = null;
					}
				}
			}
		}
	}
	//Méthode trouverItem :
	public int trouverItem(Item item) {
		for (int i=0; i<this.sac.length;i++) {
			if (sac[i] == item) {
				return i;
			}
		}
		return -1;
	}
	
	//Méthode ajouterItem
	public void ajouterItem(Item item) {
		if (this.trouverItem(null) != -1) {
			this.sac[this.trouverItem(null)] = item;			
		} else {
			System.out.println("Le sac est plein !");
		}
	}
	
	//Méthode lacherItem
	public void lacherItem(Item item) {
		if (this.trouverItem(item) != -1) {
			this.sac[this.trouverItem(item)] = null;
		}
	}
	
	//Méthode afficherSac :
	public void afficherSac() {
		for (int i=0;i<this.sac.length;i++) {
			if (this.sac[i] != null) {
				System.out.println(this.sac[i]);
			}
		}	
	}
	
	//Méthode rencontrer :
	void rencontrer(Pokemon pokemon) {
		this.pokedex.rencontrer(pokemon);
	}
	
	// Méthode String toString :
	public String toString() {
		return (this.prenom + " " + this.nom + ", " + this.age + " ans");
	}
}
