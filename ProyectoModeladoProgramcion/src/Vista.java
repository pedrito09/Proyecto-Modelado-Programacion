import java.util.Scanner;
/**
* Clase que representa la vista del usuario
*
* @version Noviembre 2022
*
* @author Gaelinho
* @author Pedro
* @author Andres
*/
public class Vista {
    
    /**
     * Método para pedirle al usuario que introduzca un numero
     * 
     * @return opcion - Es el número que el usuario ha introducido
     * @param minimo - Es el número mínimo que puede introducir el usuario
     * @param maximo - Es el numero máximo que puede introducir el usuario
     */
    protected int getInt(int minimo, int maximo){
        
        Scanner scn = new Scanner(System.in);

        int opcion;
        while (true) {
            if (scn.hasNextInt()) {
                opcion = scn.nextInt();
                if (opcion < minimo || maximo < opcion) {
                    System.out.println("Opcion invalida");
                } else {
                    return opcion;
                }
            } else {
                scn.next();
                System.out.println("Opcion invalida");
            }
        }
    }

    /**
     * Método para pedirle al usuario que introduzca una cadena
     * 
     * @return String - Es la cadena que el usuario ha introducido
     */
    protected String getString(){
        Scanner scn = new Scanner(System.in);

        return scn.nextLine();
    }
}
