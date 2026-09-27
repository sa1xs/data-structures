package colas.estatica;

public class ColaEstatica {

    // Atributos
    private final String[] elementos;
    private int frente;
    private int fin;
    private int cantidad;

    // Metodos
    // Constructor
    public ColaEstatica (int longitud){
        elementos = new String[longitud];
        frente = cantidad = 0;
        fin = -1;
    }

    // Operaciones
    private boolean estaVacia(){
        return cantidad == 0;
    }

    private boolean estaLlena(){
        return cantidad == elementos.length;
    }

    public void insertar(String elemento){
        if (estaLlena()) {
            System.out.println("La cola esta llena\n");
            return;
        }
        if (fin == elementos.length - 1) fin = -1;
        elementos[++fin] = elemento;
        cantidad++;
    }

    public String eliminar(){
        if (estaVacia()){
            System.out.println("La cola esta vacia\n");
            return null;
        }
        String temp = elementos[frente++];
        if (frente == elementos.length) frente = 0;
        return temp;
    }

    public String verFrente(){
        if (estaVacia()){
            System.out.println("La cola esta vacia\n");
            return null;
        }
        return elementos[frente];
    }
}
