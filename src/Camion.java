import java.util.ArrayList;
import java.util.Scanner;

public class Camion {
    //Declaracion de Scanner para poder usar Scan
    Scanner sc = new Scanner(System.in);
    String matricula;
    float capacidad_kg;//kilogramos
    float consumo_gas; //galones por kilometro
    float carga_actual;//kilogramos
    int num_camion; //variable que servira para enumerar los camiones
    //lista donde se guardaran los camiones ingresados
    ArrayList<Camion> camiones_a = new ArrayList<>();

    //funcion anadir camion permite que el usuario ingrese un camion y toma como ingresos la matricula, capacidad y consumo del camion
    public void anadirCamion(String matricula, float capacidad_kg, float consumo_gas) {
        //se crea un nuevo camion
        Camion nuevoCamion = new Camion();
        //se guarda los ingresos del usuario
        nuevoCamion.matricula = matricula;
        nuevoCamion.capacidad_kg = capacidad_kg;
        nuevoCamion.carga_actual=0;
        nuevoCamion.consumo_gas = consumo_gas;
        //se anade el camion y sus atributos a la lista de los camiones
        camiones_a.add(nuevoCamion);
    }
    //funcion que sirve para la seleccion de un camion
    public  void seleccion_camion(){
        //comienzo del do while
        do {
            //el programa toma como ingreso "num_camion"
            System.out.println("Ingrese el número del camión que desea cargar:");
            num_camion = Integer.parseInt(sc.nextLine())-1;
            //if por si el ingreso no es valido (menor a 0 o mayor al tamano de la lista de camiones)
            if (num_camion < 0 || num_camion >= camiones_a.size()) {
                System.out.println("El número ingresado no es válido. Intente nuevamente.");
            }
            //el do while se repite si el usuario hace un ingreso erroneo
        }while (num_camion < 0 || num_camion >= camiones_a.size());
        //se guarda el camion selecionado
        Camion camion_s = camiones_a.get(num_camion);
        //se imprime el camion seleccionado
        System.out.println("El camion "+ (num_camion+1) + " tiene la placa: " + camion_s.matricula + "." +
                " Su capacidad es: " + camion_s.capacidad_kg + ". Su carga actual: " + camion_s.carga_actual +
                ". Su consumo gas: " + camion_s.consumo_gas);
    }
    //funcion para anadir una carga al camion
    public void cargarCamiones() {
        //se utliza la funcion para imprimir el listado de camiones
        imprimirCamiones();
        //se selecciona un camion
        seleccion_camion();
        //variable tipo float
        float carga_nueva;
        //comienzo del "do"
        do {
            //el usuario ingresa el tamano de la carga que va a hacer
            System.out.println("Ingrese la carga nueva: ");
            carga_nueva = Float.parseFloat(sc.nextLine());
            //if por si el usuario ingresa una carga mayor a la capacidad
            if (camiones_a.get(num_camion).carga_actual + carga_nueva > camiones_a.get(num_camion).capacidad_kg) {
                System.out.println("La carga excede la capacidad del camión.");
            }
            //la condicion para que se repita el do
        }while (camiones_a.get(num_camion).carga_actual + carga_nueva > camiones_a.get(num_camion).capacidad_kg);
        //se guarda la nueva carga en la variable de la carga que le pertenece al camion
        camiones_a.get(num_camion).carga_actual += carga_nueva;
    }
    //funcion para hacer una descarga
    public void descargarCamiones() {
        //se imprime el listado de camiones
        imprimirCamiones();
        //se selecciona un camion
        seleccion_camion();
        //variable que se usara para la descarga
        float descarga;
        //comienzo del "do"
        do {
            //el usuario ingresa el tamano de la descarga
            System.out.println("Ingrese el monto de la descarga:");
            descarga = Float.parseFloat(sc.nextLine());
            //comienzo del if
            if (camiones_a.get(num_camion).carga_actual - descarga < 0) {
                //mensaje de error
                System.out.println("La descarga no se puede realizar, ingrese un valor menor");
            }
            //condicion para que se repita el do
        }while (camiones_a.get(num_camion).carga_actual - descarga < 0);
        // se guarda la descarga en la carga actual que le pertence al camion
        camiones_a.get(num_camion).carga_actual -= descarga;
    }
    //funcion para imprimir el listado de camiones
    public void imprimirCamiones() {
        //se usa println para imprimir el listado mediante un for
        System.out.println("Lista de camiones:");
        int i=1;
        //for para mostrar el listado
        for (Camion camion : camiones_a) {
            System.out.println("Camion" + i + ". Matrícula: " + camion.matricula + ", " +
                    "Capacidad: " + camion.capacidad_kg + "kg, Carga actual: " +camion.carga_actual +"kg, Consumo: "
                    + camion.consumo_gas + " gal/km");
            i++;
        }
    }
    //funcion que define el mejor camion para una carga
    public void mejorCamion() {
        //se imprime los camiones
        imprimirCamiones();
        //se crea un nuevo camion llamado mejor camion que es nulo
        Camion mejorCamion = null;
        //se crea una variable llamada mejor consumo que se utilizara para la logica del if
        float menor_consumo = 1000;
        //variable de la carga que ingresara el usuario
        float carga_todos;
        //variable tipo int que sera utilizada para mostrar el numero del camion escogido
        int mnum = 0;
        //el programa pide al usuario que ingrese la carga
        System.out.println("Ingrese la carga que desea cargar: ");
        carga_todos = Float.parseFloat(sc.nextLine());
        //for each que lee los camiones dentro de la lista
        for (Camion camion : camiones_a) {
            //se toma en cuenta si el camion en la lista tiene la capacidad de tener la carga dispuesta por el usuario
            //tambien se toma en cuenta el consumo de gasolina del camion que esta siendo evaluando
            if (camion.capacidad_kg-camion.carga_actual >= carga_todos && camion.consumo_gas < menor_consumo) {
                mnum++;
                //si el camion esta dentro de los requerimientos se guarda
                mejorCamion = camion;
                //se guarda el consumo de gasolina si es menor a la variable "menor_consumo"
                menor_consumo = camion.consumo_gas;
            }
        }
        //si mejorCamion tiene algo ingresaod
        if (mejorCamion != null) {
            //se imprime la mejor opcion
            System.out.println("El mejor camion es el camion: " + mnum + ", con la matricula:" + mejorCamion.matricula +
                    ", consumo gas: " + mejorCamion.consumo_gas + " capacidad: " + mejorCamion.capacidad_kg +
                    "kg, y carga actual de: " + mejorCamion.carga_actual);
        } else{
            //si no hay camione se desplega un mensaje
            System.out.println("No hay un camion para su carga.");
        }
    }
}
