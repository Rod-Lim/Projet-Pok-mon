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

	// M�thode permettant aux Pok�mons de dire "bonjour", "bonsoir" ou "Zzzzz"
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

	// M�thode sePr�senter.
	public void sePresenter() {
		System.out.println("Voici un pok�mon " + this.nom + " de niveau " + this.niveau + ".");
		if (null != this.monJoueur) {
			System.out.println("Il appartient � " + this.monJoueur.getNom() + " " + this.monJoueur.getPrenom() + ".");
			if (null != this.nomDonne) {
				System.out.println("Il s'appelle " + this.nomDonne + ".");
			}
		}
	}

	// M�thodes pour baiser ou monter l'Appetit.
	public void baisserAppetit(int difference) {
		if ((this.appetit - difference) < 0) {
			System.out.println("L'app�tit est d�j� au minimum !");
		} else {
			this.appetit -= difference;
		}
	}

	public void monterAppetit(int difference) {
		if ((this.appetit + difference) > 100) {
			System.out.println("L'app�tit est d�j� au max !");
		} else {
			this.appetit += difference;
		}
	}

	// M�thodes pour baisse ou monter la Loyaut�.
	public void baisserLoyaute(int difference) {
		if ((this.loyaute - difference) < 0) {
			System.out.println("La loyaut� est d�j� au minimum !");
		} else {
			this.loyaute -= difference;
		}
	}

	public void monterLoyaute(int difference) {
		if ((this.loyaute + difference) > 100) {
			System.out.println("La loyaut� est d�j� au max !");
		} else {
			this.loyaute += difference;
		}
	}

	// M�thode utiliser :
	public void utiliser(Utilisable item) {
		if (item != null) {
			if (this.monJoueur != null) {
				if (this.monJoueur.trouverPokemon(this) != -1) {
					item.utiliser(this.monJoueur, this.monJoueur.trouverPokemon(this));
				}
			} else {
				System.out.println("Le pok�mon n'a pas de ma�tre, impossible d'int�ragir avec lui !");
			}
		}
	}

	// M�thode Mettre � niveau :
	public void mettreANiveau() {
		this.niveau += 1;
	}
	
	// M�thode trouver attaque :
	 public int trouverAttaque(Attaque attaque) {
		 for (int i=0 ; i < this.attaques.size(); i++) {
			 if (attaque == this.attaques.get(i)) {
				 return i;
			 }
		 }
		 return -1;
	 }
	 
	 // M�thodes ajouter attaque :
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
	 
	 // M�thode recharger attaques :
	 public void rechargerAttaques() {
		 for (int i=0;i<this.attaques.size();i++) {
			 if (null != attaques.get(i)) {
				 attaques.get(i).resetNombreRepetitions();
			 }
		 }
	 }
	 
	 // M�thode blessure :
	 public void blessure(int dommages) {
		 if (dommages>=this.hp) {
			 this.hp=0;
		 } else {
			 this.hp-=dommages;
		 }
	 }
	 
	 // M�thode s'est �vanoui :
	 public boolean sEstEvanoui() {
		 if (this.hp==0) {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	 // M�thode utilise attaque :
	 public void utiliserAttaque(int index, Pokemon victime) {
		 if (!victime.sEstEvanoui() && index < this.attaques.size() && index >=0 && null != this.attaques.get(index)) {
			 System.out.println(this.nom + " utilise " + this.attaques.get(index).getNom() +" !");
			 this.attaques.get(index).utiliserAttaque(this, victime);
		 }
	 }
	 // M�thode afficher attaques
	 public void afficherEtatAttaques() {
		 for (int i=0;i<this.attaques.size();i++) {
			 if (null != this.attaques.get(i)) {
				 System.out.println( i + " : " + this.attaques.get(i).nom + ", " + this.attaques.get(i).repetitionsRestantes + "/" + this.attaques.get(i).nombreRepetitions);
			 }
		 }
	 }
	 //M�thode genererMemePokemon :
	 public Pokemon genererMemePokemon(boolean generer) {
		 if (generer==true) {
			 Pokemon memePokemon = this;
			 return memePokemon;
		 }
		 return null;
	 }
	 //M�thode soignerPokemon :
	 public void soignerPokemon() {
		 this.hp=this.hpMax;
		 this.rechargerAttaques();
	 }
	 
	 // M�thode "String toString()".
	 public String toString() {
		 return (this.numeroPokedex + " | Nom : " + this.nom + ", Type : " + this.type + ", Niveau : " + this.niveau + ", Nom donn� : "
				+ this.nomDonne + ", Mon joueur : " + this.monJoueur + ", App�tit : " + this.appetit + ", Loyaut� : "
				+ this.loyaute + "." + " [ " + "Attaque : " + this.attaque + ", D�fense : " + this.defense + ", Attaque Sp�ciale : "
				+ this.attaqueSpeciale + ", D�fense Sp�ciale : " + this.defenseSpeciale + ", HP : " + this.hp + ", Attaques : "
				+ this.attaques + " ]" );
	}
}
