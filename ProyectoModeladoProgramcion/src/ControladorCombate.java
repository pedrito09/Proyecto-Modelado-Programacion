import java.util.Random;
import java.io.IOException;
import org.jdom2.JDOMException;

/**
 * Clase que servirá para manejar los datos y lógica del combate
 * 
 * @version Noviembre 2022
 * 
 * @author Gaelinho
 * @author Pedro
 * @author Andres
 */
public class ControladorCombate {
    /**
     * Atributo que contiene a los pokémon
     */
    private Pokedex pokedex;
    /**
     * Atributo que indicará que cambios seran los que maneje
     * el controlador
     */
    private VistaCombate vista;
    /**
     * Atributo que representa al usuario, con este tendremos referencia
     * a él.
     */
    private Entrenador entrenador;
    /**
     * Atributo que guardara en que nivel de juego va el entrenador
     */
    private Nivel nivelBatalla;
    /**
     * Atributo de tipo pokémon que es del entrenador
     */
    private Pokemon aliado;
    /**
     * Atributo de tipo pokémon que será el contricante del aliado
     */
    private Pokemon enemigo;
    /**
     * Atributo que indicará si se quiere acabar la partida
     */
    private boolean terminarProceso;

    /**
     * Constructor de la clase Controlador
     *
     * @param vista - Es la vista que se va a utilizar
     * @throws JDOMException
     * @throws IOException
     * @throws IllegalStateException
     */
    public ControladorCombate(VistaCombate vista) throws JDOMException, IOException, IllegalStateException{
        pokedex = new Pokedex();
        this.vista = vista;
        terminarProceso = false;
    }

    /**
     * Constructor de la clase Controlador
     *
     * @param vista - Es la vista que se va a utilizar
     * @param entrenador - Es el entrenador que se va a utilizar
     * @throws JDOMException
     * @throws IOException
     * @throws IllegalStateException
     */
    public ControladorCombate(Entrenador entrenador, VistaCombate vista) throws JDOMException, IOException, IllegalStateException{
        pokedex = new Pokedex();
        this.vista = vista;
        this.entrenador = entrenador;
        terminarProceso = false;
    }

    /**
     * Método que actualiza el entrenador
     *
     * @param entrenador objeto de tipo Entrenador con el que se actualizará
     */
    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    /**
     * Método para elegir los pokémon para la batalla
     */
    public void elegirPokemones(){
        for (int i = 0; i < 3; i++) {
            int eleccion = vista.pokemonAPokebola(pokedex.getIterador(), i);
            Pokemon agregar = pokedex.getPokemon(eleccion);
            nivelBatalla.registrarObservador(agregar);
            entrenador.asignarPokemon(agregar, i);
        }
    }

    /**
    * Método para jugar una partida
    */
    public void jugar(){
        nivelBatalla = new Nivel();
        elegirPokemones();
        while (entrenador.puedeJugar() && !terminarProceso) {
            jugarNivel();
        }

        int puntuacion = nivelBatalla.getNivel() - 1;
        vista.finDelJuego(puntuacion, getTerminar());
        entrenador.setMejorRecord(puntuacion);

        setTerminar(false);
    }

