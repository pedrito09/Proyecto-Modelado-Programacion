/**
* Clase que simula un escenario en un templo marino
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public class TemploMarino implements Escenario{

    private String tipoPotenciado;

    /**
     * Constructor de la clase TemploMarino
     */
    public TemploMarino(){
        this.tipoPotenciado = "Agua";
    }

    /**
     * Método para ver la descripción del escenario
     *
     * @return String - Cadena con la descripción del escenario
     */
    @Override
    public String getDescripcion(){
        return "\nTemplo Marino, cuidado donde te metes, podrías ahogarte\n"+
        "Los pokemones de agua seran mas fuertes";
    }

    /**
    * Método para ver el tipo de pokémon potenciado por el escenario
    *
    * @return tipoPotenciado - Es el tipo de elemento que potenciará a los pokémon en el escenario
    */
    @Override
    public String getTipoPotenciado(){
        return tipoPotenciado;
    }

}
