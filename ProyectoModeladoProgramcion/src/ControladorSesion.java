import java.util.ArrayList;
import java.io.IOException;
import org.jdom2.JDOMException;

/**
 * Clase que servirá para manejar los datos y lógica del incio de sesión
 * 
 * @version Noviembre 2022
 * 
 * @author Gaelinho
 * @author Pedro
 * @author Andres
 */
public class ControladorSesion {
    /**
     * Atributo que contiene a los pokémon
     */
    private Pokedex pokedex;
    /**
     * Atributo que contendra la referencia a los entrenadores
     */
    private ArrayList<Entrenador> entrenadores;
    /**
     * Atributo tipo VistaSesion que utilizaremos
     */
    private VistaSesion vista;
    /**
     * Atributo que servirá para crear mas usuarios
     */
    private Entrenador entrenador;
    /**
     * Atributo que sirve para la referencia a la base de datos
     * de entrenadores.
     */
    private BDEntrenadores bdEntrenadores;
    /**
     * Atributo con el que manejaremos los datos del usuario para el combate
     */
    private ControladorCombate controladorCombate;
    /**
     * Atributo del cual obtendremos las respuestas del usuario
     */
    private VistaCombate vistaCombate;
    /**
     * Atributo tipo boolean que indica si la sesion fue iniciada
     */
    private boolean sesionIniciada;

    /**
     * Constructor de la clase Controlador
     *
     * @param vista - Es la vista que se va a utilizar
     * @throws JDOMException
     * @throws IOException
     * @throws IllegalStateException
     */
    public ControladorSesion(VistaSesion vista) throws JDOMException, IOException, IllegalStateException{
        pokedex = new Pokedex();
        this.vista = vista;
        this.vistaCombate = new VistaCombate();
        sesionIniciada = false;
        bdEntrenadores = new BDEntrenadores();
        entrenadores = bdEntrenadores.getEntrenadores();
    }

    /**
     * Método para crear un nuevo usuario
     *
     * @return boolean - True sí se ha creado el usuario correctamente
     *                 - False sí el usuario ya existe
     */
    public boolean crearSesion(){
        String nombre = vista.ingresarNombre();
        String contrasena = vista.ingresarContrasena();
        entrenador = new Entrenador(nombre, contrasena);
        for (Entrenador entrenador : entrenadores) {
            if(entrenador.getNombre().equals(nombre)){
                entrenador = null;
                return false;
            }
        }

        entrenadores.add(entrenador);
        try {
            bdEntrenadores.actualizarBDEntrenadores(entrenadores);
        } catch (IllegalStateException | JDOMException | IOException e) {
            e.printStackTrace();
        }
        return true;

    }

    /**
     * Método para iniciar sesión
     *
     * @return boolean - True si se ha iniciado sesión correctamente
     *                 - False si el usuario no existe
     */
    public boolean iniciarSesion(){
        String nombre = vista.ingresarNombre();
        String contrasena = vista.ingresarContrasena();
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getNombre().equals(nombre) && entrenador.getContrasena().equals(contrasena)) {
                this.entrenador = entrenador;
                return true;
            }
        }
        return false;
    }

    /**
     * Método para cerrar sesión
     */
    public void cerrarSesion(){
        entrenador = null;
    }

    /**
     * Método para ayudarle al método mostrarRecord de VistaUsuario pasandole un objeto entrenador
     */
    public void mostrarRecord(){
        vista.mostrarRecord(entrenador);
    }

    /**
     * Método para ayudale al método checarInfoPokemon de VistaUsuario pasandole un pokémon
     */
    public void checarInfoPokemon(){
        int opcion = vista.pokemonDePokedex(pokedex.getIterador());
        Pokemon pokemon = pokedex.getPokemon(opcion);
        vista.checarInfoPokemon(pokemon);
    }

    /**
     * Método para obtener el entrenador que va a jugar
     * 
     * @return Entrenador - Es el entrenador que va a jugar
     */
    public Entrenador getEntrenador(){
        return this.entrenador;
    }

    /**
    * Ejecuta el inicio de la vista junto con el programa
    */
    public void ejecutar(){
        int menuInicial = -1;
        vista.instrucciones();
        do {
            menuInicial = vista.menuInicial();
            switch (menuInicial) {
                case 1:
                    sesionIniciada = this.crearSesion();
                    if (sesionIniciada) {
                        vista.mostrarMensaje(1);

                        try{
                            this.controladorCombate = new ControladorCombate(this.getEntrenador(), vistaCombate);
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        vista.mostrarMensaje(2);
                    }
                    break;
                case 2:
                    sesionIniciada = this.iniciarSesion();
                    if (sesionIniciada) {
                        vista.mostrarMensaje(3);

                        try{
                            this.controladorCombate = new ControladorCombate(this.getEntrenador(), vistaCombate);
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        vista.mostrarMensaje(4);
                    }
                    break;
                case 0:
                    sesionIniciada = false;
                    vista.mostrarMensaje(5);
                    break;
                default:
                    break;
            }

            if (menuInicial != 0 && sesionIniciada) {
                int menuJuego;
                do {
                    menuJuego = vista.menuJuego();
                    switch (menuJuego) {
                        case 1:
                            controladorCombate.jugar();
                            try {
                                bdEntrenadores.actualizarBDEntrenadores(entrenadores);
                            } catch (IllegalStateException | JDOMException | IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            this.mostrarRecord();
                            break;
                        case 3:
                            this.checarInfoPokemon();
                            break;
                        case 0:
                            this.cerrarSesion();
                            break;
                        default:
                            break;
                    }
                } while (menuJuego != 0);
            }
        } while (menuInicial != 0);

    }
}
