
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.io.IOException;
import org.jdom2.JDOMException;

/**
* Clase que funciona como base de datos de los pokémon
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public class Pokedex {

    /**
    * Arreglo que almacena los pokémon
    */
    private ArrayList<Pokemon> pokemonBD;

    /**
    * Constructor del Pokedex
    */
    public Pokedex() throws JDOMException, IOException, IllegalStateException{
        Parser parser = new Parser();
        pokemonBD = parser.parsePokemon();
    }

    /**
    * Devuelve el pokémon en la posición dada
    *
    * @param posicion - Posicion del pokémon deseado en la lista
    *
    * @return pokemon - Pokémon en dicha posisición
    */
    public Pokemon getPokemon(int posicion){
        Pokemon pokemon = pokemonBD.get(posicion);
        return (Pokemon) pokemon.clone();
    }

    /**
    * Devuelve un pokémon al azar de la colección
    *
    * @return pokemon - Pokémon aleatorio
    */
    public Pokemon getRandomPokemon(){
        Random rdm = new Random();
        int posicion = rdm.nextInt(pokemonBD.size());
        Pokemon random = pokemonBD.get(posicion);
        return (Pokemon) random.clone();
    }

    /**
    * Devuelve un Iterador de la lista de Pokemon
    *
    * @return Iterator - Iterador de la lista de Pokemon
    */
    public Iterator getIterador(){
        return pokemonBD.iterator();
    }
}
