/**
 * Interface que ayuda a implementar el patron observer
 *
 * @author Gaelinho
 * @author Pedro
 * @author Andres
 * @version Noviembre 2022
 *
 */
public interface Sujeto {

    /**
     * Método que nos permite agregar observadores a su lista
     * correspondientes
     *
     * @param observador - Observador que sera agregado a la lista
     */
    public void registrarObservador(Observador observador);

    /**
     * Método que nos permite quitar observadores de su lista
     *
     * @param observador - Observador que será quitado de la lista
     */
    public void quitarObservador(Observador observador);

    /**
     * Método que notificara y hará los cambios correspondientes
     * cada que haya un mensaje nuevo
     *
     * @param mensaje - String que notificará a los observers cuando
     * haya modificaciones que agregar.
     */
    public void notificarObservadores(String mensaje);

}