    /**
     * Método para jugar un nivel
     */
    public void jugarNivel(){
        nivelBatalla.siguienteNivel();
        vista.imprimirNivel(nivelBatalla);
        int opcionCambio = 0;
        if (nivelBatalla.getNivel() == 1) {
            aliado = entrenador.cambiarPokemon(0);
        } else {
            while (true) {
                opcionCambio = vista.hacerCambio();
                if (opcionCambio == 2) {
                    if (entrenador.puedeCambiar()) {
                        cambioPokemon();
                        break;
                    } else {
                        vista.noHayCambios();
                    }
                }else if(opcionCambio == 3){
                    terminarProceso = true;
                    break;
                } else {
                    break;
                }
            }
        }

        if(opcionCambio != 3){
            enemigo = nivelBatalla.getEnemigo();
            nivelBatalla.registrarObservador(enemigo);
            nivelBatalla.notificarObservadores(nivelBatalla.getEscenario().getTipoPotenciado());

            while (!enemigo.estaDebilitado() && entrenador.puedeJugar() && !getTerminar()) {
                System.out.println("Tu Pokemon: " + aliado);
                System.out.println("Pokemon Enemigo: " + enemigo);

                int opcion = vista.menuPelea();
                if (opcion == 2) {
                    if (entrenador.puedeCambiar()) {
                        cambioPokemon();
                        ataquePokemon(enemigo, aliado);
                        if (aliado.estaDebilitado()) {
                            vista.seHaDebilitado(aliado);
                            if (entrenador.puedeJugar()) {
                                cambioPokemon();
                            }
                        }
                    } else {
                        vista.noHayCambios();
                    }
                } else if (opcion == 1) {
                    if (aliado.getVelocidad() > enemigo.getVelocidad()) {
                        ataquePokemon(aliado, enemigo);
                        if (enemigo.estaDebilitado()) {
                            vista.seHaDebilitado(enemigo);
                        } else {
                            ataquePokemon(enemigo, aliado);
                            if (aliado.estaDebilitado()) {
                                vista.seHaDebilitado(aliado);
                                if (entrenador.puedeJugar()) {
                                    cambioPokemon();
                                }
                            }
                        }
                    } else if (aliado.getVelocidad() < enemigo.getVelocidad()) {
                        ataquePokemon(enemigo, aliado);
                        if (aliado.estaDebilitado()) {
                            vista.seHaDebilitado(aliado);
                            if (entrenador.puedeJugar()) {
                                cambioPokemon();
                            }
                        } else {
                            ataquePokemon(aliado, enemigo);
                            if (enemigo.estaDebilitado()) {
                                vista.seHaDebilitado(enemigo);
                            }
                        }
                    } else {
                        Random rdm = new Random();
                        int suerte = rdm.nextInt(2);
                        if (suerte == 0) {
                            ataquePokemon(aliado, enemigo);
                            if (enemigo.estaDebilitado()) {
                                vista.seHaDebilitado(enemigo);
                            } else {
                                ataquePokemon(enemigo, aliado);
                                if (aliado.estaDebilitado()) {
                                    vista.seHaDebilitado(aliado);
                                    if (entrenador.puedeJugar()) {
                                        cambioPokemon();
                                    }
                                }
                            }
                        } else {
                            ataquePokemon(enemigo, aliado);
                            if (aliado.estaDebilitado()) {
                                vista.seHaDebilitado(aliado);
                                if (entrenador.puedeJugar()) {
                                    cambioPokemon();
                                }
                            } else {
                                ataquePokemon(aliado, enemigo);
                                if (enemigo.estaDebilitado()) {
                                    vista.seHaDebilitado(enemigo);
                                }
                            }
                        }
                    }
                }else if(opcion == 3){
                    setTerminar(true);
                }
            }
        }

    }

    /**
    * Método que simula el ataque de un pokémon a otro
    *
    * @param atacante pokémon que ataca
    * @param receptor pokémon que recibe el ataque
    */
    public void ataquePokemon(Pokemon atacante, Pokemon receptor){
        Random rdm = new Random();
        double efectividad = rdm.nextDouble();

        int dano = atacante.getAtaque() - receptor.getDefensa();
        if (atacante.estaPotenciado()) {
            dano += 10;
        }

        if (efectividad < 0.1) {
            vista.falloPokemon(atacante, receptor);
            return ;
        }

       if(atacante.getTipo().equals("Fuego")){
           if(receptor.getTipo().equals("Agua")){
               dano = dano - ((int)((double) dano * 15 / 100));
           }else if(receptor.getTipo().equals("Planta")){
               dano = dano + ((int)((double) dano * 15 / 100));
           }
       }else if(atacante.getTipo().equals("Agua")){
           if(receptor.getTipo().equals("Planta")){
               dano = dano - ((int)((double) dano * 15 / 100));
           }else if(receptor.getTipo().equals("Fuego")){
               dano = dano + ((int)((double) dano * 15 / 100));
           }
       }else if(atacante.getTipo().equals("Planta")){
           if(receptor.getTipo().equals("Fuego")){
               dano = dano - ((int)((double) dano * 15 / 100));
           }else if(receptor.getTipo().equals("Agua")){
               dano = dano + ((int)((double) dano * 15 / 100));
           }
       }
       if (efectividad > 0.95) {
           vista.golpeCritico(atacante);
           dano = dano * 2;
       }
       vista.ataquePokemon(atacante, receptor, dano);
       receptor.recibeDano(dano);
    }

    /**
     * Método para cambiar de pokémon
     */
    public void cambioPokemon(){
        Pokemon antes = aliado;
        while (antes.equals(aliado) || aliado.estaDebilitado()) {
            int nuevo = vista.menuPokemon(entrenador.getIteratorPokemon());
            aliado = entrenador.cambiarPokemon(nuevo);
            if (aliado.estaDebilitado()) {
                vista.estaDebilitado();
            } else if (antes.equals(aliado)) {
                vista.mismoPokemon();
            }
        }
    }

    /**
     * Método para obtener terminarProceso
     *
     * @return terminarProceso - true si el usuario quiere terminar el proceso
     *                         - false si el usuario no quiere terminar el proceso
     */
    public boolean getTerminar(){
        return  terminarProceso;
    }

    /**
     * Método para cambiar el valor de terminarProceso
     * @param terminarProceso - true si el usuario quiere terminar el proceso
     *                        - false si el usuario no quiere terminar el proceso
     */
    public void setTerminar(boolean terminarProceso){
        this.terminarProceso = terminarProceso;
    }
}
