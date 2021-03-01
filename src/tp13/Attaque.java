package tp13;

import java.util.Arrays;

public abstract class Attaque {
	protected String nom;
	protected String[] compatibilites;
	protected int puissance;
	protected int precision;
	protected int nombreRepetitions;
	protected int repetitionsRestantes;
	
	//Constructeurs :
	public Attaque(String nom, String[] compatibilites, int puissance, int precision, int nombreRepetitions) {
		this.nom=nom;
		this.compatibilites=compatibilites;
		this.puissance=puissance;
		this.precision=precision;
		this.nombreRepetitions=nombreRepetitions;
		this.repetitionsRestantes=nombreRepetitions;
	}
	public Attaque(String nom, int puissance, int precision, int nombreRepetitions) {
		this (nom,Nourriture.TOUS_LES_TYPES_DE_POKEMONS,puissance,precision,nombreRepetitions); 
	}
	
	//Getters :
	public String getNom() {
		return this.nom;
	}
	public String[] getCompatibilites() {
		return this.compatibilites;
	}
	public int getPuissance() {
		return this.puissance;
	}
	public int getPrecision() {
		return this.precision;
	}
	public int getNombreRepetitions() {
		return this.nombreRepetitions;
	}
	public int getRepetitionsRestantes() {
		return this.repetitionsRestantes;
	}
	// Méthodes :
	public void resetNombreRepetitions() {
		this.repetitionsRestantes=this.nombreRepetitions;
	}
	
	public void baisserNombreRepetitions() {
		if (this.repetitionsRestantes==0) {
			System.out.println("Le nombre de répétitions étant déjà à 0, impossible d'utiliser l'attaque !");
		} else {
			this.repetitionsRestantes-=1;
		}
	}
	//Méthodes Abstraites :
	 protected abstract void utiliserAttaque(Pokemon attaquant, Pokemon victime);
	 protected abstract boolean isCompatible(Pokemon pokemon);
	 protected abstract Attaque genererMemeAttaque(boolean generer);

	 
	//Methode String toString() :
	 public String toString() {
		 return this.nom + " : " + this.puissance + ", " + this.precision + ", " + this.repetitionsRestantes + "/" + 
				 this.nombreRepetitions + ", " + Arrays.toString(this.compatibilites);
	 }
	 
	
}
