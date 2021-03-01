package tp13;

import java.util.List;

import tp13.Attaque;

import java.util.ArrayList;

public class Pokemon {
	private int numeroPokedex;
	private String nom;
	private String type;
	private int niveau;
	private boolean diurne;
	private String nomDonne;
	private boolean nomDejaDonne;
	private Joueur monJoueur;
	private int appetit;
	private int loyaute;
	private int attaque;
	private int defense;
	private int attaqueSpeciale;
	private int defenseSpeciale;
	private int hp;
	private int hpMax;
	private List<Attaque> attaques = new ArrayList<Attaque>();
	
	
	// Constructeurs de la classe Pokemon.
	public Pokemon(int numeroPokedex,String nom, String type, int niveau, boolean diurne, String nomDonne, Joueur monJoueur, 
			int attaque, int defense, int attaqueSpeciale, int defenseSpeciale, Attaque[] attaques) {
		this.numeroPokedex = numeroPokedex;
		this.nom = nom;
		this.type = type;
		this.niveau = niveau;
		this.diurne = diurne;
		this.nomDonne = nomDonne;
		this.nomDejaDonne = false;
		this.monJoueur = monJoueur;
		this.appetit = 50;
		this.loyaute = 0;
		this.attaque=attaque;
		this.defense=defense;
		this.attaqueSpeciale=attaqueSpeciale;
		this.defenseSpeciale=defenseSpeciale;
		for (int i=0;i<attaques.length;i++) {
			this.ajouterAttaque(attaques[i]);
		}
		this.hp=30;
		this.hpMax=30;
	}
	
	public Pokemon(int numeroPokedex,String nom, String type, int age, 
			boolean diurne,int attaque, int defense, 
			int attaqueSpeciale, int defenseSpeciale,
			Attaque[] attaques) {
		this(numeroPokedex,nom, type, age, diurne, null, null, attaque, defense, attaqueSpeciale,defenseSpeciale, attaques);
	}

	// Getters
	public int getNumeroPokedex() {
		return this.numeroPokedex;
	}
	public String getNom() {
		return this.nom;
	}

