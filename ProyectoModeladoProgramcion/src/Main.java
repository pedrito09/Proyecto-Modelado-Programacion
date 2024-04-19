/**
 * Clase que inicia la interaccion del usuario en el juego
 *
 * @author Pedro
 * @author Gaelinho
 * @author Andres
 * @version Noviembre 2022
 */
public class Main {

    /**
    * Método principal del programa
    *
    * @param args Argumentos en la línea de comandos
    */
    public static void main(String[] args) {
        try {
            VistaSesion vistaSesion = new VistaSesion();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
