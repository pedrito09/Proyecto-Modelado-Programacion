import java.util.LinkedList;
import java.util.Random;
/**
 * Clase que simulará los niveles por los que el usuario
 * deberá ganarle a los pokémon
 *
 * @version Noviembre 2022
 * @author Gaelinho
 * @author Pedro
 * @author Andres
 */
public class Nivel implements Sujeto{

    /**
    * Lista de observadores
    */
    private LinkedList<Pokemon> listaObservers=new LinkedList<>();

    /**
    * Indica el número de nivel
    */
    private int nivel;

    /**
    * Pokémon enemigo del nivel
    */
    private Pokemon enemigo;

    /**
    * Pokedex con todos los pokémon
    */
    private Pokedex pokedex;

    /**
    * Escenario donde se encuentra el nivel
    */
    private Escenario escenario;

    /**
    * Constructor del nivel
    */
    public Nivel(){
        try {
            pokedex = new Pokedex();
            nivel = 0;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para avanzar al siguiente nivel
     */
    public void siguienteNivel(){
        nivel++;
        escenario = escenarioAzar();
        enemigo = pokedex.getRandomPokemon();
    }

    /**
    * Devuelve el enemigo actual
    *
    * @return Enemigo actual a vencer
    */
    public Pokemon getEnemigo(){
        return enemigo;
    }

    /**
    * Devuelve el nivel actual
    *
    * @return Nivel actual
    */
    public int getNivel(){
        return nivel;
    }

    /**
    * Devuelve el escenario del nivel
    *
    * @return Escenario actual
    */
    public Escenario getEscenario(){
        return escenario;
    }

    @Override
    /**
     * Método que registra pokemones a la lista de pokémon que hay en
     * en la batalla
     * 
     * @param observador - Objeto de clase Pokemon que será agregado a la lista
     */
    public void registrarObservador(Observador observador) {
        listaObservers.add((Pokemon)observador);
    }

    @Override
    /**
     * Método que remueve el pokémon indicado de la lista
     * 
     * @param observador - Objeto de clase pokémon que será removido de la lista
     */
    public void quitarObservador(Observador observador) {
        listaObservers.remove((Pokemon)observador);

    }

    @Override
    /**
     * Método que notifica a los observadores sobre algún cambio
     *
     * @param mensaje que servirá para notificar a los observers
     */
    public void notificarObservadores(String mensaje) {
        for(Pokemon po: listaObservers){
            po.actualizar(mensaje);
        }
    }

    /**
     * Método que elige de manera al azar un escenario posible
     * si el número generado al azar es:
     *
     * suerte==0 se genera el escenario ladera lava
     * suerte==1 se genera el escenario bosque olvido
     * suerte==2 se genera el escenario templo marino
     * en cualquier otro caso regresa null
     *
     * @return Escenario - Escenario generado al azar
     */
    public Escenario escenarioAzar(){
        Random random=new Random();
        int suerte= random.nextInt(3);

        if(suerte==0)
            return new LaderaLava();
        else if(suerte==1)
            return new BosqueOlvido();
        else if(suerte==2)
            return new TemploMarino();

        return null;
    }

}
