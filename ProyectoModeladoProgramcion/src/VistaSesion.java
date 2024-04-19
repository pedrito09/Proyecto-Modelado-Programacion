import java.util.Iterator;
/**
* Clase que manejará los datos del usuario para la sesion del usuario
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public class VistaSesion extends Vista {
    /**
    * Controlador el programa que interactúa con esta vista
    */
    private ControladorSesion controladorSesion;

    /**
    * Constructor de la vista del usuario
    */
    public VistaSesion(){
        try{
            this.controladorSesion = new ControladorSesion(this);
        }catch(Exception e) {
            e.printStackTrace();
        }
        controladorSesion.ejecutar();
    }

    /**
    * Imprime las instrucciones del juego
    */
    public void instrucciones(){
        System.out.println("\nInstrucciones del juego para cada partida:");
        System.out.println("- Primero elegiras 3 Pokemones con los que " +
                            "combatiras durante toda la partida");
        System.out.println("- Recuerda que tu primer elección será tu Pokemon inicial");
        System.out.println("- Deberás derrotar a la mayor cantidad de Pokemones que " +
                            "apareceran a lo largo de los niveles (1 por nivel)");
        System.out.println("- Considera los potenciadores de los niveles");
        System.out.println("- Toma en cuenta que cierto tipo de Pokemon es más efectivo frente a otros");
        System.out.println("\t-> Fuego más efectivo contra Planta");
        System.out.println("\t-> Planta más efectivo contra Agua");
        System.out.println("\t-> Agua más efectivo contra Fuego");
        System.out.println("- En tu turno podrás atacar y cambiar tu Pokemon, " +
                            "puede que haya alguno que te beneficie más");
        System.out.println("- El juego depende mucho de la suerte, así que reza " +
                            "por tener mucha");
        System.out.println("- Intenta superar tu récord");
    }

    /**
    * Imprime el menú inicial y pide al usuario elegir una opción
    *
    * @return int - Opción elegida por el usuario
    */
    public int menuInicial(){
        System.out.println("\nBienvenido a Tour Pokemon\n");
        System.out.println("1. Crear sesión");
        System.out.println("2. Iniciar sesión");
        System.out.println("0. Salir");
        return getInt(0, 2);
    }

    /**
    * Imprime el menú del juego y pide al usuario elegir una opción
    *
    * @return int - Opción elegida por el usuario
    */
    public int menuJuego(){
        System.out.println("1. Jugar partida");
        System.out.println("2. Ver mi récord");
        System.out.println("3. Checar info de un Pokemon");
        System.out.println("0. Cerrar Sesión");
        return getInt(0, 3);
    }

    /**
    * Pide al usuario escribir su nombre
    *
    * @return nombre - Nombre escrito por el usuario
    */
    public String ingresarNombre(){
        System.out.println("Entrenador, ingresa tu nombre");
        String nombre = getString();
        return nombre;
    }

    /**
    * Pide al usuario escribir su contraseña
    *
    * @return contrasena - Contraseña escrita por el usuario
    */
    public String ingresarContrasena(){
        System.out.println("Entrenador, ingresa tu contraseña");
        String contrasena = getString();
        return contrasena;
    }

    /**
    * Imprime un menú de Pokemon a partir de un iterador
    * y le pide al usuario seleccionar uno
    *
    * @param iterador - Iterador de una lista o arreglo de Pokemon
    *
    * @return int - Opción del usuario
    */
    public int menuPokemon(Iterator iterador){
        int i = 0;
        while (iterador.hasNext()) {
            Pokemon p = (Pokemon) iterador.next();
            String descripcion = "" + (i++);
            descripcion += ". " + p.getNombre();
            if (p.estaDebilitado()) {
                descripcion += " (DEBILITADO)";
            }
            System.out.println(descripcion);
        }
        return getInt(0, i-1);
    }


    /**
     * Método para llamar al Método menuPokemon y obtener la información de un pokemon
     * 
     * @param it - iterador de la lista de pokemon
     * @return int - opción del usuario
     */
    public int pokemonDePokedex(Iterator it){
        System.out.println("Selecciona el pokemon del que deseas la información:");
        return menuPokemon(it);
    }

    /**
     * Método para mostrarle al usuario su record actual
     *
     * @param entrenador - entrenador del que se quiere mostrar el record
     */
    public void mostrarRecord(Entrenador entrenador){
        System.out.println("Tu récord personal es: " + entrenador.getMejorRecord() + "\n");
    }

    /**
     * Método para revisar la información de un pokémon
     *
     * @param pokemon - Pokémon del que se quiere mostrar la información
     */
    public void checarInfoPokemon(Pokemon pokemon){
        System.out.println(pokemon + "\n");
    }

    /**
     * Método para mostrarle al usuario un mensaje correspondiente a la situación
     * 
     * @param opcion - opción del usuario
     */
    public void mostrarMensaje(int opcion){
        switch (opcion) {
            case 1:
                System.out.println("Sesión creada e iniciada correctamente\n");
                break;
            case 2:
                System.out.println("No puedes usar este nombre, ya le pertenece a alguien más\n");
                break;
            case 3:
                System.out.println("Sesión iniciada correctamente\n");
                break;
            case 4:
                System.out.println("Nombre y/o contraseña incorrectos\n");
                break;
            case 5:
                System.out.println("Adios");
                break;
            default:
                break;
        }

    }
}
