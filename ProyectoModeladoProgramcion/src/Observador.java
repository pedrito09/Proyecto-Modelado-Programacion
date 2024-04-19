/**
 * Inteface que nos ayudará a implementar el patron Observer
 *
 * @author Gaelinho
 * @author Pedro
 * @author Andres
 * @version Noviembre 2022
 *
 */
public interface Observador {

    /**
     * Método que actualiza toda la info de nuestros Observadores
     * 
     * @param mensaje - Mensaje que iremos actualizando conforme se mande a llamar
     */
    public void actualizar(String mensaje);

}
