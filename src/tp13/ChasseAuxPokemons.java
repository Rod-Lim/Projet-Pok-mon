package tp13;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import tp13.Attaque;
import tp13.Pokemon;

import java.io.*;

public class ChasseAuxPokemons {
	
	public static void main(String[] args) throws IOException {
		final Random seedAlea = new Random();
		final ArrayList<Pokemon> pokemonsUtilises = new ArrayList();
		final Map<String, Attaque> mappeAttaques = new HashMap<String, Attaque>();
		mappeAttaques.put("bulle",new AttaqueBulle());
		mappeAttaques.put("coupDeTete",new AttaqueCoupDeTete());
		mappeAttaques.put("croquer",new AttaqueCroquer());
		mappeAttaques.put("enfer",new AttaqueEnfer());
		mappeAttaques.put("feinte",new AttaqueFeinte());
		mappeAttaques.put("morsure",new AttaqueMorsure());
		mappeAttaques.put("pistoleEau",new AttaquePistoleEau());
		mappeAttaques.put("tornadeFeuilles",new AttaqueTornadeFeuilles());
		
		final Map<String,Integer> mapPokemons = new HashMap<String,Integer>();
		try(FileReader reader = new FileReader("C:\\Users\\rodli\\eclipse-workspace\\TP13\\src\\tp13\\PokedexComplet.txt")){
			Scanner scan = new Scanner(reader);
			while(scan.hasNext()) {
				Integer numero = scan.nextInt();
				String nom = scan.next();
				mapPokemons.put(nom,numero);
			}
			scan.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try(FileReader lecteur = new FileReader("C:\\Users\\rodli\\eclipse-workspace\\TP13\\src\\tp13\\InputFile.txt")){
			Scanner s = new Scanner(lecteur);
			while(s.hasNext()) {
				String nom = s.next();
				int numeroPokedex = mapPokemons.get(nom);
				String type = s.next();
				int niveau = s.nextInt();
				boolean diurne = s.nextBoolean();
				int attaque = s.nextInt();
				int defense = s.nextInt();
				int attaqueSpeciale = s.nextInt();
				int defenseSpeciale = s.nextInt();
				ArrayList<Attaque> sesAttaques = new ArrayList<>();
				String nomAttaque = s.next();
				while(! nomAttaque.equals("END")) {
					sesAttaques.add(mappeAttaques.get(nomAttaque).genererMemeAttaque(true));
					nomAttaque = s.next();
				}
				Attaque[] sesAttaquesTableau = new Attaque[sesAttaques.size()];
				for(int i = 0; i<sesAttaques.size(); i++) {
					sesAttaquesTableau[i] = sesAttaques.get(i);
				}
				pokemonsUtilises.add(new Pokemon(numeroPokedex,nom,type,niveau,diurne,attaque,defense,attaqueSpeciale,defenseSpeciale,sesAttaquesTableau));
			}
			s.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
		// Création des 3 Pokémons Piplup, Rowlet et Totodile ainsi que d'un Joueur ayant mon nom.
		final Joueur moi = new Joueur("Limousin", "Rodolphe", 18);
		final Pokemon starter = new Pokemon(2,"Rowlet", "plante", 10, false,55,55,50,50,new Attaque[] {new AttaqueMorsure(), new AttaqueTornadeFeuilles()});
		moi.capturerPokemon(starter);
		// Création de la nourriture :
		final Nourriture tartiflette = new Nourriture("Tartiflette",35,20, new String[] { "dragon", "feu", "combat", "normal", "eau", "electrique" });
		final Nourriture ratatouille = new Nourriture("Ratatouille",10,50, new String[] { "plante", "eau", "vol", "feu", "normal", "electrique", "combat" });

		// Création d'une gourmandise :
		final Gourmandise barreChocolatee = new Gourmandise("Barre chocolatee",20,10, new String[] { "glace", "fee", "feu" },7);

		// Création d'une potion magique :
		final PotionMagique mojito = new PotionMagique("Mojito", 20);
		
		Nourriture[] diversesNourritures = new Nourriture[] { tartiflette, ratatouille , barreChocolatee, mojito };
		
		// Création d'un Jouet :
		final Jouet balle = new Jouet("Balle",20,10,10,5);
		
		// Création d'un Outil :
		final Outil marteau = new Outil("Le Petit Marteau des Merveilles", 10 , 2);
		
		
		try {
			moi.getPokedex().charger("C:\\Users\\rodli\\eclipse-workspace\\TP13\\src\\tp13\\sauvegarde.txt");
		} catch(FileNotFoundException e) {
            System.out.println("Le fichier n'existe pas encore, mais il sera créé une prochaine fois.");
		} catch(IOException e) {
			System.out.println("IOException : On ne peut pas charge le fichier ;-;");
			e.getMessage();
			System.exit(1);
		} catch(InputMismatchException e) {
			System.out.println("InputMismatchException : On ne peut pas charge le fichier ;-;");
			e.getMessage();
			System.exit(1);
		}
		
		try{
			moi.getPokedex().sauvegarder("C:\\Users\\rodli\\eclipse-workspace\\TP13\\src\\tp13\\sauvegarde.txt");
		} catch (IOException e) {
			System.out.println("On ne peut pas charge le fichier ;-;");
			e.getMessage();
		}
		
		
		//Switch pour les choix du joueur :

		Scanner scanner=new Scanner(System.in);
		String answer = " ";
		int indexPokemon;
		int indexProvision;
		while (!"8".contentEquals(answer)) {
			
			System.out.println("Que veux-tu faire ??");
			System.out.println("1- Regarder mes Pokémons.");
			System.out.println("2- Caresser l'un de mes Pokémons.");
			System.out.println("3- Regarder mes provisions.");
			System.out.println("4- Nourrir un de mes Pokémon.");
			System.out.println("5- Aller chercher de la nourriture.");
			System.out.println("6- Aller se balader et essayer de rencontrer des Pokémons sauvages.");
			System.out.println("7- Soigner mes Pokémons.");
			System.out.println("8- Stop.");
			answer = scanner.next();
			switch(answer) {
			  case "1":
				for (int x=0; x<moi.getPokemons().length;x++) {
					if (null != moi.getPokemons()[x]) {
						System.out.println("Index "+ x + " : "+ moi.getPokemons()[x].getNom() + ", " + moi.getPokemons()[x].getHP() + "HP.");	
					}
				}
			    break;
			  case "2":
				System.out.println("Quel est l'index du pokémon que tu veux caresser ??");
				indexPokemon = scanner.nextInt();
				if (indexPokemon > 4 || indexPokemon < 0 || (null == moi.getPokemons()[indexPokemon])) {
					System.out.println("Il n'y a pas de pokémon à caresser dans cet Index ;-;");
				} else {
					moi.caresserPokemon(moi.getPokemons()[indexPokemon]);
				}
			    break;
			  case "3":
				for (int x=0; x<moi.getSac().length;x++) {
					if (null != moi.getSac()[x]) {
						System.out.println("Index "+ x + " : "+ moi.getSac()[x].getNom() + ".");	
					}
				}
				break;
			  case "4":
				System.out.println("Quel est l'index du Pokémon que tu veux nourrir ?");
				indexPokemon = scanner.nextInt();
				if (indexPokemon > 4 || indexPokemon < 0 || (null == moi.getPokemons()[indexPokemon])) {
					System.out.println("Il n'y a pas de Pokémon à cet index :(");
				} else {
					System.out.println("Quel est l'index de la provision que tu veux lui donner ?");
					indexProvision = scanner.nextInt();
					if (indexProvision > 15 || indexProvision < 0 || (null == moi.getSac()[indexProvision])) {
						System.out.println("Il n'y a pas de provision à cet index :(");
					} else {
						moi.nourrirPokemon(moi.getPokemons()[indexPokemon],(Nourriture) moi.getSac()[indexProvision]);
					}
				}
				break;
			  case "5":
				// Code qui génère de la nourriture :
				Item[] itemGenere = new Item[999];
				String reponse = "o";
				int i=0;
				while (!"n".equals(reponse)){
					double nombreAleatoire = Math.random()*100;
					double typeNourriture = Math.random()*diversesNourritures.length;
					if (nombreAleatoire<diversesNourritures[(int) typeNourriture].getFrequence()) {
						itemGenere[i] = diversesNourritures[(int) typeNourriture].genererMemeItem(true);
						System.out.println("Vous avez trouvé 1 " + diversesNourritures[(int) typeNourriture].getNom() + " !");
						System.out.println("Voulez vous prendre cette provision ? (o/n)");
						String reponse2 = scanner.next();
						if ("o".equals(reponse2)) {
							moi.ajouterItem(itemGenere[i]);
						}
						i=i+1;
					}  else {
						System.out.println("Vous n'avez rien trouvé :(");
					}
					System.out.println("Voulez-vous continuer ? (o/n)");
					reponse = scanner.next();
				}   
				break;
			  case "6":
				int ans;
				Pokemon pokemonGenere = pokemonsUtilises.get(seedAlea.nextInt(pokemonsUtilises.size()));
				double nbrAlea = Math.random()*100;
				if (nbrAlea< (100/pokemonGenere.getNiveau())) {
					Pokemon pokemonTrouve = pokemonGenere.genererMemePokemon(true);
					System.out.println("Vous trouvez un " + pokemonTrouve.getNom() + " de niveau " + pokemonTrouve.getNiveau() + " !");
					System.out.println("Voulez-vous vous battre contre ce pokémon ? (o/n)");
					reponse = scanner.next();
					if ("o".equals(reponse)) {
						for (int j=0;j<moi.getPokemons().length;j++) {
							if (null != moi.getPokemons()[j] && 0 != moi.getPokemons()[j].getHP()) {
								System.out.println("Index : " + j +", " + moi.getPokemons()[j].getNom());
							}
						}
						System.out.println("Quel pokémon voulez-vous envoyer au combat ?? (Indiquer l'index)");
						ans = scanner.nextInt();
						Bataille bataille = new Bataille(moi.getPokemons()[ans],pokemonTrouve);
						System.out.println(bataille);
						bataille.run();
					}
				} else {
					System.out.println("Vous n'avez rencontrer aucun pokémon lors de votre balade.");
				}  
				break;
			  case "7":
				System.out.println("Vos Pokémons ont été soignés !");
				for (int x=0;x<moi.getPokemons().length;x++){
					if (null != moi.getPokemons()[x]) {
						moi.getPokemons()[x].soignerPokemon();	
					}			
				}
				break;
			  case "8":
				System.out.println("Au revoir !");
				break;
			  default:
			    System.out.println("Ce n'est pas l'un des choix !");
			}
		}	
	}
}



