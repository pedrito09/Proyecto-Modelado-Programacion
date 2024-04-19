import java.util.Iterator;
/**
* Clase que manejará los datos del usuario para el combate
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public class VistaCombate extends Vista {
    /**
    * Controlador el programa que interactúa con esta vista
    */
    private ControladorCombate controladorCombate;

    /**
    * Constructor de la vista del usuario
    */
    public VistaCombate(){
        try{
            this.controladorCombate = new ControladorCombate(this);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * Pregunta al usuario si quiere hacer algún cambio
    * antes de su próximo combate
    *
    * @return int - Opción del usuario
    */
    public int hacerCambio(){
        System.out.println("¿Quieres hacer un cambio?");
        System.out.println("1. Seguir luchando");
        System.out.println("2. Cambiar pokemon");
        System.out.println("3. Terminar partida");
        return getInt(1,3);
    }

    /**
    * Imprime al usuario que no tiene cambios disponibles
    */
    public void noHayCambios(){
        System.out.print("Es tu único pokemon disponible, ");
        System.out.println("no tienes alguno para cambiar");
    }

    /**
    * Imprime un menú de Pokemon a partir de un iterador
    * y le pide al usuario seleccionar uno
    *
    * @param iterador - Iterador de una lista o arreglo de Pokemon
    *
    * @return int - Opción del usuario
    */
    public int menuPokemon(Iterator iterador){
        int i = 0;
        while (iterador.hasNext()) {
            Pokemon p = (Pokemon) iterador.next();
            String descripcion = "" + (i++);
            descripcion += ". " + p.getNombre();
            if (p.estaDebilitado()) {
                descripcion += " (DEBILITADO)";
            }
            System.out.println(descripcion);
        }
        return getInt(0, i-1);
    }

    /**
    * Pide al usuario elegir que Pokemon quiere elegir para la pelea
    *
    * @param it -Iterador de una lista de Pokemon
    * @param posicion - Posición del entrenador en que se guardará el Pokemon
    *
    * @return int - Opción del usuario
    */
    public int pokemonAPokebola(Iterator it, int posicion){
        System.out.println("Elige a tu pokemon " + posicion + ":\n");
        return menuPokemon(it);
    }

    /**
     * Método para imprimir las acciones que debe realizar el usuario en un combate contra un pokémon
     *
     * @return int - opción del usuario
     */
    public int menuPelea(){
        System.out.println("1. Atacar");
        System.out.println("2. Cambiar Pokemon");
        System.out.println("3. Terminar partida");
        return getInt(1, 3);
    }

    /**
     * Método para mostrarle al usuario el proceso de un ataque a un pokémon
     *
     * @param atacante - pokémon que ataca
     * @param receptor - pokémon que recibe el ataque
     * @param dano - daño que recibe el pokémonreceptor
     */
    public void ataquePokemon(Pokemon atacante, Pokemon receptor, int dano){
        System.out.print(atacante.getNombre() + " hizo " + dano);
        System.out.println(" de daño a " + receptor.getNombre());
    }

    /**
    * Imprime que un pokémon falló su ataque hacia otro
    *
    * @param atacante - Pokémon que atacó y falló
    * @param receptor - Pokémon que iba a recibir daño
    */
    public void falloPokemon(Pokemon atacante, Pokemon receptor){
        System.out.print(atacante.getNombre() + " ataca pero ha fallado. ");
        System.out.println(receptor.getNombre() + " no ha recibido daño.");
    }

    /**
    * Imprime que un pokémon falló su ataque hacia otro
    *
    * @param atacante - Pokémon que atacó y falló
    */
    public void golpeCritico(Pokemon atacante){
        System.out.println(atacante.getNombre() + " dió un golpe CRITICO.");
    }

    /**
     * Método para mostarle al usuario que un pokémon se ha debilitado
     *
     * @param pokemon - Pokémon que se debilito
     */
    public void seHaDebilitado(Pokemon pokemon){
        System.out.println(pokemon.getNombre() + " se ha debilitado");
    }

    /**
     * Método para mostrarle al usuario que el pokémon que quiere cambiar es el que está en el combate
     */
    public void mismoPokemon(){
        System.out.println("Elige un Pokemon diferente al que esta en combate");
    }

    /**
     * Método para mostrarle al usuario que elija un pokémon que no este debilitado
     */
    public void estaDebilitado(){
        System.out.println("Elige un Pokemon que no este debilitado");
    }

    /**
    * Imprime las especificaciones de un nivel
    *
    * @param nivel - Nivel a imprimir
    */
    public void imprimirNivel(Nivel nivel){
        System.out.println("\nNivel: " + nivel.getNivel());
        System.out.println(nivel.getEscenario().getDescripcion());
        System.out.println("Un " + nivel.getEnemigo().getNombre() +
                            " se cruza en tu camino\n");
    }

    /**
    * Imprime el fin del juego y la puntuación obtenida
    *
    * @param puntuacion -Puntaje final de la partida
    * @param terminar - Indica si la partida se termino antes de perder
    */
    public void finDelJuego(int puntuacion, boolean terminar){
        if (terminar) {
            System.out.println("Terminaste la partida antes de tiempo, que decepción.");
        } else {
            System.out.println("\nTodos tus Pokemon se han debilitado.");
        }
        System.out.println("Tu puntuación final fue: " + puntuacion);
        System.out.println("FIN DEL JUEGO\n");
    }

}
