/**
* Clase que simula un escenario en unas laderas de lava
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public class LaderaLava implements Escenario{
    /** 
     * Atributo que guarda en un String el tipo de pokémon
     * que será beneficiado en la batalla.      
     */
    private String tipoPotenciado;

    /**
     * Constructor de la clase LaderaLava
     */
    public LaderaLava(){
        this.tipoPotenciado = "Fuego";
    }

    /**
     * Método para ver la descripción del escenario
     *
     * @return String - Cadena con la descripción del escenario
     */
    @Override
    public String getDescripcion(){
        return "\nLaderas de lava, cuidado donde pisas, podrías quemarte\n"+
        "Los pokemones de fuego seran mas fuertes";
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
