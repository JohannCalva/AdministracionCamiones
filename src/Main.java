import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int op;
        Camion cam = new Camion();
        //for para anadir 4 camiones
        for (int i = 0; i < 4; i++) {
            System.out.println("Ingrese la matrícula del camión " + (i + 1) + ":");
            String matricula = br.readLine();
            System.out.println("Ingrese la capacidad de carga en kg para el camión " + (i + 1) + ":");
            float capacidad_kg = Float.parseFloat(br.readLine());
            System.out.println("Ingrese el consumo de gasolina en galones/km para el camión " + (i + 1) + ":");
            float consumo_gas = Float.parseFloat(br.readLine());
            cam.anadirCamion(matricula, capacidad_kg, consumo_gas);
        }
        //comienzo de loop "do" que se repite hasta que el usuario decida salir del programa
        do {
            System.out.println("Seleccione una opción: ");
            System.out.println("1. Mostrar camiones");
            System.out.println("2. Añadir carga a camión");
            System.out.println("3. Descargar un camión");
            System.out.println("4. Mostrar mejor camión para carga específica");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            op = Integer.parseInt(br.readLine());
            //ingreso de variable "op"

            //comienzo del Switch Case
            switch (op) {
                //el primer case, donde se imprimen los camiones
                case 1:
                    cam.imprimirCamiones();
                    break;
                //el segundo case, que permite al usuario anadir una carga al camion que escoja
                case 2:
                    cam.cargarCamiones();
                    break;
                case 3:
                    //el tercer case, que permite al usuario quitar una carga del camion que escoja
                    cam.descargarCamiones();
                    break;
                //el cuarto case, que permite al usuario ingresar una carga para que el programa le diga cual camion es mejor
                case 4:
                    cam.mejorCamion();
                    break;
                //el quinto case, que sale del programa
                case 5:
                    System.out.println("Gracias por usar");
                    break;
                    //default for si el usuario ingresa algo erroneo
                default:
                    System.out.println("Ingreso no valido. Por favor intente de muevo");
                    break;
            }
        } while (op != 5);
    }
}