	public String getType() {
		return this.type;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public boolean isDiurne() {
		return this.diurne;
	}

	public String getNomDonne() {
		return this.nomDonne;
	}

	public boolean getNomDejaDonne() {
		return this.nomDejaDonne;
	}

	public Joueur getMonJoueur() {
		return this.monJoueur;
	}

	public int getLoyaute() {
		return this.loyaute;
	}

	public int getAppetit() {
		return this.appetit;
	}
	public int getAttaque() {
		return this.attaque;
	}
	public int getDefense() {
		return this.defense;
	}
	public int getAttaqueSpeciale() {
		return this.attaqueSpeciale;
	}
	public int getDefenseSpeciale() {
		return this.defenseSpeciale;
	}
	public int getHP() {
		return this.hp;
	}
	public int getHPMax() {
		return this.hpMax;
	}
	public List<Attaque> getAttaques() {
		return this.attaques;
	}

	// Setters
	public void setNomDonne(String nomDonne) {
		this.nomDonne = nomDonne;
	}

	public void setNomDejaDonne(boolean nomDejaDonne) {
		this.nomDejaDonne = nomDejaDonne;
	}

	public void setMonJoueur(Joueur monJoueur) {
		this.monJoueur = monJoueur;
	}

	public void setLoyaute(int loyaute) {
		this.loyaute = loyaute;
	}

	public void setAppetit(int appetit) {
		this.appetit = appetit;
	}

	// Méthode permettant aux Pokémons de dire "bonjour", "bonsoir" ou "Zzzzz"
	// selon s'il fait jour/nuit et s'ils sont diurne ou non.
	public void direBonjour(String periode) {
		if ("jour".equals(periode)) {
			if (this.diurne) {
				System.out.println(this.nom + " dit bonjour !");
			} else {
				System.out.println(this.nom + " : ZzZzZzzz...");
			}
		}

		if ("nuit".equals(periode)) {
			if (this.diurne) {
				System.out.println(this.nom + " : ZzZzZzzz...");
			} else {
				System.out.println(this.nom + " dit bonsoir !");
			}
		}
	}

	// Méthode sePrésenter.
	public void sePresenter() {
		System.out.println("Voici un pokémon " + this.nom + " de niveau " + this.niveau + ".");
		if (null != this.monJoueur) {
			System.out.println("Il appartient à " + this.monJoueur.getNom() + " " + this.monJoueur.getPrenom() + ".");
			if (null != this.nomDonne) {
				System.out.println("Il s'appelle " + this.nomDonne + ".");
			}
		}
	}

	// Méthodes pour baiser ou monter l'Appetit.
	public void baisserAppetit(int difference) {
		if ((this.appetit - difference) < 0) {
			System.out.println("L'appétit est déjà au minimum !");
		} else {
			this.appetit -= difference;
		}
	}

	public void monterAppetit(int difference) {
		if ((this.appetit + difference) > 100) {
			System.out.println("L'appétit est déjà au max !");
		} else {
			this.appetit += difference;
		}
	}

	// Méthodes pour baisse ou monter la Loyauté.
	public void baisserLoyaute(int difference) {
		if ((this.loyaute - difference) < 0) {
			System.out.println("La loyauté est déjà au minimum !");
		} else {
			this.loyaute -= difference;
		}
	}

	public void monterLoyaute(int difference) {
		if ((this.loyaute + difference) > 100) {
			System.out.println("La loyauté est déjà au max !");
		} else {
			this.loyaute += difference;
		}
	}

	// Méthode utiliser :
	public void utiliser(Utilisable item) {
		if (item != null) {
			if (this.monJoueur != null) {
				if (this.monJoueur.trouverPokemon(this) != -1) {
					item.utiliser(this.monJoueur, this.monJoueur.trouverPokemon(this));
				}
			} else {
				System.out.println("Le pokémon n'a pas de maître, impossible d'intéragir avec lui !");
			}
		}
	}

	// Méthode Mettre à niveau :
	public void mettreANiveau() {
		this.niveau += 1;
	}
	
	// Méthode trouver attaque :
	 public int trouverAttaque(Attaque attaque) {
		 for (int i=0 ; i < this.attaques.size(); i++) {
			 if (attaque == this.attaques.get(i)) {
				 return i;
			 }
		 }
		 return -1;
	 }
	 
	 // Méthodes ajouter attaque :
	 public void ajouterAttaque(Attaque attaque) {
		 if (attaque.isCompatible(this) && this.attaques.size() < 4) {
			 this.attaques.add(attaque);
		 }
	 }
	 
	 public void ajouterAttaque(Attaque attaque, int i) {
		 if (i>=0 && i <=3 && attaque.isCompatible(this)) {
			 this.attaques.set(i,attaque);
		 }
	 }
	 
	 // Méthode recharger attaques :
	 public void rechargerAttaques() {
		 for (int i=0;i<this.attaques.size();i++) {
			 if (null != attaques.get(i)) {
				 attaques.get(i).resetNombreRepetitions();
			 }
		 }
	 }
	 
	 // Méthode blessure :
	 public void blessure(int dommages) {
		 if (dommages>=this.hp) {
			 this.hp=0;
		 } else {
			 this.hp-=dommages;
		 }
	 }
	 
	 // Méthode s'est évanoui :
	 public boolean sEstEvanoui() {
		 if (this.hp==0) {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	 // Méthode utilise attaque :
	 public void utiliserAttaque(int index, Pokemon victime) {
		 if (!victime.sEstEvanoui() && index < this.attaques.size() && index >=0 && null != this.attaques.get(index)) {
			 System.out.println(this.nom + " utilise " + this.attaques.get(index).getNom() +" !");
			 this.attaques.get(index).utiliserAttaque(this, victime);
		 }
	 }
	 // Méthode afficher attaques
	 public void afficherEtatAttaques() {
		 for (int i=0;i<this.attaques.size();i++) {
			 if (null != this.attaques.get(i)) {
				 System.out.println( i + " : " + this.attaques.get(i).nom + ", " + this.attaques.get(i).repetitionsRestantes + "/" + this.attaques.get(i).nombreRepetitions);
			 }
		 }
	 }
	 //Méthode genererMemePokemon :
	 public Pokemon genererMemePokemon(boolean generer) {
		 if (generer==true) {
			 Pokemon memePokemon = this;
			 return memePokemon;
		 }
		 return null;
	 }
	 //Méthode soignerPokemon :
	 public void soignerPokemon() {
		 this.hp=this.hpMax;
		 this.rechargerAttaques();
	 }
	 
	 // Méthode "String toString()".
	 public String toString() {
		 return (this.numeroPokedex + " | Nom : " + this.nom + ", Type : " + this.type + ", Niveau : " + this.niveau + ", Nom donné : "
				+ this.nomDonne + ", Mon joueur : " + this.monJoueur + ", Appétit : " + this.appetit + ", Loyauté : "
				+ this.loyaute + "." + " [ " + "Attaque : " + this.attaque + ", Défense : " + this.defense + ", Attaque Spéciale : "
				+ this.attaqueSpeciale + ", Défense Spéciale : " + this.defenseSpeciale + ", HP : " + this.hp + ", Attaques : "
				+ this.attaques + " ]" );
	}
}
