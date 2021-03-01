package tp13;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JoueurTest {
	Joueur joueur;
	
	@BeforeEach
	void setUp() throws Exception {
		joueur = new Joueur("Limousin","Rodolphe",18);
	}

	@AfterEach
	void tearDown() throws Exception {
		joueur = null;
	}
	
	@Test
	void testGetNom() {
		if (!"Limousin".equals(joueur.getNom())) {
			fail("Ce n'est pas le bon nom.");
		} 
	}

	@Test
	void testGetPrenom() {
		if (!"Rodolphe".equals(joueur.getPrenom())) {
			fail("Ce n'est pas le bon prénom.");
		}
	}

	@Test
	void testCapturer() {
		Pokemon pokemon = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		joueur.capturerPokemon(pokemon);
		if (joueur.getPokemons().length != 5){
			fail("La taille de joueur.getPokemons() n'est plus la bonne");
		} 
		if (joueur.getPokemons()[0] != pokemon) {
			fail("Ce n'est pas le même pokémon !");
		}
		for (int i=1;i<joueur.getPokemons().length;i++) {
			if (null != joueur.getPokemons()[i]) {
				fail("Une autre position du tableau a été remplie !");
			}
		}
		if (joueur != pokemon.getMonJoueur()) {
			fail("Le nouveau maître du pokémon n'est pas celui qu'on attend.");
		}
	}
	
	@Test
	void testLiberer() {
		Pokemon pokemon = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		joueur.capturerPokemon(pokemon);
		joueur.libererPokemon(pokemon);
		if (joueur.getPokemons().length != 5){
			fail("La taille de joueur.getPokemons() n'est plus la bonne");	
		}
		if (joueur.getPokemons()[0] != null) {
			fail("Il y a toujours un pokémon sur la position 0 du tableau de pokémons !");
		}
		for (int i=1;i<joueur.getPokemons().length;i++) {
			if (null != joueur.getPokemons()[i]) {
				fail("Il y a un pokémon sur  une autre position du tableau !");
			}
		}
		if (pokemon.getMonJoueur() != null) {
			fail("Ce pokémon a un maître alors qu'il ne devrait pas en avoir (null)");
		}			
	}
	
 	@Test
	void testCapturerPlusDePlace() {
		Pokemon pokemon1 = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		Pokemon pokemon2 = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		Pokemon pokemon3 = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		Pokemon pokemon4 = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		Pokemon pokemon5 = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		joueur.capturerPokemon(pokemon1);
		joueur.capturerPokemon(pokemon2);
		joueur.capturerPokemon(pokemon3);
		joueur.capturerPokemon(pokemon4);
		joueur.capturerPokemon(pokemon5);
		
		Pokemon pokemonDeTrop = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		
		
		Assertions.assertThrows(PlusDePlaceException.class, () -> {
		    joueur.capturerPokemon(pokemonDeTrop);
		  });
	}
	
	@Test
	void testDejaUnMaitre() {
		Pokemon pokemon = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		Joueur joueur1= new Joueur("Dupont","Durand",25);
		joueur1.capturerPokemon(pokemon);
		
		Assertions.assertThrows(DejaUnMaitreException.class, () -> {
		    joueur.capturerPokemon(pokemon);
		  });
	}

	@Test
	void testLibererDUnAutreMaitre() {
		Pokemon pokemon = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		Joueur joueur1= new Joueur("Dupont","Durand",25);
		joueur1.capturerPokemon(pokemon);
		Assertions.assertThrows(LibererDUnAutreMaitreException.class, () -> {
		    joueur.libererPokemon(pokemon);
		  });
	}
	
	@Test
	void testLibererLibre() {
		Pokemon pokemon = new Pokemon(393,"Piplup", "eau", 5, false,50,50,50,50,new Attaque[] {new AttaqueBulle()});
		Assertions.assertThrows(LibererLibreException.class, () -> {
		    joueur.libererPokemon(pokemon);
		  });
	}
}

/* Exercice 2 :
 * 2) Les tests sont au rouge.
 * 
 * Exercice 3 :
 * 1) (La bonne taille est de 5).
 * 2) Il passe les vérifications.
 * 3) (Le maître attendu est "null").
 * 	  Tout va bien, les tests sont tous au vert ;)
 * 4) La différence est que pour que les tests soient vert, ils doivent être exécutés avec le second joueur et non le premier joueur.
 * 
 * Exercice 4:
 * 1) Cette classe devrait être une sous-classe de RuntimeException car de ce fait, on peut utiliser ces nouvelles classe dans nu bloc try/catch..
 * 
 * Exercice 5:
 * 1)On doit changer l'annotation en : @Test(expectedMaClasseErreurException.class)
 * Pour créer un test où l'o a plus de place, il suffit de faire capturer 6 pokémons différents par le même joueur puis d'essayer de lui en faire capturer un 7ème.
 */