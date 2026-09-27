package arbolbmas;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Se crea un árbol B+ de orden 4.
        ArbolBMas arbol = new ArbolBMas(4);

        ejecutarMenu(arbol);

        scanner.close();
    }

    /**
     * Mantiene el programa en ejecución hasta que el usuario
     * seleccione la opción de salir.
     */
    private static void ejecutarMenu(ArbolBMas arbol) {

        boolean salir = false;

        while (!salir) {

            mostrarMenu();

            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    insertarElemento(arbol);
                    break;

                case 2:
                    buscarElemento(arbol);
                    break;

                case 3:
                    eliminarElemento(arbol);
                    break;

                case 4:
                    recorrerRango(arbol);
                    break;

                case 5:
                    mostrarArbol(arbol);
                    break;

                case 6:
                    salir = true;
                    System.out.println("\nPrograma finalizado.");
                    break;

                default:
                    System.out.println(
                            "\nOpción inválida. Seleccione una opción del 1 al 6."
                    );
            }
        }
    }

    /**
     * Muestra las opciones disponibles.
     */
    private static void mostrarMenu() {

        System.out.println("\n================================");
        System.out.println("          ÁRBOL B+");
        System.out.println("================================");
        System.out.println("1. Insertar elemento");
        System.out.println("2. Buscar elemento");
        System.out.println("3. Eliminar elemento");
        System.out.println("4. Recorrer un rango");
        System.out.println("5. Mostrar estructura del árbol");
        System.out.println("6. Salir");
        System.out.println("================================");
    }

    /**
     * Solicita una clave y un dato para insertarlos en el árbol.
     */
    private static void insertarElemento(ArbolBMas arbol) {

        System.out.println("\n--- INSERTAR ELEMENTO ---");

        int clave = leerEntero("Ingrese la clave: ");

        // Se evita insertar una clave repetida.
        if (arbol.buscar(clave)) {
            System.out.println(
                    "La clave " + clave + " ya existe en el árbol."
            );
            return;
        }

        String dato = leerTexto("Ingrese el dato asociado: ");

        arbol.insertar(clave, dato);

        System.out.println("Elemento insertado correctamente.");
    }

    /**
     * Busca una clave dentro de las hojas del árbol.
     */
    private static void buscarElemento(ArbolBMas arbol) {

        System.out.println("\n--- BUSCAR ELEMENTO ---");

        int clave = leerEntero("Ingrese la clave por buscar: ");

        if (arbol.buscar(clave)) {
            System.out.println(
                    "La clave " + clave + " existe en el árbol."
            );
        } else {
            System.out.println(
                    "La clave " + clave + " no existe en el árbol."
            );
        }
    }

    /**
     * Elimina una clave y su dato asociado.
     */
    private static void eliminarElemento(ArbolBMas arbol) {

        System.out.println("\n--- ELIMINAR ELEMENTO ---");

        int clave = leerEntero("Ingrese la clave por eliminar: ");

        boolean eliminado = arbol.eliminar(clave);

        if (eliminado) {
            System.out.println(
                    "La clave " + clave + " fue eliminada correctamente."
            );
        }
    }

    /**
     * Muestra una cantidad de elementos a partir de una clave inicial.
     */
    private static void recorrerRango(ArbolBMas arbol) {

        System.out.println("\n--- RECORRER RANGO ---");

        int claveInicial = leerEntero(
                "Ingrese la clave inicial: "
        );

        int cantidad = leerEnteroPositivo(
                "Ingrese la cantidad de elementos por mostrar: "
        );

        arbol.recorrerRango(claveInicial, cantidad);
    }

    /**
     * Imprime la estructura jerárquica del árbol.
     */
    private static void mostrarArbol(ArbolBMas arbol) {

        System.out.println("\n--- ESTRUCTURA DEL ÁRBOL ---");

        arbol.imprimirArbol();
    }

    /**
     * Lee un número entero y evita que el programa falle
     * cuando el usuario escribe texto u otro valor inválido.
     */
    private static int leerEntero(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException error) {
                System.out.println(
                        "Entrada inválida. Debe ingresar un número entero."
                );
            }
        }
    }

    /**
     * Lee un número entero mayor que cero.
     */
    private static int leerEnteroPositivo(String mensaje) {

        while (true) {

            int numero = leerEntero(mensaje);

            if (numero > 0) {
                return numero;
            }

            System.out.println(
                    "El número debe ser mayor que cero."
            );
        }
    }

    /**
     * Lee una cadena no vacía.
     */
    private static String leerTexto(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println(
                    "El dato no puede quedar vacío."
            );
        }
    }
}