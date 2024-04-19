/**
* Interfaz que define los métodos que debe implementar un escenario.
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public interface Escenario {

    /**
     * Método para ver la descripción del escenario
     */
    public String getDescripcion();

    /**
     * Método para ver el tipo de pokémon potenciado por el escenario
     * 
     * @return String 
     */
    public String getTipoPotenciado();
}
