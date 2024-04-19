/**
* Clase que simula un pokémon
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public class Pokemon implements Cloneable, Observador {

     /**
     * Cadena que denota el nombre del pokémon
     */
     private String nombre;

     /**
     * Cadena que denota el tipo del pokémon
     */
     private String tipo;

     /**
     * Entero que denota la vida del pokémon
     */
     private int vida;

     /**
     * Entero que denota el ataque del pokémon
     */
     private int ataque;

     /**
     * Entero que denota la defensa del pokémon
     */
     private int defensa;

     /**
     * Entero que denota la velocidad del pokémon
     */
     private int velocidad;

     /**
     * Cadena que denota el tipo de pokémon potenciado por el escenario
     */
     private String tipoPotenciado;

     /**
     * Constructor de un pokémon
     */
     public Pokemon(){

     }

     /**
     * Devuelve el nombre del pokémon
     *
     * @return nombre - Nombredel pokémon
     */
     public String getNombre(){
         return nombre;
     }

     /**
     * Establece el nombre del pokémon
     *
     * @param nombre - Nombre del pokémon
     */
     public void setNombre(String nombre){
         this.nombre = nombre;
     }

     /**
     * Devuelve la vida del pokémon
     *
     * @return Vida - Vida del pokémon
     */
     public int getVida(){
         return vida;
     }

     /**
     * Establece la vida del pokemon
     *
     * @param vida - Vida del pokemon
     */
     public void setVida(int vida){
         this.vida = vida;
     }

     /**
     * Afecta la vida del pokemon a partir de un daño recibido
     *
     * @param dano - Daño recibido
     */
     public void recibeDano(int dano){
         vida -= dano;
         if (vida < 0) {
             vida = 0;
         }
     }

     /**
     * Devuelve el tipo del pokémon
     *
     * @return tipo - Tipo del pokémon
     */
     public String getTipo(){
         return tipo;
     }

     /**
     * Establece el tipo del pokémon
     *
     * @param tipo - Tipo del pokémon
     */
     public void setTipo(String tipo){
         this.tipo = tipo;
     }

     /**
     * Devuelve el ataque del pokémon
     *
     * @return ataque - Ataque del pokémon
     */
     public int getAtaque(){
         return ataque;
     }

     /**
     * Establece el ataque del pokémon
     *
     * @param ataque - Ataque del pokémon
     */
     public void setAtaque(int ataque){
         this.ataque = ataque;
     }

     /**
     * Devuelve la defensa del pokémon
     *
     * @return defensa - Defensa del pokémon
     */
     public int getDefensa(){
         return defensa;
     }

     /**
     * Establece la defensa del pokémon
     *
     * @param defensa - Defensa del pokémon
     */
     public void setDefensa(int defensa){
         this.defensa = defensa;
     }

     /**
     * Devuelve la velocidad del pokémon
     *
     * @return velocidad - Velocidad del pokémon
     */
     public int getVelocidad(){
         return velocidad;
     }

     /**
     * Establece la velocidad del pokémon
     *
     * @param velocidad - Velocidad del pokémon
     */
     public void setVelocidad(int velocidad){
         this.velocidad = velocidad;
     }

     /**
     * Indica si el pokemon esta debilitado.
     * Es decir, si su vida es mayor a 0.
     *
     * @return  true    -   si su vida es igual a cero
     *          false   -   si su vida es mayor a cero
     */
     public boolean estaDebilitado(){
         return vida == 0;
     }

     /**
     * Indica si el pokémon esta potenciado
     *
     * @return  true    -   si esta potenciado
     *          false   -   si no lo esta
     */
     public boolean estaPotenciado(){
         return tipoPotenciado.equals(this.getTipo());
     }

     @Override
     /** 
      * Método que actualiza el tipo potenciado y servirá 
      * para comprobar si es igual al del pokémon para poteniciarlo
      */
     public void actualizar(String mensaje){
         tipoPotenciado = mensaje;
     }

     @Override
     /** 
      * Método que imprime la información del pokémon
      *
      * @return  info - Información del pokémon
      */
     public String toString(){
         String info = nombre;
         info += "\n\tTipo: " + tipo;
         info += "\n\tVida: " + vida;
         info += "\n\tAtaque: " + ataque;
         info += "\n\tDefensa: " + defensa;
         info += "\n\tVelocidad: " + velocidad;
         return info;
     }

     /**
     * Indica si este pokémon es igual a otro
     *
     * @param aComparar  - Pokémon a comparar
     *
     * @return  true    -   si son iguales
     *          false   -   si son distintos
     */
     public boolean equals(Pokemon aComparar){
         return this.nombre.equals(aComparar.getNombre()) &&
            this.tipo.equals(aComparar.getTipo()) &&
            this.vida == aComparar.getVida() &&
            this.ataque == aComparar.getAtaque() &&
            this.defensa == aComparar.getDefensa() &&
            this.velocidad == aComparar.getVelocidad();
     }

     @Override
     /**
      * Método para clonar el objeto de tipo pokémon
      *
      * @return clon -Oobjeto de tipo pokémon con el que se trabajará
      */
     public Object clone(){
         Object clon = null;
         try {
             clon = super.clone();
         } catch(Exception e) {
             e.printStackTrace();
         }
         return clon;
     }
}
