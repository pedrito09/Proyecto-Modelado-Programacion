import java.util.Iterator;
/**
* Clase que simula un entrenador que utiliza pokemones para combatir
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public class Entrenador {

    /**
    * Cadena que denota el nombre del entrenador
    */
    private String nombre;

    /**
    * Cadena que denota la contraseña del entrenador
    */
    private String contrasena;

    /**
    * Entero que denota el mejor record del entrenador
    */
    private int mejorRecord;

    /**
    * Arreglo de pokémon que representan las pokebolas del entrenador
    */
    private Pokemon[] pokebolas;

    /**
    * Pokémon del entrenador que se encuentra peleando
    */
    private Pokemon pokemonEnCombate;

    /**
     * Constructor predeterminado de la clase Entrenador
     */
    public Entrenador (){
        this.pokebolas = new Pokemon[3];
    }

    /**
     * Constructor por parametro de la clase Entrenador
     *
     * @param nombre - Nombre del entrenador
     */
    public Entrenador (String nombre, String contrasena){
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.pokebolas = new Pokemon[3];
    }

    /**
     * Método para obtener el nombre del entrenador
     * 
     * @return nombre - Nombre del entrenador
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Método para cambiar el nombre del entrenador
     * 
     * @param nombre - Nuevo nombre del entrenador
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Método para obtener la contraseña del entrenador
     * 
     * @return contrasena - Contraseña del entrenador
     */
    public String getContrasena(){
        return contrasena;
    }

    /**
     * Método para cambiar la contraseña del entrenador
     * 
     * @param contrasena - Nueva contraseña del entrenador
     */
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    /**
    * Asigna un pokémona una pokebola
    *
    * @param pokemon - Pokémon a guardar
    * @param posicion - Lugar donde se guardara
    */
    public void asignarPokemon(Pokemon pokemon, int posicion){
        pokebolas[posicion] = pokemon;
    }

    /**
    * Cambia el pokémon que se encuentra peleando
    *
    * @return pokemonEnCombate - Nuevo pokémon que combatirá
    */
    public Pokemon cambiarPokemon(int posicion){
        if (posicion < 3 && posicion >= 0){
            pokemonEnCombate = pokebolas[posicion];
        }

        return pokemonEnCombate;
    }

    /**
    * Devuelve un iterador de las pokebolas del entrenador
    *
    * @return Iterator - Iterador del arreglo de pokemones
    */
    public Iterator getIteratorPokemon(){
        return new PokemonIterator();
    }

    /**
     * Clase interna que implementa la interfaz Iterator para recorrer el arreglo de los pokebolas
     */
    private class PokemonIterator implements Iterator {
        /**
        * Indica la posición en la que se encuentra el iterador
        */
        private int posicion = 0;

        /**
         * Método que comprueba que haya el siguiente pokémon en las pokebolas del entrenador
         * 
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            if(posicion >= pokebolas.length || pokebolas[posicion] == null) {
                return false;
            } else {
                return true;
            }
        }

        /**
         * Método que regresa el siguiente pokémon en las pokebolas del entrenador
         * 
         * @return Pokemon - Siguiente pokémon en las pokebolas del entrenador
         */
        @Override
        public Object next() {
            Pokemon Pokemon = pokebolas[posicion];
            posicion++;
            return Pokemon;
        }

        /**
         * Método que comprueba que haya un pokémon anterior
         * 
         * @return boolean - True si hay un pokémon anterior
         *                 - False si no hay un pokémon anterior
         */
        public boolean hasPrevious() {

            if(posicion < 0 || pokebolas[posicion-1] == null ) {
                return false;
            } else {
                return true;
            }
        }

        /**
         * Método que regresa el pokémon anterior
         * 
         * @return Pokemon - Pokémon anterior
         */
        public Object previous() {
            Pokemon Pokemon = pokebolas[posicion-1];
            posicion--;
            return Pokemon;
        }

        /**
         * Método que elimina el pokémon actual
         */
        public void remove() {
            if(posicion < 0 || pokebolas[posicion] == null || posicion >= pokebolas.length) {
                throw new IllegalStateException("No se puede eliminar el elemento");
            }
            if(pokebolas[posicion] != null) {
                for(int i = posicion; i < (pokebolas.length - 1); i++) {
                    pokebolas[i] = pokebolas[i+1];
                }
                pokebolas[pokebolas.length-1] = null;
            }

        }
    }

    /**
     * Método para obtener el mejor record actual
     * 
     * @return mejorRecord - Mejor record
     */
    public int getMejorRecord() {
        return mejorRecord;
    }

    /**
     * Método para cambiar el mejor record
     * 
     * @param mejorRecord - Nuevo mejor record
     */
    public void setMejorRecord(int mejorRecord) {
        if (this.mejorRecord < mejorRecord) {
            this.mejorRecord = mejorRecord;
        }
    }

    /**
    * Indica si el entrenador aun puede jugar
    *
    * @return   true    -   sí aun tiene algún pokémon vivo
    *           false   -   sí todos sus pokémon estan debilitados
    */
    public boolean puedeJugar(){
        return !pokebolas[0].estaDebilitado() ||
            !pokebolas[1].estaDebilitado() ||
            !pokebolas[2].estaDebilitado();
    }

    /**
    * Indica si el entrenador tiene otro pokémon disponible
    * además del que está en combate
    *
    * @return   true    -   sí aun puede cambiar
    *           false   -   sí no tiene un cambio disponible
    */
    public boolean puedeCambiar(){
        Iterator it = getIteratorPokemon();
        while (it.hasNext()) {
            Pokemon pokemon = (Pokemon) it.next();
            if (!pokemon.equals(pokemonEnCombate)) {
                if (!pokemon.estaDebilitado()) {
                    return true;
                }
            }
        }
        return false;
    }

}
