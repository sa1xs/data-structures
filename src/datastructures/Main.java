public class Main {

    public static void main(String[] args) {

        int[] numeros = {19, 27, 39, 42, 56, 64, 69, 86, 87, 95};

    }

    public int busquedaBinaria(int[] numeros, int numero) {
        // Definir el valor inicial de las referencias: izq, der, pivote
        int limiteizq = 0;
        int limiteder = numeros.length - 1;
        int pivote = numeros.length / 2;
        int contador = 0;

        // Realizar las iteraciones necesarias para encontrar el dato
        while (numeros[pivote] != numero) {
            // Si el dato es mayor o menor que el pivote, ajustar el límite correspondiente
            if (numeros[pivote] < pivote) limiteizq = pivote + 1;
            else limiteder = pivote - 1;

            // Ajuste del pivote?
            pivote = (limiteizq + limiteder) / 2;

            // Si tras hacer el ajuste los límites se traslapan, se da un mensaje de error
            if (limiteizq > limiteder) {
                System.out.println("El dato buscado no se encuentra en el arreglo");

                // y se retorna un valor simbólico (-1)
                return -1;
            }
            return contador++;
        }

        // Si el ciclo termina (numeros[pivote] == numero) se retorna pivote
        return pivote;
    }

    public static int factorial (int n){
        if (n == 1 || n == 0) return 1;
        return n * factorial(n - 1);
    }
}