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
			fail("Ce n'est pas le bon pr�nom.");
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
			fail("Ce n'est pas le m�me pok�mon !");
		}
		for (int i=1;i<joueur.getPokemons().length;i++) {
			if (null != joueur.getPokemons()[i]) {
				fail("Une autre position du tableau a �t� remplie !");
			}
		}
		if (joueur != pokemon.getMonJoueur()) {
			fail("Le nouveau ma�tre du pok�mon n'est pas celui qu'on attend.");
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
			fail("Il y a toujours un pok�mon sur la position 0 du tableau de pok�mons !");
		}
		for (int i=1;i<joueur.getPokemons().length;i++) {
			if (null != joueur.getPokemons()[i]) {
				fail("Il y a un pok�mon sur  une autre position du tableau !");
			}
		}
		if (pokemon.getMonJoueur() != null) {
			fail("Ce pok�mon a un ma�tre alors qu'il ne devrait pas en avoir (null)");
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
 * 2) Il passe les v�rifications.
 * 3) (Le ma�tre attendu est "null").
 * 	  Tout va bien, les tests sont tous au vert ;)
 * 4) La diff�rence est que pour que les tests soient vert, ils doivent �tre ex�cut�s avec le second joueur et non le premier joueur.
 * 
 * Exercice 4:
 * 1) Cette classe devrait �tre une sous-classe de RuntimeException car de ce fait, on peut utiliser ces nouvelles classe dans nu bloc try/catch..
 * 
 * Exercice 5:
 * 1)On doit changer l'annotation en : @Test(expectedMaClasseErreurException.class)
 * Pour cr�er un test o� l'o a plus de place, il suffit de faire capturer 6 pok�mons diff�rents par le m�me joueur puis d'essayer de lui en faire capturer un 7�me.
 */