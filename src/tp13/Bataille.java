package tp13;

import java.util.Scanner;

public class Bataille {
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	private Scanner lecteurBataille;
	private boolean batailleFinie;
	
	//Constructeur :
	public Bataille(Pokemon pokemon1, Pokemon pokemon2) {
		this.pokemon1=pokemon1;
		this.pokemon2=pokemon2;
		this.lecteurBataille = new Scanner(System.in);
		this.batailleFinie = false;
	}
	
	//getters :
	public Pokemon getPokemon1() {
		return this.pokemon1;
	}
	
	public Pokemon getPokemon2() {
		return this.pokemon2;
	}
	
	public Scanner getLecteurBataille() {
		return this.lecteurBataille;
	}
	
	public boolean getBatailleFinie() {
		return this.batailleFinie;
	}
	
	//Méthode String toString :
	public String toString() {
		return this.pokemon1.getNom() + " VS. " + this.pokemon2.getNom();
	}
	
	//Méthodes :
	public void run() {
		while (this.batailleFinie!=true) {
			choisirAction(this.pokemon1,this.pokemon2);
			if (!this.pokemon2.sEstEvanoui()) {
				double randomAttaque = Math.random()*pokemon2.getAttaques().size();
				System.out.println("Au tour de " + this.pokemon2.getNom() + "!");
				this.pokemon2.utiliserAttaque((int)randomAttaque, this.pokemon1);
				System.out.println("Vie restante à " + this.pokemon1.getNom() + " : " + this.pokemon1.getHP());
				if (this.pokemon1.sEstEvanoui()) {
					this.batailleFinie=true;
					System.out.println(this.pokemon1.getNom() + " s'est évanoui, victoire de " + this.pokemon2.getNom() + "!");
				}
			}
		}	
		this.pokemon1.rechargerAttaques();
		this.pokemon2.rechargerAttaques();
	}
	
	public void choisirAction(Pokemon pokemonActif, Pokemon pokemonPassif) {
		String reponse;
		if (!pokemonActif.sEstEvanoui()) {
			System.out.println("Au tour de " + pokemonActif.getNom() + "!");
			pokemonActif.afficherEtatAttaques();
			int ans = this.lecteurBataille.nextInt();
			if (null!=pokemonActif.getAttaques().get(ans) && pokemonActif.getAttaques().get(ans).nombreRepetitions != 0) {
				pokemonActif.utiliserAttaque(ans, pokemonPassif);
				System.out.println("Vie restante à " + pokemonPassif.getNom() + " : " + pokemonPassif.getHP());
				if (pokemonPassif.sEstEvanoui()) {
					this.batailleFinie=true;
					System.out.println(pokemonPassif.getNom() + " s'est évanoui, victoire de " + pokemonActif.getNom() + "!");
					if (null != pokemonActif.getMonJoueur() && null == pokemonPassif.getMonJoueur()) {
						System.out.println("Veux-tu capturer " + pokemonPassif.getNom() + " ? (o/n)");
						reponse = this.lecteurBataille.next();
						if ("o".contentEquals(reponse)) {
							if (pokemonActif.getMonJoueur().trouverPokemon(null) != -1) {
								pokemonActif.getMonJoueur().capturerPokemon(pokemonPassif);
							} else {
								System.out.println("Votre équipe est pleine ! Il vous faudra renoncer à un autre pokémon pour capturer celui-ci !.");
								System.out.println("Voulez-vous renoncer à un de vos pokémons afin de capturer " + pokemonPassif.getNom() + " ? (o/n)");
								reponse = this.lecteurBataille.next();
								if ("o".contentEquals(reponse)) {
									System.out.println("Indiquez l'index du pokémon que vous voulez libérer :");
									ans=this.lecteurBataille.nextInt();
									while (ans > pokemonActif.getMonJoueur().getPokemons().length || ans < 0) {
										System.out.println("Index invalide, rechoisissez un index valide. (Entre 0 et " + pokemonActif.getMonJoueur().getPokemons().length +" compris)");
										ans = this.lecteurBataille.nextInt();
									}
									pokemonActif.getMonJoueur().libererPokemon(pokemonActif.getMonJoueur().getPokemons()[ans]);
									pokemonActif.getMonJoueur().capturerPokemon(pokemonPassif);
								}
							}
						}
					}
				}
			} else {
				System.out.println("Tu perds ton tour ! (Plus de PP ou  pas d'attaque sur cet emplacement)");
			}
		}
	}
	
}
