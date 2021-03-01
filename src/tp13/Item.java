package tp13;

public abstract class Item {
	protected String nom;
	protected int frequence;
	protected int nombreUtilisations;
	protected int utilisationsRestantes;
	
	//Constructeur :
	public Item(String nom, int frequence, int nombreUtilisations){
		this.nom = nom;
		this.frequence = frequence;
		this.nombreUtilisations = nombreUtilisations;
		this.utilisationsRestantes = nombreUtilisations;
	}
	
	//Getters :
	public String getNom() {
		return this.nom;
	}
	public int getFrequence() {
		return this.frequence;
	}
	public int getNombreUtilisations() {
		return this.nombreUtilisations;
	}
	public int getUtilisationsRestantes() {
		return this.utilisationsRestantes;
	}
	
	//Méthodes de la classe :
	public void monterUtilisationsRestantes(int difference) {
		if (this.utilisationsRestantes+difference > this.nombreUtilisations) {
			this.utilisationsRestantes = this.nombreUtilisations;
		} else {
			this.utilisationsRestantes += difference;
		}
	}
	public void baisserUtilisationsRestantes(int difference) {
		if (this.utilisationsRestantes+difference > this.nombreUtilisations) {
			this.utilisationsRestantes = 0;
		} else {
			this.utilisationsRestantes -= difference;
		}
	}
	public void resetUtilisationsRestantes() {
		this.utilisationsRestantes=this.nombreUtilisations;
	}
	public void setNom(String nom) {
		this.nom=nom;
	}
	//Méthode abstraite :
	protected abstract Item genererMemeItem(boolean generer);
	
	//Méthode String toString() :
	public String toString() {
		return this.nom + " : " + this.frequence + ", " + this.utilisationsRestantes + "/" + this.nombreUtilisations + ".";
	}
}
