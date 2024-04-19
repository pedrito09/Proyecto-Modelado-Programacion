import java.io.IOException;
import java.util.ArrayList;
import org.jdom2.JDOMException;

/**
 * Clase para la gestión de los entrenadores
 * 
 * @version Noviembre 2022
 * @author Gaelinho
 * @author Pedro
 * @author Andres
 */
public class BDEntrenadores {

    /**
     * Lista que guardara los usuarios para después hacer 
     * lo necesario para guardarla en el xml
     */
    private ArrayList<Entrenador> listaEntrenadores;

    /**
     * Constructor de la clase BDEntrenadores
     * El constructor lo que hace es descargar la lista de los entrenadores.
     * 
     * @throws IllegalStateException
     * @throws JDOMException
     * @throws IOException
     */
    public BDEntrenadores() throws IllegalStateException, JDOMException, IOException{
        this.listaEntrenadores=new Parser().parseEntrenadores();
    }   

    /**
     * Método para actualizar el banco de datos de los entrenadores
     * 
     * @param listaEntrenadores - Es la lista de los entrenadores que se va a actualizar
     * @throws IllegalStateException
     * @throws JDOMException
     * @throws IOException
     */
    public void actualizarBDEntrenadores(ArrayList<Entrenador> listaEntrenadores) throws IllegalStateException, JDOMException, IOException{
        this.listaEntrenadores = listaEntrenadores;
        new Parser().actualizarBDEntrenadores(listaEntrenadores);
    }

    /**
     * Método para obtener el banco de datos de los entrenadores
     * 
     * @return listaEntrenadores - Es la lista de los entrenadores
     */
    public ArrayList<Entrenador> getEntrenadores() {
        return listaEntrenadores;
    }

}
