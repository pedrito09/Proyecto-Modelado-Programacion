/**
* Clase que simula un escenario en el bosque del olvido
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public class BosqueOlvido implements Escenario{

    /** 
     * Atributo que guarda en un String el tipo de pokémon 
     * que será beneficiado en la batalla.      
     */
    private String tipoPotenciado;

    /**
     * Constructor predeterminado de la clase BosqueOlvido
     */
    public BosqueOlvido(){
        this.tipoPotenciado = "Planta";
    }

    /**
     * Método para ver la descripción del escenario
     *
     * @return Cadena con la descripción del escenario
     */
    @Override
    public String getDescripcion(){
        return "\nBosque del olvido, cuidado donde avanzas, podrías perderte  \n"+
        "Los pokémones de planta serán mas fuertes";
    }

    /**
     * Método para ver el tipo de pokémon potenciado por el escenario
     *
     * @return tipoPotenciado - Es el tipo de elemento que potenciará a los pokémones en el escenario
     */
    @Override
    public String getTipoPotenciado(){
        return tipoPotenciado;
    }

}
