package tp13;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.io.PrintWriter ;


public class Pokedex {
	private Set<Integer> setPokemons;

	//Constructeur :
	public Pokedex() {
		setPokemons = new TreeSet<>();
	}
	
	//Méthodes :
	void rencontrer(Pokemon pokemon) {
		if (null != pokemon) {
			this.setPokemons.add(pokemon.getNumeroPokedex());
		}
	}	
	
	void rencontrer(Pokemon[] pokemons) {
		for (int i=0; i<pokemons.length;i++) {
			rencontrer(pokemons[i]);
		}
	}
	
	void charger(String chemin) throws IOException{
		try(FileReader reader = new FileReader(chemin)){
			Scanner scan = new Scanner(reader);
			while(scan.hasNext()) {
				this.setPokemons.add(scan.nextInt());
			}
			scan.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sauvegarder(String chemin) throws IOException{
        try {
            BufferedWriter scribe = new BufferedWriter(new FileWriter(chemin));
            PrintWriter afficheur= new PrintWriter(scribe);
            for (Integer i : this.setPokemons) {
                afficheur.println(i.intValue());
            }
            afficheur.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
